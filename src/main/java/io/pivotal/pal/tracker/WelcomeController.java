package io.pivotal.pal.tracker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;

@RestController
public class WelcomeController {

    private String welMsg;

    public WelcomeController(@Value("${welcome.message}") String message)
    {
        welMsg = message;
    }

    @GetMapping("/")
    public String sayHello() {
        //return "hello";
        return welMsg;
    }
}
