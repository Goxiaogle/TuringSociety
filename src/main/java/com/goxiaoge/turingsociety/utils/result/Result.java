package com.goxiaoge.turingsociety.utils.result;

import com.goxiaoge.turingsociety.enums.Status;

public interface Result<T> {
    /**
     * @return 结果的状态
     */
    Status getStatus();

    /**
     * @return 结果包裹的实际数据
     */
    T getData();

    /**
     * @return 结果的提示信息
     */
    String getMessage();
}
