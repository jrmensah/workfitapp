package me.jrmensah.workit.DataLoader;

import me.jrmensah.workit.Entity.*;
import me.jrmensah.workit.Repository.*;
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

    @Autowired
    ExperianceRepository experianceRepository;

    @Autowired
    SpecialityRepository specialityRepository;

//    @Autowired
//    AppointmentRepository appointmentRepository;

    @Autowired
    LocationRepository locationRepository;



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

        Experiance beginner = new Experiance("Beginner");
        experianceRepository.save(beginner);

        Experiance intermediate = new Experiance("Intermediate");
        experianceRepository.save(intermediate);

        Experiance advanced = new Experiance("Advanced");
        experianceRepository.save(advanced);

        Speciality dance = new Speciality("Dance");
        specialityRepository.save(dance);

        Speciality martialarts = new Speciality("Martial arts");
        specialityRepository.save(martialarts);

        Speciality watersports = new Speciality("Water Sports");
        specialityRepository.save(watersports);

        Speciality aerobics = new Speciality("Aerobics");
        specialityRepository.save(aerobics);

        Speciality weighttraining =  new Speciality("Weight Training");
        specialityRepository.save(weighttraining);

        Location frederick = new Location("Frederick");
        locationRepository.save(frederick);

        Location montgomerycounty = new Location("Montgomery County");
        locationRepository.save(montgomerycounty);

        Location princegeorge = new Location("Prince George's County");
        locationRepository.save(princegeorge);



//        Appointment appointment = new Appointment("One","December 5th","3:00pm","Frederick","Trainer 3","Pending");
//        appointmentRepository.save(appointment);

//        Appointment appointment2 = new Appointment("Two","December 2nd","2:00pm","Montgomery County","Trainer 1","Pending");
//        appointmentRepository.save(appointment2);

//        Appointment appointment3 = new Appointment("Three","December 8th","8:00pm","Prince George's County","Trainer 2","Pending");
//        appointmentRepository.save(appointment3);





        Client client = new Client("Jim","Gray","3016310818","jim@montgormerycollege.edu","Male");
//////Need to add in add methods for Experiance,Location,Speacilaty,Appointment
        client.addSpeciality(weighttraining);
        client.addExperiance(beginner);
        client.addLocation(frederick);
//        client.addAppointment(appointment);
        clientRepository.save(client);

        Client client2 = new Client("Rachel","Turner","3457896081","Sally@gmail.com","Female");
        client2.addSpeciality(martialarts);
        client2.addExperiance(beginner);
        client2.addLocation(frederick);
//        client2.addAppointment(appointment3);
        clientRepository.save(client2);


        Client client3 = new Client("Tim","Gray","3016328818","Tim@montgormerycollege.edu","Male");
        client3.addSpeciality(dance);
        client3.addExperiance(beginner);
        client3.addLocation(frederick);
//        client3.addAppointment(appointment3);
        clientRepository.save(client3);

        Client client4 = new Client("Ashley","Brook","3222555999","Ashley@gmail.com","Female");
        client4.addSpeciality(dance);
        client4.addExperiance(beginner);
        client4.addLocation(frederick);
//        client4.addAppointment(appointment2);
        clientRepository.save(client4);

        Client client5 = new Client("Prince","Harry","124568099","prince@montgormerycollege.edu","Male");
        client5.addSpeciality(weighttraining);
        client5.addExperiance(intermediate);
        client5.addLocation(princegeorge);
//        client2.addAppointment(appointment);
        clientRepository.save(client5);

        Client client6 = new Client("Derrick","Turner","3457896081","Derrick@gmail.com","Male");
        client6.addLocation(montgomerycounty);
        client6.addExperiance(advanced);
        client6.addSpeciality(watersports);
//        client6.addAppointment(appointment2);
        clientRepository.save(client6);

        Client client7 = new Client("Conner","Gray","3016310818","Conner@montgormerycollege.edu","Male");
        client7.addLocation(montgomerycounty);
        client7.addExperiance(advanced);
        client7.addSpeciality(aerobics);
//        client7.addAppointment(appointment3);
        clientRepository.save(client7);

        Client client8 = new Client("Rachel","Turner","3457896081","Sally@gmail.com","Female");
        client8.addLocation(montgomerycounty);
        client8.addExperiance(intermediate);
        client8.addSpeciality(watersports);
//        client8.addAppointment(appointment2);
        clientRepository.save(client8);

        Client client9 = new Client("Kim","Gray","3016310818","Kim@montgormerycollege.edu","Female");
        client9.addLocation(frederick);
        client9.addExperiance(intermediate);
        client9.addSpeciality(weighttraining);
//        client9.addAppointment(appointment);
        clientRepository.save(client9);

        Client client10 = new Client("Laura","Johnson","3457896081","Laura@gmail.com","Female");
        client10.addLocation(princegeorge);
        client10.addExperiance(beginner);
        client10.addSpeciality(weighttraining);
//        client10.addAppointment(appointment);
        clientRepository.save(client10);


        Trainer trainer = new Trainer("Sally","Turner","3457896081","Sally@gmail.com","5 Stars","female");
        trainer.addExperiance(advanced);
        trainer.addSpeciality(martialarts);
        trainer.addLocation(frederick);
//        trainer.addAppointment(appointment);
        trainerRepository.save(trainer);

        Trainer trainer2 = new Trainer("Jack","Pine","3017345681","Jack@gmail.com","5 Stars","Male");
        trainer2.addExperiance(intermediate);
        trainer2.addSpeciality(watersports);
        trainer2.addLocation(princegeorge);
////        trainer2.addAppointment(appointment3);
        trainerRepository.save(trainer2);

        Trainer trainer3 = new Trainer("Bob","Miller","3226899083","Bob@gmail.com","5 Stars","Male");
        trainer3.addExperiance(advanced);
        trainer3.addSpeciality(aerobics);
        trainer3.addLocation(montgomerycounty);
//        trainer3.addAppointment(appointment3);
        trainerRepository.save(trainer3);

        Trainer trainer4 = new Trainer("Sarah","Mensa","2453609056","Sarah@gmail.com","5 Stars","Female");
        trainer4.addExperiance(intermediate);
        trainer4.addSpeciality(weighttraining);
        trainer4.addLocation(montgomerycounty);
//        trainer4.addAppointment(appointment2);
        trainerRepository.save(trainer4);

        Trainer trainer5 = new Trainer("Ally","Long","18009697960","Ally@gmail.com","5 Stars","Female");
        trainer5.addExperiance(beginner);
        trainer5.addSpeciality(weighttraining);
        trainer5.addLocation(princegeorge);
//        trainer5.addAppointment(appointment3);
        trainerRepository.save(trainer5);


    }
}
