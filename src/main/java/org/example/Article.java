package org.example;

import org.example.annotation.Column;
import org.example.annotation.Entity;
import org.example.annotation.Primary;

@Entity(name = "article")
public class Article {
    @Primary
    @Column(name = "guid")
    String guid;
    @Column(name = "title")
    String title;
    @Column(name = "imgUrl")
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

    @Column(name = "pubDate")
    String pubDate;
    @Column(name = "sourceLink")
    String sourceLink;
    @Column(name = "channelId")
    String channelId;
}
