package com.goxiaoge.turingsociety.utils.chainbuilder.handler;

import com.goxiaoge.turingsociety.utils.ReflectUtils;
import com.goxiaoge.turingsociety.utils.chainbuilder.BooleanChainBuilder;
import com.goxiaoge.turingsociety.utils.chainbuilder.ChainBuilder;

import java.lang.reflect.Field;

public abstract class BooleanChainBuilderAbstractHandler<T> implements ChainBuilderHandler<T, Boolean> {
    @Override
    public boolean isSupported(ChainBuilder<T, Boolean> builder, Field field) {
        return builder instanceof BooleanChainBuilder<T>;
    }

    @Override
    public ChainBuilder<T, Boolean> handler(ChainBuilder<T, Boolean> builder, Field field, Object object) {
        Object fieldValue = ReflectUtils.getFieldValue(object, field);
        return handler(builder, field, object, fieldValue);
    }
}
