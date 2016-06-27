package ml.stephen.demo.service;

import org.springframework.stereotype.Service;

/**
 * Created by Stephen on 16/6/27.
 */
@Service
public class HelloWorldService {

    public String getMsg() {
        return "Hello World!";
    }

    public String getTitle() {
        return "Hello World";
    }
}
