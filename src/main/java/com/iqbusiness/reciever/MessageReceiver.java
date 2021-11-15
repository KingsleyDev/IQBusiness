package com.iqbusiness.reciever;

import com.rabbitmq.client.*;

import java.io.*;
import java.nio.charset.*;
import java.util.concurrent.*;

public class MessageReceiver {

   public static  void main(String [] args) {

       final String HOST = "localhost";
       final String QUEUE_NAME = "IQBUSINESSQUEUE";


      ConnectionFactory factory = new ConnectionFactory();
      factory.setHost(HOST);

       try (Connection connection = factory.newConnection()) {

           Channel channel = connection.createChannel();
           channel.queueDeclare(QUEUE_NAME,false,false,false,null);
           System.out.println("Waiting for message.....");

           DeliverCallback deliverCall = (consumerTag , delivery) -> {
               String name = new String(delivery.getBody(), StandardCharsets.UTF_8);
               System.out.println("Hello '" + name + "I am your father");
           };

           channel.basicConsume(QUEUE_NAME, true, deliverCall, consumerTag -> { });

       } catch (IOException e) {
           e.printStackTrace();
       } catch (TimeoutException e) {
           e.printStackTrace();
       }


   }
}
