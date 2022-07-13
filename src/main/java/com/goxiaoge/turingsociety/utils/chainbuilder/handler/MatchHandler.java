package com.goxiaoge.turingsociety.utils.chainbuilder.handler;

import com.goxiaoge.turingsociety.utils.chainbuilder.ChainBuilder;
import com.goxiaoge.turingsociety.utils.chainbuilder.StringChainBuilder;
import com.goxiaoge.turingsociety.utils.chainbuilder.annotation.Match;

import java.lang.reflect.Field;

public class MatchHandler<T> extends StringChainBuilderAbstractHandler<T> {

    @Override
    public boolean isSupported(ChainBuilder<T, Boolean> builder, Field field) {
        return super.isSupported(builder, field) && field.isAnnotationPresent(Match.class);
    }

    @Override
    public ChainBuilder<T, Boolean> handler(ChainBuilder<T, Boolean> builder, Field field, Object object, Object fieldValue) {
        String regex = field.getAnnotation(Match.class).value();
        String value = (String) fieldValue;
        if (builder instanceof StringChainBuilder<T> stringChainBuilder) {
            stringChainBuilder.match((String) fieldValue, regex);
            return stringChainBuilder;
        }
        return builder;
    }
}
