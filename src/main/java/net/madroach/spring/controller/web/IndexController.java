package net.madroach.spring.controller.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by sampark on 2016. 12. 17..
 */
@Controller
public class IndexController {


    @Value("${spring.url.base}")
    private String baseUrl;

    @Value("${spring.env}")
    private String env;

    @RequestMapping("/hello")
    public String hello(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
        model.addAttribute("env", env);
        model.addAttribute("baseUrl", baseUrl);
        model.addAttribute("name", name);

        return "ftl/index";
    }
}
