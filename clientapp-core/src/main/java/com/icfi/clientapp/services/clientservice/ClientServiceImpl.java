package com.icfi.clientapp.services.clientservice;

import com.icfi.clientapp.domain.client.Client;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;

import java.util.ArrayList;

/**
 * Created by 35243 on 3/27/15.
 */
@Component
@Reference
@Properties(value = {@Property()})
public class ClientServiceImpl implements ClientService{


    @Reference
    ClientCacheServiceImpl clientCacheServiceImpl;

    @Override
    public ArrayList<Client> getAllClients(){

        ArrayList<Client> clientList = new ArrayList<Client>();


        return clientList;
    }

}
