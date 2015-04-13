package com.icfi.clientapp.services.clientservice;

import com.icfi.clientapp.domain.client.Client;
import com.icfi.clientapp.domain.client.Clients;
import com.icfi.clientapp.webservice.exceptions.ClientsServiceException;

import java.util.ArrayList;

/**
 * Created by 35243 on 3/27/15.
 */
public interface ClientService {

    Clients getAllClients() throws ClientsServiceException;

    Client getClientById(int id) throws ClientsServiceException;

    Client getClientByName(String name) throws ClientsServiceException;

    Clients getClientsByClientSince(String since) throws ClientsServiceException;

    Clients getClientsByIndustry(String industry) throws ClientsServiceException;

    Clients getClientsByAemVersion(String aemVersion) throws ClientsServiceException;



}
