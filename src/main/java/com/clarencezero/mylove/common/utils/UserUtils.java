package com.clarencezero.mylove.common.utils;

import com.clarencezero.mylove.config.springsecurity.JWTUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtils {
    public static JWTUserDetails getCurrentUser() {
        return (JWTUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
