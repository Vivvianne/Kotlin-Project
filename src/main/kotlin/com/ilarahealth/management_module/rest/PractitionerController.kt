package com.ilarahealth.management_module.rest

import com.ilarahealth.management_module.dto.PractitionerDto
import com.ilarahealth.management_module.models.GenericSerializedResponse
import com.ilarahealth.management_module.models.HapiClient
import com.ilarahealth.management_module.services.PractitionerService
import org.hl7.fhir.r4.model.Bundle
import org.hl7.fhir.r4.model.Practitioner
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("practitioner")
class PractitionerController(val practitionerService: PractitionerService,
                             val hapiClient: HapiClient) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createPractitioner(@RequestBody practitionerDto: PractitionerDto) : GenericSerializedResponse {
        // TODO - Uncomment once you've finished implementing createPractitioner
        // val practitioner = practitionerService.createPractitioner(practitionerDto)
        return GenericSerializedResponse(
                "OK",
                "" //practitioner
        )
    }


    @GetMapping
    fun searchForPractitioner() : GenericSerializedResponse {
        var resultString = practitionerService.searchForPractitioner()
        return GenericSerializedResponse(
                "OK",
                resultString
        )
    }
}