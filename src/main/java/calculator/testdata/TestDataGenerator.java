package calculator.testdata;

import java.time.Instant;

public class TestDataGenerator {
    //Текущая дата и время в формате 2024-12-04T08:30:35.635Z
    public static String getCurrentDateTimeISO() {
        return Instant.now().toString();
    }

    //Генерация уникального calculation_id на основе текущей даты-времени
    public static String generateId() {
        long currentTimeMillis = Instant.now().toEpochMilli();  // Получаем текущие миллисекунды
        return "id_" + currentTimeMillis;  // Преобразуем в строку с префиксом
    }






}
