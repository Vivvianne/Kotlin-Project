package com.ilarahealth.management_module.mappers

import org.hl7.fhir.r4.model.StringType
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
abstract class StringTypeMapper {


    @Mapping(source = "string", target = "value")
    abstract fun stringToStringType(string: String) : StringType
}