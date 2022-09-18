package Task2;

import java.io.*;
import java.nio.file.Files;

public class Task2 {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = "FileExampleWords.txt";

        try {
            System.out.print("Введите путь к файлу: ");
            String dirInput = reader.readLine();
            System.out.print("Введите слово для поиска: ");
            String wordFindInput = reader.readLine();
            System.out.print("Введите слово для замены: ");
            String wordReplaceInput = reader.readLine();

            File file = new File(dirInput, fileName);
            InputStream inputStream = Files.newInputStream(file.toPath());

            byte[] bufferInput = new byte[inputStream.available()];
            inputStream.read(bufferInput);
            inputStream.close();

            String[] allFile = new String(bufferInput).split(" ");
            OutputStream outputStream = Files.newOutputStream(file.toPath());

            int countWordsReplace = 0;
            for (int i = 0; i < allFile.length; i++) {
                if (allFile[i].equalsIgnoreCase(wordFindInput)) {
                    allFile[i] = wordReplaceInput;
                    countWordsReplace++;
                }
                byte[] bufferOutput = (allFile[i] + " ").getBytes();
                outputStream.write(bufferOutput);
            }

            outputStream.close();
            System.out.println("Кол-во слов \"" + wordFindInput + "\" заменено в файле: " + countWordsReplace + " раз(а)");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
