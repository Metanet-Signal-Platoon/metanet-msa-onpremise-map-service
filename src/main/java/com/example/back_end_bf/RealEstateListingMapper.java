package com.example.back_end_bf;

import com.example.back_end_bf.dto.RealEstateListingDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface RealEstateListingMapper {
    @Select("SELECT * FROM real_estate_listings")
    List<RealEstateListingDTO> getAllListings();
}
