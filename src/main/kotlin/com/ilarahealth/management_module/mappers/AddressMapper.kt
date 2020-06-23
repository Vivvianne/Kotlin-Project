package com.ilarahealth.management_module.mappers

import com.ilarahealth.management_module.dto.AddressDto
import org.hl7.fhir.r4.model.Address
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper(componentModel = "spring", uses = [StringTypeMapper::class])
abstract class AddressMapper {

    @Mappings(
        Mapping(source = "town", target = "district"),
        Mapping(source = "county", target = "state"))
    abstract fun addressDtoToAddress(addressDto: AddressDto) : Address

    fun addressDtoToAddressList(addressDto: AddressDto) : List<Address> {
        return listOf(addressDtoToAddress(addressDto))
    }
}