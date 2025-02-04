package com.example.back_end_bf.controller;

import com.example.back_end_bf.dto.UserDTO;
import com.example.back_end_bf.service.UserService;
import org.apache.ibatis.jdbc.Null;
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
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user_index")
    public ResponseEntity<Map<String, Object>> findUserIndexByUserID (@RequestParam String user_id) {

        System.out.println("findUserIndexByUserID Operating...");

        return ResponseEntity.ok(userService.findUserIndexByUserID(user_id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUser () {

        System.out.println("getAllUser Operating...");

        return ResponseEntity.ok(userService.getAllUser());
    }

    @PostMapping("/duplicatedid_check")
    public ResponseEntity<String> userIdDuplicateCheck (@RequestParam String id) {

        System.out.println("userIdDuplicateCheck Operating...");

        Boolean result = userService.userIdDuplicateCheck(id);

        return ResponseEntity.ok(result ? "Already exist Id" : "Not exist Id" );
    }

    @PostMapping("/sign_up")
    public ResponseEntity<String> join (
            @RequestParam String id,
            @RequestParam String password,
            @RequestParam (defaultValue = "")String email,
            @RequestParam String birthday,
            @RequestParam String name,
            @RequestParam Character gender,
            @RequestParam String disability,
            @RequestParam(required = false) String disability_detail,
            @RequestParam(required = false) String disability_rank,
            @RequestParam (required = false) String exercise_experience,
            @RequestParam (required = false) String exercise_purpose
    ) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(birthday, formatter);
        LocalDateTime birthday_localDateTime = localDate.atStartOfDay();

        System.out.println("sign_up Operating...");

        Integer result = userService.signUp(
                id,
                password,
                email,
                birthday_localDateTime,
                name,
                gender,
                disability,
                disability_detail,
                disability_rank,
                exercise_experience,
                exercise_purpose
        );

        return ResponseEntity.ok(result > 0 ? "Successful sign_up" : "sign_up Failure" );
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> logIn (
            @RequestParam String id,
            @RequestParam String password
    ) {

        System.out.println(id + " login Operating...");

        UserDTO result = userService.logIn(id,password);

        System.out.println(result);

//        if(result == null) {
//            System.out.println(id + "login failure...");
//            return ResponseEntity.badRequest().build();
//        }
//        else{
//            System.out.println(id + "login successful...");
//            return ResponseEntity.ok(result);
//        }

        return ResponseEntity.ok(result);
    }

}
