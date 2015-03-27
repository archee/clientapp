package com.icfi.clientapp.domain.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * Created by 35243 on 3/27/15.
 */
public class Clients {

    ArrayList<Client> clientList;

    public Clients(ArrayList<Client> clientList) {
        this.clientList = clientList;
    }

    public ArrayList<Client> getClientList() {
        return clientList;
    }

    @JsonCreator
    public static Clients create(@JsonProperty("clients") final ArrayList<Client> clientList)
                               {

        return new Clients(clientList);

    }
}
