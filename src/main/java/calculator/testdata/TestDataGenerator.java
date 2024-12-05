package calculator.testdata;

import calculator.model.calculation.Calculation;
import calculator.model.calculation.SpecialTerms;

import java.time.Instant;

public class TestDataGenerator {
    //Текущая дата и время в формате 2024-12-04T08:30:35.635Z
    public String getCurrentDateTimeISO() {
        return Instant.now().toString();
    }

    //Генерация уникального calculation_id на основе текущей даты-времени
    public static String generateCalculationId() {
        long currentTimeMillis = Instant.now().toEpochMilli();  // Получаем текущие миллисекунды
        return "calcID" + currentTimeMillis;  // Преобразуем в строку с префиксом
    }






}
