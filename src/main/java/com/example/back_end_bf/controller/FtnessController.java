package com.example.back_end_bf.controller;

import com.example.back_end_bf.dto.RecoFtnessDTO;
import com.example.back_end_bf.service.FtnessService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/ftness")
public class FtnessController {

    private final FtnessService ftnessService;

    public FtnessController(FtnessService ftnessService) {
        this.ftnessService = ftnessService;
    }

    @PostMapping("/insert")
    public ResponseEntity<String> recoFtnessListUp (
            @RequestParam String user_id,
            @RequestParam String exercise_name,
            @RequestParam String reasonNode

    ) {
        System.out.println("recoFtnessListUp Operating...");
        System.out.println(ftnessService.getRecoFtnessFromModel(user_id));


        return ResponseEntity.ok(ftnessService.recoFtnessListUp(user_id, exercise_name, reasonNode) > 0 ? "complete": "fail");
    }

    @GetMapping("/reco_model")
    public ResponseEntity<Map<String, Object>> getRecoFtnessFromModel (
            @RequestParam String user_id
    ) {
        System.out.println("getRecoFtnessFromModel Operating...");
        System.out.println(ftnessService.getRecoFtnessFromModel(user_id));


        return ResponseEntity.ok(ftnessService.getRecoFtnessFromModel(user_id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<RecoFtnessDTO>> getFtnessByUser (
            @RequestParam int user_index,
            @RequestParam String user_id
    ) {
        System.out.println("getFtnessByUser Operating...");

        return ResponseEntity.ok(ftnessService.getFtnessByUser(user_index, user_id));
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateRecoFtness (
            @RequestParam int reco_ftness_index,
            @RequestParam String reason,
            @RequestParam int like_yn
    ) {
        System.out.println("updateRecoFtness Operating...");

        Integer result = ftnessService.updateRecoFtness(
                reco_ftness_index,
                reason,
                like_yn
        );

        return ResponseEntity.ok(result > 0 ?  "Successful updateRecoFtness" : "updateRecoFtness Failure");
    }

    @GetMapping("/like")
    public ResponseEntity<List<RecoFtnessDTO>> getLikeFtness (
            @RequestParam int user_index,
            @RequestParam String user_id
    ) {
        System.out.println("getLikeFtness Operating...");

        return ResponseEntity.ok(ftnessService.getLikeFtness(user_index, user_id));
    }

}
