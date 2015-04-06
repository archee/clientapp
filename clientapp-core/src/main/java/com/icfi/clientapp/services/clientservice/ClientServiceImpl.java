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

}
