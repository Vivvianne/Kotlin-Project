package com.ilarahealth.management_module.rest

import com.ilarahealth.management_module.dto.OrganizationDto
import com.ilarahealth.management_module.models.GenericSerializedResponse
import com.ilarahealth.management_module.services.OrganizationService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("organization")
class OrganizationController(val organizationService: OrganizationService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createOrganization(@RequestBody organizationDto: OrganizationDto) : GenericSerializedResponse {
        var organization = organizationService.createOrganization(organizationDto)
        return GenericSerializedResponse(
                "OK",
                organization
        )
    }

    @GetMapping("/{name}")
    fun searchForOrganization(@PathVariable name:String) : GenericSerializedResponse {
        var resultString = organizationService.searchForOrganization(name)
        return GenericSerializedResponse(
                "OK",
                resultString
        )
    }
}