package com.safnas.jms.security;

import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.safnas.jms.hr.Employee;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

public class SecurityApp {

    public static void main(String[] args) throws NamingException, JMSException {

        InitialContext context = new InitialContext();
        Topic topic = (Topic) context.lookup("topic/empTopic");

        try (ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
                JMSContext jmsContext = cf.createContext()) {
            
            JMSConsumer consumer = jmsContext.createConsumer(topic);
            Message message = consumer.receive();

            Employee employee = message.getBody(Employee.class);

            System.out.println(employee.getFirstName());
        }

    }
    
}
