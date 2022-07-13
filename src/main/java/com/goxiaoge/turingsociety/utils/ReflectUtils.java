package com.goxiaoge.turingsociety.utils;

import java.lang.reflect.Field;

public class ReflectUtils {

    public static Object getFieldValue(Object object, Field field) {
        try{
            // 可以直接修改权限
            if(field.trySetAccessible()) {
                return field.get(object);
            } else {
                // 调用 getter 方法
                return object.getClass().getMethod("get" + firstUpper(field.getName())).invoke(object);
            }
        } catch (Exception e) {
            return null;
        }

    }

    public static String firstUpper(String str) {
        if(str == null) {
            return null;
        }
        char[] chars = str.toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);
        return new String(chars);
    }
}
