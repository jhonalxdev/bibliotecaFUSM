/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.NonexistentEntityException;
import DAO.exceptions.PreexistingEntityException;
import bibliotecafusm.Libro;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Paulker
 */
public class LibroJpaController implements Serializable {

    public LibroJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Libro libro) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(libro);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findLibro(libro.getCodigoLibro()) != null) {
                throw new PreexistingEntityException("Libro " + libro + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Libro libro) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            libro = em.merge(libro);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = libro.getCodigoLibro();
                if (findLibro(id) == null) {
                    throw new NonexistentEntityException("The libro with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Libro libro;
            try {
                libro = em.getReference(Libro.class, id);
                libro.getCodigoLibro();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The libro with id " + id + " no longer exists.", enfe);
            }
            em.remove(libro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Libro> findLibroEntities() {
        return findLibroEntities(true, -1, -1);
    }

    public List<Libro> findLibroEntities(int maxResults, int firstResult) {
        return findLibroEntities(false, maxResults, firstResult);
    }

    private List<Libro> findLibroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Libro.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            System.out.println("consultando libros.....5");
            List<Libro> libros = q.getResultList();
            System.out.println("consultando libros.....6");
            return libros;
        } finally {
            em.close();
        }
    }

    public Libro findLibro(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Libro.class, id);
        } finally {
            em.close();
        }
    }

    public List<Libro> findLibros() {

        EntityManager em = getEntityManager();
        try {
            String cantidad = "SELECT COUNT(l.CODIGOLIBRO) FROM LIBRO l";
            String[] atributos = {"CODIGOLIBRO", "NOMBRELIBRO", "AUTOR_ES", "PROCEDENCIA", "CARRERA", "ANNIOPUB", "EDITORIAL", "TOMO", "FECHAINGRESO", "ESTADO"};
            String[] datos = new String[atributos.length];
            Query query = em.createNativeQuery(cantidad);
            long maximo = (long) query.getSingleResult();
            List<Libro> libros = new LinkedList<Libro>();
            String consulta = "SELECT CODIGOLIBRO, ANNIOPUB, AUTOR_ES, CARRERA,EDITORIAL,ESTADO,FECHAINGRESO,NOMBRELIBRO,PROCEDENCIA,TOMO FROM LIBRO";
            for (int x = 0; x < maximo; x++) {
                for (int y = 0; y < atributos.length; y++) {
                    consulta = "SELECT " + atributos[y] + " FROM LIBRO LIMIT " + x + ",1";
                    query = em.createNativeQuery(consulta);
                    datos[y] = (String) query.getSingleResult();
                }
                Libro book = new Libro(datos[0], datos[1], datos[2], datos[3],
                        datos[4], datos[5], datos[6], datos[7], datos[8]);
                book.setEstado(datos[9]);
                libros.add(book);
            }
            return libros;
        } finally {
            em.close();
        }
    }

    public int getLibroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Libro> rt = cq.from(Libro.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
