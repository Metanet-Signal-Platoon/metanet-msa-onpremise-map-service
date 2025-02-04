package com.example.back_end_bf.repository;

import com.example.back_end_bf.dto.FtnessDTO;
import com.example.back_end_bf.dto.RecoFtnessDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class FtnessRepository {

    private final SqlSessionTemplate sql;

    public Integer recoFtnessListUp(Map<String, Object> params){
        return sql.insert("Ftness.recoFtnessListUp", params);
    }

    public List<RecoFtnessDTO> getFtnessByUser(Map<String, Object> params){
        return sql.selectList("Ftness.getFtnessByUser", params);
    }

    public Integer updateRecoFtness(Map<String, Object> params) {
        return sql.update("Ftness.updateRecoFtness", params);
    }

    public List<RecoFtnessDTO> getLikeFtness(Map<String, Object> params){
        return sql.selectList("Ftness.getLikeFtness", params);
    }

    public FtnessDTO countFtnessLike(Map<String, Object> params){
        return sql.selectOne("Ftness.countFtnessLike", params);
    }


}