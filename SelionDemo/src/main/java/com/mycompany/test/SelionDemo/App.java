package com.mycompany.test.SelionDemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mycompany.test.SelionDemo.HeyWorld;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	ApplicationContext context = new ClassPathXmlApplicationContext("config/spring.xml");

        HeyWorld objA = (HeyWorld) context.getBean("heyWorld");

        objA.setMessage("I'm object A");
        objA.getMessage();

        HeyWorld objB = (HeyWorld) context.getBean("heyWorld");
        objB.getMessage();
    }
}
