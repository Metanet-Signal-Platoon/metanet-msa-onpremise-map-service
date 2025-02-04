package com.example.back_end_bf.repository;

import com.example.back_end_bf.dto.MapFilterDTO;
import com.example.back_end_bf.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class MapFilterRepository {

    private final SqlSessionTemplate sql;

    public List<MapFilterDTO> mapFilter(Map<String, Object> params){
        return sql.selectList("MapFilter.mapFilter", params);
    }
}