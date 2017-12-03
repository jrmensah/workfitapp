package me.jrmensah.workit.Repository;

import me.jrmensah.workit.Entity.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long> {
}
