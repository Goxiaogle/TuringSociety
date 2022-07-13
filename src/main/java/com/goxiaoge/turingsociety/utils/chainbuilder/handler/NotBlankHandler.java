package com.goxiaoge.turingsociety.utils.chainbuilder.handler;

import com.goxiaoge.turingsociety.utils.chainbuilder.ChainBuilder;
import com.goxiaoge.turingsociety.utils.chainbuilder.StringChainBuilder;
import com.goxiaoge.turingsociety.utils.chainbuilder.annotation.NotBlank;

import java.lang.reflect.Field;

public class NotBlankHandler<T> extends StringChainBuilderAbstractHandler<T> {

    @Override
    public boolean isSupported(ChainBuilder<T, Boolean> builder, Field field) {
        return super.isSupported(builder, field) && field.isAnnotationPresent(NotBlank.class);
    }

    @Override
    public ChainBuilder<T, Boolean> handler(ChainBuilder<T, Boolean> builder, Field field, Object object, Object fieldValue) {
        if(builder instanceof StringChainBuilder<T> stringChainBuilder) {
            return stringChainBuilder.isNotBlank((String) fieldValue);
        }
        return builder;
    }
}
