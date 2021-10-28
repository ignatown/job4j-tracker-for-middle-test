package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindByIdActionTest {
    @Test
    public void execute() {
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        Item item = new Item("Some Item");
        tracker.add(item);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);
        new FindByIdAction(out).execute(input, tracker);
        assertThat(out.toString(), is("=== Find Items by ID ===="
                + System.lineSeparator()
                + item
                + System.lineSeparator()));
    }
}