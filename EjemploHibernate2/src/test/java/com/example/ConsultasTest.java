package com.example;

import com.example.model.Address;
import com.example.model.Author;
import com.example.model.Category;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;

import com.example.model.Book;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

public class ConsultasTest {


    @Test
    public void consultaVarios() {
        //insertData();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            List libros = session.createQuery("FROM Book").list();

            Iterator iterator = libros.iterator();
            while (iterator.hasNext()){
                Book libro = (Book) iterator.next();
                System.out.println(libro);
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
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

        Book book1 = new Book("Las obras de Boscán", 19.90, 450, true, author);
        Book book2 = new Book("Las obras de Roldán", 29.90, 157, false, author);
        Book book3 = new Book("Las obras de Gutierrez", 39.90, 123, true, author2);
        Book book4 = new Book("Las obras de Sánchez", 9.90, 987, false, author2);

        Category cat1 = new Category("CF",8);
        Category cat2 = new Category("Mistery",16);
        Category cat3 = new Category("Horror",18);
        Category cat4 = new Category("Infantil",0);

        session.persist(cat1);
        session.persist(cat2);
        session.persist(cat3);
        session.persist(cat4);

        book1.getCategories().add(cat1);
        book1.getCategories().add(cat2);

        book2.getCategories().add(cat1);
        book2.getCategories().add(cat3);

        book3.getCategories().add(cat1);


        session.persist(book1);
        session.persist(book2);
        session.persist(book3);
        session.persist(book4);

        session.getTransaction().commit();
    }
}

