package app05a.common;

import org.springframework.format.FormatterRegistrar;
import org.springframework.format.FormatterRegistry;

/**
 * Formatter注册器
 */
public class DateRegistrar implements FormatterRegistrar {
    private String datePattern;

    public DateRegistrar(String datePattern) {
        this.datePattern = datePattern;
    }

    public void registerFormatters(FormatterRegistry formatterRegistry) {
        formatterRegistry.addFormatter(new DateFormatter(datePattern));
    }
}
