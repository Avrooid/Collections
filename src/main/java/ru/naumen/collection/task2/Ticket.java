package ru.naumen.collection.task2;

import java.util.Objects;

/**
 * Билет
 *
 * @author vpyzhyanov
 * @since 19.10.2023
 */
public class Ticket {
    private long id;
    private String client;

    public Ticket(long id, String client) {
        this.id = id;
        this.client = client;
    }

    public long getId() {
        return id;
    }

    public String getClient() {
        return client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id && Objects.equals(client, ticket.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client);
    }
}
