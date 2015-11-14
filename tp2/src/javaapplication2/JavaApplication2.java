/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import java.lang.reflect.Array;
import java.util.ArrayList;
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
            long lCursor = -1;
            //List<Status> st=twitter.getRetweets(lCursor);
            PagableResponseList<User> lista = twitter.getFriendsList("gritamais_", lCursor);
            //System.out.println("Showing @" + user + "'s user timeline.");
            int i=0,pagina=0;
            
            do{ // pega seguidores
                pagina++;
            
                do{
                    String nome =lista.get(i).getName();
                    i++;
                    System.out.println("nome= "+nome);
                }while(i<lista.size());
            
            
                lista = twitter.getFriendsList("gritamais_", lCursor);
                i=0;
                System.out.println(lista.size());
            }while( (lCursor=lista.getNextCursor()) != 0 && pagina<2);
            
            
            System.out.println("Showing @" + user + "'s user timeline.");
            for (Status status : statuses) {
                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
                pegaLink(status.getText());
                
            }
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
            System.exit(-1);
        }
    }
   
    public static  void pegaLink(String twit) {
        
        ArrayList<String> array = new ArrayList<>();
        String [] str;
        
        String link="vazio";
        
        int i=twit.indexOf("http://");
        int tamanho=twit.split(" ").length;
        str=new String[tamanho];
        str=twit.split(" ");
        //System.out.println(str.length);
        for (int j = 0; j < tamanho; j++) {
            if(str[j].contains("http"))
                link=str[j];
            //System.out.println(str[j]);
        }
        
        System.out.println(link);
    }
    
}
    
    

