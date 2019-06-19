package com.kketter.example.codeFellowship;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ApplicationUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String username;
    String password;
    String firstName;
    String lastName;
    //DD/MM/YYYY
    String dateOfBirth;
    String bio;

    public ApplicationUser(){}

    public ApplicationUser(String username, String password, String firstName, String lastName, String dateOfBirth, String bio){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getBio() {
        return bio;
    }
}

// An ApplicationUser should have a username, password ( hashed using BCrypt),
// firstName, lastName, dateOfBirth, bio, and any other fields you think are useful.
// repository?
//implementation w/o security - start here
//https://github.com/KKetter/songr/blob/master/src/main/java/com/ketter/stuff/things0/firstWebApp/Album.java
//https://github.com/KKetter/songr/blob/master/src/main/java/com/ketter/stuff/things0/firstWebApp/AlbumController.java
//https://github.com/KKetter/songr/blob/master/src/main/java/com/ketter/stuff/things0/firstWebApp/AlbumRepository.java