package com.clarencezero.mylove.mapper;

import com.clarencezero.mylove.config.springsecurity.JWTUserDetails;
import com.clarencezero.mylove.entity.dao.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDetailMapper {
    JWTUserDetails loadUserByUsername(@Param("username") String username);

    List<Role> getRolesByUserId(Long id);

    int userRegistration(@Param("username") String username, @Param("password") String password);

    List<JWTUserDetails> getUserByKeyWords(@Param("keywords") String keywords);

    int updateUser(@Param("JWTUserDetails") JWTUserDetails JWTUserDetails);

    int deleteRoleByUserId(@Param("hrId") Long userId);

    int addRolesForUser(@Param("userId") Long userId, @Param("rids") Long[] rids);

    JWTUserDetails getHrById(@Param("userId") Long userId);

    int deleteUserByid(@Param("userId") Long userId);

    List<JWTUserDetails> getAllUser(@Param("currentId") Long currentId);
}
