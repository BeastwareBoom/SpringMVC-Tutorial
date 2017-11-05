package app05a.common;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Converter和Formatter的选择？
 * Converter是一般工具，可以将String或Long转换为Date类型，既可以用在Web层，也可以用在其他层
 * Formatter只能讲String类型装换为另一种Java类型吗，如Date，不能转换其他类型，适用于Web层，
 * 因此在SpringMVC应用中，更适合用Formatter
 */
public class DateConverter implements Converter<String, Date> {
    private String datePattern;

    public DateConverter(String datePattern) {
        this.datePattern = datePattern;
    }

    public Date convert(String s) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);
            return simpleDateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("日期格式输入错误，请使用" + datePattern + "");
        }
    }
}
