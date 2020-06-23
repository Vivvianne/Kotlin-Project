package com.ilarahealth.management_module.mappers

import com.ilarahealth.management_module.dto.HumanNameDto
import org.hl7.fhir.r4.model.HumanName
import org.hl7.fhir.r4.model.StringType
import org.mapstruct.Mapper
import org.springframework.stereotype.Component


/**
 * Maps human name DTOs to HumanNames. Not currently a mapping class because there aren't
 * any abstract methods on it
 *
 */
@Mapper(componentModel = "spring")
abstract class HumanNameMapper {

    fun humanNameDtoToHumanName(humanNameDto: HumanNameDto): HumanName {
        return HumanName()
                .setFamily(humanNameDto.lastName)
                .setGiven(mutableListOf(StringType(humanNameDto.firstName)))
                .setPrefix(mutableListOf(StringType(humanNameDto.prefix)))
    }

    fun humanNameDtoToHumanNameList(humanNameDto: HumanNameDto): List<HumanName> {
        return listOf(humanNameDtoToHumanName(humanNameDto))
    }

    fun humanNameListToHumanNameDto(humanName: List<HumanName>): HumanNameDto {
        val name = humanName[0]
        return HumanNameDto(name.family, name.given[0].value, name.prefix[0].value)
    }
}