package me.jrmensah.workit.Repository;

import me.jrmensah.workit.Entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long>
{
    Role findByRole(String role);
}
