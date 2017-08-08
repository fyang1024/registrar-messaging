/*
TODO add license
 */
package io.veredictum.registrar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * It configures Spring Components and runs the application
 *
 * @author Fei Yang <fei.yang@veredictum.io>
 */

@SpringBootApplication
@ComponentScan(basePackages = {"io.veredictum"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
