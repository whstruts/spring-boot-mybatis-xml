package com.kfit.demo.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;

/**
 * 发送请求客户端
 * 
 * @ClassName: PostClient
 * @Author:Administrator
 * @Create:2013-6-25 - 下午02:47:34
 * @version Change History:
 * @version: (1) 2013-6-25 Administrator
 */
public class PostClient {

    private static final Log    log        = LogFactory.getLog(PostClient.class);
    private static final String USER_AGENT = "RGETL";

    /**
     * 使用post发送请求
     * 
     * @param appParamMap 请求的参数
     * @param urlStr 请求的URL
     * @return
     */
    public static String sendByGet(Map<String, String> appParamMap, String urlStr,
                                   String encoding) {
        try {
            HttpClient httpClient = new DefaultHttpClient();
            if (urlStr == null || urlStr.trim().length() == 0) {
                return null;
            }

            if (StringUtils.isBlank(encoding)) {
                encoding = "UTF-8";
            }
            StringBuffer sb = new StringBuffer(urlStr);
            if (appParamMap != null && !appParamMap.isEmpty()) {
                if (urlStr.contains("?")) {
                    for (Entry<String, String> entry : appParamMap.entrySet()) {
                        sb.append("&").append(entry.getKey()).append("=").append(entry.getValue());
                    }
                } else {
                    int i = 0;
                    for (Entry<String, String> entry : appParamMap.entrySet()) {
                        if (i == 0) {
                            sb.append("?").append(entry.getKey()).append("=")
                                .append(entry.getValue());
                        } else {
                            sb.append("&").append(entry.getKey()).append("=")
                                .append(entry.getValue());
                        }
                        i++;
                    }
                }
            }

            URL url = new URL(sb.toString());
            URI uri = new URI(url.getProtocol(), null, url.getHost(), url.getPort(), url.getPath(),
                url.getQuery(), null);

            log.info(uri);

            HttpGet httpGet = new HttpGet(uri);

            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity httpEntity = response.getEntity();
            InputStream inputStream = httpEntity.getContent();

            // 获取返回的数据信息
            StringBuffer postResult = new StringBuffer();
            String readLine = null;
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream, encoding));
            while ((readLine = reader.readLine()) != null) {
                postResult.append(readLine);
            }

            httpClient.getConnectionManager().shutdown();

            return postResult.toString();

        } catch (Exception e) {
            log.error("系统出现异常",e);
        }
        return null;
    }

    /**
     * 使用post发送请求
     * 
     * @param appParamMap 请求的参数
     * @param urlStr 请求的URL
     * @return
     */
    public static String sendByPostEncoding(Map<String, String> appParamMap, String urlStr,
                                            String encoding) {
        try {
            HttpClient httpClient = new DefaultHttpClient();
            if (urlStr == null || urlStr.trim().length() == 0) {
                return null;
            }

            if (StringUtils.isBlank(encoding)) {
                encoding = "UTF-8";
            }

            HttpConnectionParams.setConnectionTimeout(httpClient.getParams(), 60000*10); /* 请求超时 */
            HttpConnectionParams.setSoTimeout(httpClient.getParams(), 60000*10);

            HttpPost httpPost = new HttpPost(urlStr);
            httpPost.addHeader("User-Agent", USER_AGENT);
            TreeMap<String, String> treeMap = new TreeMap<String, String>();
            if (appParamMap != null) {
                treeMap.putAll(appParamMap);
            }

            Iterator<String> iterator = treeMap.keySet().iterator();

            List<NameValuePair> params = new ArrayList<NameValuePair>();

            while (iterator.hasNext()) {
                String key = iterator.next();
                params.add(new BasicNameValuePair(key, treeMap.get(key)));
            }

            UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(params, "UTF-8");
            httpPost.setEntity(uefEntity);

            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            InputStream inputStream = httpEntity.getContent();

            // 获取返回的数据信息
            StringBuffer postResult = new StringBuffer();
            String readLine = null;
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream, encoding));
            while ((readLine = reader.readLine()) != null) {
                postResult.append(readLine);
            }

            httpClient.getConnectionManager().shutdown();

            return postResult.toString();

        } catch (Exception e) {
            log.error("系统出现异常",e);
        }
        return null;
    }

    /**
     * 使用post发送请求
     * 
     * @param appParamMap 请求的参数
     * @param urlStr 请求的URL
     * @return
     */
    public static String sendByPost(Map<String, String> appParamMap, String urlStr) {
        return sendByPostEncoding(appParamMap, urlStr, "UTF-8");
    }

    /**
     * 使用post发送https请求
     * 
     * @param appParamMap 请求参数
     * @param urlStr 请求URL
     * @return
     */
    public static String sendHttpsByPost(Map<String, String> appParamMap, String urlStr) {
        try {
            HttpClient httpClient = new DefaultHttpClient();
            if (urlStr == null || urlStr.trim().length() == 0) {
                return null;
            }

            HttpPost httpPost = new HttpPost(urlStr);

            Iterator<String> iterator = appParamMap.keySet().iterator();

            List<NameValuePair> params = new ArrayList<NameValuePair>();

            while (iterator.hasNext()) {
                String key = iterator.next();
                params.add(new BasicNameValuePair(key, appParamMap.get(key)));
            }

            UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(params, "UTF-8");
            httpPost.setEntity(uefEntity);

            HttpResponse response = wrapClient(httpClient).execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            InputStream inputStream = httpEntity.getContent();

            // 获取返回的数据信息
            StringBuffer postResult = new StringBuffer();
            String readLine = null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            while ((readLine = reader.readLine()) != null) {
                postResult.append(readLine);
            }

            httpClient.getConnectionManager().shutdown();

            return postResult.toString();

        } catch (Exception e) {
            log.error("系统出现异常",e);
        }
        return null;
    }

    /**
     * 让Https跳过安全验证
     * 
     * @param base
     * @return
     */
    @SuppressWarnings("deprecation")
    private static HttpClient wrapClient(HttpClient base) {
        try {
            SSLContext ctx = SSLContext.getInstance("TLS");
            X509TrustManager tm = new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                @Override
                public void checkClientTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {
                }
            };
            ctx.init(null, new TrustManager[] { tm }, null);
            SSLSocketFactory ssf = new SSLSocketFactory(ctx);
            ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            ClientConnectionManager ccm = base.getConnectionManager();
            SchemeRegistry sr = ccm.getSchemeRegistry();
            sr.register(new Scheme("https", ssf, 443));
            return new DefaultHttpClient(ccm, base.getParams());
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return null;
        }
    }

}