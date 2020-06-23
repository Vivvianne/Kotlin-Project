package com.ilarahealth.management_module.mappers

import org.hl7.fhir.r4.model.ContactPoint
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
abstract class TelecomMapper {

    @Mapping(source = "phoneNumber", target = "value")
    abstract fun phoneNumberToContactPoint(phoneNumber: String) : ContactPoint


    fun phoneNumberToContactPointList(phoneNumber: String) : List<ContactPoint> {
        return listOf(phoneNumberToContactPoint(phoneNumber))
    }
}