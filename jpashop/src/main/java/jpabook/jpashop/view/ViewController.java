package jpabook.jpashop.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class ViewController {

    @GetMapping("/main")
    public String viewMain(Model model){
        System.out.println("??");
        model.addAttribute("data","mainData");

        return "main";
    }

}