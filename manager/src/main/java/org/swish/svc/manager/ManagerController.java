package org.swish.svc.manager;

import com.google.gson.Gson;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Controller
public class ManagerController {

    /**
     * RabbitTemplate provided free by Spring-Boot
     */
    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queue;

    /**
     * Maps the /form endpoint to the view that displays the data entry form
     * @param model
     * @return Serve form.html to user
     */
    @GetMapping("/form")
    public String dataEntryForm(Model model) {
        model.addAttribute("message", new Message());
        return "form";
    }

    /**
     * Maps the /sent endpoint to the view that displays the message sent confirmation
     * @param message
     * @return Serve sent.html to the user
     */
    @PostMapping("/sent")
    public String dataSubmit(@ModelAttribute Message message) {
        this.template.convertAndSend(queue.getName(), message.getMessage());
        return "sent";
    }

    /**
     * Maps the /content endpoint to the view that displays the contents of the database
     * Retrieves the database contents from the content API
     * @param model
     * @return
     */
    @GetMapping("/content")
    public String view(Model model) {
        RestTemplate restTemplate = new RestTemplate();
                                           //Get json from database and convert it back to a map
        model.addAttribute("data", new Gson().fromJson(restTemplate
                .getForObject("http://localhost:8080/view", String.class), HashMap.class));
        return "content";

    }
}
