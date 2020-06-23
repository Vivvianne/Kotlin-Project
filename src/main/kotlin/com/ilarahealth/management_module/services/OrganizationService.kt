package com.ilarahealth.management_module.services

import ca.uhn.fhir.rest.api.PreferReturnEnum
import com.ilarahealth.management_module.dto.OrganizationDto
import com.ilarahealth.management_module.mappers.OrganizationMapper
import com.ilarahealth.management_module.models.HapiClient
import org.springframework.stereotype.Service

@Service
class OrganizationService(val hapiClient: HapiClient,
                          val organizationMapper: OrganizationMapper) {

    fun createOrganization(organizationDto: OrganizationDto): String {
        val resource = organizationMapper.organizationDtoToOrganization(organizationDto)
        val methodOutcome = hapiClient.client.create()
                .resource(resource)
                .prefer(PreferReturnEnum.REPRESENTATION)
                .prettyPrint()
                .encodedJson()
                .execute()

        return hapiClient.fhirContext.newJsonParser()
                .setPrettyPrint(true)
                .encodeResourceToString(methodOutcome.resource)
    }

    //TODO - Implement the search for organization business logic here
}