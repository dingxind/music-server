package com.example.demo.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

@Data
public class Comment {
    private Integer id;

    private Integer userId;

    private Integer songId;

    private Integer songListId;

    private String content;

    private Date createTime;

    private Byte type;

    private Integer up;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}