package com.clarencezero.mylove.config.springsecurity;

import com.clarencezero.mylove.entity.dao.Menu;
import com.clarencezero.mylove.entity.dao.Role;
import com.clarencezero.mylove.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * 1、获取requestURL所具具有的用户角色
 * 2、
 */
@Component
public class UrlFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Autowired
    MenuService menuService;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        // 1、获取请求地址
        String currentUrl = ((FilterInvocation) object).getRequestUrl();

        boolean isMatchLoginURL = antPathMatcher.match("/auth/login", currentUrl);
//        if ("/auth/login".equals(currentUrl)) {
        if (isMatchLoginURL) {
            return null;
        }

        // 权限(包含URL)-用户
        List<Menu> allMenu = menuService.listAllMenu();
        for (Menu menu : allMenu) {
            if (antPathMatcher.match(menu.getUrl(), currentUrl) && menu.getRoles().size() > 0) {
                List<Role> roles    = menu.getRoles();
                int        roleSize = roles.size();
                String[]   values   = new String[roleSize];
                for (int i = 0; i < roleSize; i++) {
                    values[i] = roles.get(i).getName();
                }
                return SecurityConfig.createList(values);
            }
        }
        // 没有,则全是登录访问
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
}
