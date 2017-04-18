package ir.zeroandone.app.integration.rest.address.service;

import com.sun.jndi.cosnaming.IiopUrl;
import ir.zeroandone.app.application.dto.AddressDto;
import ir.zeroandone.app.integration.rest.address.AddressProxy;
import ir.zeroandone.app.integration.rest.address.JsonToAddressDtoTransformer;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
