package ru.job4j.tracker;

import ru.job4j.react.Observe;

import java.util.List;

public interface Store extends AutoCloseable {
    Item add(Item item);

    boolean replace(int id, Item item);

    boolean delete(int id);

    List<Item> findAll();

    List<Item> findByName(String key);

    Item findById(int id);

    void findAllByReact(Observe<Item> observe);
}