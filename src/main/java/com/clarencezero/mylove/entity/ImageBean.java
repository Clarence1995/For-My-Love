package com.clarencezero.mylove.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 封装照片实体类
 */
@Getter
@Setter
public class ImageBean {
    private String id;
    private String userId;
    private String src;
    private String info;
    private String link;
}
