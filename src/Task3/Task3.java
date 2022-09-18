package Task3;

import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

public class Task3 {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = "FileExampleWords.txt";

        try {
            System.out.print("Введите путь к файлу: ");
            String dirInput = reader.readLine();

            HashMap<String, Integer> wordDeleteInput = new HashMap<>();
            System.out.println("Введите запрещенные слова(\".\" - остановка ввода): ");

            String s;
            while (!(s = reader.readLine()).equals(".")) {
                wordDeleteInput.put(s, 0);
            }

            File file = new File(dirInput, fileName);
            InputStream inputStream = Files.newInputStream(file.toPath());


            byte[] bufferInput = new byte[inputStream.available()];
            inputStream.read(bufferInput);
            inputStream.close();

            String[] allFile = new String(bufferInput).split(" ");
            OutputStream outputStream = Files.newOutputStream(file.toPath());

            for (int i = 0; i < allFile.length; i++) {
                AtomicReference<String> str = new AtomicReference<>(allFile[i]);

                wordDeleteInput.entrySet().forEach((key) -> {
                    if (key.getKey().equalsIgnoreCase(str.get())) {
                        key.setValue(key.getValue() + 1);
                        str.set("");
                    }
                });

                if (str.get().equals("")) {
                    byte[] bufferOutput = (str.get()).getBytes();
                    outputStream.write(bufferOutput);
                } else {
                    byte[] bufferOutput = (str.get() + " ").getBytes();
                    outputStream.write(bufferOutput);
                }
            }

            outputStream.close();
            wordDeleteInput.forEach((k,v) -> System.out.println("Запрещенное слово - " + k + "; Удалили: " + v + " раз(а)."));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
