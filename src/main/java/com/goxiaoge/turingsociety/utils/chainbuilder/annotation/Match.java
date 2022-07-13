package com.goxiaoge.turingsociety.utils.chainbuilder.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Match 注解作用于 StringChainBuilder
 * 会调用其提供的 match 方法（受 NullSkip 影响）
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
public @interface Match {
    String value();
}
