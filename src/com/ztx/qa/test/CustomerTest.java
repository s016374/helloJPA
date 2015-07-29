package com.ztx.qa.test;

import com.ztx.qa.learn1.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Created by s016374 on 15/7/28.
 */
public class CustomerTest {
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
    public void testFind() {
        System.out.println("$$$$");
        Customer customer = entityManager.find(Customer.class, 1);
        System.out.println("####");
        System.out.println(customer);
    }

    @Test
    public void testGetReference() {
        System.out.println("$$$$");
        Customer customer = entityManager.getReference(Customer.class, 1);
        System.out.println("####");
        System.out.println(customer);
    }

    @Test
    public void testPersistence() {
        Customer customer = new Customer("Mary", "Mary321@mail.com", 25);
        entityManager.persist(customer);
        System.out.println("####" + customer.getId());
    }

    @Test
    public void testRemove() {
        Customer customer = entityManager.find(Customer.class, 4);
        entityManager.remove(customer);
    }

    @Test
    public void testMerge1() {
        Customer customer = new Customer("Mike", "Mike123@mail.com", 20);
        Customer customer1 = entityManager.merge(customer);
        System.out.println("#customer: " + customer);
        System.out.println("#customer1: " + customer1);
    }

    @Test
    public void testMerge2() {
        Customer customer = new Customer("Mike", "Mike123@mail.com", 20);
        customer.setId(10);
        Customer customer1 = entityManager.merge(customer);
        System.out.println("#customer: " + customer);
        System.out.println("#customer1: " + customer1);
    }

    @Test
    public void testMerge3() {
        Customer customer = new Customer("Jane", "Jane321@mail.com", 15);
        customer.setId(6);
        Customer customer1 = entityManager.merge(customer);
        System.out.println("####");
        System.out.println(customer == customer1);
    }

    @Test
    public void testFlush() {
        Customer customer = entityManager.find(Customer.class, 1);
        customer.setAge(30);
        entityManager.flush();
        System.out.println("####");
        System.out.println("Age: " + customer.getAge());
    }
}