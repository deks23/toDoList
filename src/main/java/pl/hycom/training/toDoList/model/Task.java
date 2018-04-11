package pl.hycom.training.toDoList.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Calendar;
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



    @Column (name = "finishDate")
    private LocalDate finishDate;

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate dateField) {
        this.finishDate = dateField;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
