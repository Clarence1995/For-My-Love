package com.clarencezero.mylove.mapper;



import com.clarencezero.mylove.entity.dao.Menu;
import com.clarencezero.mylove.entity.dao.MenuMeta;

import java.util.List;

public interface MenuMapper {
    List<Menu> getAllMenu();

    List<MenuMeta> listAllMeta();

//    List<MenuVO> getMenusByHrId(Long hrId);
//
//    List<MenuVO> menuTree();
//
//    List<Long> getMenusByRid(Long rid);
}
