package com.example.demo;

import com.example.demo.config.SpringApplicationRegister;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Vector;

@RestController
@RequestMapping("/")
public class TestController {

    @RequestMapping(method = RequestMethod.GET, value = "index")
    public String index() {
        Vector v = new Vector();
        while (true) {
            byte b[] = new byte[1048576 * 2];
            v.add(b);
            Runtime rt = Runtime.getRuntime();
            System.out.println("free memory: " + rt.freeMemory());
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "bean")
    public Boolean testBean() {
      Object object =  SpringApplicationRegister.getBean("applicationEventMulticaster");
      if (object instanceof SimpleApplicationEventMulticaster){
          return  true;
      }else
      {return  false;}
    }

    @RequestMapping(method = RequestMethod.GET, value = "")
    public String index(HttpServletRequest request) {
        System.out.println("the server port is :"+request.getServerPort());
        return String.format("this server port is : %d ,hehehe",request.getServerPort());
    }

    @RequestMapping(method = RequestMethod.GET, value = "info")
    public String serverInfo(HttpServletRequest request) throws UnknownHostException {
        InetAddress addr = InetAddress.getLocalHost();
        String ip = addr.getHostAddress(); //获取本机ip
        String hostName = addr.getHostName(); //获取本机计算机名称
        System.out.println(ip);
        System.out.println(hostName);
        String result = "hostname is %s" + "<br/>" + "ip is %s <br/>";
        return String.format(result, hostName, ip);
    }
}