package ir.zeroandone.app.infra.http;

import com.codahale.metrics.httpclient.HttpClientMetricNameStrategies;
import com.codahale.metrics.httpclient.InstrumentedHttpClientConnectionManager;
import com.codahale.metrics.httpclient.InstrumentedHttpRequestExecutor;
import ir.zeroandone.app.infra.config.ConfigurationFactory;
import ir.zeroandone.app.infra.monitoring.MetricsServletContextListener;
import org.apache.commons.configuration.Configuration;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;

import javax.net.ssl.*;
import java.security.*;
import java.security.cert.X509Certificate;

public class SSLUtilities {

    private static HttpClientBuilder clientBuilder;
    /**
     * Hostname verifier for the Sun's deprecated API.
     *
     * @deprecated see {@link #_hostnameVerifier}.
     */
    private static HostnameVerifier __hostnameVerifier;
    /**
     * Thrust managers for the Sun's deprecated API.
     *
     * @deprecated see {@link #_trustManagers}.
     */
    private static TrustManager[] __trustManagers;
    /**
     * Hostname verifier.
     */
    private static HostnameVerifier _hostnameVerifier;
    /**
     * Thrust managers.
     */
    private static TrustManager[] _trustManagers;

    public synchronized static HttpClient createHttpClient_AcceptsUntrustedCerts() throws KeyStoreException,
            NoSuchAlgorithmException,
            KeyManagementException {
        if (clientBuilder == null)
            initHttpClientBuilder();
        return clientBuilder.build();
    }

    private synchronized static HttpClientBuilder initHttpClientBuilder() throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        Configuration config = ConfigurationFactory.config;
        int connectionRequestTimeout = config.getInt("HttpClient.ConnectTimeout",20000);
        int connectTimeout = config.getInt("HttpClient.ConnectionRequestTimeout",20000);
        int socketTimeout = config.getInt("HttpClient.SocketTimeout",20000);

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(connectTimeout)
                .setConnectionRequestTimeout(connectionRequestTimeout)
                .setSocketTimeout(socketTimeout).build();
        httpClientBuilder.setDefaultRequestConfig(requestConfig);

