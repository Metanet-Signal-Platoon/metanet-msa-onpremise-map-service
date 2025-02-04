package com.example.back_end_bf.service;

import com.example.back_end_bf.dto.MapFilterDTO;
import com.example.back_end_bf.dto.UserDTO;
import com.example.back_end_bf.repository.MapFilterRepository;
import com.example.back_end_bf.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MapFilterService {
    private final MapFilterRepository mapFilterRepository;


    public List<MapFilterDTO> mapFilter(
            Double r_lo,
            Double l_lo,
            Double r_la,
            Double l_la
                                        ) {

        Map<String, Object> params = new HashMap<>();
        params.put("r_lo", r_lo);
        params.put("l_lo", l_lo);
        params.put("r_la", r_la);
        params.put("l_la", l_la);

        return mapFilterRepository.mapFilter(params);
    }





}