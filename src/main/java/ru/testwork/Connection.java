package ru.testwork;

import java.util.ArrayList;
import java.util.Optional;
import javax.persistence.*;

/**
 *
 * @author ShaldNikita
 */
public class Connection {

    private EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction tx;
    private static Connection connection;

    static {
        connection = new Connection();
    }

    {
        emf = Persistence.createEntityManagerFactory("RemakingWebAppPU");
        em = emf.createEntityManager();
        tx = em.getTransaction();
    }

    private Connection() {
    }

    public boolean addElementToDB(BouquetOrder p) throws Exception {
        try {
            tx.begin();
            em.persist(p);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            return false;
        }
    }

    public BouquetOrder getElementFromDB(String id) throws Exception {
        BouquetOrder p = null;
        p = em.find(BouquetOrder.class, id);
        return p;
    }

    public boolean updateField(Long id, Optional<String> wishes, Optional<Integer> moneySpent, Optional<Integer> total, Optional<String> path, Boolean isReady) throws Exception {
        BouquetOrder order = null;
        try {
            tx.begin();
            order = em.getReference(BouquetOrder.class, id);
            order.setWishes(wishes.orElse(null));
            order.setMoneySpent(moneySpent.orElse(null));
            order.setTotal(total.orElse(null));
            order.setImgPath(path.orElse(null));
            order.setStatus(isReady ? Status.Ready : Status.Accepted);
            tx.commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateStatus(String id, Status status) {
        BouquetOrder order;
        try {
            tx.begin();
            order = em.getReference(BouquetOrder.class, id);
            order.setStatus(status);
            tx.commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeOrder(Long id) throws Exception {
        BouquetOrder order = null;
        try {
            tx.begin();
            order = em.find(BouquetOrder.class, id);
            em.remove(order);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
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
