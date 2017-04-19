package ir.zeroandone.app.integration.rest.address.service;


import ir.zeroandone.app.application.address.dto.AddressDto;

import java.util.HashMap;
import java.util.List;

public interface AddressIntegrationService {
    List<AddressDto> getAddress(HashMap<String, String> params) throws Exception;
}
