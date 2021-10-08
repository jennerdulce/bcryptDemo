package com.jennerdulce.bcryptDemo.models;

import javax.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    public String content;

    protected Post(){

    }

    public Post(String content, Account actualAccount){
        myAccount = actualAccount;
        this.content = content;
    }

    @ManyToOne
    Account myAccount;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
