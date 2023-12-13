package org.rdtroya.java.jdbc;

import jakarta.persistence.EntityManager;
import org.rdtroya.java.jdbc.entity.Cliente;
import org.rdtroya.java.jdbc.entity.Factura;
import org.rdtroya.java.jdbc.util.JpaUtil;

public class HibernateAsocciacionesOneToManyBidireccional {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            Cliente cliente = new Cliente("Cata", "Edu");
            cliente.setFormaPago("paypal");

            Factura factura1 = new Factura("compra supermercado", 5000L);
            Factura factura2 = new Factura("compra tecnologia", 4000L);

            cliente.addFactura(factura1).addFactura(factura2);

            em.persist(cliente);

            em.getTransaction().commit();
            System.out.println(cliente);

            em.getTransaction().begin();
            Factura factura3 = em.find(Factura.class, 1L);
            cliente.removeFactura(factura3);
            em.getTransaction().commit();
            System.out.println(cliente);
        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
    }
}
