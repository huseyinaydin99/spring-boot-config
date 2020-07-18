package com.huseyinaydin.springbootconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
public class GreetingController {

    @Value("${my.greeting: default value}")
    private String greetingMessage;

    @Value("bu bir static mesajdır")
    private String staticMessage;

    @Value("${my.list.values}")
    private List<String> listValues;

    @Value("#{${dbValues}}")
    private Map<String,String> dbValues;

    @Autowired
    @Qualifier("dbSettings")
    private DbSettings dbSettings;

    @Autowired
    private Environment environment;

    @GetMapping("/greeting")
    public String greeting(){
        System.out.println(dbValues.get("connectionString"));
        System.out.println(dbValues.get("userName"));
        System.out.println(dbValues.get("password"));
        this.listValues.stream().forEach(i-> System.out.println(i));
        return this.greetingMessage + " " + this.staticMessage + " " + this.listValues + " " + this.dbValues + " " + dbSettings.getConnection() + " " + dbSettings.getHost();
    }

    @GetMapping("envdetails")
    public String envDetails(){
        return environment.toString();
    }
}
