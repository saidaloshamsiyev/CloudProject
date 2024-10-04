package org.example.cloudproject;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Project {


    @GetMapping()
    public String behzod() {
        return "yoshla baxtli bosin ";
    }

}
