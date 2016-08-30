/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.NonexistentEntityException;
import DAO.exceptions.PreexistingEntityException;
import bibliotecafusm.PrestamoBeam;
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
public class PrestamoBeamJpaController implements Serializable {

    public PrestamoBeamJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PrestamoBeam prestamoBeam) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(prestamoBeam);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPrestamoBeam(prestamoBeam.getSerialBeam()) != null) {
                throw new PreexistingEntityException("PrestamoBeam " + prestamoBeam + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PrestamoBeam prestamoBeam) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            prestamoBeam = em.merge(prestamoBeam);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = prestamoBeam.getSerialBeam();
                if (findPrestamoBeam(id) == null) {
                    throw new NonexistentEntityException("The prestamoBeam with id " + id + " no longer exists.");
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
            PrestamoBeam prestamoBeam;
            try {
                prestamoBeam = em.getReference(PrestamoBeam.class, id);
                prestamoBeam.getSerialBeam();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The prestamoBeam with id " + id + " no longer exists.", enfe);
            }
            em.remove(prestamoBeam);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PrestamoBeam> findPrestamoBeamEntities() {
        return findPrestamoBeamEntities(true, -1, -1);
    }

    public List<PrestamoBeam> findPrestamoBeamEntities(int maxResults, int firstResult) {
        return findPrestamoBeamEntities(false, maxResults, firstResult);
    }

    private List<PrestamoBeam> findPrestamoBeamEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PrestamoBeam.class));
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

    public PrestamoBeam findPrestamoBeam(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PrestamoBeam.class, id);
        } finally {
            em.close();
        }
    }

    public int getPrestamoBeamCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PrestamoBeam> rt = cq.from(PrestamoBeam.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
