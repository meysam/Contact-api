package ir.zeroandone.app.application.address.service;

import ir.zeroandone.app.application.address.dto.AddressDto;
import ir.zeroandone.app.integration.rest.address.service.AddressIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class AddressServiceImpl implements AddressService{

    @Autowired
    AddressIntegrationService service;

    @Override
    public String getAddress(HashMap<String,String> params) throws Exception {
        AddressDto address = service.getAddress(params);
        return address.getValue();
    }
}
