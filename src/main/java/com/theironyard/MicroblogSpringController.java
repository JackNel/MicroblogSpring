package com.theironyard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by Jack on 11/9/15.
 */

@Controller
public class MicroblogSpringController {
    ArrayList<Message> messages = new ArrayList();

    @RequestMapping("/")
    public String home(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        model.addAttribute("messages", messages);
        return "home";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, String username) {
        HttpSession session = request.getSession();
        session.setAttribute("username", username);
        return "redirect:/";
    }

    @RequestMapping("/add-message")
    public String message(String text) {
        Message message = new Message();
        message.id = messages.size() + 1;
        message.text = text;
        messages.add(message);
        return "redirect:/";
    }

    @RequestMapping("/delete-message")
    public String delete(Integer id) {
        messages.remove(id-1);
        for (int i = 0; i < messages.size(); i++) {
            messages.get(i).id = i + 1;
        }
        return "redirect:/";
    }
}
