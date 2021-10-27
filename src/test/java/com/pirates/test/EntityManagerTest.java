package com.pirates.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

@SpringBootTest
public class EntityManagerTest {

    @Autowired
    EntityManager em;

    @Test
    public void test(){
        em.createQuery("select min(o.price) from Option o where o.product.id = 1L").getSingleResult();
    }
}
