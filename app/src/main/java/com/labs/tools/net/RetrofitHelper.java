package com.labs.tools.net;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.labs.tools.util.TimeUtils;
import com.squareup.okhttp.OkHttpClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.ConversionException;
import retrofit.converter.GsonConverter;
import retrofit.mime.MimeUtil;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;
import timber.log.Timber;

public class RetrofitHelper {

    private static final Object LOCK = new Object();

    public static final long DEFAULT_CONNECTION_TIMEOUT = 30L * TimeUtils.ONE_SECOND;

    private static RetrofitHelper sInstance;

    private OkHttpClient okHttpClient;

    public static RetrofitHelper getInstance() {
        synchronized (LOCK) {
            if (sInstance == null) {
                sInstance = new RetrofitHelper();
            }
        }
        return sInstance;
    }

    public RestService createRestService(String endPoint, RestAdapter.LogLevel logLevel) {
        return createRestService(endPoint, DEFAULT_CONNECTION_TIMEOUT, DEFAULT_CONNECTION_TIMEOUT, logLevel);
    }

    public RestService createRestService(String endPoint, long connectTimeout, long readTimeout, RestAdapter.LogLevel logLevel) {
        return createRestService(endPoint, connectTimeout, readTimeout, null, logLevel);
    }

    public RestService createRestService(String endPoint, long connectTimeout, long readTimeout, RequestInterceptor requestInterceptor, RestAdapter.LogLevel logLevel) {
        okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(connectTimeout, TimeUnit.MILLISECONDS);
        okHttpClient.setReadTimeout(readTimeout, TimeUnit.MILLISECONDS);

        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setClient(new OkClient(okHttpClient))
                .setEndpoint(endPoint)
                .setLogLevel(logLevel)
                .setLog(new Log());
                //.setConverter(new EncryptedGsonConverter(new Gson()));
        if (requestInterceptor != null) {
            builder.setRequestInterceptor(requestInterceptor);
        }
        return builder.build().create(RestService.class);
    }

    /*private static class EncryptedGsonConverter extends GsonConverter {

        private Gson gson;

        public EncryptedGsonConverter(Gson gson) {
            super(gson);
            this.gson = gson;
        }

        @Override
        public Object fromBody(TypedInput body, Type type) throws ConversionException {
            String charset = "UTF-8";
            if (body.mimeType() != null) {
                charset = MimeUtil.parseCharset(body.mimeType(), "UTF-8");
            }
            InputStreamReader isr = null;
            BufferedReader r = null;
            try {
                isr = new InputStreamReader(body.in(), charset);
                r = new BufferedReader(isr);
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                String resultCode, data = null, timestamp, output = null;
                while ((line = r.readLine()) != null) {
                    stringBuilder.append(line);
                }
                JSONObject jObject = new JSONObject(stringBuilder.toString());
                resultCode = jObject.getString("resultCode");
                timestamp = jObject.getString("timestamp");
                if (!jObject.isNull("data")) {
                    data = SecurityUtils.AESDecrypt(jObject.getString("data"));
                }
                output = "{" + '"' + "resultCode" + '"' + ":" + resultCode + "," + '"' + "timestamp" + '"' + ":" + timestamp + "," + '"' + "data" + '"' + ":" + data + "}";
                Timber.d("retrofit::decrypt:%s", data);
                return gson.fromJson(output, type);
            } catch (JSONException | JsonParseException | IOException e) {
                throw new ConversionException(e);
            } finally {
                if (isr != null) {
                    try {
                        isr.close();
                    } catch (IOException ignored) {
                    }
                }
                if (r != null) {
                    try {
                        r.close();
                    } catch (IOException ignored) {
                    }
                }
            }
        }

        @Override
        public TypedOutput toBody(Object object) {
            return super.toBody(object);
        }
    }*/

    private static class Log implements RestAdapter.Log {

        @Override
        public void log(String msg) {
            Timber.d("retrofit::%s", msg);
        }
    }
}
