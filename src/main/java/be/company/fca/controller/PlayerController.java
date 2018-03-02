package be.company.fca.controller;

import be.company.fca.model.PlayedCard;
import be.company.fca.model.Player;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PlayerController {

    List<Player> players = new ArrayList<>();

    public PlayerController() {
        for (int i=0;i<4;i++){
            Player player = new Player();
            player.setId(i);
            player.setName("Joueur " + (i+1));
            players.add(player);
        }
    }

    @RequestMapping(value = "/api/players", method = RequestMethod.GET)
    public List<Player> getAllPlayers(@RequestParam(required = false) String name){
//        throw new RuntimeException("Il y a une erreur");
        if (StringUtils.isEmpty(name)){
            return players;
        }else{
            List<Player> filteredPlayers = new ArrayList<>();
            for (Player player : players){
                if (player.getName().toLowerCase().startsWith(name.toLowerCase())){
                    filteredPlayers.add(player);
                }
            }
            return filteredPlayers;
        }
    }

    @RequestMapping(value = "/api/players/{id}", method= RequestMethod.GET)
    public Player getPlayerById(@PathVariable Integer id){
        return players.get(id);
    }

    @RequestMapping(value = "/api/players/{id}", method = RequestMethod.PUT)
    public Player updatePlayer(@PathVariable Integer id, @RequestBody Player player){
        players.set(id, player);
        return player;
    }

    @RequestMapping(value = "/api/players/{id}", method = RequestMethod.DELETE)
    public Player deletePlayer(@PathVariable Integer id){
        Player player = players.get(id);
        players.remove(id.intValue());
        for (Player playerToUpdate : players){
            playerToUpdate.setId(players.indexOf(playerToUpdate));
        }
        return player;
    }

//    @CrossOrigin(origins = "*",allowedHeaders = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT} )
    @RequestMapping(value = "/api/players", method = RequestMethod.POST)
    public Player addPlayer(@RequestBody Player player){
        player.setId(players.size());
        players.add(player);
        return player;
    }

//    @CrossOrigin(origins = "http://localhost:4200")
//    @RequestMapping(value= "/api/players/", method=RequestMethod.OPTIONS)
//    public void corsHeaders(HttpServletResponse response) {
//        response.addHeader("Access-Control-Allow-Origin", "*");
//        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
//        response.addHeader("Access-Control-Allow-Headers", "origin, content-type, accept, x-requested-with");
//        response.addHeader("Access-Control-Max-Age", "3600");
//    }

}
