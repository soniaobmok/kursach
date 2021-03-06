package com.gateway.apigateway.Booking;

import com.gateway.apigateway.Barber.Barber;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Booking {
    private Integer id;
    private Integer clientId;
    private Integer barberId;
    private Date date;
    private Barber br;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).format(date);
    }

    public void setDate(String date) throws ParseException {
        this.date = (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).parse(date);
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer client) {
        this.clientId = client;
    }

    public Integer getBarberId() {
        return barberId;
    }

    public void setBarberId(Integer barberId) {
        this.barberId = barberId;
    }

    public Barber getBr() { return br; }

    public void setBr(Barber br) { this.br = br; }
}