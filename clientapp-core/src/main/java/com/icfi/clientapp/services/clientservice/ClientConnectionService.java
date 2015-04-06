package com.icfi.clientapp.services.clientservice;


import com.icfi.clientapp.webservice.exceptions.ClientsServiceException;

/**
 * Created by 35243 on 3/27/15.
 */
public interface ClientConnectionService {

    String getAllClients() throws ClientsServiceException;
}
