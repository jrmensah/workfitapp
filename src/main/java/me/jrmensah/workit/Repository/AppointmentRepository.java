package me.jrmensah.workit.Repository;

import me.jrmensah.workit.Entity.Appointment;
import org.springframework.data.repository.CrudRepository;

public interface AppointmentRepository extends CrudRepository<Appointment, Long> {
}
