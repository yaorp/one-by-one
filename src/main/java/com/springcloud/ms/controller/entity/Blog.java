package com.springcloud.ms.controller.entity;

import com.sun.org.apache.xpath.internal.operations.Bool;
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
