package com.icfi.clientapp.services.clientservice;

import com.icfi.clientapp.domain.client.Client;
import com.icfi.clientapp.domain.client.Clients;
import com.icfi.clientapp.webservice.exceptions.ClientsServiceException;
import org.apache.felix.scr.annotations.*;

import java.util.ArrayList;

/**
 * Created by 35243 on 3/27/15.
 */
@Component
@Service
public class ClientServiceImpl implements ClientService{


    @Reference
    ClientCacheService clientCacheServiceImpl;


    @Override
    public Clients getAllClients() throws ClientsServiceException {

        return clientCacheServiceImpl.getAllClients();
    }

    //Get List of all clients
    @Override
    public Client getClientById(int id) throws ClientsServiceException{

        Clients clients = getAllClients();
        Client client = null;


        if (clients != null) {
            for(Client c : clients.getClientList()){
                if (c.getId() == id) {
                    client = c;
                }
            }
        }
        return client;
    }

    //Get a client by name
    @Override
    public Client getClientByName(String name) throws ClientsServiceException{

        Clients clients = getAllClients();
        Client client = null;

        if (clients != null) {
            for(Client c : clients.getClientList()){
                if (c.getName().equals(name )){
                    client = c;
                }
            }
        }
        return client;
    }


    //Get clients by length of time as clients
    @Override
    public Clients getClientsByClientSince(String since) throws ClientsServiceException {

        Clients clients = getAllClients();

        return null;
    }

    //Get clients by their industry
    @Override
    public Clients getClientsByIndustry(String industry) throws ClientsServiceException {

        Clients clients = getAllClients();
        ArrayList<Client> clientListByIndustry = new ArrayList<Client>();

        if (clients != null) {
            for(Client c : clients.getClientList()){
                if (c.getIndustry().equals(industry)) {
                    clientListByIndustry.add(c);
                }
            }
        }

        return  new Clients(clientListByIndustry);
    }

    //Get clients by their version of AEM
    @Override
    public Clients getClientsByAemVersion(String aemVersion) throws ClientsServiceException {

        Clients clients = getAllClients();
        ArrayList<Client> clientList = new ArrayList<Client>();

        if (clients != null) {
            for(Client c : clients.getClientList()){
                if (c.getAemVersion().equals(aemVersion)) {
                    clientList.add(c);
                }
            }
        }

        return  new Clients(clientList);
    }


}
