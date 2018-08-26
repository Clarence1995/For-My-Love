package com.clarencezero.mylove.controller;

import com.clarencezero.mylove.config.springsecurity.jwt.JwtTokenUtil;
import com.clarencezero.mylove.entity.ResponseResult;
import com.clarencezero.mylove.entity.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @RequestMapping(value = "/login")
    public ResponseResult login(@RequestBody LoginVO loginBean) {
        authenticate(loginBean.getUsername(), loginBean.getPassword());

        // Reload password post-security so we can generate the token
        final UserDetails userDetails    = userDetailsService.loadUserByUsername(loginBean.getUsername());
        final String      token          = jwtTokenUtil.generateToken(userDetails);
        Authentication    authentication = SecurityContextHolder.getContext().getAuthentication();

        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(200);
        responseResult.setMsg("登录成功");
        responseResult.setData(token);
        return responseResult;
    }

    private void authenticate(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

}
