package org.CodeForPizza.connection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.nio.file.Paths;
import java.util.HashMap;

import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.HttpVersion;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.routing.HttpRoutePlanner;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.ContentEncodingHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.DefaultRedirectStrategy;
import org.apache.http.impl.client.DefaultUserTokenHandler;
import org.apache.http.impl.client.ProxyAuthenticationStrategy;
import org.apache.http.impl.client.SystemDefaultHttpClient;
import org.apache.http.impl.client.TargetAuthenticationStrategy;
import org.apache.http.impl.conn.BasicClientConnectionManager;
import org.apache.http.impl.conn.DefaultHttpRoutePlanner;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.impl.conn.ProxySelectorRoutePlanner;
import org.apache.http.message.BasicRequestLine;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.SyncBasicHttpParams;
import org.apache.http.protocol.HttpContext;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class HttpConnectionTest {
    /**
     * Method under test: {@link HttpConnection#sendRequestToAPI(JSONObject)}
     */


    @Test
    void testSendRequestToAPI5() {
        HttpConnection httpConnection = new HttpConnection();
        JSONObject movie = mock(JSONObject.class);
        when(movie.toJSONString()).thenReturn("Json String");
        httpConnection.sendRequestToAPI(movie);
        verify(movie).toJSONString();
    }

    /**
     * Method under test: {@link HttpConnection#sendRequestToAPI(JSONObject)}
     */
    @Test
    void testSendRequestToAPI6() {
        HttpConnection httpConnection = new HttpConnection();
        JSONObject movie = mock(JSONObject.class);
        when(movie.toJSONString()).thenReturn(null);
        httpConnection.sendRequestToAPI(movie);
        verify(movie).toJSONString();
    }

    /**
     * Method under test: {@link HttpConnection#executePOST(CloseableHttpClient, HttpPost)}
     */
    @Test
    void testExecutePOST() {
        HttpConnection httpConnection = new HttpConnection();
        ContentEncodingHttpClient httpClient = new ContentEncodingHttpClient();
        HttpPost httpPost = new HttpPost("https://example.org/example");
        httpConnection.executePOST(httpClient, httpPost);
        assertTrue(httpClient.getUserTokenHandler() instanceof DefaultUserTokenHandler);
        assertTrue(httpClient.getTargetAuthenticationStrategy() instanceof TargetAuthenticationStrategy);
        assertTrue(httpClient.getRoutePlanner() instanceof DefaultHttpRoutePlanner);
        assertEquals(2, httpClient.getResponseInterceptorCount());
        assertEquals(11, httpClient.getRequestInterceptorCount());
        assertTrue(httpClient.getConnectionKeepAliveStrategy() instanceof DefaultConnectionKeepAliveStrategy);
        assertTrue(httpClient.getRedirectStrategy() instanceof DefaultRedirectStrategy);
        assertTrue(httpClient.getProxyAuthenticationStrategy() instanceof ProxyAuthenticationStrategy);
        HttpParams params = httpClient.getParams();
        assertTrue(params instanceof SyncBasicHttpParams);
        HttpRequestRetryHandler httpRequestRetryHandler = httpClient.getHttpRequestRetryHandler();
        assertTrue(httpRequestRetryHandler instanceof DefaultHttpRequestRetryHandler);
        CookieStore cookieStore = httpClient.getCookieStore();
        assertTrue(cookieStore instanceof BasicCookieStore);
        assertTrue(httpClient.getCredentialsProvider() instanceof BasicCredentialsProvider);
        ClientConnectionManager connectionManager = httpClient.getConnectionManager();
        assertTrue(connectionManager instanceof BasicClientConnectionManager);
        assertTrue(httpClient.getConnectionReuseStrategy() instanceof DefaultConnectionReuseStrategy);
        assertEquals(7, httpClient.getCookieSpecs().getSpecNames().size());
        assertEquals(5, ((SyncBasicHttpParams) params).getNames().size());
        assertFalse(((DefaultHttpRequestRetryHandler) httpRequestRetryHandler).isRequestSentRetryEnabled());
        assertTrue(cookieStore.getCookies().isEmpty());
        assertEquals(3, ((DefaultHttpRequestRetryHandler) httpRequestRetryHandler).getRetryCount());
        assertEquals(5, httpClient.getAuthSchemes().getSchemeNames().size());
        assertEquals(2, connectionManager.getSchemeRegistry().getSchemeNames().size());
        assertFalse(httpPost.isAborted());
        assertTrue(httpPost.getParams() instanceof BasicHttpParams);
        assertTrue(httpPost.getProtocolVersion() instanceof HttpVersion);
    }

    /**
     * Method under test: {@link HttpConnection#executePOST(CloseableHttpClient, HttpPost)}
     */
    @Test
    void testExecutePOST2() {
        HttpConnection httpConnection = new HttpConnection();
        HttpPost httpPost = new HttpPost("https://example.org/example");
        httpConnection.executePOST(null, httpPost);
        assertEquals(0, httpPost.getAllHeaders().length);
        assertTrue(httpPost.getRequestLine() instanceof BasicRequestLine);
        assertTrue(httpPost.getParams() instanceof BasicHttpParams);
        assertTrue(httpPost.getProtocolVersion() instanceof HttpVersion);
    }

    /**
     * Method under test: {@link HttpConnection#executePOST(CloseableHttpClient, HttpPost)}
     */
    @Test
    void testExecutePOST3() {
        HttpConnection httpConnection = new HttpConnection();
        SystemDefaultHttpClient httpClient = new SystemDefaultHttpClient();
        HttpPost httpPost = new HttpPost("https://example.org/example");
        httpConnection.executePOST(httpClient, httpPost);
        assertTrue(httpClient.getUserTokenHandler() instanceof DefaultUserTokenHandler);
        assertTrue(httpClient.getTargetAuthenticationStrategy() instanceof TargetAuthenticationStrategy);
        assertTrue(httpClient.getRoutePlanner() instanceof ProxySelectorRoutePlanner);
        assertEquals(1, httpClient.getResponseInterceptorCount());
        assertEquals(10, httpClient.getRequestInterceptorCount());
        assertTrue(httpClient.getConnectionKeepAliveStrategy() instanceof DefaultConnectionKeepAliveStrategy);
        CookieStore cookieStore = httpClient.getCookieStore();
        assertTrue(cookieStore instanceof BasicCookieStore);
        assertTrue(httpClient.getCredentialsProvider() instanceof BasicCredentialsProvider);
        assertTrue(httpClient.getRedirectStrategy() instanceof DefaultRedirectStrategy);
        assertTrue(httpClient.getProxyAuthenticationStrategy() instanceof ProxyAuthenticationStrategy);
        HttpParams params = httpClient.getParams();
        assertTrue(params instanceof SyncBasicHttpParams);
        HttpRequestRetryHandler httpRequestRetryHandler = httpClient.getHttpRequestRetryHandler();
        assertTrue(httpRequestRetryHandler instanceof DefaultHttpRequestRetryHandler);
        ClientConnectionManager connectionManager = httpClient.getConnectionManager();
        assertTrue(connectionManager instanceof PoolingClientConnectionManager);
        assertTrue(httpClient.getConnectionReuseStrategy() instanceof DefaultConnectionReuseStrategy);
        assertEquals(7, httpClient.getCookieSpecs().getSpecNames().size());
        assertEquals(10, ((PoolingClientConnectionManager) connectionManager).getMaxTotal());
        assertEquals(5, httpClient.getAuthSchemes().getSchemeNames().size());
        assertEquals(5, ((PoolingClientConnectionManager) connectionManager).getDefaultMaxPerRoute());
        assertEquals(5, ((SyncBasicHttpParams) params).getNames().size());
        assertEquals(3, ((DefaultHttpRequestRetryHandler) httpRequestRetryHandler).getRetryCount());
        assertFalse(((DefaultHttpRequestRetryHandler) httpRequestRetryHandler).isRequestSentRetryEnabled());
        assertTrue(cookieStore.getCookies().isEmpty());
        assertEquals(2, connectionManager.getSchemeRegistry().getSchemeNames().size());
        assertTrue(httpPost.getProtocolVersion() instanceof HttpVersion);
        assertTrue(httpPost.getParams() instanceof BasicHttpParams);
    }

    /**
     * Method under test: {@link HttpConnection#executePOST(CloseableHttpClient, HttpPost)}
     */
    @Test
    void testExecutePOST4() {
        HttpConnection httpConnection = new HttpConnection();

        ContentEncodingHttpClient httpClient = new ContentEncodingHttpClient();
        httpClient.addResponseInterceptor(mock(HttpResponseInterceptor.class));
        HttpPost httpPost = new HttpPost("https://example.org/example");
        httpConnection.executePOST(httpClient, httpPost);
        assertTrue(httpClient.getUserTokenHandler() instanceof DefaultUserTokenHandler);
        assertTrue(httpClient.getTargetAuthenticationStrategy() instanceof TargetAuthenticationStrategy);
        assertTrue(httpClient.getRoutePlanner() instanceof DefaultHttpRoutePlanner);
        assertTrue(httpClient.getConnectionKeepAliveStrategy() instanceof DefaultConnectionKeepAliveStrategy);
        assertTrue(httpClient.getRedirectStrategy() instanceof DefaultRedirectStrategy);
        assertTrue(httpClient.getProxyAuthenticationStrategy() instanceof ProxyAuthenticationStrategy);
        HttpParams params = httpClient.getParams();
        assertTrue(params instanceof SyncBasicHttpParams);
        HttpRequestRetryHandler httpRequestRetryHandler = httpClient.getHttpRequestRetryHandler();
        assertTrue(httpRequestRetryHandler instanceof DefaultHttpRequestRetryHandler);
        CookieStore cookieStore = httpClient.getCookieStore();
        assertTrue(cookieStore instanceof BasicCookieStore);
        assertTrue(httpClient.getCredentialsProvider() instanceof BasicCredentialsProvider);
        ClientConnectionManager connectionManager = httpClient.getConnectionManager();
        assertTrue(connectionManager instanceof BasicClientConnectionManager);
        assertTrue(httpClient.getConnectionReuseStrategy() instanceof DefaultConnectionReuseStrategy);
        assertEquals(7, httpClient.getCookieSpecs().getSpecNames().size());
        assertEquals(5, ((SyncBasicHttpParams) params).getNames().size());
        assertFalse(((DefaultHttpRequestRetryHandler) httpRequestRetryHandler).isRequestSentRetryEnabled());
        assertTrue(cookieStore.getCookies().isEmpty());
        assertEquals(3, ((DefaultHttpRequestRetryHandler) httpRequestRetryHandler).getRetryCount());
        assertEquals(5, httpClient.getAuthSchemes().getSchemeNames().size());
        assertEquals(2, connectionManager.getSchemeRegistry().getSchemeNames().size());
        assertFalse(httpPost.isAborted());
        assertTrue(httpPost.getParams() instanceof BasicHttpParams);
        assertTrue(httpPost.getProtocolVersion() instanceof HttpVersion);
    }

    /**
     * Method under test: {@link HttpConnection#executePOST(CloseableHttpClient, HttpPost)}
     */
    @Test
    void testExecutePOST5() throws HttpException {
        HttpConnection httpConnection = new HttpConnection();
        HttpRoutePlanner routePlanner = mock(HttpRoutePlanner.class);
        when(routePlanner.determineRoute(Mockito.<HttpHost>any(), Mockito.<HttpRequest>any(), Mockito.<HttpContext>any()))
                .thenReturn(null);

        ContentEncodingHttpClient httpClient = new ContentEncodingHttpClient();
        httpClient.setRoutePlanner(routePlanner);
        httpClient.addResponseInterceptor(mock(HttpResponseInterceptor.class));
        HttpPost httpPost = new HttpPost("https://example.org/example");
        httpConnection.executePOST(httpClient, httpPost);
        verify(routePlanner).determineRoute(Mockito.<HttpHost>any(), Mockito.<HttpRequest>any(),
                Mockito.<HttpContext>any());
        assertTrue(httpClient.getUserTokenHandler() instanceof DefaultUserTokenHandler);
        assertTrue(httpClient.getTargetAuthenticationStrategy() instanceof TargetAuthenticationStrategy);
        assertTrue(httpClient.getConnectionKeepAliveStrategy() instanceof DefaultConnectionKeepAliveStrategy);
        assertTrue(httpClient.getRedirectStrategy() instanceof DefaultRedirectStrategy);
        assertTrue(httpClient.getProxyAuthenticationStrategy() instanceof ProxyAuthenticationStrategy);
        HttpParams params = httpClient.getParams();
        assertTrue(params instanceof SyncBasicHttpParams);
        HttpRequestRetryHandler httpRequestRetryHandler = httpClient.getHttpRequestRetryHandler();
        assertTrue(httpRequestRetryHandler instanceof DefaultHttpRequestRetryHandler);
        CookieStore cookieStore = httpClient.getCookieStore();
        assertTrue(cookieStore instanceof BasicCookieStore);
        assertTrue(httpClient.getCredentialsProvider() instanceof BasicCredentialsProvider);
        ClientConnectionManager connectionManager = httpClient.getConnectionManager();
        assertTrue(connectionManager instanceof BasicClientConnectionManager);
        assertTrue(httpClient.getConnectionReuseStrategy() instanceof DefaultConnectionReuseStrategy);
        assertEquals(7, httpClient.getCookieSpecs().getSpecNames().size());
        assertEquals(5, ((SyncBasicHttpParams) params).getNames().size());
        assertFalse(((DefaultHttpRequestRetryHandler) httpRequestRetryHandler).isRequestSentRetryEnabled());
        assertTrue(cookieStore.getCookies().isEmpty());
        assertEquals(3, ((DefaultHttpRequestRetryHandler) httpRequestRetryHandler).getRetryCount());
        assertEquals(5, httpClient.getAuthSchemes().getSchemeNames().size());
        assertEquals(2, connectionManager.getSchemeRegistry().getSchemeNames().size());
        assertFalse(httpPost.isAborted());
        assertTrue(httpPost.getParams() instanceof BasicHttpParams);
        assertTrue(httpPost.getProtocolVersion() instanceof HttpVersion);
    }

    /**
     * Method under test: {@link HttpConnection#executePOST(CloseableHttpClient, HttpPost)}
     */
    @Test
    void testExecutePOST6() throws HttpException {
        HttpConnection httpConnection = new HttpConnection();
        HttpRoutePlanner routePlanner = mock(HttpRoutePlanner.class);
        when(routePlanner.determineRoute(Mockito.<HttpHost>any(), Mockito.<HttpRequest>any(), Mockito.<HttpContext>any()))
                .thenReturn(new HttpRoute(HttpHost.create("https://example.org/example")));

        ContentEncodingHttpClient httpClient = new ContentEncodingHttpClient();
        httpClient.setRoutePlanner(routePlanner);
        httpClient.addResponseInterceptor(mock(HttpResponseInterceptor.class));
        HttpPost httpPost = new HttpPost("Uri");
        httpConnection.executePOST(httpClient, httpPost);
        verify(routePlanner).determineRoute(Mockito.<HttpHost>any(), Mockito.<HttpRequest>any(),
                Mockito.<HttpContext>any());
        assertTrue(httpClient.getUserTokenHandler() instanceof DefaultUserTokenHandler);
        assertTrue(httpClient.getTargetAuthenticationStrategy() instanceof TargetAuthenticationStrategy);
        assertTrue(httpClient.getConnectionKeepAliveStrategy() instanceof DefaultConnectionKeepAliveStrategy);
        assertTrue(httpClient.getRedirectStrategy() instanceof DefaultRedirectStrategy);
        assertTrue(httpClient.getProxyAuthenticationStrategy() instanceof ProxyAuthenticationStrategy);
        HttpParams params = httpClient.getParams();
        assertTrue(params instanceof SyncBasicHttpParams);
        HttpRequestRetryHandler httpRequestRetryHandler = httpClient.getHttpRequestRetryHandler();
        assertTrue(httpRequestRetryHandler instanceof DefaultHttpRequestRetryHandler);
        CookieStore cookieStore = httpClient.getCookieStore();
        assertTrue(cookieStore instanceof BasicCookieStore);
        assertTrue(httpClient.getCredentialsProvider() instanceof BasicCredentialsProvider);
        ClientConnectionManager connectionManager = httpClient.getConnectionManager();
        assertTrue(connectionManager instanceof BasicClientConnectionManager);
        assertTrue(httpClient.getConnectionReuseStrategy() instanceof DefaultConnectionReuseStrategy);
        assertEquals(7, httpClient.getCookieSpecs().getSpecNames().size());
        assertEquals(5, ((SyncBasicHttpParams) params).getNames().size());
        assertFalse(((DefaultHttpRequestRetryHandler) httpRequestRetryHandler).isRequestSentRetryEnabled());
        assertTrue(cookieStore.getCookies().isEmpty());
        assertEquals(3, ((DefaultHttpRequestRetryHandler) httpRequestRetryHandler).getRetryCount());
        assertEquals(5, httpClient.getAuthSchemes().getSchemeNames().size());
        assertEquals(2, connectionManager.getSchemeRegistry().getSchemeNames().size());
        assertFalse(httpPost.isAborted());
        assertTrue(httpPost.getParams() instanceof BasicHttpParams);
        assertTrue(httpPost.getProtocolVersion() instanceof HttpVersion);
    }

    /**
     * Method under test: {@link HttpConnection#executePOST(CloseableHttpClient, HttpPost)}
     */
    @Test
    void testExecutePOST7() {
        HttpConnection httpConnection = new HttpConnection();

        ContentEncodingHttpClient httpClient = new ContentEncodingHttpClient();
        httpClient.setRoutePlanner(mock(HttpRoutePlanner.class));
        httpClient.addResponseInterceptor(mock(HttpResponseInterceptor.class));
        HttpPost httpPost = new HttpPost();
        httpConnection.executePOST(httpClient, httpPost);
        assertTrue(httpClient.getUserTokenHandler() instanceof DefaultUserTokenHandler);
        assertTrue(httpClient.getTargetAuthenticationStrategy() instanceof TargetAuthenticationStrategy);
        assertEquals(3, httpClient.getResponseInterceptorCount());
        assertTrue(httpClient.getConnectionKeepAliveStrategy() instanceof DefaultConnectionKeepAliveStrategy);
        assertTrue(httpClient.getRedirectStrategy() instanceof DefaultRedirectStrategy);
        assertTrue(httpClient.getProxyAuthenticationStrategy() instanceof ProxyAuthenticationStrategy);
        assertTrue(httpClient.getParams() instanceof SyncBasicHttpParams);
        assertTrue(httpClient.getHttpRequestRetryHandler() instanceof DefaultHttpRequestRetryHandler);
        assertTrue(httpClient.getCookieStore() instanceof BasicCookieStore);
        assertTrue(httpClient.getCredentialsProvider() instanceof BasicCredentialsProvider);
        assertTrue(httpClient.getConnectionManager() instanceof BasicClientConnectionManager);
        assertTrue(httpClient.getConnectionReuseStrategy() instanceof DefaultConnectionReuseStrategy);
        assertEquals(0, httpPost.getAllHeaders().length);
        assertTrue(httpPost.getProtocolVersion() instanceof HttpVersion);
        assertTrue(httpPost.getParams() instanceof BasicHttpParams);
    }

}

