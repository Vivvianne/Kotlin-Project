package com.ilarahealth.management_module.models

import ca.uhn.fhir.context.FhirContext
import ca.uhn.fhir.rest.client.api.IGenericClient

class HapiClient(serverBase: String) {
    var fhirContext = FhirContext.forR4()
    var client: IGenericClient = fhirContext.newRestfulGenericClient(serverBase)
}