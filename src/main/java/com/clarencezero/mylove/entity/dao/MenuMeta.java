package com.clarencezero.mylove.entity.dao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MenuMeta {
    private long id;
    private boolean keepAlive;
    private boolean requireAuth;
}
