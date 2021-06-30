package com.quanmin.djdata;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication
// 开启定时器
@EnableScheduling
//Servlet扫描
@ServletComponentScan
//指定dao扫描
@MapperScan(basePackages={"com.quanmin.djdata.**.dao",
        "com.baomidou.mybatisplus.samples.quickstart.mapper"})
public class DjdataApplication {

    public static void main(String[] args) {
        SpringApplication.run(DjdataApplication.class, args);
    }

}
