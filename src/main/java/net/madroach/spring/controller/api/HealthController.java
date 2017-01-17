package net.madroach.spring.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sampark on 2016. 12. 17..
 */
@RestController
@RequestMapping("/api/ping")
public class HealthController {

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String ping(){
        return "PONG";
    }
}
