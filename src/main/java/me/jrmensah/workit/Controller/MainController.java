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


    @RequestMapping("/")
    public String showIndex(Model model){
        model.addAttribute("allclients", clientRepository.findAll());
        model.addAttribute("fittrain", trainerRepository.findAll());
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
        model.addAttribute("fittrain", trainerRepository.findAllByFirstNameOrLastNameContainingIgnoreCase(searchTrainername,searchTrainername));
//
        return "searchtrainerlist";
    }

    @GetMapping("/addTrainertoExperiance/{id}")                 //Experiance mapped by Trainer
    public String addStudents(@PathVariable("id") long trainerid, Model model)
    {

        model.addAttribute("trainer", trainerRepository.findOne(new Long(trainerid)));
        model.addAttribute("experiancelist", experianceRepository.findAll());
        return "traineraddexperiance";
    }
    @PostMapping("/addTrainertoExperiance")
    public String addTrainertoExperiance(HttpServletRequest request, Model model)
    {
        String trainerId = request.getParameter("trainerId");
        String experianceid = request.getParameter("experianceid");
        Trainer trainer=trainerRepository.findOne(new Long(trainerId));
        trainer.addExperiance(experianceRepository.findOne(new Long(experianceid)));
        trainerRepository.save(trainer);
        model.addAttribute("experiancelist", experianceRepository.findAll());
        model.addAttribute("trainerlist", trainerRepository.findAll());
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
}