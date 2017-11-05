package app05a.common;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 日期转换器，将String类型转换为Date类型
 */
public class DateFormatter implements Formatter<Date> {
    private SimpleDateFormat simpleDateFormat;
    private String datePattern;

    public DateFormatter(String datePattern) {
        this.datePattern = datePattern;
        simpleDateFormat = new SimpleDateFormat(datePattern);
        simpleDateFormat.setLenient(false);
    }

    public Date parse(String s, Locale locale) throws ParseException {

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);
            return simpleDateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException("日期格式输入错误，请使用" + datePattern + "");
        }
    }

    public String print(Date date, Locale locale) {
        return simpleDateFormat.format(date);
    }
}
