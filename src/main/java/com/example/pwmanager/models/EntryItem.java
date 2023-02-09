package com.example.pwmanager.models;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@Entity

@Table(name = "entry_items")
public class EntryItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String url;

    private String username;

    private String password;

    private Instant createdAt;

    private Instant updatedAt;

    @Override
    public String toString(){
        return String.format("EntryItem{id=%d, url='%s', username='%s', password='%s', createdAt='%s', updatedAt='%s'}",
                id, url, username, password, createdAt, updatedAt);
    }


}
