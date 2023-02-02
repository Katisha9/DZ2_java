//2. Создать метод, который запишет результат работы в файл
// Обработайте исключения и запишите ошибки в лог файл

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class task2 {
    public static void main(String[] args) {
        Logger logger = Logger.getAnonymousLogger();
        logger.log(Level.INFO, "Все работает");
        SimpleFormatter formatter = new SimpleFormatter();
        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler("log.txt");
            fileHandler.setFormatter(formatter);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            fileHandler.close();
        }
        logger.addHandler(fileHandler);

        String strBuilderStart = "Студент ";
        String answerBuilder = null;
        String jStringPars = "[{\"фамилия\":\"Иванов\",\"оценка\":\"5\",\"предмет\":\"Математика\"}," +
                "{\"фамилия\":\"Петрова\",\"оценка\":\"4\",\"предмет\":\"Информатика\"}" +
                ",{\"фамилия\":\"Краснов\",\"оценка\":\"5\",\"предмет\":\"Физика\"}]";
        jStringPars = jStringPars.substring(1, jStringPars.length() - 1);
        jStringPars = jStringPars.replaceAll("[\"\\s{]", "");
        jStringPars = jStringPars.replaceAll("},", "#");
        jStringPars = jStringPars.replaceAll("}", "");
        String[] lines = jStringPars.split("#");
        for (String line : lines) {

            answerBuilder = new StringBuilder(strBuilderStart).append(makeResult(line)).toString();
            System.out.println(answerBuilder);
            try (FileWriter fileWriter = new FileWriter("task2.txt", true)) {
                fileWriter.write(answerBuilder);
                fileWriter.write("\n");
            } catch (Exception e) {
                logger.log(Level.WARNING, e.getMessage());
                e.printStackTrace();
            }
        }
    }

        public static String makeResult(String myStr) {
        String[] myStrArr = myStr.split(",");
        StringBuilder sb = new StringBuilder();

        for (String line : myStrArr) {
            String[] keyValue = line.split(":");
            if (keyValue[0].equals("фамилия")) {
                sb.append(keyValue[1]);
                sb.append(" получил ");
            }
            if (keyValue[0].equals("оценка")) {
                sb.append("оценку ");
                sb.append(keyValue[1]);
            }
            if (keyValue[0].equals("предмет")) {
                sb.append(" по предмету ");
                sb.append(keyValue[1]);
                sb.append(". ");
            }
        }
        return sb.toString();
    }
}