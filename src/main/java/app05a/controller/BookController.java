package app05a.controller;

import app05a.common.BookValidator;
import app05a.domain.Book;
import app05a.domain.Category;
import app05a.service.BookService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    private static final Log logger = LogFactory.getLog(BookController.class);


//    @RequestMapping(value = "/book_input")
//    public String inputBook(Model model) {
//        List<Category> categories = bookService.getAllCategories();
//        model.addAttribute("categories", categories);
//        /**
//         * Neither BindingResult nor plain target object for bean name 'book'
//         * available as request attribute
//         * 需要在Model中添加book对象
//         * 因为form表单里的<form:input id="title" path="title"/>等path会
//         * 直接去form中的commandName或modelAttribute中取对应属性
//         */
//        model.addAttribute("book", new Book());
//        return "BookAddForm";
//    }


    @RequestMapping(value = "/book_input")
    public String inputBook(Model model, @ModelAttribute Book book) {
        List<Category> categories = bookService.getAllCategories();
        model.addAttribute("categories", categories);
        return "BookAddForm";
    }

    @RequestMapping(value = "/book_edit/{id}")
    public String editBook(Model model, @PathVariable long id) {
        List<Category> categories = bookService.getAllCategories();
        model.addAttribute("categories", categories);
        Book book = bookService.get(id);
        model.addAttribute("book", book);
        return "BookEditForm";
    }

    /**
     * ModelAttribute可以注解方法或方法参数：
     * 【1】带@ModelAttribute的参数，SpringMVC会将该参数添加到Model对象中，若没写键值名，则使用形参作为键值名
     * 【2】带@ModelAttribute的方法，用于标注一个非处理请求的方法，控制器内的任意一个请求方法被调用时都会先调用该方法，
     * 被@ModelAttribute修饰的方法可以返回一个对象或void类型，若返回对象，则该对象会被添加到Model对象中；
     * 若返回void，则必须在方法形参中定义一个Model对象，并手动将实例添加到Model对象中
     *
     * @param book
     * @return
     */
    @RequestMapping(value = "/book_save")
    public String saveBook(@ModelAttribute Book book, BindingResult bindingResult, Model model) {
        Category category = bookService.getCategory(book.getCategory().getId());
        /*
         * 这里输出：Category{id=1, name='null'}
         * 在BookAddForm.jsp页面中，<form:select id="category" path="category.id"
                             items="${categories}" itemLabel="name"
                             itemValue="id"/>
         * 只提交了category.id，故需执行查询并设置
         */
        logger.info("category = " + book.getCategory());
        //启用输入验证
        BookValidator bookValidator = new BookValidator();
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors()) {
            List<Category> categories = bookService.getAllCategories();
            model.addAttribute("categories", categories);
            FieldError fieldError = bindingResult.getFieldError();
            logger.info("Code:" + fieldError.getCode() + ",field:" + fieldError.getRejectedValue());
//            返回录入界面
            return "BookAddForm";
        }


        book.setCategory(category);
        bookService.save(book);
        return "redirect:/book_list";
    }

    @RequestMapping(value = "/book_update")
    public String updateBook(@ModelAttribute Book book) {
        Category category = bookService.getCategory(book.getCategory().getId());
        book.setCategory(category);
        bookService.update(book);
        return "redirect:/book_list";
    }

    @RequestMapping(value = "/book_list")
    public String listBooks(Model model) {
        logger.info("book_list");
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "BookList";
    }
}