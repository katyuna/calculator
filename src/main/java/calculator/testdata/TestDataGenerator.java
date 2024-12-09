package calculator.testdata;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class TestDataGenerator {
    // Текущая дата и время в формате 2024-12-04T08:30:35.635Z
    public static String getCurrentDateTimeISO() {
        Instant now = Instant.now(); // Получаем текущее время в UTC
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .withZone(ZoneOffset.UTC); // Устанавливаем временную зону UTC
        return formatter.format(now); // Возвращаем строку в нужном формате
    }
    //Генерация уникального id на основе текущей даты-времени
    public static String generateId() {
        long currentTimeMillis = Instant.now().toEpochMilli();  // Получаем текущие миллисекунды
        return "id_" + currentTimeMillis;  // Преобразуем в строку с префиксом
    }
}
