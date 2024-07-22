1. Создай файл со строками вида (x:1,y:5)-(x:2,y:6), описывающими отрезки на плоскости.
Можно использовать другой формат.

2. Прочитай файл.
3. Преврати его в поток строк.
4. Найди в нем длину самого длинного отрезка одной конструкцией на потоках.
5. Выведи длину в поток вывода.



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
public static void main(String[] args) {
try {
Optional<Integer> max = new BufferedReader(new FileReader("data.txt")).lines()
.map(Integer::parseInt)
.max(Integer::compare);
System.out.println(max.orElseGet(() -> 0));
} catch (IOException e){
throw new RuntimeException(e);
}
}
}






import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Main {
public static void main(String[] args) {

        try (Stream<String> formulas = Files.lines(Paths.get("data.txt"))) {
            double maxLeng = formulas.map(line -> line.split("\\)-\\(")) // Это страшное нечто разделит строку на координаты до скобки и после
                    .mapToDouble(points -> {
                        // Парсирование первого (начального) значения
                        String[] point1 = points[0].replace("(x:", "").replace("y:", "").split(",");
                        double x1 = Double.parseDouble(point1[0]);
                        double y1 = Double.parseDouble(point1[1]);
                        // Парсирование второго (конечного) значения
                        String[] point2 = points[1].replace("x:", "").replace("y:", "").replace(")", "").split(",");
                        double x2 = Double.parseDouble(point2[0]);
                        double y2 = Double.parseDouble(point2[1]);

                        // Calculate the distance between the two points
                        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
                    })
                    .max().orElse(0); // Find the maximum length or Handle the case where there might be no lines

            System.out.println("Max length: " + maxLeng);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}