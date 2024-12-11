package utils;

import com.google.gson.Gson;

public class PojoJsonParser {
    private static final Gson gson = new Gson();

    // Метод для преобразования JSON в POJO
    public static <T> T parseToPojo(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

    // Метод для преобразования POJO в JSON
    public static String parseToJson(Object object) {
        return gson.toJson(object);
    }
}
