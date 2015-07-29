package com.ztx.qa.learn1;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Created by s016374 on 15/7/28.
 */
public class Main {
    public static void main(String args[]) {
        String persistenceUnitName = "learnJPA";
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Customer customer = new Customer("Tom", "Tom123@google.com", 30);
        entityManager.persist(customer);

        entityTransaction.commit();

        entityManager.close();

        entityManagerFactory.close();
    }
}
