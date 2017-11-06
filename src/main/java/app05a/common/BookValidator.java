package app05a.common;

import app05a.domain.Book;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Date;

/**
 * 验证器
 * 在调用Controller期间，会有一个或多个Formatter，试图将字符串转换为domain中的field，一旦格式化成功，验证器将介入。
 * 更像是service和dao的关系，负责业务层，如日期是否恰当。
 * 如果验证器可以处理指定的Class，supports方法会返回true；validate方法会验证目标对象，并将验证错误填入Errors对象。
 * Errors对象包含了一系列FieldError对象和ObjectError对象。FieldError表示被验证对象某个属性相关的一个错误；
 * <p>
 * 编写验证器时，不直接创建Errors对象，而是在Errors对象上调用一个reject或rejectValue方法，往FieldError中添加一个FieldError对象或
 * ObjectError对象。Errors对象中的错误消息，可以显示在表单标签库中的Errors标签中
 * <p>
 * ValidationUtils:rejectIfEmpty("fieldName")或rejectIfEmptyOrWhitespace("fieldName")
 */
public class BookValidator implements Validator {

    /**
     * 绑定特定类
     *
     * @param aClass
     * @return
     */
    public boolean supports(Class<?> aClass) {
        return Book.class.isAssignableFrom(aClass);
    }

    public void validate(Object o, Errors errors) {
        Book book = (Book) o;
        ValidationUtils.rejectIfEmpty(errors, "id", "id.required");
        if (book.getId() < 0) {
            errors.rejectValue("id", "id.invalid");
        }
        ValidationUtils.rejectIfEmpty(errors, "isbn", "isbn.required");
        ValidationUtils.rejectIfEmpty(errors, "title", "title.required");
        ValidationUtils.rejectIfEmpty(errors, "category", "category.required");
        ValidationUtils.rejectIfEmpty(errors, "author", "author.required");

        Date addDate = book.getAddDate();
        if (addDate != null) {
            if (addDate.after(new Date())) {
                errors.rejectValue("addDate", "addDate.invalid");
            }
        }
    }
}
