package com.tlp.mrhill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tlp.mrhill.mapper")
public class MrhillApplication {

    public static void main(String[] args) {
        SpringApplication.run(MrhillApplication.class, args);
    }

}
