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
    private int id;
    @Column(name = "description")
    private String description;
    @Column(name = "date")
    private Date finishDate;

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }
}
