package com.example.back_end_bf.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;


@Getter
@Setter
public class ClubDTO {

    private String item_nm;

    private String ctprvn_nm;

    private String signgu_nm;

    private String nm;

    private String trobl_ty_nm;

    private String oper_time_cn;

    private String intrcn_cn;

    private int total_prefer;

    private int host_index;

    private String host_id;

    private Timestamp create_date;

}
