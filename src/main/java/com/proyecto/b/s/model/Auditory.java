package com.proyecto.b.s.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Auditory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String modificationType;
    @Column(length = 2000)
    private String beforeModification;
    @Column(length = 2000)
    private String afterModification;

    private String user;
    private LocalDateTime date;


    public Auditory() {
    }

    public Auditory(Long id, String modificationType, String beforeModification, String afterModification, String user, LocalDateTime date) {
        this.id = id;
        this.modificationType = modificationType;
        this.beforeModification = beforeModification;
        this.afterModification = afterModification;
        this.user = user;
        this.date = date;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModificationType() {
        return modificationType;
    }

    public void setModificationType(String modificationType) {
        this.modificationType = modificationType;
    }

    public String getBeforeModification() {
        return beforeModification;
    }

    public void setBeforeModification(String beforeModification) {
        this.beforeModification = beforeModification;
    }

    public String getAfterModification() {
        return afterModification;
    }

    public void setAfterModification(String afterModification) {
        this.afterModification = afterModification;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Auditory{" +
                "id=" + id +
                ", modificationType='" + modificationType + '\'' +
                ", beforeModification='" + beforeModification + '\'' +
                ", afterModification='" + afterModification + '\'' +
                ", user='" + user + '\'' +
                ", date=" + date +
                '}';
    }
}
