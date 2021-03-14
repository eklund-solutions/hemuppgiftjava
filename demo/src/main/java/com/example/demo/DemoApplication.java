// Skapad av Erik Eklund
// Hemuppgift i kursen Java Automation Developer - STI, JAD-21
// E-post: ee223eg@lnu.se

package com.example.demo;

// import java.util.ArrayList;
// import java.util.List;

// Spring
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

										// Växla denna mot REST-anrop senare
public class DemoApplication implements CommandLineRunner {


	// Flytta till controller
	@Autowired
	private PlayerRepository playerRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	// Spara lista med spelare
	// ArrayList<Player> allPlayers = new ArrayList<Player>(); 


	// Metod: Lägg till spelare
	public void AddPlayer() {
		System.out.println("\n *** ADD NEW PLAYER *** ");

		System.out.print("Name: ");
		String name = System.console().readLine();

		System.out.print("Age: ");
		int age = Integer.parseInt(System.console().readLine());
		
		System.out.print("Jersey number: ");
		int jersey = Integer.parseInt(System.console().readLine());

		// Skapa ny instans för spelare
		Player p = new Player(name, age, jersey);

		// v.1: Lägg in spelaren i listan
		// allPlayers.add(p);		

		// v.2: Spara spelaren i databasen
		playerRepository.save(p);	

	}

 

	// Metod: Visa alla spelare
	public void ListAllPlayers() {
		System.out.println("\n *** ALL PLAYERS *** ");

		// v.1 Hämta spelare från listan
		/* 
		for(int i=0; i<allPlayers.size(); i++) {			
			// Numrerar spelare i ordning som de är lagrade i listan. Numreringen utgår från index-platsen + 1.
			// Denna numrering används just nu som identifikator vid ändring av spelaruppgifter.
			// När vi börjar spara i databasen så bör istället en primärnyckel erhållas som används som identifikator.
			System.out.println( (i+1)+". "+allPlayers.get(i).getFullDescription() );			
		}
		*/

		// v.2: Hämta spelare från databasen
		for(Player p: playerRepository.findAll()) {
			System.out.println( p.getFullDescription() );	
		}

	}
/*
	// Metod: Uppdatera spelaruppgifter
	public void UpdatePlayer() {
		System.out.println("\n *** UPDATE PLAYER INFO *** ");
		System.out.println("Which player do you want to update? ");
		System.out.print("Player id>");
		int id = Integer.parseInt(System.console().readLine());
		

		// Kontrollera att spelaren finns
		if( playerIndex<1 || playerIndex>allPlayers.size() )
			System.out.println("Player does not exist.");
		else {
			// Välj vad man ska uppdatera
			System.out.println("\nWhat do you want to change?");
			System.out.println("1. Name ("+allPlayers.get(playerIndex-1).getName()+")");
			System.out.println("2. Age ("+allPlayers.get(playerIndex-1).getAge()+")");
			System.out.println("3. Jersey number ("+allPlayers.get(playerIndex-1).getJersey()+")");
			System.out.println("4. Abort update");
			System.out.print("Your choice>");
			int updateChoice = Integer.parseInt(System.console().readLine());
			
			if(updateChoice==1) {
				System.out.println("\nNew name:");
				String newName = System.console().readLine();
				allPlayers.get(playerIndex-1).setName(newName);
			}
			if(updateChoice==2) {
				System.out.println("\nNew age:");
				int newAge = Integer.parseInt(System.console().readLine());
				allPlayers.get(playerIndex-1).setAge(newAge);
			}
			if(updateChoice==3) {
				System.out.println("\nNew jersey number:");
				int newJerseyNumber = Integer.parseInt(System.console().readLine());
				allPlayers.get(playerIndex-1).setJersey(newJerseyNumber);
			}


			
		}
		

		
	}

*/


	@Override
	public void run(String... args) throws Exception {

		// Här är programmet
		while(true) {
			System.out.println("\n *** MENU *** ");
			System.out.println("1. Add new player");
			// System.out.println("2. Update player info");
			System.out.println("3. List all players");
			System.out.println("100. Exit");
			System.out.print("Your choice>");
			int sel = Integer.parseInt(System.console().readLine());
			
			// Avsluta 
			if(sel == 100) break;

			if(sel == 1)
				AddPlayer();

			// if(sel == 2)
				// UpdatePlayer();

			if(sel == 3)
				ListAllPlayers();

		}

	}

}
