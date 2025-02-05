package com.example.back_end_bf.service;

import com.example.back_end_bf.LocationMapper;
import com.example.back_end_bf.dto.LocationDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {
    private final LocationMapper locationMapper;

    public LocationService(LocationMapper locationMapper) {
        this.locationMapper = locationMapper;
    }

    public List<LocationDTO> getAllLocations() {
        return locationMapper.getAllLocations();
    }
}