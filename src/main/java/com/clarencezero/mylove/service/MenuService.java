package com.clarencezero.mylove.service;

import com.clarencezero.mylove.entity.dao.Menu;
import com.clarencezero.mylove.entity.dao.MenuMeta;

import java.util.List;

public interface MenuService {

    List<Menu> listAllMenu();

    List<MenuMeta> listAllMeta();
}
