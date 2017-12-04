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


        Client client3 = new Client("Tim","Gray","3016328818","Tim@montgormerycollege.edu","Male");
        clientRepository.save(client3);

        Client client4 = new Client("Ashley","Brook","3222555999","Ashley@gmail.com","Female");
        clientRepository.save(client4);

        Client client5 = new Client("Prince","Harry","124568099","prince@montgormerycollege.edu","Male");
        clientRepository.save(client5);

        Client client6 = new Client("Derrick","Turner","3457896081","Derrick@gmail.com","Male");
        clientRepository.save(client6);

        Client client7 = new Client("Conner","Gray","3016310818","Conner@montgormerycollege.edu","Male");
        clientRepository.save(client7);

        Client client8 = new Client("Rachel","Turner","3457896081","Sally@gmail.com","Female");
        clientRepository.save(client8);

        Client client9 = new Client("Kim","Gray","3016310818","Kim@montgormerycollege.edu","Female");
        clientRepository.save(client9);

        Client client10 = new Client("Laura","Johnson","3457896081","Laura@gmail.com","Female");
        clientRepository.save(client10);


        Trainer trainer = new Trainer("Sally","Turner","3457896081","Sally@gmail.com","5 Stars","female");
        trainerRepository.save(trainer);

        Trainer trainer2 = new Trainer("Jack","Pine","3017345681","Jack@gmail.com","5 Stars","Male");
        trainerRepository.save(trainer2);

        Trainer trainer3 = new Trainer("Bob","Miller","3226899083","Bob@gmail.com","5 Stars","Male");
        trainerRepository.save(trainer3);

        Trainer trainer4 = new Trainer("Sarah","Mensa","2453609056","Sarah@gmail.com","5 Stars","Female");
        trainerRepository.save(trainer4);

        Trainer trainer5 = new Trainer("Ally","Long","18009697960","Ally@gmail.com","5 Stars","Female");
        trainerRepository.save(trainer5);


    }
}
