package com.goxiaoge.turingsociety.utils.result;

import com.goxiaoge.turingsociety.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResult<T> implements Result<T> {
    protected Status status;
    protected T data;
    protected String message;

    public BaseResult(Status status, T data) {
        this.status = status;
        this.data = data;
    }
}
