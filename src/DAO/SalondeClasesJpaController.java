/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.exceptions.NonexistentEntityException;
import Conexion.exceptions.PreexistingEntityException;
import bibliotecafusm.SalondeClases;
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
public class SalondeClasesJpaController implements Serializable {

    public SalondeClasesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SalondeClases salondeClases) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(salondeClases);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSalondeClases(salondeClases.getNumeroSalon()) != null) {
                throw new PreexistingEntityException("SalondeClases " + salondeClases + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SalondeClases salondeClases) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            salondeClases = em.merge(salondeClases);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = salondeClases.getNumeroSalon();
                if (findSalondeClases(id) == null) {
                    throw new NonexistentEntityException("The salondeClases with id " + id + " no longer exists.");
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
            SalondeClases salondeClases;
            try {
                salondeClases = em.getReference(SalondeClases.class, id);
                salondeClases.getNumeroSalon();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The salondeClases with id " + id + " no longer exists.", enfe);
            }
            em.remove(salondeClases);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SalondeClases> findSalondeClasesEntities() {
        return findSalondeClasesEntities(true, -1, -1);
    }

    public List<SalondeClases> findSalondeClasesEntities(int maxResults, int firstResult) {
        return findSalondeClasesEntities(false, maxResults, firstResult);
    }

    private List<SalondeClases> findSalondeClasesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SalondeClases.class));
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

    public SalondeClases findSalondeClases(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SalondeClases.class, id);
        } finally {
            em.close();
        }
    }

    public int getSalondeClasesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SalondeClases> rt = cq.from(SalondeClases.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
