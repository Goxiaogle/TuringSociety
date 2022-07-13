package com.goxiaoge.turingsociety.utils.chainbuilder.handler;

import com.goxiaoge.turingsociety.utils.chainbuilder.ChainBuilder;

import java.lang.reflect.Field;

public interface ChainBuilderHandler<T, K> {

    /**
     * 该处理器是否支持该字段
     * @param field 要检查的字段
     * @return 是否支持处理
     */
    boolean isSupported(ChainBuilder<T, K> builder, Field field);

    /**
     * 处理 对象 中的某个字段
     * @param builder 要操作的建造器
     * @param field 处理的字段
     * @param object 要处理的对象
     */
    ChainBuilder<T, K> handler(ChainBuilder<T, K> builder, Field field, Object object);

    /**
     * 处理 对象 中的某个字段，且不使用 field 中的值进行处理（缓存）
     * @param builder 要操作的建造器
     * @param field 处理的字段
     * @param object 要处理的对象
     * @param fieldValue 要使用的字段的值
     */
    ChainBuilder<T, K> handler(ChainBuilder<T, K> builder, Field field, Object object, Object fieldValue);

}
