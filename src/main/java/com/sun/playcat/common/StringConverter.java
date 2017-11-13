package com.sun.playcat.common;

/**
 * Created by sunlin on 2017/11/6.
 */

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * 实现了 序列化 接口    对为null的字段进行转换
 */
public  class StringConverter implements JsonSerializer<String>, JsonDeserializer<String> {
    //字符串为null 转换成"",否则为字符串类型
    @Override
    public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return json.getAsJsonPrimitive().getAsString();
    }

    @Override
    public JsonElement serialize(String src, Type typeOfSrc, JsonSerializationContext context) {
        Log.debug(src);
        return src == null || src.equals("null") ? new JsonPrimitive("") : new JsonPrimitive(src.toString());
    }
}
