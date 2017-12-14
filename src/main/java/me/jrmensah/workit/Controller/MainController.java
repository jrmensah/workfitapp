package me.jrmensah.workit.Controller;

import me.jrmensah.workit.Entity.*;
import me.jrmensah.workit.Repository.*;
import me.jrmensah.workit.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class MainController {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    UserService userService;

    @Autowired
    TrainerRepository trainerRepository;

    @Autowired
    SpecialityRepository specialityRepository;

    @Autowired
    ExperianceRepository experianceRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    LocationRepository locationRepository;


    @RequestMapping("/")
    public String showIndex(Model model){
        model.addAttribute("allclients", clientRepository.findAll());
        model.addAttribute("fittrain", trainerRepository.findAll());
        model.addAttribute("experiancelist", experianceRepository.findAll());
        model.addAttribute("locationlist",locationRepository.findAll());
        return "index";
    }
    @RequestMapping("/home")
    public String showHomePage(){
        return "home";
    }

    @GetMapping("/add")
    public String addRecordForm(Model model){
        model.addAttribute("clients", new Client());
        return "addclientform";
    }
    @PostMapping("/process")
    public String processRecordForm(@Valid Client client, BindingResult result){
        {
            if(result.hasErrors()){
                return "index";
            }
            clientRepository.save(client);
            return "clientlist";
        }
    }
    @GetMapping("/addclient")
    public String addClientForm(Model model){
        model.addAttribute("client", new Client());
        return "addclientform";
    }
    @PostMapping("/addclient")
    public String processClientForm(@Valid Client client, BindingResult result, Model model){
        {
            if(result.hasErrors()){
                return "addclientform";
            }
            clientRepository.save(client);
            model.addAttribute("allclients",clientRepository.findAll());
            return "clientlist";
        }
    }

    @RequestMapping("/showclientlist")
    public String showclientlisting(){
        return "clientlist";
    }

    @RequestMapping("/login")
    private String login(Model model)
    {
        return "login2";
    }



    @GetMapping("/register")
    private String register(Model model)
    {   model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/register")
    public String processRegistrationPage(
            @Valid @ModelAttribute("user") User user,
            BindingResult result,
            Model model)
    {

        model.addAttribute("user", user);

        if (result.hasErrors())
        {
            return "registration";
        }
        else
        {
            userService.saveAdmin(user);
            model.addAttribute("message", "User Account Successfully Created");
        }
        return "/login";
    }

    @GetMapping("/addtrainer")
    private String addTrainer(Model model)
    {
        model.addAttribute("trainer", new Trainer());
        return "trainerform";
    }

    @PostMapping("/processtrainer")
    private String processtrainer(@Valid Trainer trainer, BindingResult result, Model model)
    {
        if(result.hasErrors())
        {
            return "trainerform";
        }
        trainerRepository.save(trainer);
        model.addAttribute("fittrain", trainerRepository.findAll());
        return "trainerlist";
    }

    @RequestMapping("/showtrainerlist")
    private String showTrainerList(){
        return "trainerlist";
    }

    @GetMapping("/addspeciality")
    private String addspeciality(Model model)
    {
        model.addAttribute("speciality", new Speciality());
        return "specialityform";
    }

    @PostMapping("/processspeciality")
    private String processspeciality(@Valid Speciality speciality, BindingResult result, Model model)
    {
        if(result.hasErrors())
        {
            return "specialityform";
        }
        specialityRepository.save(speciality);
        model.addAttribute("fitspeciality", specialityRepository.findAll());
        return "specialitylist";
    }

    @GetMapping("/search")
    public String getSearch(){
        return "searchform";
    }

    @PostMapping("/search")
    public String showSearchResults(HttpServletRequest request, Model model){
        String searchTrainername = request.getParameter("search");
        model.addAttribute("search",searchTrainername);
//

//        Expecting multiple parameters or else will throw No parameter available Need to pass as many as are in constructor in Entity.
        model.addAttribute("fittrain", trainerRepository.findAllByFirstNameOrLastNameOrContactNumberOrEmailOrRatingOrGenderContainingIgnoreCase(searchTrainername,searchTrainername,searchTrainername,searchTrainername,searchTrainername,searchTrainername));
//
        return "searchtrainerlist";
    }

    @GetMapping("/addtrainertoexperiance/{id}")                 //Experiance mapped by Trainer
    public String addtrainertoexperiance(@PathVariable("id") long trainerid, Model model)
    {

        model.addAttribute("trainer",trainerRepository.findOne(new Long(trainerid)));
        model.addAttribute("experiancelist",experianceRepository.findAll());
        System.out.println("Count for Trainer Repo"+trainerRepository.count());
        return "traineraddexperiance";
    }
    @PostMapping("/addtrainertoexperiance/{id}")
    public String addtrainertoexperiance(HttpServletRequest request, Model model)
    {
        String trainerid = request.getParameter("trainerid");
        String experianceid = request.getParameter("experianceid");
        Trainer trainer = trainerRepository.findOne(new Long(trainerid));
        System.out.println("Trainer ID:"+ trainerid);
        System.out.println("Experience ID:"+ experianceid);
        trainer.addExperiance(experianceRepository.findOne(new Long(experianceid)));
        trainerRepository.save(trainer);
        model.addAttribute("experiancelist",experianceRepository.findAll());
        model.addAttribute("fittrain",trainerRepository.findAll());
        return "redirect:/";
    }

    @GetMapping("/addtrainertolocation/{id}")                 //Location mapped by Trainer
    public String addtrainertolocation(@PathVariable("id") long trainerid, Model model)
    {

        model.addAttribute("trainer",trainerRepository.findOne(new Long(trainerid)));
        model.addAttribute("locationlist",locationRepository.findAll());
        System.out.println("Count for Trainer Repo"+trainerRepository.count());
        return "traineraddlocation";
    }
    @PostMapping("/addtrainertolocation/{id}")
    public String addtrainertolocation(HttpServletRequest request, Model model)
    {
        String trainerid = request.getParameter("trainerid");
        String locationid = request.getParameter("locationid");
        Trainer trainer = trainerRepository.findOne(new Long(trainerid));
        System.out.println("Trainer ID:"+ trainerid);
        System.out.println("Location ID:"+ locationid);
        trainer.addLocation(locationRepository.findOne(new Long(locationid)));
        trainerRepository.save(trainer);
        model.addAttribute("locationlist",locationRepository.findAll());
        model.addAttribute("fittrain",trainerRepository.findAll());
        return "redirect:/";
    }

    @GetMapping("/addtrainertospeciality/{id}")                 //Speciality mapped by Trainer
    public String addtrainertospeciality(@PathVariable("id") long trainerid, Model model)
    {

        model.addAttribute("trainer",trainerRepository.findOne(new Long(trainerid)));
        model.addAttribute("specialitylist",specialityRepository.findAll());
        System.out.println("Count for Trainer Repo"+trainerRepository.count());
        return "traineraddspeciality";
    }
    @PostMapping("/addtrainertospeciality/{id}")
    public String addtrainertospeciality(HttpServletRequest request, Model model)
    {
        String trainerid = request.getParameter("trainerid");
        String specialityid = request.getParameter("specialityid");
        Trainer trainer = trainerRepository.findOne(new Long(trainerid));
        System.out.println("Trainer ID:"+ trainerid);
        System.out.println("Speciality ID:"+ specialityid);
        trainer.addSpeciality(specialityRepository.findOne(new Long(specialityid)));
        trainerRepository.save(trainer);
        model.addAttribute("specialitylist",specialityRepository.findAll());
        model.addAttribute("fittrain",trainerRepository.findAll());
        return "redirect:/";
    }

    @GetMapping("/addtrainertoappointment/{id}")                 //Speciality mapped by Trainer
    public String addtrainertoappointment(@PathVariable("id") long trainerid, Model model)
    {

        model.addAttribute("trainer",trainerRepository.findOne(new Long(trainerid)));
        model.addAttribute("appointmentlist",appointmentRepository.findAll());
        System.out.println("Count for Trainer Repo"+trainerRepository.count());
        return "traineraddappointment";
    }
    @PostMapping("/addtrainertoappointment/{id}")
    public String addtrainertoappointment(HttpServletRequest request, Model model)
    {
        String trainerid = request.getParameter("trainerid");
        String appointmentid = request.getParameter("appointmentid");
        Trainer trainer = trainerRepository.findOne(new Long(trainerid));
        System.out.println("Trainer ID:"+ trainerid);
        System.out.println("Appointment ID:"+ appointmentid);
        trainer.addAppointment(appointmentRepository.findOne(new Long(appointmentid)));
        trainerRepository.save(trainer);
        model.addAttribute("appointmentlist",appointmentRepository.findAll());
        model.addAttribute("fittrain",trainerRepository.findAll());
        return "redirect:/";
    }


    @GetMapping("/addclienttoexperiance/{id}")                 //Experiance mapped by client
    public String addclienttoexperiance(@PathVariable("id") long clientid, Model model)
    {

        model.addAttribute("client",clientRepository.findOne(new Long(clientid)));
        model.addAttribute("experiancelist",experianceRepository.findAll());
        System.out.println("Count for client Repo"+clientRepository.count());
        return "clientaddexperiance";
    }
    @PostMapping("/addclienttoexperiance/{id}")
    public String addclienttoexperiance(HttpServletRequest request, Model model)
    {
        String clientid = request.getParameter("clientid");
        String experianceid = request.getParameter("experianceid");
        Client client = clientRepository.findOne(new Long(clientid));
        System.out.println("client ID:"+ clientid);
        System.out.println("Experience ID:"+ experianceid);
        client.addExperiance(experianceRepository.findOne(new Long(experianceid)));
        clientRepository.save(client);
        model.addAttribute("experiancelist",experianceRepository.findAll());
        model.addAttribute("allclients",clientRepository.findAll());
        return "redirect:/";
    }

    @GetMapping("/addclienttolocation/{id}")                 //location mapped by client
    public String addclienttolocation(@PathVariable("id") long clientid, Model model)
    {

        model.addAttribute("client",clientRepository.findOne(new Long(clientid)));
        model.addAttribute("locationlist",locationRepository.findAll());
        System.out.println("Count for client Repo"+clientRepository.count());
        return "clientaddlocation";
    }
    @PostMapping("/addclienttolocation/{id}")
    public String addclienttolocation(HttpServletRequest request, Model model)
    {
        String clientid = request.getParameter("clientid");
        String locationid = request.getParameter("locationid");
        Client client = clientRepository.findOne(new Long(clientid));
        System.out.println("client ID:"+ clientid);
        System.out.println("Location ID:"+ locationid);
        client.addLocation(locationRepository.findOne(new Long(locationid)));
        clientRepository.save(client);
        model.addAttribute("locationlist",locationRepository.findAll());
        model.addAttribute("allclients",clientRepository.findAll());
        return "redirect:/";
    }
    @GetMapping("/addclienttospeciality/{id}")                 //speciality mapped by client
    public String addclienttospeciality(@PathVariable("id") long clientid, Model model)
    {

        model.addAttribute("client",clientRepository.findOne(new Long(clientid)));
        model.addAttribute("specialitylist",specialityRepository.findAll());
        System.out.println("Count for client Repo"+clientRepository.count());
        return "clientaddspeciality";
    }
    @PostMapping("/addclienttospeciality/{id}")
    public String addclienttospeciality(HttpServletRequest request, Model model)
    {
        String clientid = request.getParameter("clientid");
        String specialityid = request.getParameter("specialityid");
        Client client = clientRepository.findOne(new Long(clientid));
        System.out.println("client ID:"+ clientid);
        System.out.println("Speciality ID:"+ specialityid);
        client.addSpeciality(specialityRepository.findOne(new Long(specialityid)));
        clientRepository.save(client);
        model.addAttribute("specialitylist",specialityRepository.findAll());
        model.addAttribute("allclients",clientRepository.findAll());
        return "redirect:/";
    }
    @GetMapping("/addclienttoappointment/{id}")                 //appointment mapped by client
    public String addclienttoappointment(@PathVariable("id") long clientid, Model model)
    {

        model.addAttribute("client",clientRepository.findOne(new Long(clientid)));
        model.addAttribute("appointmentlist",appointmentRepository.findAll());
        System.out.println("Count for client Repo"+clientRepository.count());
        return "clientaddappointment";
    }
    @PostMapping("/addclienttoappointment/{id}")
    public String addclienttoappointment(HttpServletRequest request, Model model)
    {
        String clientid = request.getParameter("clientid");
        String appointmentid = request.getParameter("appointmentid");
        Client client = clientRepository.findOne(new Long(clientid));
        System.out.println("client ID:"+ clientid);
        System.out.println("Appointment ID:"+ appointmentid);
        client.addAppointment(appointmentRepository.findOne(new Long(appointmentid)));
        clientRepository.save(client);
        model.addAttribute("appointmentlist",appointmentRepository.findAll());
        model.addAttribute("allclients",clientRepository.findAll());
        return "redirect:/";
    }

    //Adding/ Booking and Appointment
    @GetMapping("/addappointment")
    private String addAppointment(Model model)
    {
        model.addAttribute("appointment", new Appointment());
        return "appointmentform";
    }

    @PostMapping("/addappointment")
    private String processAppointment(@Valid Appointment appointment, Model model)
    {
        model.addAttribute("allappointments", appointmentRepository.findAll());
        return "appointmentlist";
    }

    @RequestMapping("/showappointments")
    private String showAppointment(Model model)
    {
        return "appointmentlist";
    }


    //Adding/ Location
    @GetMapping("/addlocation")
    private String addLocation(Model model)
    {
        model.addAttribute("location", new Location());
        return "locationform";
    }

    @PostMapping("/processlocationlist")
    private String processLocation(@Valid Location location, Model model)
    {
        model.addAttribute("locationlist",locationRepository.findAll());
        return "locationlist";
    }

    @RequestMapping("/showlocation")
    private String showLocationList(Model model)
    {
        return "locationlist";
    }


}