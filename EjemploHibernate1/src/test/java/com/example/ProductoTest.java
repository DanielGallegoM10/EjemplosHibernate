package com.example;

import com.example.model.Employee;
import com.example.model.Producto;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

/*
CRUD:
- Create
- Retrieve
- Update
- Delete
 */
public class ProductoTest {

    @Test
    void persist() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction tx = session.beginTransaction();

        Producto producto1 = new Producto("producto1", 5, BigDecimal.valueOf(30.00), LocalDate.parse("2025-01-20"), "Descripcion del producto 1");
        Producto producto2 = new Producto("producto2", 10, BigDecimal.valueOf(20.50), LocalDate.parse("2025-01-30"), "Descripcion del producto 2");

        session.persist(producto1);
        session.persist(producto2);

        tx.commit();

        session.close();
    }

    @Test
    void retrieve() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction tx = session.beginTransaction();

        Producto producto1 = new Producto("producto1", 5, BigDecimal.valueOf(30.00), LocalDate.parse("2025-01-20"), "Descripcion del producto 1");
        Producto producto2 = new Producto("producto2", 10, BigDecimal.valueOf(20.50), LocalDate.parse("2025-01-30"), "Descripcion del producto 2");

        session.persist(producto1);
        session.persist(producto2);

        tx.commit();

        System.out.println("Producto creado: " + producto1);
        Producto producto1FromDb = session.find(Producto.class, producto1.getId());

        System.out.println(producto1FromDb);

    }


    @Test
    void update() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Producto pr1 = new Producto("producto1", 5, BigDecimal.valueOf(30.00), LocalDate.parse("2025-01-20"), "Descripcion del producto 1");
        pr1.setId(3L);
        pr1.setCantidad(8);

        Transaction tx = session.beginTransaction();
        session.merge(pr1);
        tx.commit();

        System.out.println(pr1);

    }

    @Test
    void delete() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Producto pr1 = new Producto("producto1", 5, BigDecimal.valueOf(30.00), LocalDate.parse("2025-01-20"), "Descripcion del producto 1");

        session.persist(pr1);

        System.out.println("Producto creado para eliminar: " + pr1);
        session.remove(pr1);

        tx.commit();
    }
}
