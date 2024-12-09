package com.klef.jfsd.exam;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.*;

import java.util.List;

public class App {
    public static void main(String[] args) {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();

        Transaction tx = session.beginTransaction();

        // Insert Data
        Customer customer1 = new Customer();
        customer1.setName("Gayatri");
        customer1.setEmail("gaya3@mail.com");
        customer1.setAge(25);
        customer1.setLocation("Vijayawada");

        Customer customer2 = new Customer();
        customer2.setName("meka");
        customer2.setEmail("makagaya3@mail.com");
        customer2.setAge(30);
        customer2.setLocation("vaddeswaram");

        session.save(customer1);
        session.save(customer2);

        tx.commit();

        // Apply Restrictions
        Criteria criteria = session.createCriteria(Customer.class);

        System.out.println("Equal Condition:");
        criteria.add(Restrictions.eq("name", "Gayathri"));
        List<Customer> result1 = criteria.list();
        result1.forEach(c -> System.out.println(c.getName()));

        System.out.println("\nNot Equal Condition:");
        criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.ne("name", "meka"));
        List<Customer> result2 = criteria.list();
        result2.forEach(c -> System.out.println(c.getName()));

        System.out.println("\nLess Than Condition:");
        criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.lt("age", 30));
        List<Customer> result3 = criteria.list();
        result3.forEach(c -> System.out.println(c.getName()));

        System.out.println("\nGreater Than Condition:");
        criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.gt("age", 20));
        List<Customer> result4 = criteria.list();
        result4.forEach(c -> System.out.println(c.getName()));

        System.out.println("\nBetween Condition:");
        criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.between("age", 20, 30));
        List<Customer> result5 = criteria.list();
        result5.forEach(c -> System.out.println(c.getName()));

        System.out.println("\nLike Condition:");
        criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.like("location", "New%"));
        List<Customer> result6 = criteria.list();
        result6.forEach(c -> System.out.println(c.getName()));

        session.close();
        factory.close();
    }
}
