package ir.zeroandone.app.application.address.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

public class AddressDto {
    @JsonIgnoreProperties(ignoreUnknown = true)
    String id;
    String value;
    List<AddressExtensiondto> extensions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<AddressExtensiondto> getExtensions() {
        return extensions;
    }

    public void setExtensions(List<AddressExtensiondto> extensions) {
        this.extensions = extensions;
    }
}
