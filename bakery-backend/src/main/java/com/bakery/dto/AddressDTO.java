package com.bakery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
	private String name;
	private String phoneNumber;
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
}

