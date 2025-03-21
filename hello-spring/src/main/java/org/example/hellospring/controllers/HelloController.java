package org.example.hellospring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
//@ResponseBody
@RequestMapping("hello") //every single method from this class should begin with /hello/...
public class HelloController {

    //Handles request at path /hello
    @GetMapping("hello")
    @ResponseBody
    public String hello() {
        return "Hello World";
    }

    @GetMapping("goodbye")
    @ResponseBody
    public String goodbye() {
        return "Goodbye World";
    }

    // Handles requests of the form /helloWithQueryParam?name=Something
//    @GetMapping("helloWithQueryParam")
    // Now the method accepts both request and post requests.
    @RequestMapping(value = "hello-with-query-param", method = {RequestMethod.GET, RequestMethod.POST})
//    @ResponseBody
    public String HelloWithQueryParam1(@RequestParam String name, Model model) {
//        return "Hello " + name;
        String greeting = "Hello " + name + "!";
        model.addAttribute("greeting", greeting);
        return "hello";
    }

    // Handles requests of the form /helloWithQueryParam/Something
    @GetMapping("hello-with-query-param/{name}")
//    @ResponseBody
    public String HelloWithQueryParam2(@PathVariable String name,  Model model) {
//        return "Hello " + name;
        model.addAttribute("greeting", "Hello " + name + "!");
        return "hello";
    }

//    @GetMapping("form")
//    @ResponseBody
//    public String form() {
//        return "<html>" +
//                "<body>" +
//                "<form action='helloWithQueryParam'>" + //submit a request to helloWithQueryParam
//                "<input type='text' name='name'>" +
//                "<input type='submit' value='Submit'>" +
//                "</form>" +
//                "</body>" +
//                "</html>";
//    }

    @GetMapping("form") // won't use ResponseBody any longer
    public String form() {
        return "form"; //return template name
    }

    @GetMapping("hello-names")
    public String helloNames(Model model) {
        List<String> names = new ArrayList<>();
        names.add("John");
        names.add("Jane");
        names.add("Jack");

        model.addAttribute("names", names);
        return "hello-list";
    }
}
