package com.mylu.util;

/**
 * Created by hansen on 1/7/14.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class JSONParser extends AsyncTask <String, Void, JSONObject>
{
    public interface JSONParserCallback {
        void showList(JSONObject result);
    }

    private JSONParserCallback mCallback;
    public JSONParser(JSONParserCallback callback) {
        mCallback =  callback;
    }

    static InputStream is = null;
    JSONObject jObj = null;
    static String json = "";

    public String getStringfromUrl(String url) {
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);

            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            is = httpEntity.getContent();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = in.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }
        return json;
    }

    public JSONObject postJSONfromUrl(String url, boolean isPost) {
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpResponse response = null;
            if (isPost) {
                HttpPost httpPost = new HttpPost(url);
                response = httpClient.execute(httpPost);
            } else {
                HttpGet httpGet = new HttpGet(url);
                response = httpClient.execute(httpGet);
            }
            HttpEntity httpEntity = response.getEntity();
            is = httpEntity.getContent();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = in.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jObj;
    }

    @Override
    protected JSONObject doInBackground(String... params)
    {
        if (params[1] == "POST") {
            return postJSONfromUrl(params[0], true);
        } else if (params[1] == "GET")
            return postJSONfromUrl(params[0], false);
        else {
            return null;  //TODO: fix this please...
        }

    }

    @Override
    protected void onPostExecute(JSONObject result) {
        mCallback.showList(result);
    }

}
