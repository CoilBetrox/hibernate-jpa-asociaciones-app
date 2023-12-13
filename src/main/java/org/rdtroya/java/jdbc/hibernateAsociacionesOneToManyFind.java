package org.rdtroya.java.jdbc;

import jakarta.persistence.EntityManager;
import org.rdtroya.java.jdbc.entity.Cliente;
import org.rdtroya.java.jdbc.entity.Direccion;
import org.rdtroya.java.jdbc.util.JpaUtil;

public class hibernateAsociacionesOneToManyFind {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Cliente cliente = em.find(Cliente.class, 2L);

            Direccion direccion1 = new Direccion("dirxd", 123);
            Direccion direccion2 = new Direccion("la casita 2", 456);

            cliente.getDirecciones().add(direccion1);
            cliente.getDirecciones().add(direccion2);
            em.merge(cliente);

            em.getTransaction().commit();

            System.out.println(cliente);

            //eliminamos una direccion
            em.getTransaction().begin();
            direccion1 = em.find(Direccion.class, 1L);
            cliente.getDirecciones().remove(direccion1);
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
