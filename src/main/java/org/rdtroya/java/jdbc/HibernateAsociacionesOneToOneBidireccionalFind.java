package org.rdtroya.java.jdbc;

import jakarta.persistence.EntityManager;
import org.rdtroya.java.jdbc.entity.Cliente;
import org.rdtroya.java.jdbc.entity.ClienteDetalle;
import org.rdtroya.java.jdbc.util.JpaUtil;

public class HibernateAsociacionesOneToOneBidireccionalFind {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            Cliente cliente = em.find(Cliente.class, 2L);

            ClienteDetalle detalle = new ClienteDetalle(true, 8000L);

            cliente.addClienteDetalle(detalle);


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
