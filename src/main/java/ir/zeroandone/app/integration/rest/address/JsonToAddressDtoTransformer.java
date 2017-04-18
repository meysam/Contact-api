package ir.zeroandone.app.integration.rest.address;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.zeroandone.app.application.address.dto.AddressDto;
import ir.zeroandone.app.infra.transformer.JsonTransformer;

import java.io.IOException;

public class JsonToAddressDtoTransformer extends JsonTransformer<AddressDto>{
    public AddressDto unMarshalling(String jsonString) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonString, AddressDto.class);
    }
}
