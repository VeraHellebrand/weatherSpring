package cz.vh.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
    @RequestMapping(value = {"/hello","hello/"}, method = RequestMethod.GET)
    public String greeting(){
        return "Hello";
    }
}
