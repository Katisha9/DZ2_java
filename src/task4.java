//4*.Реализуйте алгоритм сортировки пузырьком числового массива, результат после каждой итерации запишите в лог-файл.

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class task4
{
    public static int[] randomArray() {
        Random rand = new Random();
        int myArr[] = new int[10];
        for (int i = 0; i < myArr.length; i++) {
            myArr[i] = rand.nextInt(100);
            System.out.print(myArr[i] + " ");
        }
        System.out.println("");
        return myArr;
    }

    public static int[] babbleSort(int[] myArr) throws IOException {
        Logger loger = Logger.getLogger(task4.class.getName());
        FileHandler fh = new FileHandler("task4.txt");
        SimpleFormatter sFormatter = new SimpleFormatter();

        fh.setFormatter(sFormatter);
        loger.addHandler(fh);

        int temp;
        for (int i = myArr.length - 1; i >= 0; i--) {
            for (int j = 0; j < myArr.length - 1; j++) {
                if (myArr[j] < myArr[j + 1]) {
                    temp = myArr[j];
                    myArr[j] = myArr[j + 1];
                    myArr[j + 1] = temp;
                }
            }

            loger.info(Arrays.toString(myArr));
        }
        return myArr;

    }

    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void main(String[] args) throws IOException {

        print(babbleSort(randomArray()));
    }

}