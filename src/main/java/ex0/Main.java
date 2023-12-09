package ex0;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    /**
     * Метод заполнения списка из файла
     * @param humans список людей
     * @param csvFilePath путь до файла
     * @param separator разделитель
     * @throws RuntimeException не удалось прочесть файл
     */
    public static void input(ArrayList<Person> humans, final String csvFilePath, final char separator) {
        try (InputStream in = Main.class.getClassLoader().getResourceAsStream(csvFilePath);
             CSVReader reader = in == null ? null : new CSVReaderBuilder(new InputStreamReader(in))
                     .withCSVParser(new CSVParserBuilder()
                             .withSeparator(separator)
                             .build()
                     ).build()) {
            if (reader == null) {
                throw new FileNotFoundException(csvFilePath);
            }

            String[] nextLine;
            reader.readNext();
            while ((nextLine = reader.readNext()) != null) {
                humans.addLast(new Person(nextLine));
            }
        } catch (CsvValidationException e) {
            throw new RuntimeException("CSV validation failed: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        ArrayList<Person> humans = new ArrayList<>();
        input(humans, "foreign_names.csv", ';');

        for (Person human: humans) {
            System.out.println(human.toString());
        }

    }
}
