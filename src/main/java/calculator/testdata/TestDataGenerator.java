package calculator.testdata;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class TestDataGenerator {
    // Текущая дата и время в формате yyyy-MM-dd'T'HH:mm:ss.SSS'Z'
    public static String getCurrentDateTimeISO() {
        Instant now = Instant.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .withZone(ZoneOffset.UTC);
        return formatter.format(now);
    }

    // Текущая дата и время в формате yyyy-MM-dd
    public static String getCurrentDate() {
        LocalDate currentDate = LocalDate.now(ZoneOffset.UTC);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return currentDate.format(formatter);
    }

    // Генерация уникального id на основе текущей даты-времени
    public static String generateId() {
        long currentTimeMillis = Instant.now().toEpochMilli();
        return "id_" + currentTimeMillis;
    }

    // Расчет количества дней займа. Минимальный срок 90 дней. Последняя выплата должна приходится на понедельник.
    public static int getTotalDaysUntilMonday(int baseDays) {
        // Текущая дата
        LocalDate currentDate = LocalDate.now();

        // Дата через baseDays (например, 90 дней)
        LocalDate targetDate = currentDate.plusDays(baseDays);

        // День недели этой даты
        DayOfWeek dayOfWeek = targetDate.getDayOfWeek();

        // Рассчитать, сколько дней до ближайшего понедельника
        int daysToMonday = (DayOfWeek.MONDAY.getValue() - dayOfWeek.getValue() + 7) % 7;

        // Вернуть общее количество дней
        return baseDays + daysToMonday;
    }


}
