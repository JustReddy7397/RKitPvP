package ga.justreddy.wiki.rkitpvp.util;

import com.google.gson.*;
import ga.justreddy.wiki.rkitpvp.RKitPvP;


public class GsonUtil {

    public static <T> T fromJson(String str, Class<T> clazz) {
        return RKitPvP.getInstance().getGson().fromJson(str, clazz);
    }

    public static <T> T fromJson(JsonElement element, Class<T> clazz) {
        return RKitPvP.getInstance().getGson().fromJson(element, clazz);
    }

    public static <T> T fromJson(String str, JsonDeserializer<T> deserializer,  Class<T> clazz) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(clazz, deserializer);
        Gson gson = builder.create();
        return gson.fromJson(str, clazz);
    }

    public static String toJson(Object obj, Class<?> clazz) {
        return RKitPvP.getInstance().getGson().toJson(obj, clazz);
    }

    public static String toJson(Object obj, JsonSerializer<?> serializer, Class<?> clazz) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(clazz, serializer);
        Gson gson = builder.create();
        return gson.toJson(obj, clazz);
    }

}
