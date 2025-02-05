package com.example.back_end_bf;
import com.example.back_end_bf.dto.LocationDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;
@Mapper
public interface LocationMapper {
    @Select("SELECT * FROM locations")
    List<LocationDTO> getAllLocations();
}
