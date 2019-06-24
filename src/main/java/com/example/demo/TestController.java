package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
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
    @RequestMapping(method = RequestMethod.GET,value = "info")
    public String   serverInfo() throws UnknownHostException {
        InetAddress addr = InetAddress.getLocalHost();
        String ip=addr.getHostAddress().toString(); //获取本机ip
        String hostName=addr.getHostName().toString(); //获取本机计算机名称
        System.out.println(ip);
        System.out.println(hostName);
        String result = "hostname is %s"+"<br/>"+"ip is %s <br/>";
        return  String.format(result,hostName,ip);
    }

}
