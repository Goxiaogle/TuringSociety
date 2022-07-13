package com.goxiaoge.turingsociety.utils.chainbuilder.handler;

import com.goxiaoge.turingsociety.utils.chainbuilder.ChainBuilder;
import com.goxiaoge.turingsociety.utils.chainbuilder.StringChainBuilder;

import java.lang.reflect.Field;

public abstract class StringChainBuilderAbstractHandler<T> extends BooleanChainBuilderAbstractHandler<T> {
    @Override
    public boolean isSupported(ChainBuilder<T, Boolean> builder, Field field) {
        return super.isSupported(builder, field) && field.getType().isAssignableFrom(String.class) && builder instanceof StringChainBuilder<T>;
    }
}
