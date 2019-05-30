package com.yb.common.center.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Http请求工具
 *
 * @author yebing
 */
public class HttpUtil {
    private static CloseableHttpClient httpClient;
    public static final String CHARSET = "UTF-8";

    static {
        /*设置连接超时为30s,等待超时为10分钟*/
        RequestConfig config = RequestConfig.custom().setConnectTimeout(60000).setSocketTimeout(600000).build();
        httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
    }

    /**
     * 设置请求头
     *
     * @param httpPost
     * @param header
     */
    public static void setHeader(HttpPost httpPost, Map<String, String> header) {
        /* start 请求头参数设置*/
        if (header != null && header.size() != 0) {
            Set<Map.Entry<String, String>> entries = header.entrySet();
            Iterator<Map.Entry<String, String>> iterator = entries.iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> next = iterator.next();
                httpPost.addHeader(next.getKey(), next.getValue());
            }
        }
    }

    public static String post(String url, Map<String, String> header, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection urlConnection = realUrl.openConnection();
            urlConnection.setConnectTimeout(3000);
            if (header != null && header.size() != 0) {
                /* start 请求头参数设置*/
                Set<Map.Entry<String, String>> entries = header.entrySet();
                Iterator<Map.Entry<String, String>> iterator = entries.iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, String> next = iterator.next();
                    urlConnection.setRequestProperty(next.getKey(), next.getValue());
                }
            }

            /*发送POST请求必须设置如下两行*/
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            /*获取输出流*/
            out = new PrintWriter(urlConnection.getOutputStream());
            out.print(param);
            out.flush();
            /*建立实际的连接*/
            urlConnection.connect();

            in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * JSON形式Post请求
     *
     * @param url
     * @param header
     * @param param
     * @return
     */
    public static String doPostJSON(String url, Map<String, String> header, String param) {
        HttpPost httpPost = new HttpPost(url);
        try {
            setHeader(httpPost, header);
            /* 请求参数设置 */
            if (param != null && !StringUtils.isEmpty(param)) {
                httpPost.setEntity(new StringEntity(param));
            }
            CloseableHttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null) {
                result = EntityUtils.toString(entity, CHARSET);
            }
            EntityUtils.consume(entity);
            response.close();
            return result;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * FORM请求
     *
     * @param url
     * @param header
     * @param form
     * @return
     */
    public static String doPostForm(String url, Map<String, String> header, Map<String, String> form) {
        HttpPost httpPost = new HttpPost(url);
        try {
            setHeader(httpPost, header);
            /* 请求参数设置 */
            if (form != null && StringUtils.isEmpty(form)) {
                List<BasicNameValuePair> pair = new ArrayList<BasicNameValuePair>();
                //我们遍历map 将数据转化为我们的表单数据
                for (Map.Entry<String, String> entry :
                        form.entrySet()) {
                    pair.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                //httpPost 中放入经过url编码的表单参数
                httpPost.setEntity(new UrlEncodedFormEntity(pair));
            }
            CloseableHttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null) {
                result = EntityUtils.toString(entity, CHARSET);
            }
            EntityUtils.consume(entity);
            response.close();
            return result;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * FORM带文件请求
     *
     * @param url
     * @param header
     * @param form
     * @return
     */
    public static String doPostMultipart(String url, Map<String, String> header, Map<String, String> form, Map<String, String> formData) {
        HttpPost httpPost = new HttpPost(url);
        try {
            setHeader(httpPost, header);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null) {
                result = EntityUtils.toString(entity, CHARSET);
            }
            EntityUtils.consume(entity);
            response.close();
            return result;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
