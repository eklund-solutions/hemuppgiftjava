// Skapad av Erik Eklund
// Hemuppgift i kursen Java Automation Developer - STI, JAD-21
// E-post: ee223eg@lnu.se

package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Integer> {
    
}
