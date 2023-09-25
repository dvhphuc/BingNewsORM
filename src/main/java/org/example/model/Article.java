package org.example.model;

import org.example.annotation.Column;
import org.example.annotation.Entity;
import org.example.annotation.Primary;

@Entity(name = "article")
public class Article {
    @Primary
    @Column(value = "guid")
    String guid;
    @Column(value = "title")
    String title;
    @Column(value = "imgurl")
    String imgUrl;

    public Article(String guid, String title, String imgUrl, String pubDate, String sourceLink, String channelId) {
        this.guid = guid;
        this.title = title;
        this.imgUrl = imgUrl;
        this.pubDate = pubDate;
        this.sourceLink = sourceLink;
        this.channelId = channelId;
    }

    public Article() {

    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getSourceLink() {
        return sourceLink;
    }

    public void setSourceLink(String sourceLink) {
        this.sourceLink = sourceLink;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    @Column(value = "pubDate")
    String pubDate;
    @Column(value = "sourceLink")
    String sourceLink;
    @Column(value = "channelId")
    String channelId;

}
