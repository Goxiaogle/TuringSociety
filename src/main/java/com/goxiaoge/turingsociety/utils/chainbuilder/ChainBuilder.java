package com.goxiaoge.turingsociety.utils.chainbuilder;

/**
 * 执行链构造器，用于将判断返回显得更加优雅
 * @param <T> 返回内容的类型
 * @param <K> 可执行对象返回的结果
 * @see Executable
 * @see DefaultChainBuilder
 */
public interface ChainBuilder<T, K> {

    /**
     * 由本次执行结果判定是否继续执行后面的所有链
     * @param executable 可执行对象
     * @return 是否继续执行后续链中的 [可执行对象]
     */
    boolean getProceed(Executable<K> executable);

    /**
     * 将下一条要执行的语句加入到链中
     * @param executable 可执行对象（要执行的语句）
     * @return 链建造器本身
     */
    ChainBuilder<T, K> then(Executable<K> executable);

    /**
     * 更新失败返回的结果后，再将下一条要执行的语句加入到链中
     * @param executable 可执行对象（要执行的语句）
     * @param failResult 新失败要返回的结果
     * @return 链建造器本身
     */
    ChainBuilder<T, K> then(Executable<K> executable, T failResult);

    /**
     * 更新 / 设置 失败返回的结果
     * @param failResult 失败返回的结果
     * @return 链建造器本身
     */
    ChainBuilder<T, K> setFailResult(T failResult);

    /**
     * 设置是否继续执行下面的 可执行对象
     * @param proceed 是否继续执行
     * @return 链建造器本身
     */
    ChainBuilder<T, K> setProceed(boolean proceed);

    /**
     * 更新失败的条件
     * @param failCondition 失败的判定条件
     * @return 链建造器本身
     */
    ChainBuilder<T, K> setFailCondition(K failCondition);

    /**
     * 结束链
     * @param successResult 如果前面所有链的可执行对象都被执行成功，返回该值
     * @return 若前面至少有一个执行对象触发了失败条件，则返回 失败的结果（failResult）
     */
    T end(T successResult);

}
