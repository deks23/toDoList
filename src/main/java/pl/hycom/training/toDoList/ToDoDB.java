package pl.hycom.training.toDoList;

import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by hycom on 03.04.18.
 */
@Repository
@Transactional
public class ToDoDB {

    @PersistenceContext
    EntityManager entityManager;

    public Task findTaskById(int id){
        return entityManager.find(Task.class, Long.valueOf(id));
    }
}
