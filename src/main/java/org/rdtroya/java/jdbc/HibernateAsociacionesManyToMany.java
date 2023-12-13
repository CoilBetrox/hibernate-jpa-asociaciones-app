package org.rdtroya.java.jdbc;

import jakarta.persistence.EntityManager;
import org.rdtroya.java.jdbc.entity.Alumno;
import org.rdtroya.java.jdbc.entity.Curso;
import org.rdtroya.java.jdbc.util.JpaUtil;

public class HibernateAsociacionesManyToMany {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            Alumno alumno1 = new Alumno("Cata", "Edu");
            Alumno alumno2 = new Alumno("Jano", "Fernan");

            Curso curso1 = new Curso("Curso Java", "Andres");
            Curso curso2 = new Curso("Curso Hibernate", "Alex");

            alumno1.getCursos().add(curso1);
            alumno1.getCursos().add(curso2);

            alumno2.getCursos().add(curso1);

            em.persist(alumno1);
            em.persist(alumno2);

            em.getTransaction().commit();
            System.out.println(alumno1);
            System.out.println(alumno2);

            //desasignar un curso de un alumno
            em.getTransaction().begin();
            Curso c2 = em.find(Curso.class, 3L);
            alumno1.getCursos().remove(c2);
            em.getTransaction().commit();
            System.out.println(alumno1);

        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
    }
}
