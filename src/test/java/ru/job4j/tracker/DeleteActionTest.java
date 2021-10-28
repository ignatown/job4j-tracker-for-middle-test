package ru.job4j.tracker;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DeleteActionTest {
    @Test
    public void execute() {
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        Item item = new Item("Some item");
        tracker.add(item);
        assertThat(tracker.findAll().get(0).getName(), is(item.getName()));
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);
        assertThat(tracker.findAll(), is(List.of(item)));
        new DeleteAction(out).execute(input, tracker);
        assertThat(out.toString(), is("=== Delete Items by ID ===="
                + System.lineSeparator()
                + "Item deleted"
                + System.lineSeparator()));
        assertThat(tracker.findAll(), is(List.of()));
    }
}