package com.infoshareacademy.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.ejb.Stateless;
import javax.servlet.http.Part;

@JsonIgnoreProperties(ignoreUnknown = true)
@Stateless
public class BookDTO {

    private String author;
    private String title;
    private String epoch;
    private String genre;
    private String cover;
    private String kind;
    private Part coverPart;

    @JsonProperty("cover_thumb")
    String coverThumb;

    @JsonProperty("has_audio")
    Boolean hasAudio;

    @JsonProperty("simple_thumb")
    String simpleThumb;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEpoch() {
        return epoch;
    }

    public void setEpoch(String epoch) {
        this.epoch = epoch;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCoverThumb() {
        return coverThumb;
    }

    public void setCoverThumb(String coverThumb) {
        this.coverThumb = coverThumb;
    }

    public Boolean getHasAudio() {
        return hasAudio;
    }

    public void setHasAudio(Boolean hasAudio) {
        this.hasAudio = hasAudio;
    }

    public String getSimpleThumb() {
        return simpleThumb;
    }

    public void setSimpleThumb(String simpleThumb) {
        this.simpleThumb = simpleThumb;
    }

    public Part getCoverPart() {
        return coverPart;
    }

    public void setCoverPart(Part coverPart) {
        this.coverPart = coverPart;
    }
}
