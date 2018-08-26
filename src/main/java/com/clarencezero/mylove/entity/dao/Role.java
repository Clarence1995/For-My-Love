package com.clarencezero.mylove.entity.dao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Role {
    /** ID*/
    private long id;

    /** 角色名,以ROLE_开头,更好用于SpringSecuriy*/
    private String name;

    /** 角色中文名*/
    private String namezh;
}
