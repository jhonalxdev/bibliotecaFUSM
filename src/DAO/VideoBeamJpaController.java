/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.NonexistentEntityException;
import DAO.exceptions.PreexistingEntityException;
import bibliotecafusm.VideoBeam;
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
 * @author Paulker
 */
public class VideoBeamJpaController implements Serializable {

    public VideoBeamJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(VideoBeam videoBeam) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(videoBeam);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findVideoBeam(videoBeam.getSerialNum()) != null) {
                throw new PreexistingEntityException("VideoBeam " + videoBeam + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(VideoBeam videoBeam) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            videoBeam = em.merge(videoBeam);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = videoBeam.getSerialNum();
                if (findVideoBeam(id) == null) {
                    throw new NonexistentEntityException("The videoBeam with id " + id + " no longer exists.");
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
            VideoBeam videoBeam;
            try {
                videoBeam = em.getReference(VideoBeam.class, id);
                videoBeam.getSerialNum();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The videoBeam with id " + id + " no longer exists.", enfe);
            }
            em.remove(videoBeam);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<VideoBeam> findVideoBeamEntities() {
        return findVideoBeamEntities(true, -1, -1);
    }

    public List<VideoBeam> findVideoBeamEntities(int maxResults, int firstResult) {
        return findVideoBeamEntities(false, maxResults, firstResult);
    }

    private List<VideoBeam> findVideoBeamEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(VideoBeam.class));
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

    public VideoBeam findVideoBeam(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(VideoBeam.class, id);
        } finally {
            em.close();
        }
    }

    public int getVideoBeamCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<VideoBeam> rt = cq.from(VideoBeam.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
