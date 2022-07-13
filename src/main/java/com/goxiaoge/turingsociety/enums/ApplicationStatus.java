package com.goxiaoge.turingsociety.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum ApplicationStatus implements Status {
    APPROVAL_PENDING(0, "待审批"),
    REJECT(1, "已驳回"),
    INTERVIEW_PENDING(2, "待面试"),
    RESULT_PENDING(3, "等待结果通知"),
    REFUSE(4, "未通过"),
    PASSED(5, "已通过");


    @EnumValue
    private final int value;
    private final String comment;

    ApplicationStatus(Integer value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    @Override
    public String toString() {
        return this.comment;
    }
}
