package org.example.model;

import org.example.annotation.Column;
import org.example.annotation.Entity;
import org.example.annotation.Primary;

@Entity(name = "AdTopic")
public class AdTopic {
    @Primary
    @Column(name = "guid")
    String guid;
    @Column(name = "title")
    String title;
    @Column(name = "imgUrl")
    String imgUrl;
}
