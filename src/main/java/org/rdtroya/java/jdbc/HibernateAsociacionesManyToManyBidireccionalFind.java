package org.rdtroya.java.jdbc;

import jakarta.persistence.EntityManager;
import org.rdtroya.java.jdbc.entity.Alumno;
import org.rdtroya.java.jdbc.entity.Curso;
import org.rdtroya.java.jdbc.util.JpaUtil;

public class HibernateAsociacionesManyToManyBidireccionalFind {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            Alumno alumno1 = em.find(Alumno.class, 1L);
            Alumno alumno2 = em.find(Alumno.class, 2L);

            Curso curso1 = em.find(Curso.class, 1L);//new Curso("Curso Java", "Andres");
            Curso curso2 = em.find(Curso.class, 2L); //new Curso("Curso Hibernate", "Alex");

            alumno1.addCurso(curso1);
            alumno1.addCurso(curso2);

            alumno2.addCurso(curso1);

            em.getTransaction().commit();
            System.out.println(alumno1);
            System.out.println(alumno2);


            //desasignar un curso de un alumno
            em.getTransaction().begin();
            Curso c2 = em.find(Curso.class, 2L);
            //Curso c2 = new Curso("Curso Java", "Andres");
            //c2.setId(3L);
            alumno1.removeCurso(c2);
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
