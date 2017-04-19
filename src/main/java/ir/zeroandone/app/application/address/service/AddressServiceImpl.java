package ir.zeroandone.app.application.address.service;

import ir.zeroandone.app.application.address.dto.AddressDto;
import ir.zeroandone.app.integration.rest.address.service.AddressIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class AddressServiceImpl implements AddressService{

    @Autowired
    AddressIntegrationService service;

    @Override
    public List<String> getAddress(HashMap<String,String> params) throws Exception {
        List<AddressDto> addresses = service.getAddress(params);
        List<String> addressList=new ArrayList<>();
        addresses.forEach(addressDto -> addressList.add(addressDto.getValue()));
        return addressList;
    }
}
