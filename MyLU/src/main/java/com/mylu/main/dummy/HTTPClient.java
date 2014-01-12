package com.mylu.main.dummy;

import org.apache.http.client.protocol.ClientContext;
import java.net.CookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import java.net.CookieStore;

/**
 * Created by hansen on 1/7/14.
 * @Deprecated may not need this class if the jsonparser class can get and post http responses from
 * url data...
 */
public class HTTPClient {
    private static DefaultHttpClient client;
    private static CookieStore cookieStore;
    private static HttpContext httpContext;
    private static final int CONNECTION_TIMEOUT = 100;
    private static final int SOCKET_TIMEOUT = 1000;


    static {
        //cookieStore = new BasicCookieStore();
        httpContext = new BasicHttpContext();
        httpContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
        client = getThreadSafeClient();
        HttpParams params = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(params, CONNECTION_TIMEOUT);
        HttpConnectionParams.setSoTimeout(params, SOCKET_TIMEOUT);
        client.setParams(params);
    }

    private static DefaultHttpClient getThreadSafeClient() {
        return null; //this really should matter cause this class is deprecated anyways
    }



}
