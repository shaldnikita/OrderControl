package ru.testwork;

import java.util.ArrayList;
import java.util.Optional;
import javax.persistence.*;

/**
 *
 * @author ShaldNikita
 */
public class Connection {

    private EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("RemakingWebAppPU");
    private EntityManager em;
    private static Connection connection;

    static {
        connection = new Connection();
    }

    private Connection() {
    }

    public boolean addElementToDB(BouquetOrder p) throws Exception {
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
    }

    public BouquetOrder getElementFromDB(String id) throws Exception {
        BouquetOrder p = null;
        try {
            em = emf.createEntityManager();
            p = em.find(BouquetOrder.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return p;
    }

    public boolean updateField(Long id, Optional<String> wishes, Optional<Integer> moneySpent, Optional<Integer> total, Optional<String> path,Boolean isReady) throws Exception {
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            BouquetOrder order = em.find(BouquetOrder.class, id);
            order.setWishes(wishes.orElse(null));
            order.setMoneySpent(moneySpent.orElse(null));
            order.setTotal(total.orElse(null));
            order.setImgPath(path.orElse(null));
            order.setStatus(isReady? Status.Ready : Status.Accepted);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    public boolean updateStatus(String id, Status status) {
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            BouquetOrder order = em.find(BouquetOrder.class, id);
            order.setStatus(status);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
        
    }

    public boolean removeOrder(Long id) throws Exception {
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            BouquetOrder order = em.find(BouquetOrder.class, id);
            em.remove(order);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    public ArrayList<BouquetOrder> getBouquetOrderList() {
        em = emf.createEntityManager();
        return new ArrayList(em.createNamedQuery("getBouquetOrderList", BouquetOrder.class).getResultList());

    }

    public static Connection getConnection() {
        return connection;
    }
}
