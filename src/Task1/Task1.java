package Task1;

import java.io.*;
import java.nio.file.Files;

public class Task1 {
    public static void main(String[] args) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = "FileExampleWords.txt";

        try {
            System.out.print("Введите путь к файлу: ");
            String dirInput = reader.readLine();
            System.out.print("Введите слово для поиска: ");
            String wordFindInput = reader.readLine();

            File file = new File(dirInput, fileName);
            InputStream inputStream = Files.newInputStream(file.toPath());
            byte[] bufferInput = new byte[inputStream.available()];
            inputStream.read(bufferInput);
            inputStream.close();

            int countWords = 0;
            String[] allFile = new String(bufferInput).split(" ");
            for (String str : allFile) {
                if (str.equalsIgnoreCase(wordFindInput)) {
                    countWords++;
                }
            }
            System.out.println("Кол-во слов \"" + wordFindInput + "\" в файле: " + countWords);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
