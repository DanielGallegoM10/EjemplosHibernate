package com.example;

import com.example.model.Address;
import com.example.model.Author;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class OneToOneTest {


    @Test
    void oneToOne() {

        insertData();
        Session session = HibernateUtil.getSessionFactory().openSession(); //Se puede sustituir Session y Author por "var"
//        var session = HibernateUtil.getSessionFactory().openSession();

        Author author1 = session.find(Author.class, 2L);
//        var author1 = session.find(Author.class, 2L);

        System.out.println(author1);
    }

    void insertData() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        Address address = new Address("Calle Mayor, 1","Pamplona","España");
        Address address2 = new Address("Calle Mayor, 2","Madrid","España");

        Author author = new Author(
                "Garcilaso",
                "garcilaso@poeta.com",
                LocalDate.of(1536,10,14)
        );
        author.setAddress(address);

        Author author2 = new Author(
                "Petrarca",
                "petrarca@poeta.com",
                LocalDate.of(1304,7,19)
        );
        author2.setAddress(address2);

        session.persist(address);
        session.persist(address2);

        session.persist(author);
        session.persist(author2);

        session.getTransaction().commit();
    }
}
