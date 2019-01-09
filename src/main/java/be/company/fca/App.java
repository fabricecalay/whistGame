package be.company.fca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * First class of Whist Game
 * Created by fca on 18-09-17.
 */
@SpringBootApplication
public class App {

    public static void main(String[] args) throws Exception{

//        System.setProperty("http.proxyHost", "proxy-http.spw.wallonie.be");
//        System.setProperty("http.proxyPort", "3129");
//
//        System.setProperty("https.proxyHost", "proxy-http.spw.wallonie.be");
//        System.setProperty("https.proxyPort", "3129");

        SpringApplication.run(App.class, args);

//        Game game = new Game();
//        game.initGame();
//
//        game.chooseContract();
////        game.showPlayersDeck();
//
//        game.launchCardGame();
    }
}
