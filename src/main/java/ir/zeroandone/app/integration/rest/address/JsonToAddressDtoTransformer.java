package ir.zeroandone.app.integration.rest.address;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.zeroandone.app.application.address.dto.AddressDto;
import ir.zeroandone.app.infra.transformer.JsonTransformer;
import org.json.simple.JSONArray;

import java.io.IOException;
import java.util.List;

public class JsonToAddressDtoTransformer extends JsonTransformer<List<AddressDto>>{
    public List<AddressDto> unMarshalling(JSONArray jsonArray) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false).readValue(jsonArray.toString(),
                mapper.getTypeFactory().constructCollectionType(List.class, AddressDto.class));
    }
}
