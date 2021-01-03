package com.safnas.jms.hr;

import javax.jms.JMSContext;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

public class HRApp {

    public static void main(String[] args) throws NamingException {

        InitialContext context = new InitialContext();
        Topic topic = (Topic) context.lookup("topic/empTopic");

        try (ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
                JMSContext jmsContext = cf.createContext()) {
            
            Employee employee = new Employee();
            employee.setId(123);
            employee.setFirstName("Safnas");
            employee.setLastName("Oc");
            employee.setDesignation("IT Analyst");
            employee.setEmail("iamsafnas@gmail.com");
            employee.setPhone("123456");

            jmsContext.createProducer().send(topic, employee);
            System.out.println("Message Sent");
        }

    }

}
