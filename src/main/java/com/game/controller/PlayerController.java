package com.game.controller;

import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.service.PlayerServiceImplementation;
import com.game.service.PlayerSevice;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.lang.Double.valueOf;

@RestController
@RequestMapping("/rest/")
public class PlayerController {
    @Autowired
    private PlayerSevice playerSevice;
    private Player player;
    @GetMapping("players")
    public List<Player> getAll(@RequestParam Map<String, String> params) {


        return playerSevice.getAll(params);
    }


    @GetMapping("players/{id}")
    public ResponseEntity<Player> getPlayer (@PathVariable("id") Long id) {
        if (id < 1) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (!playerSevice.existsById(id)) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
return playerSevice.findById(id);
    }

    @PostMapping("players/")
    public ResponseEntity<Player> createNewPlayer (@RequestBody Player player){
        if (player == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (player.getName() ==null || player.getTitle() ==null || player.getRace() ==null
                || player.getProfession() ==null || player.getBirthday() ==null || player.getExperience() ==null ||
         !(player.getExperience() >=0 && player.getExperience() <= 10_000_000)
                || player.getName().length() > 12 || player.getTitle().length() > 30 || player.getBirthday().getTime() < 0)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
if (player.isBanned() == null)  player.setBanned(false);
player.setLevel(player.getExperience());
player.setUntilNextLevel(player.getExperience());
        playerSevice.save(player);
        return new ResponseEntity<>(player, HttpStatus.OK);
    }


@DeleteMapping("players/{id}")
    public ResponseEntity<Void> deletePlayer (@PathVariable(value = "id", required = false) Long id)  {
    if (id <= 0) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    if (!playerSevice.existsById(id)) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      playerSevice.deleteById(id);
    return new ResponseEntity<>(HttpStatus.OK);
}




@GetMapping("players/count")
public Integer getCount(@RequestParam Map<String, String > params) {
    return playerSevice.getCount(params);}



@PostMapping("players/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable(value = "id", required = false) Long id,
                                               @RequestBody Player player)  {

    return playerSevice.update(id, player);
}

}
