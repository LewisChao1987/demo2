package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Vector;

@RestController
@RequestMapping("/")
public class TestController {

    @RequestMapping(method = RequestMethod.GET,value = "")
    public  String index(){

        Vector v = new Vector();
        while (true)
        {
            byte b[] = new byte[1048576*2];
            v.add(b);
            Runtime rt = Runtime.getRuntime();
            System.out.println( "free memory: " + rt.freeMemory() );
        }
    }
}
