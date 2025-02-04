package com.example.back_end_bf.service;

import com.example.back_end_bf.dto.UserDTO;
import com.example.back_end_bf.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<UserDTO> getAllUser(){
        return userRepository.findAll();
    }

    public Boolean userIdDuplicateCheck(String id) {

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);

        Integer DuplicatedUserIdCheck = userRepository.userIdDuplicateCheck(params);

        if(DuplicatedUserIdCheck > 0 ){
            return true;
        }

        return false;
    }


    public Integer signUp(
            String id,
            String password,
            String email,
            LocalDateTime birthday,
            String name,
            Character gender,
            String disability,
            String disability_detail,
            String disability_rank,
            String exercise_experience,
            String exercise_purpose
    ){
        Map<String, Object> params2 = new HashMap<>();
        params2.put("id", id);

        Integer DuplicatedUserIdCheck = userRepository.userIdDuplicateCheck(params2);

        if (DuplicatedUserIdCheck>0){
            return 0;
        }

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("password", bCryptPasswordEncoder.encode(password));
        params.put("email", email);
        params.put("birthday", birthday);
        params.put("name", name);

        Integer age =LocalDateTime.now().getYear()-birthday.getYear();
        params.put("age", age);
        params.put("gender", gender);
        params.put("disability", disability);
        params.put("disability_detail", disability_detail);
        params.put("disability_rank", disability_rank);
        params.put("exercise_experience", exercise_experience);
        params.put("exercise_purpose", exercise_purpose);
        params.put("create_date", LocalDateTime.now()); // 현재 시간 설정
        params.put("age_floor", (age/10)*10 + "대");

        // MyBatis insert 호출
        return userRepository.signUp(params);

    }

    public UserDTO logIn(
            String id,
            String password
    ){

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("password", password);

        return userRepository.logIn(params);
    }

    public Map<String, Object> findUserIndexByUserID(
            String id
    ){

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("user_index", userRepository.findUserIndexByUserID(id));


        return params;
    }



}