package com.caionastu.userservice.api.application.address.dto;

import com.caionastu.core.interfaces.IAssemblerDTO;
import com.caionastu.userservice.api.application.coordinate.dto.CoordinateAssemblerDTO;
import com.caionastu.userservice.api.domain.address.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressAssemblerDTO implements IAssemblerDTO<AddressDTO, Address> {

    private final CoordinateAssemblerDTO coordinateAssemblerDTO;

    public AddressAssemblerDTO(CoordinateAssemblerDTO coordinateAssemblerDTO) {
        this.coordinateAssemblerDTO = coordinateAssemblerDTO;
    }

    @Override
    public AddressDTO toDTO(Address address) {
        return AddressDTO.builder()
                .street(address.getStreet())
                .number(address.getNumber())
                .district(address.getDistrict())
                .city(address.getCity())
                .country(address.getCountry())
                .cep(address.getCep())
                .location(coordinateAssemblerDTO.toDTO(address.getLocation(), address.getDistance()))
                .build();
    }

    @Override
    public Address toEntity(AddressDTO addressDTO) {
        return Address.builder()
                .street(addressDTO.getStreet())
                .number(addressDTO.getNumber())
                .district(addressDTO.getDistrict())
                .city(addressDTO.getCity())
                .country(addressDTO.getCountry())
                .cep(addressDTO.getCep())
                .location(coordinateAssemblerDTO.toEntity(addressDTO.getLocation()))
                .build();
    }
}
