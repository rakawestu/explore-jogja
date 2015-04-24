package com.github.rakawestu.explorejogja.domain.model;

import org.parceler.Parcel;

/**
 * @author rakawm
 */
@Parcel
public class Place {
    String id;
    String judul;
    String deskripsi;
    String id_alamat;
    String price_range;
    String opening_hours;
    String cp_web;
    String facility;
    String review;
    String photos;

    public Place(){
    }

    public Place(String id, String judul, String deskripsi, String id_alamat,
                 String price_range, String opening_hours, String cp_web, String facility,
                 String review, String photos){
        this.id = id;
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.id_alamat = id_alamat;
        this.price_range = price_range;
        this.opening_hours = opening_hours;
        this.cp_web = cp_web;
        this.facility = facility;
        this.review = review;
        this.photos = photos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getId_alamat() {
        return id_alamat;
    }

    public void setId_alamat(String id_alamat) {
        this.id_alamat = id_alamat;
    }

    public String getPrice_range() {
        return price_range;
    }

    public void setPrice_range(String price_range) {
        this.price_range = price_range;
    }

    public String getOpening_hours() {
        return opening_hours;
    }

    public void setOpening_hours(String opening_hours) {
        this.opening_hours = opening_hours;
    }

    public String getCp_web() {
        return cp_web;
    }

    public void setCp_web(String cp_web) {
        this.cp_web = cp_web;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }
}
