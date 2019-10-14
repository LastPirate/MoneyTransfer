package com.hometask.moneytransfer.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ResponseTransformer;

public class JsonUtil {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static String toJson(Object object) {
        return GSON.toJson(object);
    }

    public static ResponseTransformer json() {
        return JsonUtil::toJson;
    }
}
