package com.jennerdulce.bcryptDemo.models;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

// Tell to hibernate into JPA "please go make this table"
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
//  @Lob: Use for bigger files
    @Type(type = "org.hibernate.type.TextType")
    public String username;
    public String password;

    protected Account(){

    }

    @OneToMany(mappedBy = "myAccount")
    List<Post> usersPosts;

    public Account(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Post> getUsersPosts() {
        return usersPosts;
    }

    public void setUsersPosts(List<Post> usersPosts) {
        this.usersPosts = usersPosts;
    }
}
