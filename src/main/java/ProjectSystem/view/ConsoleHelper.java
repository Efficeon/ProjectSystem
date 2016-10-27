package ProjectSystem.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    public static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static String readString() throws IOException {
        return bufferedReader.readLine();
    }

    public static int readInt() throws IOException {
        int number = 0;
        try {
            number = Integer.parseInt(bufferedReader.readLine());
        }catch (NumberFormatException e){
            ConsoleHelper.writeMessage("Введены некорректные данные. Повторите ввод.");
            readInt();
        }
        return number;
    }
}
