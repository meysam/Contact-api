package ir.zeroandone.app.application.address.service;

import java.util.HashMap;
import java.util.List;

public interface AddressService {
    List<String> getAddress(HashMap<String,String> params) throws Exception;
}
