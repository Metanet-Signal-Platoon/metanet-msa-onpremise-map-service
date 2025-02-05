package com.example.back_end_bf.service;

import com.example.back_end_bf.RealEstateListingMapper;
import com.example.back_end_bf.dto.RealEstateListingDTO;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RealEstateListingService {
    private final RealEstateListingMapper listingMapper;

    public RealEstateListingService(RealEstateListingMapper listingMapper) {
        this.listingMapper = listingMapper;
    }

    public List<RealEstateListingDTO> getAllListings() {
        return listingMapper.getAllListings();
    }
}

