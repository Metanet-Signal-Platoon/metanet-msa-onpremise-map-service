package com.example.back_end_bf.controller;

import com.example.back_end_bf.dto.ClubDTO;
import com.example.back_end_bf.service.ClubService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/club")
public class ClubController {

    private final ClubService ClubService;

    public ClubController(ClubService ClubService) {
        this.ClubService = ClubService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<ClubDTO>> selectClubPaginated (
            @RequestParam Integer limit,
            @RequestParam Integer offset

    ) {
        System.out.println("selectClubPaginated Operating...");


        return ResponseEntity.ok(ClubService.selectClubPaginated(limit, offset));
    }


    @PostMapping("/insert")
    public ResponseEntity<String> insertClub (
            @RequestParam String item_nm,
            @RequestParam String ctprvn_nm,
            @RequestParam String signgu_nm,
            @RequestParam String nm,
            @RequestParam String trobl_ty_nm,
            @RequestParam String oper_time_cn,
            @RequestParam(defaultValue = "null") String intrcn_cn,
            @RequestParam Integer host_index,
            @RequestParam String host_id
    ) {
        System.out.println("insertClub Operating...");

        Integer result = ClubService.insertClub(
                item_nm,
                ctprvn_nm,
                signgu_nm,
                nm,
                trobl_ty_nm,
                oper_time_cn,
                intrcn_cn,
                host_index,
                host_id
        );

        return ResponseEntity.ok(result > 0 ? "Successful Insert Club" : "Insert Club Failure" );
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteClub (
            @RequestParam String nm,
            @RequestParam String oper_time_cn
    ) {
        System.out.println("deleteClub Operating...");

        Integer result = ClubService.deleteClub(
                nm,
                oper_time_cn
        );

        return ResponseEntity.ok(result > 0 ? "Successful Delete Club" : "Delete Club Failure" );
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateClub (
            @RequestParam String item_nm,
            @RequestParam String ctprvn_nm,
            @RequestParam String signgu_nm,
            @RequestParam String nm,
            @RequestParam String trobl_ty_nm,
            @RequestParam String oper_time_cn,
            @RequestParam String intrcn_cn
    ) {
        System.out.println("updateClub Operating...");

        Integer result = ClubService.updateClub(
                item_nm,
                ctprvn_nm,
                signgu_nm,
                nm,
                trobl_ty_nm,
                oper_time_cn,
                intrcn_cn
        );

        return ResponseEntity.ok(result > 0 ? "Successful Update Club" : "Update Club Failure" );
    }

}
