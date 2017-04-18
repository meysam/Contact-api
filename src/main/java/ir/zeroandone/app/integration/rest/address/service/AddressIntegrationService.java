package ir.zeroandone.app.integration.rest.address.service;


import ir.zeroandone.app.application.dto.AddressDto;

import java.util.HashMap;

public interface AddressIntegrationService {
    AddressDto getAddress(HashMap<String, String> params) throws Exception;
}
