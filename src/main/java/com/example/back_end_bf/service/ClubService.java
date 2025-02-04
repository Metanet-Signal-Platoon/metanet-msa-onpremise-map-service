package com.example.back_end_bf.service;

import com.example.back_end_bf.dto.ClubDTO;
import com.example.back_end_bf.repository.ClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ClubService {
    private final ClubRepository clubRepository;

    public List<ClubDTO> selectClubPaginated(
            Integer limit,
            Integer offset
    ){
        Map<String, Object> params = new HashMap<>();
        params.put("limit", limit);
        params.put("offset", offset);

        return clubRepository.selectClubPaginated(params);
    }

    public Integer insertClub(
            String item_nm,
            String ctprvn_nm,
            String signgu_nm,
            String nm,
            String trobl_ty_nm,
            String oper_time_cn,
            String intrcn_cn,
            Integer host_index,
            String host_id
    ){
        Map<String, Object> params = new HashMap<>();
        params.put("item_nm", item_nm);
        params.put("ctprvn_nm", ctprvn_nm);
        params.put("signgu_nm", signgu_nm);
        params.put("nm", nm);
        params.put("trobl_ty_nm", trobl_ty_nm);
        params.put("oper_time_cn", oper_time_cn);
        params.put("intrcn_cn", intrcn_cn);
        params.put("host_index", host_index);
        params.put("host_id", host_id);

        return clubRepository.insertClub(params);
    }

    public Integer deleteClub(
            String nm,
            String oper_time_cn
    ){
        Map<String, Object> params = new HashMap<>();
        params.put("nm", nm);
        params.put("oper_time_cn", oper_time_cn);

        return clubRepository.deleteClub(params);
    }

    public Integer updateClub(
            String item_nm,
            String ctprvn_nm,
            String signgu_nm,
            String nm,
            String trobl_ty_nm,
            String oper_time_cn,
            String intrcn_cn
    ){
        Map<String, Object> params = new HashMap<>();
        params.put("item_nm", item_nm);
        params.put("ctprvn_nm", ctprvn_nm);
        params.put("signgu_nm", signgu_nm);
        params.put("nm", nm);
        params.put("trobl_ty_nm", trobl_ty_nm);
        params.put("oper_time_cn", oper_time_cn);
        params.put("intrcn_cn", intrcn_cn);

        return clubRepository.updateClub(params);
    }


}