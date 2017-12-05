package me.jrmensah.workit.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Location
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    ////
//    @NotNull
//    @Size(min = 3)
    private String description;

    @ManyToMany(mappedBy = "locations")
    private Set<Trainer> training;

    @ManyToMany(mappedBy = "locations")
    private Set<Client> clients;

    public Location()
    {
    }

    public Location(String description)
    {
        this.description = description;
        this.training=new HashSet<Trainer>();
        this.clients=new HashSet<Client>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Trainer> getTraining() {
        return training;
    }

    public void setTraining(Set<Trainer> training) {
        this.training = training;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }
}
