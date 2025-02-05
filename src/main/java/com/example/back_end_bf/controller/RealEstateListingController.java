package com.example.back_end_bf.controller;

import com.example.back_end_bf.dto.RealEstateListingDTO;
import com.example.back_end_bf.service.RealEstateListingService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/real-estate")
public class RealEstateListingController {
    private final RealEstateListingService listingService;

    public RealEstateListingController(RealEstateListingService listingService) {
        this.listingService = listingService;
    }

    @GetMapping
    public List<RealEstateListingDTO> getAllListings() {
        return listingService.getAllListings();
    }
}
