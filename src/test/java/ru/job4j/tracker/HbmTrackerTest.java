package ru.job4j.tracker;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class HbmTrackerTest {

    @Test
    public void whenAdd() {
        HbmTracker tracker = new HbmTracker();
        Item item = new Item("Item");
        tracker.add(item);
        assertThat(tracker.findAll(), is(List.of(item)));
    }

    @Test
    public void whenReplace() {
        HbmTracker tracker = new HbmTracker();
        Item item = new Item("Item");
        tracker.add(item);
        Item replacedItem = new Item("New item");
        tracker.replace(1, replacedItem);
        assertThat(tracker.findAll().get(0).getName(), is(replacedItem.getName()));
    }

    @Test
    public void whenDelete() {
        HbmTracker tracker = new HbmTracker();
        Item item1 = new Item("Item1");
        Item item2 = new Item("Item2");
        assertThat(tracker.findAll().size(), is(2));
        tracker.add(item1);
        tracker.add(item2);
        tracker.delete(1);
        assertThat(tracker.findAll().size(), is(1));
    }

    @Test
    public void whenFindAll() {
        HbmTracker tracker = new HbmTracker();
        Item item1 = new Item("Item1");
        Item item2 = new Item("Item2");
        tracker.add(item1);
        tracker.add(item2);
        assertThat(tracker.findAll(), is(List.of(item1, item2)));
    }

    @Test
    public void whenFindByName() {
        HbmTracker tracker = new HbmTracker();
        Item item1 = new Item("Item1");
        tracker.add(item1);
        assertThat(tracker.findByName(item1.getName()), is(List.of(item1)));
    }

    @Test
    public void whenFindById() {
        HbmTracker tracker = new HbmTracker();
        Item item1 = new Item("Item1");
        tracker.add(item1);
        assertThat(tracker.findById(1), is(item1));
    }
}