package com.ilarahealth.management_module.dto

class AddressDto (
    var text: String,
    var line: List<String>?,
    var city: String,
    var town: String,
    var county: String,
    var country: String
)