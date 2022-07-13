package com.goxiaoge.turingsociety.utils.chainbuilder;

import com.goxiaoge.turingsociety.utils.ReflectUtils;
import com.goxiaoge.turingsociety.utils.chainbuilder.handler.ChainBuilderHandler;
import com.goxiaoge.turingsociety.utils.chainbuilder.handler.MatchHandler;
import com.goxiaoge.turingsociety.utils.chainbuilder.handler.NotNullHandler;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 链建造器的默认实现
 * @param <T> 最终返回的结果类型
 * @param <K> 可执行语句的结果和失败条件的类型
 */
public class DefaultChainBuilder<T, K> implements ChainBuilder<T,K> {

    /**
     * 失败返回的结果
     */
    protected T failResult;
    /**
     * 作为是否继续运行链后续 Executable[可执行对象] 的标识
     * 注意：proceed 的值不仅仅单纯影响是否 执行可执行对象，同时也会影响 setFailResult 是否执行
     */
    protected boolean proceed = true;
    /**
     * 判定失败的条件
     */
    protected K failCondition;
    /**
     * 缓存池，用于存放字段与相对应的处理器
     */
    private final Map<Class<?>,
            Map<Field,
                    List<ChainBuilderHandler<T, K>>>> cache = new HashMap<>();
    private final List<ChainBuilderHandler<T, K>> handlers = initHandlers();

    /**
     * 使用失败的结果开始构造
     * @param failResult 失败的结果
     */
    public DefaultChainBuilder(T failResult) {
        this.failResult = failResult;
    }

    /**
     * 使用失败的结果和失败条件开始构造
     * @param failResult 失败的结果
     * @param failCondition 失败的条件
     */
    public DefaultChainBuilder(T failResult, K failCondition) {
        this.failResult = failResult;
        this.failCondition = failCondition;
    }

    @Override
    public ChainBuilder<T, K> then(Executable<K> executable) {
        if(proceed) {
            proceed = getProceed(executable);
        }
        return this;
    }

    @Override
    public ChainBuilder<T, K> then(Executable<K> executable, T failResult) {
        return setFailResult(failResult).then(executable);
    }

    @Override
    public boolean getProceed(Executable<K> executable) {
        K exe = executable.execute();
        return (failCondition == null && exe == null) ||
                failCondition != null && !failCondition.equals(exe);
    }

    @Override
    public T end(T successResult) {
        return proceed ? successResult : failResult;
    }

    /**
     * 根据传入对象上的注解快速构建 链建造器
     *
     * @param object 传入的对象
     * @return 构建完毕后的 链构造器
     */
    public DefaultChainBuilder<T, K> quickBuilder(Object object) {
        if (object == null) {
            throw new NullPointerException("quickBuilder 传入的参数不允许为 null，如果对象可能为 null，请先在使用该语句前尝试使用 isNotNull()");
        }
        Class<?> clazz = object.getClass();
        // 如果缓存池里没有该类 class
        if (!cache.containsKey(clazz)) {
            putClassToCache(clazz);
        }
        ChainBuilder<T, K> builder = this;
        // 此时缓存时里拥有该 class
        Map<Field, List<ChainBuilderHandler<T, K>>> fieldListMap = cache.get(clazz);
        for(Map.Entry<Field, List<ChainBuilderHandler<T, K>>> entry: fieldListMap.entrySet()) {
            Object fieldValue = ReflectUtils.getFieldValue(object, entry.getKey());
            for(ChainBuilderHandler<T, K> handler: entry.getValue()) {
                builder = handler.handler(builder, entry.getKey(), object, fieldValue);
            }
        }
        return (DefaultChainBuilder<T, K>) builder;
    }

    /**
     * 将某个类添加到缓存池中
     * @param clazz 要添加进缓存池的类
     */
    protected void putClassToCache(Class<?> clazz) {
        Map<Field, List<ChainBuilderHandler<T, K>>> map = new HashMap<>();
        for (Field field : clazz.getDeclaredFields()) {
            List<ChainBuilderHandler<T, K>> list = new ArrayList<>();
            for(ChainBuilderHandler<T, K> handler: handlers) {
                // 该 handler 支持处理该字段
                if(handler.isSupported(this, field)) {
                    list.add(handler);
                }
            }
            // 如果没有处理器可以处理则跳过
            if(list.size() > 0)
                map.put(field, list);
        }
        cache.put(clazz, map);
    }

    protected List<ChainBuilderHandler<T, K>> initHandlers() {
        return new ArrayList<>();
    }

    /**
     * 使得用户可以添加自己的处理器处理字段
     * @param handler 用户自己的处理器
     */
    public void addHandler(ChainBuilderHandler<T, K> handler) {
        this.handlers.add(handler);
    }

    @Override
    public ChainBuilder<T, K> setFailResult(T failResult) {
        if(proceed)
            this.failResult = failResult;
        return this;
    }

    @Override
    public ChainBuilder<T, K> setProceed(boolean proceed) {
        this.proceed = proceed;
        return this;
    }

    @Override
    public ChainBuilder<T, K> setFailCondition(K failCondition) {
        this.failCondition = failCondition;
        return this;
    }
}
