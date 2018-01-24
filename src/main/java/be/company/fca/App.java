package be.company.fca;

import be.company.fca.model.Game;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * First class of Whist Game
 * Created by fca on 18-09-17.
 */
@SpringBootApplication
public class App {

    public static void main(String[] args){
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
