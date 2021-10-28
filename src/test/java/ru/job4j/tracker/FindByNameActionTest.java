package ru.job4j.tracker;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindByNameActionTest {
    @Test
    public void execute() {
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        Item item = new Item("Some Item");
        tracker.add(item);
        Input input = mock(Input.class);
        when(input.askStr(any(String.class))).thenReturn(item.getName());
        new FindByNameAction(out).execute(input, tracker);
        assertThat(out.toString(), is("=== Find Items by name ===="
                + System.lineSeparator()
                + List.of(item).stream().findFirst().get()
                + System.lineSeparator()));
    }
}