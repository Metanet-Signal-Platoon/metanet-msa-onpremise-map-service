package com.example.back_end_bf.controller;

import com.example.back_end_bf.dto.MapFilterDTO;
import com.example.back_end_bf.dto.UserDTO;
import com.example.back_end_bf.service.MapFilterService;
import com.example.back_end_bf.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/map_filter")
@RequiredArgsConstructor
public class MapFilterController {

    private final MapFilterService mapFilterService;


    @GetMapping("/do")
    public ResponseEntity<List<MapFilterDTO>> mapFilter (
            @RequestParam Double r_lo,
            @RequestParam Double l_lo,
            @RequestParam Double r_la,
            @RequestParam Double l_la
    ) {

        System.out.println("mapFilter Operating...");

        return ResponseEntity.ok(mapFilterService.mapFilter(r_lo, l_lo, r_la, l_la));
    }



}
