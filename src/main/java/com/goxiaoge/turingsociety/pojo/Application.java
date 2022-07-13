package com.goxiaoge.turingsociety.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.goxiaoge.turingsociety.enums.ApplicationStatus;
import com.goxiaoge.turingsociety.utils.chainbuilder.annotation.Between;
import com.goxiaoge.turingsociety.utils.chainbuilder.annotation.Match;
import com.goxiaoge.turingsociety.utils.chainbuilder.annotation.NotBlank;
import com.goxiaoge.turingsociety.utils.chainbuilder.annotation.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Application {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    @Match("[\\u4e00-\\u9fa5]{2,5}")
    private String name;
    @NotBlank
    @Between(max = 20)
    private String contact;
    @Match("[0-9]{2}[\\u4e00-\\u9fa5]{2,}[0-9][班]")
    private String className;
    @NotBlank
    @Between(max = 20)
    private String direction;
    private ApplicationStatus status;
    @TableLogic
    @JsonIgnore
    private Boolean isDeleted;

    public Application(String name, String contact, String className, String direction) {
        this.name = name;
        this.contact = contact;
        this.className = className;
        this.direction = direction;
    }
}
