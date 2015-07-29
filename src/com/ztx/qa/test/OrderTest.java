package com.ztx.qa.test;

import com.ztx.qa.learn1.Customer;
import com.ztx.qa.learn1.Order;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Created by s016374 on 15/7/29.
 */
public class OrderTest {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    @Before
    public void setUp() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory("learnJPA");
        entityManager = entityManagerFactory.createEntityManager();
        entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
    }

    @After
    public void tearDown() throws Exception {
        entityTransaction.commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    public void testManyToOne() {
        Customer customer = new Customer("Jack", "Jack@mail.com", 40);
        Order order1 = new Order("Jack_car", customer);
        Order order2 = new Order("Jack_book", customer);
        entityManager.persist(customer);
        entityManager.persist(order1);
        entityManager.persist(order2);
    }

    @Test
    public void testFind() {
        Order order = entityManager.find(Order.class, 3);
        System.out.println(order.getOrderName());
        System.out.println(order.getCustomer().getLastName());
    }

    @Test
    public void testRemove() {
        Order order = entityManager.find(Order.class, 4);
        entityManager.remove(order);
    }

    @Test
    public void testUpdate() {
        Order order = entityManager.find(Order.class, 4);
        order.getCustomer().setAge(35);
    }
}