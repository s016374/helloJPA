package com.ztx.qa.test;

import com.ztx.qa.learn1.Department;
import com.ztx.qa.learn1.Manager;
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
public class OneToOneTest {
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
    public void testPersistence() {
        Manager manager = new Manager("mgr-A");
        Department department = new Department("dep-A");
        manager.setDepartment(department);
        department.setManager(manager);

        entityManager.persist(manager);
        entityManager.persist(department);
    }

    @Test
    public void testFind() {
        Department department = entityManager.find(Department.class, 1);
        System.out.println(department.getDeptName());
        System.out.println(department.getManager().getClass().getName());
    }
}