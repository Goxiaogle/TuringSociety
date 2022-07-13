package com.goxiaoge.turingsociety.utils.chainbuilder;

import com.goxiaoge.turingsociety.utils.chainbuilder.handler.*;

import java.util.ArrayList;
import java.util.List;

public class BooleanChainBuilder<T> extends DefaultChainBuilder<T, Boolean> {

    /**
     * 如果判断为 null 以后，是否跳过下一次执行
     */
    protected boolean nullSkip = false;
    /**
     * 是否跳过下次执行
     */
    protected boolean skipNext = false;

    public BooleanChainBuilder(T failResult) {
        super(failResult);
    }

    public BooleanChainBuilder(T failResult, Boolean failCondition) {
        super(failResult, failCondition);
    }

    public BooleanChainBuilder(T failResult, Boolean failCondition, boolean nullSkip) {
        super(failResult, failCondition);
        this.nullSkip = nullSkip;
    }

    public BooleanChainBuilder<T> then(Executable<Boolean> executable) {
        if (nullSkip && skipNext) {
            skipNext = false;
            return this;
        }
        return (BooleanChainBuilder<T>) super.then(executable);
    }

    @Override
    public BooleanChainBuilder<T> then(Executable<Boolean> executable, T failResult) {
        return (BooleanChainBuilder<T>) super.then(executable, failResult);
    }

    public BooleanChainBuilder<T> isNotNull(Object obj) {
        if (nullSkip && obj == null) {
            skipNext = true;
            return this;
        }
        return then(() -> obj != null);
    }

    @Override
    protected List<ChainBuilderHandler<T, Boolean>> initHandlers() {
        List<ChainBuilderHandler<T, Boolean>> list = new ArrayList<>();
        list.add(new NotNullHandler<>());
        list.add(new NotBlankHandler<>());
        list.add(new MatchHandler<>());
        list.add(new BetweenHandler<>());
        return list;
    }

    public BooleanChainBuilder<T> setNullSkip(boolean nullSkip) {
        this.nullSkip = nullSkip;
        return this;
    }

    public BooleanChainBuilder<T> setSkipNext(boolean skipNext) {
        this.skipNext = skipNext;
        return this;
    }
}
