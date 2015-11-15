/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import java.util.List;
import sun.rmi.runtime.Log;
import twitter4j.IDs;
import twitter4j.PagableResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.UserList;
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
                //statuses = twitter.getUserTimeline(user);
            }
            long lCursor = -1;
            
            IDs ids= twitter.getFollowersIDs("gabspalermo", lCursor);
           
            IDs ids2= twitter.getFollowersIDs("_lufontes", lCursor);
            
            for (int i = 0; i < ids.getIDs().length; i++) { // busca os amigos em comum
                for (int j = 0; j < ids2.getIDs().length; j++) {
                    if(ids.getIDs()[i]==ids2.getIDs()[j]){
                        long id=ids.getIDs()[i];
                        User u1 = twitter.showUser(id);
                        System.out.println(u1.getName()); 
                        
                        statuses = twitter.getUserTimeline(u1.getScreenName());
                        imprimeTL(statuses);
                    }
                    
                }
            }
            /*
            //List<Status> st=twitter.getRetweets(lCursor);
            PagableResponseList<User> lista = twitter.getFriendsList("gabspalermo", lCursor);
            
            int i=0,pagina=0;
            PagableResponseList<User> lista2 = twitter.getFriendsList("ThiagoS_10", lCursor);

            //lista.contains(lista.get(i));
            do{ // pega seguidores
                pagina++;
            
                do{
                    String nome =lista2.get(i).getName();
                    i++;
                    System.out.println("nome= "+nome);
                }while(i<lista.size());
            
            
                lista2 = twitter.getFriendsList("ThiagoS_10", lCursor);
                i=0;
                System.out.println(lista.size());
            }while( (lCursor=lista.getNextCursor()) != 0 && pagina<2);
                 
            lista.retainAll(lista2) ;
            System.out.println("INTERCESSAO");
            System.out.println(lista.size());
            for (int j = 0; j < lista.size(); j++) {
                System.out.println(lista.get(i).getName());
            }
            */
            
            /*for (Status status : statuses) { // mostra timeline
                
                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
            }*/
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
            System.exit(-1);
        }
    }
    
    private static void imprimeTL(List<Status> statuses) {
        try{
            
            
            for (Status status : statuses) { // mostra timeline

                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
            }
        
        }catch(Exception te){
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
            System.exit(-1);
            
        }
    }
    
}
    
    

