package com.homework;

import com.homework.common.exception.controller.CustomErrorAttributes;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

/**
 * @author：ldy on 12/02/2018 14:56
 */
@SpringBootApplication
@ImportResource(locations = {"classpath:/spring/application.xml"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * 自定义异常处理
     *
     * @return
     */
    @Bean
    public ErrorAttributes errorAttributes() {
        return new CustomErrorAttributes();
    }

}
