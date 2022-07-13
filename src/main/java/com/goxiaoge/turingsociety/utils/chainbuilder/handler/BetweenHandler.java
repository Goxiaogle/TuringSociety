package com.goxiaoge.turingsociety.utils.chainbuilder.handler;

import com.goxiaoge.turingsociety.utils.chainbuilder.ChainBuilder;
import com.goxiaoge.turingsociety.utils.chainbuilder.StringChainBuilder;
import com.goxiaoge.turingsociety.utils.chainbuilder.annotation.Between;

import java.lang.reflect.Field;

public class BetweenHandler<T> extends StringChainBuilderAbstractHandler<T>{

    @Override
    public boolean isSupported(ChainBuilder<T, Boolean> builder, Field field) {
        return super.isSupported(builder, field) && field.isAnnotationPresent(Between.class);
    }

    @Override
    public ChainBuilder<T, Boolean> handler(ChainBuilder<T, Boolean> builder, Field field, Object object, Object fieldValue) {
        Between between = field.getAnnotation(Between.class);
        if(builder instanceof StringChainBuilder<T> stringChainBuilder) {
            return stringChainBuilder.isBetween((String) fieldValue, between.min(), between.max());
        }
        return builder;
    }
}
