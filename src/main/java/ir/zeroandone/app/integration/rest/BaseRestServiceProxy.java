package ir.zeroandone.app.integration.rest;

import ir.zeroandone.app.infra.config.ConfigurationFactory;
import ir.zeroandone.app.infra.http.HttpHelper;
import org.apache.commons.configuration.Configuration;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BaseRestServiceProxy {
    protected URL url;

    public BaseRestServiceProxy(String serviceProp) throws MalformedURLException {
        Configuration config;
        config = ConfigurationFactory.config;
        String endPoint = config.getString(serviceProp).toString();
        this.url = new URL(endPoint);
    }

    public String invoke(List<NameValuePair> params)
            throws URISyntaxException, IOException {
        String json = HttpHelper.httpGet(
                new URIBuilder().setScheme(this.url.getProtocol()).setHost(this.url.getHost())
                        .setPath(this.url.getPath()).setParameters(params)
                        .build());
        return json;
    }
}
