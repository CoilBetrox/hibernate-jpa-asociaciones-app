package org.rdtroya.java.jdbc;

import jakarta.persistence.EntityManager;
import org.rdtroya.java.jdbc.entity.Cliente;
import org.rdtroya.java.jdbc.util.JpaUtil;

public class HibernateFetchLazyOneToMany {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        Cliente cliente = em.find(Cliente.class, 1L);
        System.out.println(cliente.getDirecciones());
        em.close();
    }
}
