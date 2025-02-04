package com.example.back_end_bf.service;

import com.example.back_end_bf.dto.FtnessDTO;
import com.example.back_end_bf.dto.RecoFtnessDTO;
import com.example.back_end_bf.dto.UserDTO;
import com.example.back_end_bf.repository.FtnessRepository;
import com.example.back_end_bf.repository.UserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class FtnessService {
    private final FtnessRepository recoFtnessRepository;
    private final UserRepository userRepository;
    private final WebClient.Builder webClientBuilder;

    public Integer recoFtnessListUp(
            String user_id,
            String exercise_name,
            String reasonNode
    ) {

        UserDTO userData = userRepository.findByUserID(user_id);

        System.out.println(userData.getId());


        for (int i = 2 ; i >= 0; i--){
            Map<String, Object> params3 = new HashMap<>();
            params3.put("user_index", userData.getUser_index());
            params3.put("user_id", userData.getId());
            params3.put("exercise_name",exercise_name);
            params3.put("reason", reasonNode);

            if(recoFtnessRepository.recoFtnessListUp(params3) >0){
                return 1;
            }
            else{
                return 0;
            }
        }
        return 0;
    }


    public Map<String, Object> getRecoFtnessFromModel(
            String user_id
    ){

        UserDTO userData = userRepository.findByUserID(user_id);

        System.out.println(userData.getId());


        Map<String, Object> params = new HashMap<>();
        params.put("user_index", userData.getUser_index());
        params.put("name", userData.getId());
        params.put("age", userData.getAge());
        params.put("gender", userData.getGender());

        // WebClient 생성 및 POST 요청 처리
        WebClient webClient = webClientBuilder.baseUrl("http://220.149.235.232:8000").build();

        String response = webClient.post()
                .uri("/user/recommend") // 요청 URI 설정
                .bodyValue(params) // 요청 본문 설정
                .retrieve() // 응답 받기
                .bodyToMono(String.class) // 응답 데이터를 String으로 변환
                .block(); // 동기 방식으로 결과 기다림

        try {
            // Jackson ObjectMapper를 사용해 JSON 파싱
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response);

            // "message" 필드 값 추출
            JsonNode exercisesNode = jsonNode.get("recommendations").get("exercise");
            String reasonNode = jsonNode.get("recommendations").get("reason").toString();

            List<String> exercises = new ArrayList<>();
            for (JsonNode exercise : exercisesNode) {
                exercises.add(exercise.asText());
            }

            Map<String, Object> params2 = new HashMap<>();
            for(int i = 0; i<exercises.toArray().length; i++){
                params2.put("exercise1", exercises.get(i));
            }

            params2.put("reason", reasonNode);


            return params2;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    public List<RecoFtnessDTO> getFtnessByUser(
            int user_index,
            String user_id
    ){

        Map<String, Object> params = new HashMap<>();
        params.put("user_index", user_index);
        params.put("user_id", user_id);

        return recoFtnessRepository.getFtnessByUser(params);
    }


    public Integer updateRecoFtness(
            int reco_ftness_index,
            String reason,
            int like_yn
    ){

        Map<String, Object> params = new HashMap<>();
        params.put("reco_ftness_index", reco_ftness_index);
        params.put("reason", reason);
        params.put("like_yn", like_yn);

        return recoFtnessRepository.updateRecoFtness(params);
    }

    public List<RecoFtnessDTO> getLikeFtness(
            int user_index,
            String user_id
    ){

        Map<String, Object> params = new HashMap<>();
        params.put("user_index", user_index);
        params.put("user_id", user_id);

        return recoFtnessRepository.getLikeFtness(params);
    }

    public FtnessDTO countFtnessLike(
            Integer ftness_mesure_index,
            String exercise_name
    ){

        Map<String, Object> params = new HashMap<>();
        params.put("ftness_mesure_index", ftness_mesure_index);
        params.put("exercise_name", exercise_name);

        return recoFtnessRepository.countFtnessLike(params);
    }




}