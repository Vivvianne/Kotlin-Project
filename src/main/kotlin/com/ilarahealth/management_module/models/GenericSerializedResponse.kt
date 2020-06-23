package com.ilarahealth.management_module.models

import com.fasterxml.jackson.annotation.JsonRawValue

data class GenericSerializedResponse(
    var result: String,
    @JsonRawValue var data: String
)