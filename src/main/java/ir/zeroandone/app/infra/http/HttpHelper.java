package ir.zeroandone.app.infra.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;


public class HttpHelper {

    public static String httpGet(URI uri) throws IOException {
        HttpGet getRequest = null;
        try {
            HttpClient httpClient=SSLUtilities.createHttpClient_AcceptsUntrustedCerts();

            getRequest = new HttpGet(uri);
            HttpResponse response = httpClient.execute(getRequest);
            if (response.getStatusLine().getStatusCode() != 200) {
                EntityUtils.consumeQuietly(response.getEntity());
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
            String output;
            StringBuilder strBuilder = new StringBuilder();
            while ((output = br.readLine()) != null) strBuilder.append(output);
            return strBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } finally {
            if (getRequest != null) {
                getRequest.releaseConnection();
            }
        }
        return null;

    }

/*    public static String httpGet(URI uri, String username, String password) throws IOException {
        HttpGet getRequest = null;
        try {
            HttpClient httpClient =
                    SSLUtilities.createHttpClient_AcceptsUntrustedCerts();

            getRequest = new HttpGet(uri);
            getRequest.setHeader("Authorization", "Basic " + Base64.encodeBase64String((username.trim() + ":" + password.trim()).getBytes()).trim());
            HttpResponse response = httpClient.execute(getRequest);
            if (response.getStatusLine().getStatusCode() != 200) {
                EntityUtils.consumeQuietly(response.getEntity());
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
            String output;
            StringBuilder strBuilder = new StringBuilder();
            while ((output = br.readLine()) != null) strBuilder.append(output);
            return strBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } finally {
            if (getRequest != null) {
                getRequest.releaseConnection();
            }
        }
        return null;

    }*/

    public static String httpPost(URI uri, Object body) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse res;
        try {

            // Creating an instance of HttpPost.
            HttpPost httpost = new HttpPost(uri);

            String bodyJson = null;
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            try {
                bodyJson = ow.writeValueAsString(body);
            } catch (JsonProcessingException e) {

            }

            StringEntity bodyEntity = new StringEntity(bodyJson);
            httpost.setEntity(bodyEntity);
            res = httpclient.execute(httpost);
            System.out.println("res :" + res.getStatusLine());
            try {
                if (res.getStatusLine().getStatusCode() != 200) {
                    EntityUtils.consumeQuietly(res.getEntity());
                    throw new RuntimeException("Failed : HTTP error code : " + res.getStatusLine().getStatusCode());
                }
                BufferedReader br = new BufferedReader(new InputStreamReader((res.getEntity().getContent())));
                String output;
                StringBuilder strBuilder = new StringBuilder();
                while ((output = br.readLine()) != null) strBuilder.append(output);
                return strBuilder.toString();
            }
            catch (Exception e)
            {
                throw new RuntimeException("Failed : HTTP error code : " + Response.Status.INTERNAL_SERVER_ERROR, e);
            }
            finally {
                res.close();
            }
        } finally {
            httpclient.close();
        }
    }
}
