/*
 * Copyright (c) 2019 Tanuyoki.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.yoki.library.okhttp;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import cn.yoki.library.okhttp.listener.DisposeDataListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpClient {

    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    private static final Handler handler = new Handler(Looper.getMainLooper());

    public static void get(String url, DisposeDataListener listener) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        new OkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                handler.post(() -> {
                    if (listener != null) {
                        listener.onFailed(call, e);
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String result = response.body().string();

                handler.post(() -> {
                    if (listener != null) {
                        listener.onSuccess(result);
                    }
                });
            }
        });

    }

    public static void post(String url, JSONObject json, DisposeDataListener listener) {
        if (TextUtils.isEmpty(url) || json == null) {
            return;
        }

        RequestBody body = RequestBody.create(json.toJSONString(), JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        new OkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                handler.post(() -> {
                    if (listener != null) {
                        listener.onFailed(call, e);
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                handleResponse(response.body().string(), listener);
            }
        });

    }

    private static void handleResponse(String response, DisposeDataListener listener) {
        JSONObject jsonObject = JSONObject.parseObject(response);
        
        handler.post(() -> {
            if (listener != null) {
                listener.onSuccess(response);
            }
        });
    }

}
