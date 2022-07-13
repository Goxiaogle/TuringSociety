package com.goxiaoge.turingsociety.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum HttpStatus implements Status {
    SERVICE_ERROR(-3, "业务性错误"),
    NORMATIVE_ERROR(-2, "规范性错误"),
    FAILED(-1, "失败"),
    NO_STATUS(0, "无状态"),
    SUCCESS(1, "成功");


    @EnumValue
    private final int value;
    private final String comment;

    HttpStatus(Integer value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    @Override
    public String toString() {
        return this.comment;
    }
}
