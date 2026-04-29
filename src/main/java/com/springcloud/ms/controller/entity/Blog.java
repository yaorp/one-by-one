package com.springcloud.ms.controller.entity;

import lombok.Data;

import java.util.Date;
import java.util.Set;

/**
 * @author: yaorp
 */
@Data
public class Blog {
    private String id;
    private String title;
    private String content;
    private String author;
    private Date time;

    private Boolean isLike;
    private Long likeCount;
    private Set<String> likeUsers;
}
