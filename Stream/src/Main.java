/* Порядок действия: Files.lines использую для чтения файла;
С помощью "\\)-\\(" разделяю выражения на координаты начальной и конечной точки на плоскости;
coordPoints содержит значения x и y со значениями (x:1,y:5. Надо разделить и запихать в массив;
.replace отделяет символы x и y, оставляя только цифры, а .split ставит между ними запятую (делит на массив);
Парсирую int в double для 1 -> 1.0 и т.д.;
С помощью класса Math составляю формулу расчета длины. С помощью метода .pow, который принимает на вход два значения
и возводит в степень 2 я делаю две с соответствующим выражением и ставлю вычитание;
.max находит самое большое значение в потоке Stream;
Вывод с помощью sout.
А еще я сделала так чтобы выводилось два значения после запятой потому что числа иногда длинные выходили ну это просто интересно стало.
*/



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        try (Stream<String> coords = Files.lines(Paths.get("data.txt"))) {
            double maxLeng = coords.map(line -> line.split("\\)-\\(")).mapToDouble(coordPoints -> {

                        String[] coord1 = coordPoints[0].replace("(x:", "").replace("y:", "").split(",");
                        double x1 = Double.parseDouble(coord1[0]);
                        double y1 = Double.parseDouble(coord1[1]);
                        String[] coord2 = coordPoints[1].replace("x:", "").replace("y:", "").replace(")", "").split(",");
                        double x2 = Double.parseDouble(coord2[0]);
                        double y2 = Double.parseDouble(coord2[1]);

                        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
                    })
                    .max().orElse(0);

            System.out.println("Max length: " + String.format("%.2f", maxLeng));
        } catch (IOException e) {
            throw new RuntimeException(e); // На всякий пожарный
        }
    }
}