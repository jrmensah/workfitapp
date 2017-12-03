package me.jrmensah.workit.DataLoader;

import me.jrmensah.workit.Entity.Client;
import me.jrmensah.workit.Repository.ClientRepository;
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

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("Loading data. . .");

        Client client = new Client("Jim","Gray","3016310818","jim@montgormerycollege.edu","Male");
        clientRepository.save(client);

        Client client2 = new Client("Sally","Turner","3457896081","Sally@gmail.com","Female");
        clientRepository.save(client2);

    }
}
