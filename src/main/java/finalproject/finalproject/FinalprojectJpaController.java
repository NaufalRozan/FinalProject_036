/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalproject.finalproject;

import finalproject.finalproject.exceptions.NonexistentEntityException;
import finalproject.finalproject.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author rozan
 */
public class FinalprojectJpaController implements Serializable {

    public FinalprojectJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("finalproject_finalproject_jar_0.0.1-SNAPSHOTPU");

    public FinalprojectJpaController (){}
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Finalproject finalproject) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(finalproject);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFinalproject(finalproject.getId()) != null) {
                throw new PreexistingEntityException("Finalproject " + finalproject + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Finalproject finalproject) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            finalproject = em.merge(finalproject);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = finalproject.getId();
                if (findFinalproject(id) == null) {
                    throw new NonexistentEntityException("The finalproject with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Finalproject finalproject;
            try {
                finalproject = em.getReference(Finalproject.class, id);
                finalproject.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The finalproject with id " + id + " no longer exists.", enfe);
            }
            em.remove(finalproject);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Finalproject> findFinalprojectEntities() {
        return findFinalprojectEntities(true, -1, -1);
    }

    public List<Finalproject> findFinalprojectEntities(int maxResults, int firstResult) {
        return findFinalprojectEntities(false, maxResults, firstResult);
    }

    private List<Finalproject> findFinalprojectEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Finalproject.class));
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

    public Finalproject findFinalproject(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Finalproject.class, id);
        } finally {
            em.close();
        }
    }

    public int getFinalprojectCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Finalproject> rt = cq.from(Finalproject.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
