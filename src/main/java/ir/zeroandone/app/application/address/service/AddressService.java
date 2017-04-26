package ir.zeroandone.app.application.address.service;

import ir.zeroandone.app.application.address.dto.AddressDto;

import java.util.HashMap;
import java.util.List;

public interface AddressService {
    List<String> getAddress(HashMap<String,String> params) throws Exception;
    List<AddressDto> getListAddress(HashMap<String,String> params) throws Exception;
}
