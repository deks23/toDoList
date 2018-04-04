package pl.hycom.training.toDoList.model.Dao;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import pl.hycom.training.toDoList.model.Task;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Map;

/**
 * Created by hycom on 03.04.18.
 */

@Repository
public class HibernateDao {

    private EntityManagerFactory entityManagerFactory;

    public HibernateDao() {
        entityManagerFactory = Persistence.createEntityManagerFactory("toDoList");
    }


    public void add(Task task) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(task);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

/*    public List getTaskList() {
        EntityManager  entityManager = entityManagerFactory.createEntityManager();
        return entityManager.
    }*/

    public void delete(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.remove(find(id));
        entityManager.close();


    }

    public Task find(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Task result = entityManager.find(Task.class, id);
        entityManager.close();
        return result;
    }

}
