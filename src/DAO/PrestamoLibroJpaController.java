/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.NonexistentEntityException;
import DAO.exceptions.PreexistingEntityException;
import bibliotecafusm.PrestamoLibro;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Jhon Alex
 */
public class PrestamoLibroJpaController implements Serializable {

    public PrestamoLibroJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PrestamoLibro prestamoLibro) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(prestamoLibro);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPrestamoLibro(prestamoLibro.getCodigoLibro()) != null) {
                throw new PreexistingEntityException("PrestamoLibro " + prestamoLibro + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PrestamoLibro prestamoLibro) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            prestamoLibro = em.merge(prestamoLibro);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = prestamoLibro.getCodigoLibro();
                if (findPrestamoLibro(id) == null) {
                    throw new NonexistentEntityException("The prestamoLibro with id " + id + " no longer exists.");
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
            PrestamoLibro prestamoLibro;
            try {
                prestamoLibro = em.getReference(PrestamoLibro.class, id);
                prestamoLibro.getCodigoLibro();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The prestamoLibro with id " + id + " no longer exists.", enfe);
            }
            em.remove(prestamoLibro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PrestamoLibro> findPrestamoLibroEntities() {
        return findPrestamoLibroEntities(true, -1, -1);
    }

    public List<PrestamoLibro> findPrestamoLibroEntities(int maxResults, int firstResult) {
        return findPrestamoLibroEntities(false, maxResults, firstResult);
    }

    private List<PrestamoLibro> findPrestamoLibroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PrestamoLibro.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public PrestamoLibro findPrestamoLibro(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PrestamoLibro.class, id);
        } finally {
            em.close();
        }
    }

    public int getPrestamoLibroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PrestamoLibro> rt = cq.from(PrestamoLibro.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
