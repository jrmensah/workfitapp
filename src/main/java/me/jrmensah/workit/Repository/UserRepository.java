package me.jrmensah.workit.Repository;



import me.jrmensah.workit.Entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{
	User findByUsername(String username);
	User findByEmail(String username);
	Long countByEmail(String email);
//	Long CountByUsername (String username);



}
