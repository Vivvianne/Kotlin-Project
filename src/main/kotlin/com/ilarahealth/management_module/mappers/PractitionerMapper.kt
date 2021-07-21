package com.ilarahealth.management_module.mappers

import com.ilarahealth.management_module.dto.OrganizationDto
import com.ilarahealth.management_module.dto.PractitionerDto
import org.hl7.fhir.r4.model.Organization
import org.hl7.fhir.r4.model.Practitioner
import org.mapstruct.Mapper
import org.mapstruct.Mapping


/**
 * TODO - Implement the practitioner mapper. You only have to map in one direction - from a DTO to a Practitioner FHIR R4
 * object. Look at how the OrganizationMapper works to try and understand how this one works. Your hints are that you only
 * need to use one @Mapping annotation, and that you'll need to use two mappers that are already defined in the project
 */
@Mapper(componentModel = "spring", uses = [HumanNameMapper::class])
abstract class PractitionerMapper {

    abstract fun practitionerDtoToPractitioner(practitionerDto: PractitionerDto) : Practitioner

}
