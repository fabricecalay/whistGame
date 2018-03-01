package be.company.fca.controller;

import be.company.fca.model.Player;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PlayerController {

    List<Player> players = new ArrayList<>();

    public PlayerController() {
        for (long i=0L;i<4L;i++){
            Player player = new Player();
            player.setId(i);
            player.setName("Joueur " + (i+1));
            players.add(player);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/api/players", method = RequestMethod.GET)
    public List<Player> getAllPlayers(){
//        throw new RuntimeException("Il y a une erreur");
        return players;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/api/players/{id}", method= RequestMethod.GET)
    public Player getPlayerById(@PathVariable Integer id){
        return players.get(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/api/players/{id}", method = RequestMethod.PUT)
    public Player updatePlayer(@PathVariable Integer id, @RequestBody Player player){
        players.set(id, player);
        return player;
    }
}
