package com.ilarahealth.management_module.services

import ca.uhn.fhir.rest.api.PreferReturnEnum
import com.ilarahealth.management_module.dto.PractitionerDto
import com.ilarahealth.management_module.mappers.PractitionerMapper
import com.ilarahealth.management_module.models.HapiClient
import org.hl7.fhir.r4.model.Bundle
import org.hl7.fhir.r4.model.Practitioner
import org.springframework.stereotype.Service

@Service
class PractitionerService(val practitionerMapper: PractitionerMapper,
                          val hapiClient: HapiClient) {


    fun createPractitioner(practitionerDto: PractitionerDto): String {
        val resource = practitionerMapper.practitionerDtoToPractitioner(practitionerDto)
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

    fun searchForPractitioner(): String {
        val query = hapiClient.client
                .search<Bundle>()
                .forResource(Practitioner::class.java)

        val resultBundle = query.returnBundle(Bundle::class.java).execute()
        return hapiClient.fhirContext
                .newJsonParser()
                .encodeResourceToString(resultBundle)
    }
}