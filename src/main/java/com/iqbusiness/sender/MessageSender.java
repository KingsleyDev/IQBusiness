package com.iqbusiness.sender;

import com.rabbitmq.client.*;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class MessageSender {

    final static String QUEUE_NAME = "IQBUSINESSQUEUE";


   public static void main(String[] args) {

       Scanner scanner = new Scanner(System.in);
       ConnectionFactory factory = new ConnectionFactory();
       factory.setHost("localhost");

       try (Connection connection = factory.newConnection()) {

           Channel channel = connection.createChannel();
           channel.queueDeclare(QUEUE_NAME, false, false, false, null);


           System.out.println("Please enter your name");
           String Name = scanner.nextLine();

           channel.basicPublish("", QUEUE_NAME, null,  Name.getBytes());
           System.out.println("Hello my name is, " + Name);



       } catch (IOException e) {
           e.printStackTrace();
       } catch (TimeoutException e) {
           e.printStackTrace();
       }
   }
}
