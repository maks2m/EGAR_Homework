package ru.elkin.egar;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    ApplicationContext context =
            new AnnotationConfigApplicationContext("ru.elkin.egar");
}
