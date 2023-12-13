package org.rdtroya.java.jdbc;

import jakarta.persistence.EntityManager;
import org.rdtroya.java.jdbc.entity.Cliente;
import org.rdtroya.java.jdbc.entity.ClienteDetalle;
import org.rdtroya.java.jdbc.util.JpaUtil;

public class HibernateAsociacionesOneToOneFind {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            Cliente cliente = em.find(Cliente.class, 2L);

            ClienteDetalle clienteDetalle = new ClienteDetalle(true, 5000L);
            em.persist(clienteDetalle);

            cliente.setClienteDetalle(clienteDetalle);
            em.getTransaction().commit();
            System.out.println(cliente);
            System.out.println(cliente.getClienteDetalle());

        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
    }
}
