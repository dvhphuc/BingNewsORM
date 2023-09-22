package org.example.model;

import org.example.annotation.Column;
import org.example.annotation.Entity;
import org.example.annotation.Primary;

@Entity(name = "AdTopic")
public class AdTopic {
    @Primary
    @Column(value = "guid")
    String guid;
    @Column(value = "title")
    String title;
    @Column(value = "imgUrl")
    String imgUrl;
}
