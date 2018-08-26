package com.clarencezero.mylove.service.impl;

import com.clarencezero.mylove.entity.dao.Menu;
import com.clarencezero.mylove.entity.dao.MenuMeta;
import com.clarencezero.mylove.mapper.MenuMapper;
import com.clarencezero.mylove.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("menuService")
public class MenuServiceImpl implements MenuService {
    @Resource
    private MenuMapper menuMapper;
    @Override
    public List<Menu> listAllMenu() {
        return menuMapper.getAllMenu();
    }

    @Override
    public List<MenuMeta> listAllMeta() {
        return menuMapper.listAllMeta();
    }
}
