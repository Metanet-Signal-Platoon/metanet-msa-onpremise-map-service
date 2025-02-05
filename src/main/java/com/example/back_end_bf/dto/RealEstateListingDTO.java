package com.example.back_end_bf.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RealEstateListingDTO {
    private Long id;
    private int listingIndex;
    private String city;
    private String district;
    private String propertyName;
    private String phone;
    private String address;
    private String detailAddress;
    private String propertyType;
    private Double latitude;
    private Double longitude;
}
