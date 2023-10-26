package ru.naumen.collection.task3;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * <p>Написать консольное приложение, которое принимает на вход произвольный текстовый файл в формате txt.
 * Нужно собрать все встречающийся слова и посчитать для каждого из них количество раз, сколько слово встретилось.
 * Морфологию не учитываем.</p>
 * <p>Вывести на экран наиболее используемые (TOP) 10 слов и наименее используемые (LAST) 10 слов</p>
 * <p>Проверить работу на романе Льва Толстого “Война и мир”</p>
 *
 * @author vpyzhyanov
 * @since 19.10.2023
 */
public class WarAndPeace {
    /**
     * Сложность алгоритма O(n * log(m)), так как в словаре слова идут не сортированно, то вставка элемента в методе addAll
     * занимает O(log n) и общая сложность будет O(n * log(m))
     */

    private static final Path WAR_AND_PEACE_FILE_PATH = Path.of("src/main/resources",
            "Лев_Толстой_Война_и_мир_Том_1,_2,_3,_4_(UTF-8).txt");

    public static void main(String[] args) {
        WordParser wordParser = new WordParser(WAR_AND_PEACE_FILE_PATH);
        Map<String, Long> countWords = new HashMap<>();
        wordParser.forEachWord(word -> {
            if (countWords.containsKey(word)) {
                var tempNumber = countWords.get(word);
                countWords.put(word, tempNumber + 1);
            }
            else {
                countWords.put(word, 1L);
            }
        });
        PriorityQueue<Map.Entry<String, Long>> less10 = new PriorityQueue<>(10, Comparator.comparingLong(Map.Entry::getValue));
        PriorityQueue<Map.Entry<String, Long>> top10 = new PriorityQueue<>(10, (x, y) -> Long.compare(y.getValue(), x.getValue()));

        top10.addAll(countWords.entrySet());
        less10.addAll(countWords.entrySet());
        System.out.println("10 самых частых слов: ");
        for (var i = 0; i < 10; i++) {
            System.out.println(top10.poll());
        }
        System.out.println("10 самых редких слов: ");
        for (var i = 0; i < 10; i++) {
            System.out.println(less10.poll());
        }
    }
}
