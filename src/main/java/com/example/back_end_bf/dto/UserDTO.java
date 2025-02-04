package com.example.back_end_bf.dto;

import lombok.*;
import java.sql.Timestamp;

@Setter
@Getter
public class UserDTO {

    private int user_index;

    private String id;

    private String password;

    private String email;

    private Timestamp birthday;

    private String name;

    private int age;

    private String age_floor;

    private String gender;

    private String role;

}
