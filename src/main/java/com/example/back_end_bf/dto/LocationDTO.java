package com.example.back_end_bf.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationDTO {
    private Long id;
    private String name;
    private String category;
    private Double latitude;
    private Double longitude;
    private String address;
    private String phone;
    private String operatingHours;
}
