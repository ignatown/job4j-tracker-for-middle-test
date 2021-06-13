package ru.job4j.search;

import org.junit.Test;

import java.util.ArrayList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PhoneDictionaryTest {
    @Test
    public void whenFindByName() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        ArrayList<Person> persons = phones.find("Petr");
        assertThat(persons.get(0).getSurname(), is("Arsentev"));
    }

    @Test
    public void whenFindByNumber() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        phones.add(
                new Person("Ivan", "Ivanovich", "124872", "Rostov")
        );
        ArrayList<Person> persons = phones.find("487");
        assertThat(persons.size(), is(2));
    }

    @Test
    public void whenFindEmptyNumber() {
        PhoneDictionary phones = new PhoneDictionary();
        ArrayList<Person> persons = phones.find("487");
        assertThat(persons.size(), is(0));
    }
}