package com.goxiaoge.turingsociety;

import com.goxiaoge.turingsociety.pojo.Application;
import com.goxiaoge.turingsociety.utils.chainbuilder.annotation.Match;
import com.goxiaoge.turingsociety.utils.chainbuilder.StringChainBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

@SpringBootTest
public class TrueChainTest {

    @Test
    public void testChain() {
        String end = new StringChainBuilder<>("失败")
                .match("1", "[0-9]")
                .match("12", "[0-9]")
                .match("1", "[0-9]")
                .end("成功");
        System.out.println(end);
    }

    @Test
    public void test2() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Application application = Application.builder()
                .name("李四")
                .className("haha").build();
        Class<? extends Application> clazz = application.getClass();
        for(Field field: clazz.getDeclaredFields()) {
            Match match = field.getAnnotation(Match.class);
            if(match != null) {
                Object filedValueTemp;
                if(field.trySetAccessible()) {
                    filedValueTemp = field.get(application);
                } else {
                    String methodName = "get" + firstUpper(field.getName());
                    filedValueTemp = clazz.getMethod(methodName).invoke(application);
                }
                if(filedValueTemp instanceof String filedValue) {
                    String regex = match.value();
                    System.out.println(filedValue.matches(regex));
                } else {
                    throw new UnsupportedOperationException("Match 注解仅允许在字符串上使用");
                }
            }
        }
    }

    @Test
    public void test() {
        StringChainBuilder<Integer> builder = new StringChainBuilder<>(1)
                .then(() -> true);
        builder = doi(builder);
        Integer end = builder
                .end(2);
        System.out.println(end);
    }

    <T> StringChainBuilder<T> doi(StringChainBuilder<T> builder) {
        return builder.then(() -> false);
    }

    String firstUpper(String str) {
        if(str == null) {
            return null;
        }
        char[] chars = str.toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);
        return new String(chars);
    }

    boolean say(String msg, boolean res) {
        System.out.println("say: " + msg);
        return res;
    }

}
