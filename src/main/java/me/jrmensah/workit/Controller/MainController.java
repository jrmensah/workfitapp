package me.jrmensah.workit.Controller;

import me.jrmensah.workit.Entity.Client;
import me.jrmensah.workit.Entity.Speciality;
import me.jrmensah.workit.Entity.Trainer;
import me.jrmensah.workit.Entity.User;
import me.jrmensah.workit.Repository.ClientRepository;
import me.jrmensah.workit.Repository.SpecialityRepository;
import me.jrmensah.workit.Repository.TrainerRepository;
import me.jrmensah.workit.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        model.addAttribute("clients", new Client());
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


}