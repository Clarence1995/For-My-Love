package com.clarencezero.mylove.service.impl;

import com.clarencezero.mylove.config.springsecurity.JWTUserDetails;
import com.clarencezero.mylove.mapper.UserDetailMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("jwtUserDetailsService")
public class JwtUserDetailsService implements UserDetailsService {


    @Resource
    UserDetailMapper userDetailMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1、根据用户名,获取用户详细信息
        JWTUserDetails user = userDetailMapper.loadUserByUsername(username);
        if (null == user) {
            throw new UsernameNotFoundException("用户名不正确");
        }
        return user;
    }
}
