package com.example.esgindicatorservice.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import java.io.IOException;
import java.io.StringReader;

public class GsonUtil {

    public static String toJSON(Object obj) {
        Gson gson = new Gson();
        GsonBuilder gsb = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting();
        JsonObject jo = gson.toJsonTree(obj).getAsJsonObject();
        return gsb.create().toJson(jo);
    }
    public static boolean isJsonArray(String json) throws IOException {
        JsonReader jsonReader = new JsonReader(new StringReader(json));
        JsonToken jsonToken = jsonReader.peek();
        if (jsonToken == JsonToken.BEGIN_ARRAY) {
            return true;
        } else {
            return false;
        }
    }
}
