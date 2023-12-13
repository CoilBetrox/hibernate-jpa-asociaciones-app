package org.rdtroya.java.jdbc;

import jakarta.persistence.EntityManager;
import org.rdtroya.java.jdbc.entity.Cliente;
import org.rdtroya.java.jdbc.entity.Factura;
import org.rdtroya.java.jdbc.util.JpaUtil;

public class HibernateAsociacionesManytoOneFind {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        try {

            em.getTransaction().begin();
            Cliente cliente = em.find(Cliente.class, 1L);

            Factura factura = new Factura("compras oficinas", 1000L);
            factura.setCliente(cliente);
            em.persist(factura);
            System.out.println(factura.getCliente());
            em.getTransaction().commit();
        }catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
    }
}
