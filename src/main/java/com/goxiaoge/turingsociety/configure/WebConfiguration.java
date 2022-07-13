package com.goxiaoge.turingsociety.configure;

import com.goxiaoge.turingsociety.enums.ApplicationStatus;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration {

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addFormatters(@NotNull FormatterRegistry registry) {
                // String => ApplicationStatus
                registry.addConverter(new Converter<String, ApplicationStatus>() {
                    @Override
                    public ApplicationStatus convert(@NotNull String source) {
                        return ApplicationStatus.values()[Integer.parseInt(source)];
                    }
                });
            }
        };
    }

}
