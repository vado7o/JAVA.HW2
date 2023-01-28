import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {
    public static void main(String[] args) {
        task1();
//        task2();
    }

    public static void task1() {
        var info = new JSONArray();
        StringBuilder stringBuilder = new StringBuilder();
        Logger logger = Logger.getAnonymousLogger();
        SimpleFormatter formatter = new SimpleFormatter();
        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler("log.txt");
            fileHandler.setFormatter(formatter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.addHandler(fileHandler);

        try  {
            String str = Files.readString(Path.of("text.json"));
            info = new JSONArray(str);
        } catch (Exception e) {
            logger.log(Level.WARNING, e.toString());
            e.printStackTrace();
        }
        for (int i = 0; i <= info.length() - 1; i++) {
            var data = new JSONObject(info.get(i).toString());
            stringBuilder.append("Студент " + data.get("фамилия") + " получил " + data.get("оценка") + " по предмету " + data.get("предмет") + "\n");
        }
        System.out.println(stringBuilder);
        try (FileWriter fileWriter = new FileWriter("result.txt", false)) {
            fileWriter.write(stringBuilder.toString());
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.ALL, e.getMessage());
        }
    }

    public static void task2() {
                List<Integer> list = new ArrayList<>();
        String str = "";
        while (true) {
            System.out.println("\nВведите целое число для занесения его в массив или нажмите Enter для окончания ввода: ");
            Scanner scanner = new Scanner(System.in);
            try {
                str = scanner.nextLine();
                list.add(Integer.parseInt(str));
            } catch (Exception e) {
                if (str == "") break;
                else System.out.println("Вы ввели неправильный формат числа!!!");
            }
        }
        int[] numArray = list.stream().mapToInt(Integer::intValue).toArray();
        System.out.println("Введённый Вами массив: " + list);

        int checkEnd = 0;
        while (checkEnd == 0) {
            checkEnd = 1;
            for (int i = 0; i <= list.size() - 2; i++) {
                if (numArray[i] > numArray[i+1]) {
                    int temp = numArray[i];
                    numArray[i] = numArray[i + 1];
                    numArray[i + 1] = temp;
                    checkEnd = 0;

                    try (FileWriter fileWriter = new FileWriter("logBubble.txt", true)) {
                        fileWriter.write("Очередная итерация: " + Arrays.toString(numArray) + "\n");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        System.out.println("Отсортированный массив имеет вид: " + Arrays.toString(numArray));
    }
}