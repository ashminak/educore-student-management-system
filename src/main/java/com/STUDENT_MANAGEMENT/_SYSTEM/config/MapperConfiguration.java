package com.STUDENT_MANAGEMENT._SYSTEM.config;

import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

@MapperConfig(
    componentModel = "spring",
    unmappedTargetPolicy= ReportingPolicy.IGNORE

)
public interface MapperConfiguration {

}