        // setup a Trust Strategy that allows all certificates.
        //
        SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
            public boolean isTrusted(X509Certificate[] arg0, String arg1) {
                return true;
            }
        }).build();
        httpClientBuilder.setSslcontext(sslContext);

        // don't check Hostnames, either.
        //      -- use SSLConnectionSocketFactory.getDefaultHostnameVerifier(), if you don't want to weaken
        HostnameVerifier hostnameVerifier = SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;

        // here's the special part:
        //      -- need to create an SSL Socket Factory, to use our weakened "trust strategy";
        //      -- and create a Registry, to register it.
        //
        SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext, (X509HostnameVerifier) hostnameVerifier);
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", sslSocketFactory)
                .build();

        // now, we create connection-manager using our Registry.
        //      -- allows multi-threaded use
        /*InstrumentedHttpClientConnectionManager connMgr = new InstrumentedHttpClientConnectionManager(MetricsServletContextListener.METRIC_REGISTRY, socketFactoryRegistry);*/
        InstrumentedHttpClientConnectionManager connMgr = new InstrumentedHttpClientConnectionManager(MetricsServletContextListener.METRIC_REGISTRY, socketFactoryRegistry);
        //We have 24 routes atm
        connMgr.setMaxTotal(12000);
        connMgr.setDefaultMaxPerRoute(500);
        httpClientBuilder.setConnectionManager(connMgr);

        InstrumentedHttpRequestExecutor requestExec = new InstrumentedHttpRequestExecutor(MetricsServletContextListener.METRIC_REGISTRY, HttpClientMetricNameStrategies.QUERYLESS_URL_AND_METHOD);
        clientBuilder = httpClientBuilder
                .setRequestExecutor(requestExec);
        return clientBuilder;
    }

    /**
     * Set the default Hostname Verifier to an instance of a fake class that
     * trust all hostnames. This method uses the old deprecated API from the
     * com.sun.ssl package.
     *
     * @deprecated see {@link #_trustAllHostnames()}.
     */
    private static void __trustAllHostnames() {
        // Create a trust manager that does not validate certificate chains
        if (__hostnameVerifier == null) {
            __hostnameVerifier = (HostnameVerifier) new _FakeHostnameVerifier();
        } // if
        // Install the all-trusting host name verifier
        HttpsURLConnection.
                setDefaultHostnameVerifier(__hostnameVerifier);
    } // __trustAllHttpsCertificates

    /**
     * Set the default X509 Trust Manager to an instance of a fake class that
     * trust all certificates, even the self-signed ones. This method uses the
     * old deprecated API from the com.sun.ssl package.
     *
     * @deprecated see {@link #_trustAllHttpsCertificates()}.
     */
    private static void __trustAllHttpsCertificates() {
        SSLContext context;

        // Create a trust manager that does not validate certificate chains
        if (__trustManagers == null) {
            __trustManagers = new TrustManager[]
                    {(TrustManager) new _FakeX509TrustManager()};
        } // if
        // Install the all-trusting trust manager
        try {
            context = SSLContext.getInstance("SSL");
            context.init(null, __trustManagers, new SecureRandom());
        } catch (GeneralSecurityException gse) {
            throw new IllegalStateException(gse.getMessage());
        } // catch
        HttpsURLConnection.
                setDefaultSSLSocketFactory(context.getSocketFactory());
    } // __trustAllHttpsCertificates

    /**
     * Return true if the protocol handler property java.
     * protocol.handler.pkgs is set to the Sun's com.sun.net.ssl.
     * internal.www.protocol deprecated one, false
     * otherwise.
     *
     * @return true if the protocol handler
     * property is set to the Sun's deprecated one, false
     * otherwise.
     */
    private static boolean isDeprecatedSSLProtocol() {
        return ("com.sun.net.ssl.internal.www.protocol".equals(System.
                getProperty("java.protocol.handler.pkgs")));
    } // isDeprecatedSSLProtocol

    /**
     * Set the default Hostname Verifier to an instance of a fake class that
     * trust all hostnames.
     */
    private static void _trustAllHostnames() {
        // Create a trust manager that does not validate certificate chains
        if (_hostnameVerifier == null) {
            _hostnameVerifier = new FakeHostnameVerifier();
        } // if
        // Install the all-trusting host name verifier:
        HttpsURLConnection.setDefaultHostnameVerifier(_hostnameVerifier);
    } // _trustAllHttpsCertificates

    /**
     * Set the default X509 Trust Manager to an instance of a fake class that
     * trust all certificates, even the self-signed ones.
     */
    private static void _trustAllHttpsCertificates() {
        SSLContext context;

        // Create a trust manager that does not validate certificate chains
        if (_trustManagers == null) {
            _trustManagers = new TrustManager[]{new FakeX509TrustManager()};
        } // if
        // Install the all-trusting trust manager:
        try {
            context = SSLContext.getInstance("SSL");
            context.init(null, _trustManagers, new SecureRandom());
        } catch (GeneralSecurityException gse) {
            throw new IllegalStateException(gse.getMessage());
        } // catch
        HttpsURLConnection.setDefaultSSLSocketFactory(context.
                getSocketFactory());
    } // _trustAllHttpsCertificates

    /**
     * Set the default Hostname Verifier to an instance of a fake class that
     * trust all hostnames.
     */
    public static void trustAllHostnames() {
        // Is the deprecated protocol setted?
        if (isDeprecatedSSLProtocol()) {
            __trustAllHostnames();
        } else {
            _trustAllHostnames();
        } // else
    } // trustAllHostnames

    /**
     * Set the default X509 Trust Manager to an instance of a fake class that
     * trust all certificates, even the self-signed ones.
     */
    public static void trustAllHttpsCertificates() {
        // Is the deprecated protocol setted?
        if (isDeprecatedSSLProtocol()) {
            __trustAllHttpsCertificates();
        } else {
            _trustAllHttpsCertificates();
        } // else
    } // trustAllHttpsCertificates

    /**
     * This class implements a fake hostname verificator, trusting any host
     * name. This class uses the old deprecated API from the com.sun.
     * ssl package.
     *
     * @author Francis Labrie
     * @deprecated see {@link SSLUtilities.FakeHostnameVerifier}.
     */
    public static class _FakeHostnameVerifier
            implements com.sun.net.ssl.HostnameVerifier {

        /**
         * Always return true, indicating that the host name is an
         * acceptable match with the server's authentication scheme.
         *
         * @param hostname the host name.
         * @param session  the SSL session used on the connection to
         *                 host.
         * @return the true boolean value
         * indicating the host name is trusted.
         */
        public boolean verify(String hostname, String session) {
            return (true);
        } // verify
    } // _FakeHostnameVerifier


    /**
     * This class allow any X509 certificates to be used to authenticate the
     * remote side of a secure socket, including self-signed certificates. This
     * class uses the old deprecated API from the com.sun.ssl
     * package.
     *
     * @author Francis Labrie
     * @deprecated see {@link SSLUtilities.FakeX509TrustManager}.
     */
    public static class _FakeX509TrustManager
            implements com.sun.net.ssl.X509TrustManager {

        /**
         * Empty array of certificate authority certificates.
         */
        private static final X509Certificate[] _AcceptedIssuers =
                new X509Certificate[]{};


        /**
         * Always return true, trusting for client SSL
         * chain peer certificate chain.
         *
         * @param chain the peer certificate chain.
         * @return the true boolean value
         * indicating the chain is trusted.
         */
        public boolean isClientTrusted(X509Certificate[] chain) {
            return (true);
        } // checkClientTrusted

        /**
         * Always return true, trusting for server SSL
         * chain peer certificate chain.
         *
         * @param chain the peer certificate chain.
         * @return the true boolean value
         * indicating the chain is trusted.
         */
        public boolean isServerTrusted(X509Certificate[] chain) {
            return (true);
        } // checkServerTrusted

        /**
         * Return an empty array of certificate authority certificates which
         * are trusted for authenticating peers.
         *
         * @return a empty array of issuer certificates.
         */
        public X509Certificate[] getAcceptedIssuers() {
            return (_AcceptedIssuers);
        } // getAcceptedIssuers
    } // _FakeX509TrustManager


    /**
     * This class implements a fake hostname verificator, trusting any host
     * name.
     *
     * @author Francis Labrie
     */
    public static class FakeHostnameVerifier implements HostnameVerifier {

        /**
         * Always return true, indicating that the host name is
         * an acceptable match with the server's authentication scheme.
         *
         * @param hostname the host name.
         * @param session  the SSL session used on the connection to
         *                 host.
         * @return the true boolean value
         * indicating the host name is trusted.
         */
        public boolean verify(String hostname,
                              javax.net.ssl.SSLSession session) {
            return (true);
        } // verify
    } // FakeHostnameVerifier


    /**
     * This class allow any X509 certificates to be used to authenticate the
     * remote side of a secure socket, including self-signed certificates.
     *
     * @author Francis Labrie
     */
    public static class FakeX509TrustManager implements X509TrustManager {

        /**
         * Empty array of certificate authority certificates.
         */
        private static final X509Certificate[] _AcceptedIssuers =
                new X509Certificate[]{};


        /**
         * Always trust for client SSL chain peer certificate
         * chain with any authType authentication types.
         *
         * @param chain    the peer certificate chain.
         * @param authType the authentication type based on the client
         *                 certificate.
         */
        public void checkClientTrusted(X509Certificate[] chain,
                                       String authType) {
        } // checkClientTrusted

        /**
         * Always trust for server SSL chain peer certificate
         * chain with any authType exchange algorithm types.
         *
         * @param chain    the peer certificate chain.
         * @param authType the key exchange algorithm used.
         */
        public void checkServerTrusted(X509Certificate[] chain,
                                       String authType) {
        } // checkServerTrusted

        /**
         * Return an empty array of certificate authority certificates which
         * are trusted for authenticating peers.
         *
         * @return a empty array of issuer certificates.
         */
        public X509Certificate[] getAcceptedIssuers() {
            return (_AcceptedIssuers);
        } // getAcceptedIssuers
    } // FakeX509TrustManager

}
