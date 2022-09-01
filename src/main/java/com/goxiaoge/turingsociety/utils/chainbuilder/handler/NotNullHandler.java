package com.goxiaoge.turingsociety.utils.chainbuilder.handler;

import com.goxiaoge.turingsociety.utils.chainbuilder.BooleanChainBuilder;
import com.goxiaoge.turingsociety.utils.chainbuilder.ChainBuilder;
import com.goxiaoge.turingsociety.utils.chainbuilder.annotation.NotNull;

import java.lang.reflect.Field;

public class NotNullHandler<T> extends BooleanChainBuilderAbstractHandler<T> {

    @Override
    public boolean isSupported(ChainBuilder<T, Boolean> builder, Field field) {
        return super.isSupported(builder, field) && field.isAnnotationPresent(NotNull.class);
    }

    @Override
    public ChainBuilder<T, Boolean> handler(ChainBuilder<T, Boolean> builder, Field field, Object object, Object fieldValue) {
        if(builder instanceof BooleanChainBuilder<T> booleanChainBuilder){
            booleanChainBuilder.isNotNull(fieldValue);
        } else {
            builder.then(() -> fieldValue != null);
        }
        return builder;
    }
}
