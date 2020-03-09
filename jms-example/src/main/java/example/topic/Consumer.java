/**
 * 
 */
package example.topic;

/**
 * @author BEN LAHMAR EL HABIB
 *
 */
import org.apache.qpid.jms.JmsConnectionFactory;

//JMS API types
import javax.jms.Connection;
import javax.jms.Session;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.TextMessage;
import javax.jms.*;

class Consumer {

 public static void main(String[] args) throws JMSException {

 	
     JmsConnectionFactory factory = new JmsConnectionFactory("amqp://localhost:5672");
     Connection connection = factory.createConnection("admin", "admin");
     connection.start();
     
     
     Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
     
    
     Destination destination = null;
     destination = session.createTopic("MyTopic");        	 
     MessageConsumer consumer = session.createConsumer(destination);
     String body;
     do {  	
         Message msg = consumer.receive();
         body = ((TextMessage) msg).getText();
         
         System.out.println("Received = "+body);
         
     }while (!body.equalsIgnoreCase("SHUTDOWN"));
     
   
     connection.close();
     System.exit(1);
 }
}