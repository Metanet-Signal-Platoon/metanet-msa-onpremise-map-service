package com.example.back_end_bf.repository;

import com.example.back_end_bf.dto.ClubDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ClubRepository {

    private final SqlSessionTemplate sql;

    public List<ClubDTO> selectClubPaginated(Map<String, Object> params) {
        return sql.selectList("Club.selectClubPaginated", params);
    }

    public Integer insertClub(Map<String, Object> params) {
        return sql.insert("Club.insertClub", params);
    }

    public Integer deleteClub(Map<String, Object> params) {
        return sql.insert("Club.deleteClub", params);
    }

    public Integer updateClub(Map<String, Object> params) {
        return sql.update("Club.updateClub", params);
    }


}