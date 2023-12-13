package org.rdtroya.java.jdbc;

import jakarta.persistence.EntityManager;
import org.rdtroya.java.jdbc.entity.Cliente;
import org.rdtroya.java.jdbc.entity.Direccion;
import org.rdtroya.java.jdbc.util.JpaUtil;

public class hibernateAsociacionesOneToMany {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Cliente cliente = new Cliente("Cata", "Edu");
            cliente.setFormaPago("mercado pago");

            Direccion direccion1 = new Direccion("el vergel", 123);
            Direccion direccion2 = new Direccion("la casita 2", 456);

            cliente.getDirecciones().add(direccion1);
            cliente.getDirecciones().add(direccion2);
            em.persist(cliente);

            em.getTransaction().commit();

            System.out.println(cliente);

            //eliminamos una direccion
            em.getTransaction().begin();
            cliente = em.find(Cliente.class, cliente.getId());
            cliente.getDirecciones().remove(direccion1);
            System.out.println(cliente);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            em.close();

        }
    }
}
