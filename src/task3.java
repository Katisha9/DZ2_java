//3*. Получить исходную json строку из файла, используя FileReader или Scanner
import java.io.*;

public class task3 {
    public static void main(String[] args) throws Exception {
        String strBuilderStart = "Студент ";
        String jStringPars = getString("task3.jsn");
        System.out.println(jStringPars);
        jStringPars = jStringPars.substring(1, jStringPars.length() - 1);
        jStringPars = jStringPars.replaceAll("[\"\\s{]", "");
        jStringPars = jStringPars.replaceAll("},", "#");
        jStringPars = jStringPars.replaceAll("}", "");
        String[] lines = jStringPars.split("#");
        for (String line : lines) {
            String answerBuilder = new StringBuilder(strBuilderStart).append(makeResult(line)).toString();
            System.out.println(answerBuilder);
        }
    }

    public static String getString(String fileName) {
        String jString = null;
        try {
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            jString = reader.readLine();

            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jString;
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


