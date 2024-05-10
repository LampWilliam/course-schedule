package com.courseschedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CourseScheduleApplication {

    public static void main(String[] args) {
        System.out.println("SpringBoot-1");
        SpringApplication.run(CourseScheduleApplication.class, args);
        System.out.println("SpringBoot-2");
    }
    /*
    原来的IDEA版本是2020.3.2
    但是不支持SDK18的很多东西好像 报错
    所以升级成最新版了...
     */
}
