package com.goxiaoge.turingsociety.utils.chainbuilder;

import org.springframework.context.annotation.Bean;

public class StringChainBuilder<T> extends BooleanChainBuilder<T> {


    public StringChainBuilder(T failResult) {
        super(failResult, false);
    }

    public StringChainBuilder(T failResult, boolean nullSkip) {
        super(failResult, false, nullSkip);
    }

    @Override
    public StringChainBuilder<T> isNotNull(Object str) {
        return (StringChainBuilder<T>) super.isNotNull(str);
    }

    @Override
    public StringChainBuilder<T> then(Executable<Boolean> executable) {
        return (StringChainBuilder<T>) super.then(executable);
    }

    @Override
    public StringChainBuilder<T> then(Executable<Boolean> executable, T failResult) {
        return (StringChainBuilder<T>) super.then(executable, failResult);
    }

    public StringChainBuilder<T> match(String str, String regex) {
        return isNotNull(str).then(() -> str.matches(regex));
    }

    public StringChainBuilder<T> isNotBlank(String str) {
        return isNotNull(str).then(() -> {
            int length = str.length();
            for (int i = 0; i < length; i++) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return true;
                }
            }
            return false;
        });
    }

    public StringChainBuilder<T> isLessOrEquals(String str, int length) {
        return isNotNull(str).then(() -> str.length() <= length);
    }

    @Bean
    public StringChainBuilder<T> isNotBlankAndLessOrEquals(String str, int length) {
        return isNotBlank(str).isLessOrEquals(str, length);
    }

    public StringChainBuilder<T> isBetween(String str, int min, int max) {
        int finalMax = max == -1 ? str.length() : max;
        return isNotNull(str).then(() -> min <= str.length() && str.length() <= finalMax);
    }

    public StringChainBuilder<T> isNotBlankAndBetween(String str, int min, int max) {
        return isNotBlank(str).isBetween(str, min, max);
    }

    @Override
    public StringChainBuilder<T> quickBuilder(Object object) {
        return (StringChainBuilder<T>) super.quickBuilder(object);
    }
}
