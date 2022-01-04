package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.react.Observe;

import java.util.List;

public class HbmTracker implements Store, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public Item add(Item item) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.close();
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        Session session = sf.openSession();
        session.beginTransaction();
        boolean rsl = session.createQuery("update Item set name = :name,"
                + " description = :description where id = :id")
                .setParameter("name", item.getName())
                .setParameter("description", item.getDescription())
                .setParameter("id", item.getId())
                .executeUpdate() != 0;

        session.getTransaction().commit();
        session.close();
        return rsl;
    }

    @Override
    public boolean delete(int id) {
        Item item = findById(id);
        if (item == null) {
            return false;
        }
        Session session = sf.openSession();
        session.beginTransaction();
        boolean rsl = session.createQuery("delete Item where id = :id")
                .setParameter("id", id)
                .executeUpdate() != 0;
        session.getTransaction().commit();
        session.close();
        return rsl;
    }

    @Override
    public List<Item> findAll() {
        Session session = sf.openSession();
        session.beginTransaction();
        List result = session.createQuery("from ru.job4j.tracker.Item").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public List<Item> findByName(String key) {
        Session session = sf.openSession();
        session.beginTransaction();
        List result = session.createQuery("from ru.job4j.tracker.Item where name= :name").
                setParameter("name", key).list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public Item findById(int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        Item result = session.get(Item.class, id);
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }

    @Override
    public void findAllByReact(Observe<Item> observe) {
        Session session = sf.openSession();
        session.beginTransaction();
        List<Item> result = (List<Item>) session.createQuery("from ru.job4j.tracker.Item").list();
        for (Item item : result) {
            observe.receive(item);
        }
        session.getTransaction().commit();
        session.close();
    }
}