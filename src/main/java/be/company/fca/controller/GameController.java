package be.company.fca.controller;

import be.company.fca.model.Game;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    private static final String template = "Hello, %s!";

    @RequestMapping(value = "/api/game")
//    public Game newGame(@RequestParam(value="name", defaultValue="World") String name) {
    public Game newGame() {
        System.err.println("New game");
        return new Game();
    }

}
