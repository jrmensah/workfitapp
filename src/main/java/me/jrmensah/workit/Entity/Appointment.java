package me.jrmensah.workit.Entity;


import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @NotEmpty
    private String appointNum;
    @NotNull
    @NotEmpty
    private String preferDate;
    @NotNull
    @NotEmpty
    private String preferTime;
    @NotNull
    @NotEmpty
    private String preferLocation;
    @NotNull
    @NotEmpty
    private String preferTrainer;
    @NotNull
    @NotEmpty
    private String appointStatus;

    @ManyToMany(mappedBy = "appointments")
    private Set<Trainer> trainers;

    @ManyToMany(mappedBy = "appointments")
    private Set<Client> clients;


    public Appointment() {
    }

    public Appointment(String appointNum, String preferDate, String preferTime, String preferLocation, String preferTrainer, String appointStatus) {
        this.appointNum = appointNum;
        this.preferDate = preferDate;
        this.preferDate = preferTime;
        this.preferLocation = preferLocation;
        this.preferTrainer = preferTrainer;
        this.appointStatus = appointStatus;
        this.trainers=new HashSet<Trainer>();
        this.clients=new HashSet<Client>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAppointNum() {
        return appointNum;
    }

    public void setAppointNum(String appointNum) {
        this.appointNum = appointNum;
    }

    public String getPreferDate() {
        return preferDate;
    }

    public void setPreferDate(String preferDate) {
        this.preferDate = preferDate;
    }

    public String getPreferTime() {
        return preferTime;
    }

    public void setPreferTime(String preferTime) {
        this.preferTime = preferTime;
    }

    public String getPreferLocation() {
        return preferLocation;
    }

    public void setPreferLocation(String preferLocation) {
        this.preferLocation = preferLocation;
    }

    public String getPreferTrainer() {
        return preferTrainer;
    }

    public void setPreferTrainer(String preferTrainer) {
        this.preferTrainer = preferTrainer;
    }

    public String getAppointStatus() {
        return appointStatus;
    }

    public void setAppointStatus(String appointStatus) {
        this.appointStatus = appointStatus;
    }
}
