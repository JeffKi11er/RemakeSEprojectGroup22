package com.example.uibeautifulcollection2.item;

import java.io.Serializable;

public class ItemTours implements Serializable {
    private String imageT;
    private String textT;
    private String detailInfo;
    private String country;
    private String dateStart;
    private String dateEnd;
    private long participants;

    public ItemTours() {
    }

//    public ItemTours(String imageT, String textT) {
//        this.imageT = imageT;
//        this.textT = textT;
//    }

    public ItemTours(String imageT, String textT, String detailInfo, String country, String dateStart, String dateEnd, long participants) {
        this.imageT = imageT;
        this.textT = textT;
        this.detailInfo = detailInfo;
        this.country = country;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.participants = participants;
    }

    public String getImageT() {
        return imageT;
    }

    public void setImageT(String imageT) {
        this.imageT = imageT;
    }

    public String getTextT() {
        return textT;
    }

    public void setTextT(String textT) {
        this.textT = textT;
    }

    public String getDetailInfo() {
        return detailInfo;
    }

    public void setDetailInfo(String detailInfo) {
        this.detailInfo = detailInfo;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public long getParticipants() {
        return participants;
    }

    public void setParticipants(long participants) {
        this.participants = participants;
    }
}
