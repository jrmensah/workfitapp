package me.jrmensah.workit.DataLoader;

import me.jrmensah.workit.Entity.Client;
import me.jrmensah.workit.Entity.Role;
import me.jrmensah.workit.Entity.Trainer;
import me.jrmensah.workit.Entity.User;
import me.jrmensah.workit.Repository.ClientRepository;
import me.jrmensah.workit.Repository.RoleRepository;
import me.jrmensah.workit.Repository.TrainerRepository;
import me.jrmensah.workit.Repository.UserRepository;
import me.jrmensah.workit.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by ${TravisGray} on 12/2/2017.
 */
@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    RoleRepository appRoles;

    @Autowired
    UserRepository userRepo;

    @Autowired
    UserService userService;

    @Autowired
    TrainerRepository trainerRepository;


    @Override
    public void run(String... strings) throws Exception {
        System.out.println("Loading data. . .");

        Role aRole = new Role();
        aRole.setRole("ADMIN");
        appRoles.save(aRole);
        System.out.println("Admin role has been created");


        User user = new User();
        user.setPassword("pass");
        user.setUsername("admin");
        user.setEmail("person@person.com");
        user.addRole(appRoles.findByRole("ADMIN"));
        userRepo.save(user);

        Client client = new Client("Jim","Gray","3016310818","jim@montgormerycollege.edu","Male");
        clientRepository.save(client);

        Client client2 = new Client("Rachel","Turner","3457896081","Sally@gmail.com","Female");
        clientRepository.save(client2);


        Trainer trainer = new Trainer("Sally","Turner","3457896081","Sally@gmail.com","5 Stars","female");
        trainerRepository.save(trainer);

        Trainer trainer2 = new Trainer("Jack","Pine","3017345681","Jack@gmail.com","5 Stars","Male");
        trainerRepository.save(trainer2);

    }
}
