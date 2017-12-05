package me.jrmensah.workit.Repository;

import me.jrmensah.workit.Entity.Trainer;
import org.springframework.data.repository.CrudRepository;

public interface TrainerRepository extends CrudRepository<Trainer, Long>
{
//Need to Pass Multiple Parameters to Get Working
    Iterable<Trainer> findAllByFirstNameOrLastNameOrContactNumberOrEmailOrRatingOrGenderContainingIgnoreCase(String firstName, String lastName, String contactNumber, String email, String rating, String gender);
}

