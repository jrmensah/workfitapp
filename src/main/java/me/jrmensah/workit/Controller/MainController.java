package me.jrmensah.workit.Controller;

import me.jrmensah.workit.Entity.Client;
import me.jrmensah.workit.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class MainController {

    @Autowired
    ClientRepository clientRepository;


    @RequestMapping("/")
    public String showIndex(Model model){
        model.addAttribute("allclients", clientRepository.findAll());
        return "index";
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


}