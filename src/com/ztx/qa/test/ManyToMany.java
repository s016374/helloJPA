package com.ztx.qa.test;

import com.ztx.qa.learn1.Category;
import com.ztx.qa.learn1.Item;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Created by s016374 on 15/7/30.
 */
public class ManyToMany {
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
        Category category = new Category("C1");
        Category category1 = new Category("C2");
        Item item = new Item("I1");
        Item item1 = new Item("I2");
        category.getItems().add(item);
        category.getItems().add(item1);
        category1.getItems().add(item);
        category1.getItems().add(item1);
        item.getCategories().add(category);
        item.getCategories().add(category1);
        item1.getCategories().add(category);
        item1.getCategories().add(category1);

        entityManager.persist(category);
        entityManager.persist(category1);
        entityManager.persist(item);
        entityManager.persist(item1);
    }

    @Test
    public void testFind() {
        Category category = entityManager.find(Category.class, 1);
        Item item = entityManager.find(Item.class, 1);
        System.out.println("CategoryName: " + category.getCategoryName());
        System.out.println("CategoryClass: " + category.getItems().getClass());
        System.out.println("ItemName: " + item.getItemName());
        System.out.println("ItemClass: " + item.getCategories().getClass());
    }
}