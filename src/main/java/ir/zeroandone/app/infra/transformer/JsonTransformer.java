package ir.zeroandone.app.infra.transformer;

import org.apache.commons.lang.NotImplementedException;
import org.codehaus.jettison.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.IOException;

public abstract class JsonTransformer<O> implements Transformer<String,O> {

    @Override
    public O transform(String jsonString) throws Exception {
        Object paresedObject = new JSONParser().parse(jsonString);
        if (paresedObject instanceof JSONArray) {
            return unMarshalling((JSONArray) paresedObject);
        } else if (paresedObject instanceof org.json.simple.JSONObject) {
            return unMarshalling(jsonString);
        }
        throw new JSONException("invalid json:" + jsonString);
    }

    public O unMarshalling(JSONArray jsonArray) throws Exception {
        throw new NotImplementedException();
    }


    public O unMarshalling(String jsonString) throws IOException {
        throw new NotImplementedException();
    }
}
