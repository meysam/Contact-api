package ir.zeroandone.app.infra.http;

import javax.servlet.ServletRequest;

public class ServletRequestStore {

    private final static ThreadLocal<ServletRequest> servletRequests = new ThreadLocal<ServletRequest>();

    public static void setServletRequest(ServletRequest request) {
        servletRequests.set(request);
    }

    public static ServletRequest getServletRequest() {
        return servletRequests.get();
    }

}