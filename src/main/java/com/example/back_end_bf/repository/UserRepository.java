package com.example.back_end_bf.repository;

import com.example.back_end_bf.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class UserRepository{

    private final SqlSessionTemplate sql;

    public Integer findUserIndexByUserID(String id) {
        return sql.selectOne("User.findUserIndexByUserID", id);
    }

    public UserDTO findByUserID(String id) {
        return sql.selectOne("User.findByUserID", id);
    }

    public Integer userIdDuplicateCheck(Map<String, Object> params) {
        return sql.selectOne("User.userIdDuplicateCheck", params);
    }

    public List<UserDTO> findAll(){
        return sql.selectList("User.findAll");
    }

    public Integer signUp(Map<String, Object> params) {
        return sql.insert("User.signUp", params);
    }

    public UserDTO logIn(Map<String, Object> params) {
        return sql.selectOne("User.login", params);
    }

    public UserDTO getUserInfo(Map<String, Object> params) {
        return sql.selectOne("User.login", params);
    }

}