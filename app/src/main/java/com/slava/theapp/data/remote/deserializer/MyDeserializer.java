package com.slava.theapp.data.remote.deserializer;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import com.slava.theapp.data.remote.responses.ServerResponse;
import com.slava.theapp.model.error.ApiError;
import com.slava.theapp.util.LogUtil;

import java.lang.reflect.Type;

public class MyDeserializer implements JsonDeserializer<ServerResponse> {

/*
    private final Class<T> clazz;

    public MyDeserializer(Class<T> clazz) {
        this.clazz = clazz;
    }*/

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public ServerResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        LogUtil.info(this,"typeofT: "+typeOfT);

        final JsonObject jsonObject = json.getAsJsonObject();
       /* if (jsonObject.get("error") != null) {
            ServerResponse serverResponse = new ServerResponse();
            serverResponse.setError(gson.fromJson(jsonObject, typeOfT));
            return serverResponse;
        }*/

        LogUtil.info(this, "json: "+jsonObject.toString());
        ServerResponse serverResponse = gson.fromJson(json, typeOfT);
        LogUtil.info(this, "fromJson: " + gson.fromJson(json, typeOfT));

        return serverResponse;
    }
}
