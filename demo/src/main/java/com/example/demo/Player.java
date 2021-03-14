// Skapad av Erik Eklund
// Hemuppgift i kursen Java Automation Developer - STI, JAD-21
// E-post: ee223eg@lnu.se

package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    public Integer getId() {
        return id;
      }
    
      public void setId(Integer id) {
        this.id = id;
      }
      
    private String name;
    private int age;
    private int jersey;

    
    // Konstruktor
    public Player(){}
    public Player(String n, int a, int j)
    {
        name = n;
        age = a;
        jersey = j;
    }

    // Sätt enskilda värden
    public void setName(String n)
    {
        name = n;
    }
    public void setAge(int a)
    {
        age = a;
    }
    public void setJersey(int j)
    {
        jersey = j;
    }


    // Hämta värden
    public String getName()
    {
        return name;
    }
    public int getAge()
    {
        return age;
    }    
    public int getJersey()
    {
        return jersey;
    }

    public String getFullDescription()
    {
        return "Id "+id+": "+name+", age "+age+", jersey number "+jersey;
    }


}
