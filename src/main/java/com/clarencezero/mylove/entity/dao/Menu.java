package com.clarencezero.mylove.entity.dao;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Menu {
    /** ID*/
    private long id;

    /** RequestURL*/
    @JsonIgnore
    private String url;

    /** Vue-Router需要的路径*/
    private String path;

    /** Vue需要的组件*/
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    private Object component;

    /** 组件的名字*/
    private String name;

    /** 组件图标*/
    private String iconcls;

    /** 父级ID*/
    @JsonIgnore
    private long parentid;

    @JsonIgnore
    private List<Role> roles;

    private List<Menu> children;

    private MenuMeta menuMeta;
}
