package com.ztx.qa.test;

import com.ztx.qa.learn1.Customer;
import com.ztx.qa.learn1.Order;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

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
        Customer customer = new Customer("Mike", "Mike123@mail.com", 22);
        Customer customer1 = entityManager.merge(customer);
        System.out.println("#customer: " + customer);
        System.out.println("#customer1: " + customer1);
    }

    @Test
    public void testMerge2() {
        Customer customer = new Customer("Mike", "Mike123@mail.com", 21);
        customer.setId(5);
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

    @Test
    public void testQuery() {
        String jpql = "select new Customer(c.lastName, c.email, c.age) from Customer c where c.age > ? order by c.age desc";
        Query query = entityManager.createQuery(jpql);
        query.setParameter(1, 1);
        List<Customer> customers = query.getResultList();
        System.out.println(customers);
    }

    @Test
    public void testNamedQuery() {
        Query query = entityManager.createNamedQuery("jpqlNamedQuery").setParameter(1, 2);
        Customer customer = (Customer) query.getSingleResult();
        System.out.println(customer);
    }

    @Test
    public void testNativeQuery() {
        String jpql = "select LAST_NAME from customers where ID = ?";
        Query query = entityManager.createNativeQuery(jpql).setParameter(1, 1);
        List result = query.getResultList();
        System.out.println("lastName: " + result);
    }

    @Test
    public void testGroupBy() {
        String jpql = "select o.customer from Order o group by o.customer having count(o.id)>1";
        List<Customer> customers = entityManager.createQuery(jpql).getResultList();
        System.out.println(customers);
    }

    @Test
    public void testSubQuery() {
        String jpql = "select upper(o.orderName) from Order o where o.customer = (select c from Customer c where c.age = ?)";
        List<Order> orders = entityManager.createQuery(jpql).setParameter(1, 35).getResultList();
        System.out.println(orders);
    }

    @Test
    public void testUpdate() {
        String jpql = "update Customer c set c.age = ? where c.id = ?";
        Query query = entityManager.createQuery(jpql).setParameter(1, 30).setParameter(2, 2);
        query.executeUpdate();
    }
}
