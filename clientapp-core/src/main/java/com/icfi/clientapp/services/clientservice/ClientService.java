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
}
