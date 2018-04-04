package pl.hycom.training.toDoList.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by hycom on 03.04.18.
 */
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "description")
    private String description;
    @Column(name = "date")
    private String finishDate;

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }
}
