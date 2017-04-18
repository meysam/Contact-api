package ir.zeroandone.app.application.address.service;

import java.util.HashMap;

public interface AddressService {
    String getAddress(HashMap<String,String> params) throws Exception;
}
