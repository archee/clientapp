package com.icfi.clientapp.services.clientservice;

import com.icfi.clientapp.domain.client.Client;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;

import java.util.ArrayList;

@Component(label = "Client Transformation Service", description = "Transforms raw JSON strings to Java Objects and returns it to calling code.")
@Service
public class ClientTransformationServiceImpl implements ClientTransformationService {

    @Reference
    ClientConnectionService clientConnectionService;

    @Override
    public ArrayList<Client> getAllClients() {
        return null;
    }
}
