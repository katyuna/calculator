package utils;

import com.google.gson.*;

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

    //Парсинг без создания POJO, библиотека gson

    public static Object parseJson (String json) {
        //Если передаем null - выкинуть Exception
        if (json == null || json.trim().isEmpty()) {
            throw new IllegalArgumentException("JSON string cannot be null or empty");
        }
        try {
            //Т.к. в ответе приходим массив, то используем JsonElement
            JsonElement element = JsonParser.parseString(json);
            if (element.isJsonObject()) {
                return element.getAsJsonObject(); // Вернуть JsonObject
            } else if (element.isJsonArray()) {
                return element.getAsJsonArray(); // Вернуть JsonArray
            } else {
                throw new IllegalArgumentException("Invalid JSON: Not an object or array");
            }
        } catch (JsonSyntaxException e) {
            throw new IllegalArgumentException("Invalid JSON string", e);
        }
    }
}
