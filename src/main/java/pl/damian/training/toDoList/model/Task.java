package pl.damian.training.toDoList.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;


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


    @ManyToOne
    @JsonIgnore
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
