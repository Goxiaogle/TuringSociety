package com.goxiaoge.turingsociety.utils.result;

import com.goxiaoge.turingsociety.enums.HttpStatus;

public class HttpResult<T> extends BaseResult<T> {
    /**
     * 快速构造一个成功类型的结果
     * @param data 要携带数据
     * @return 状态为成功的结果
     */
    public static <T> Result<T> success(T data) {
        return new BaseResult<>(HttpStatus.SUCCESS, data);
    }

    public static <T> Result<T> fail(T data) {
        return new BaseResult<>(HttpStatus.FAILED, data);
    }

    public static <T> Result<T> fail(T data, String message) {
        return new BaseResult<>(HttpStatus.FAILED, data, message);
    }

    public static <T> Result<T> normativeError(T data) {
        return new BaseResult<>(HttpStatus.NORMATIVE_ERROR, data, "出现了参数格式错误等的规范性错误");
    }

    public static <T> Result<T> normativeError(T data, String message) {
        return new BaseResult<>(HttpStatus.NORMATIVE_ERROR, data, message);
    }

    public static <T> Result<T> serviceError(T data) {
        return new BaseResult<>(HttpStatus.SERVICE_ERROR, data, "出现了业务性错误，可能是代码异常或者数据库插入数据错误");
    }

    public static <T> Result<T> serviceError(T data, String message) {
        return new BaseResult<>(HttpStatus.SERVICE_ERROR, data, message);
    }
}
