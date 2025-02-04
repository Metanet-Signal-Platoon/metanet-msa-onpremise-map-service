package com.example.back_end_bf.dto;

import lombok.*;


import java.sql.Timestamp;


@Getter
@Setter
public class RecoFtnessDTO {

    private int reco_ftness_index;

    private int user_index;

    private String user_id;

    private String exercise_name;

    private String reason;

    private int like_yn;

    private Timestamp update_date;
}
