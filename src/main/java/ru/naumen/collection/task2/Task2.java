package ru.naumen.collection.task2;

import java.util.*;

/**
 * Дано:
 * <pre>
 * public class Ticket {
 *     private long id;
 *     private String client;
 *     …
 * }</pre>
 * <p>Разработать программу для бармена в холле огромного концертного зала.
 * Зрители в кассе покупают билет (класс Ticket), на котором указан идентификатор билета (id) и имя зрителя.
 * При этом, есть возможность докупить наборы разных товаров (комбо-обед): нет товаров, напитки, еда и напитки.
 * Доп. услуги оформляются через интернет и привязываются к билету, но хранятся отдельно от билета
 * (нельзя добавить товары в класс Ticket).</p>
 * <p>Бармен сканирует билет и получает объект Ticket. По этому объекту нужно уметь
 * находить необходимые товары по номеру билета. И делать это нужно очень быстро,
 * ведь нужно как можно быстрее всех накормить.</p>
 * <p>
 * См. {@link Ticket}
 *
 * @author vpyzhyanov
 * @since 19.10.2023
 */
public class Task2 {
    List<Ticket> tickets = new ArrayList<>();

    /**
     * Обращение к значениям словаря происходит за O(1), в классе Ticket переопределил методы equals and hashcode
     * для лучшей работы Map
     */
    Map<Ticket, FOOD> map = new HashMap<>();

    /**
     * Сложность программы O(n), так как все 3 метода в main линейные
     */
    public static void main(String[] args) {
        Task2 task2 = new Task2();
        task2.init(20);
        task2.addFood();
        task2.ticketsScan();
    }

    private void addFood() {
        FOOD[] foodArray = FOOD.values();
        for (var i = 0; i < tickets.size(); i++) {
            map.put(tickets.get(i), foodArray[i % foodArray.length]);
        }
    }

    private void init(int count) {
        for (var i = 0; i < count; i++)
            tickets.add(new Ticket(i, i + " client"));
    }

    private void ticketsScan() {
        for (Ticket ticket : tickets) {
            System.out.println(ticket.getClient() + " with id " + ticket.getId() + " have: " + map.get(ticket).toString());
        }
    }
}

