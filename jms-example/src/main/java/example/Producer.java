package example;


import org.apache.qpid.jms.JmsConnectionFactory;

// JMS API types
import javax.jms.Connection;
import javax.jms.Session;
import javax.jms.Destination;
import javax.jms.DeliveryMode;
import javax.jms.TextMessage;
import javax.jms.MessageProducer;

import java.io.Console;
import java.util.Scanner;

class Producer {

    public static void main(String[] args) throws Exception {

        JmsConnectionFactory factory = new JmsConnectionFactory("amqp://localhost:5672");
        Connection connection = factory.createConnection("admin", "admin");
        connection.start();
        
       
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = null;     	
       // destination = session.createTopic("MyTopic");  
        destination =session.createQueue("abcd");
        MessageProducer producer = session.createProducer(destination);
        Scanner c=new Scanner(System.in) ;
        String response;
        
        do {
        	System.out.println("Enter message: ");
            response = c.nextLine();
            TextMessage msg = session.createTextMessage(response);
            producer.send(msg);
            
        } while (!response.equalsIgnoreCase("SHUTDOWN"));    
        connection.close();
        System.exit(1);
    }
}