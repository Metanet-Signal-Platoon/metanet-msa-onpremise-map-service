package com.example.back_end_bf.repository;

import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@RequiredArgsConstructor
public class RefreshRepository {

    private final SqlSessionTemplate sql;

    public Integer insertRefreshToken(Map<String, Object> params) {
        return sql.insert("Refresh.insertRefreshToken", params);
    }

    public Integer refreshTokenExistCheck(String refresh) {
        return sql.selectOne("Refresh.refreshTokenExistCheck", refresh);
    }

    public Integer deleteRefreshToken(String refresh) {
        return sql.delete("Refresh.deleteRefreshToken", refresh);
    }

}