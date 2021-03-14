// Skapad av Erik Eklund
// Hemuppgift i kursen Java Automation Developer - STI, JAD-21
// E-post: ee223eg@lnu.se

package com.example.demo;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

// http-anrop 127:0:0:1:8080/player
// http-anrop 127:0:0:1:8080/player/1
// Skapa funktion som anropas när någon anger ovan

@RestController
public class DemoController {
    
	@Autowired
	private PlayerRepository playerRepository;

    
    // Hämta alla spelare
    //////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping(path = "/player")
    List<Player> getAll() {
        var l = new ArrayList<Player>();
        for(Player r : playerRepository.findAll())
        {
            l.add(r);
        }
        // Spring konverterar till JSON är databärare
        return l;
    }


    // Hämta en spelare
    //////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping(path = "/player/{id}")
    Player getSingle(@PathVariable Integer id) {
        // Kontrollera om spelaren finns
        if( playerRepository.existsById(id) ) {
            return playerRepository.findById(id).get();
        } 
        return null;
    }


    // Lägg till spelare (POST). Förväntar oss JSON (definierad som)
    //////////////////////////////////////////////////////////////////////////////////////////////////
    @PostMapping(path = "/player", consumes="application/json", produces="application/json")
    ResponseEntity<Object> add(@RequestBody Player p) {
        playerRepository.save(p);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(p.getId())
        .toUri();
        return ResponseEntity.created(location).build();

    }


    // Uppdatera spelare (PUT)
    //////////////////////////////////////////////////////////////////////////////////////////////////
    @PutMapping(path = "/player/{id}", consumes="application/json", produces="application/json")
    Player update(@PathVariable Integer id, @RequestBody Player updatedPlayer) {
        Player dbPlayer = playerRepository.findById(id).get();        
        dbPlayer.setName(updatedPlayer.getName());
        dbPlayer.setAge(updatedPlayer.getAge());
        dbPlayer.setJersey(updatedPlayer.getJersey());        
        playerRepository.save(dbPlayer);
        return dbPlayer;
    }


    // Ta bort spelare
    //////////////////////////////////////////////////////////////////////////////////////////////////
    @DeleteMapping(path = "/player/{id}")
    boolean deletePost(@PathVariable Integer id) {
        boolean status = false;
        // Kontrollera om spelaren finns
        if( playerRepository.existsById(id) ) {
            // Ta då bort den
            playerRepository.deleteById(id);
            status = true;
        }        
        return status;
    }




}
