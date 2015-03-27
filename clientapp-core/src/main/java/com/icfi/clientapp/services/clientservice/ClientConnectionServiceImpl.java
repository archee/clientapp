package com.icfi.clientapp.services.clientservice;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;

@Component(name = "Client Connection Service", label = "Service to connect to the client web service")
@Service
public class ClientConnectionServiceImpl implements ClientConnectionService {
    @Override
    public String getAllClients() {
        return null;
    }
}
