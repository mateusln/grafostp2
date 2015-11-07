/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import java.util.List;
import sun.rmi.runtime.Log;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author mateus
 */
public class JavaApplication2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // gets Twitter instance with default credentials
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
        .setOAuthConsumerKey("mbqmHbBdeXxNDS5zFRoYuH1on")
        .setOAuthConsumerSecret("W2QVzc2N9eYb6fq2amhHgkG8erGzef7CHqGJ2uLn7yZ3cCavqj")
        .setOAuthAccessToken("55040988-cQ2srPQCzX10P4CekNLoFW2W1tnFXXQtRiEDgx7IX")
        .setOAuthAccessTokenSecret("cDGh4jIrB74dy3yH5pDhocvJxZWQySaKhtoUUkNQmnvtB");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        
        //Twitter twitter = new TwitterFactory().getInstance();
        try {
            List<Status> statuses;
            String user;
            if (args.length == 1) {
                user = args[0];
                statuses = twitter.getUserTimeline(user);
            } else {
                user = "Estadao";// digite aqui o usuario desejado
                statuses = twitter.getUserTimeline(user);
            }
            System.out.println("Showing @" + user + "'s user timeline.");
            for (Status status : statuses) {
                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
            }
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
            System.exit(-1);
        }
    }
}
    
    

