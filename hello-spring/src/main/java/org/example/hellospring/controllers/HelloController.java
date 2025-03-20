package org.example.hellospring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("hello") //every single method from this class should begin with /hello/...
public class HelloController {

    //Handles request at path /hello
    @GetMapping("hello")
//    @ResponseBody
    public String hello() {
        return "Hello World";
    }

    @GetMapping("goodbye")
//    @ResponseBody
    public String goodbye() {
        return "Goodbye World";
    }

    // Handles requests of the form /helloWithQueryParam?name=Something
//    @GetMapping("helloWithQueryParam")
    // Now the method accepts both request and post requests.
    @RequestMapping(value = "helloWithQueryParam", method = {RequestMethod.GET, RequestMethod.POST})
//    @ResponseBody
    public String HelloWithQueryParam1(@RequestParam String name) {
        return "Hello " + name;
    }

    // Handles requests of the form /helloWithQueryParam/Something
    @GetMapping("helloWithQueryParam/{name}")
//    @ResponseBody
    public String HelloWithQueryParam2(@PathVariable String name) {
        return "Hello " + name;
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

    @GetMapping("form")
//    @ResponseBody
    public String form() {
        return "<html>" +
                "<body>" +
                "<form action='helloWithQueryParam' method='post'>" + //submit a request to helloWithQueryParam
                "<input type='text' name='name'>" +
                "<input type='submit' value='Submit'>" +
                "</form>" +
                "</body>" +
                "</html>";
    }
}
