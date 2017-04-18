package ir.zeroandone.app.integration.rest.address.service;

import ir.zeroandone.app.application.address.dto.AddressDto;
import ir.zeroandone.app.integration.rest.address.AddressProxy;
import ir.zeroandone.app.integration.rest.address.JsonToAddressDtoTransformer;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class AddressIntegrationServiceImpl implements AddressIntegrationService {
    @Override
    public AddressDto getAddress(HashMap<String, String> params) throws Exception {
        List<NameValuePair> getParameters = new ArrayList<>();
        AddressProxy addressProxy = new AddressProxy("address.url");
        params.forEach((key, value) -> getParameters.add(new BasicNameValuePair(key, value)));
        String jsonAddress = addressProxy.invoke(getParameters);
        JsonToAddressDtoTransformer transformer=new JsonToAddressDtoTransformer();
        return transformer.unMarshalling(jsonAddress);
    }
}
