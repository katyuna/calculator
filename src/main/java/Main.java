import calculator.testdata.TestDataGenerator;

import static utils.PojoJsonParser.parseToPojo;

public class Main {
    public static void main(String[] args) {
        TestDataGenerator testDataGenerator = new TestDataGenerator();

        System.out.println(testDataGenerator.getCurrentDateTimeISO());



    }
}