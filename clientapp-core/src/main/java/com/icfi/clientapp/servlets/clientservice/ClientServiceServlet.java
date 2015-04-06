package com.icfi.clientapp.servlets.clientservice;

import com.citytechinc.aem.bedrock.api.request.ComponentServletRequest;
import com.citytechinc.aem.bedrock.core.servlets.AbstractComponentServlet;
import com.icfi.clientapp.domain.client.Client;
import com.icfi.clientapp.domain.client.Clients;
import com.icfi.clientapp.services.clientservice.ClientServiceImpl;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by 35243 on 4/6/15.
 */
@SlingServlet(paths = "/bin/clientapp/clients", methods = {"POST", "GET"})
public class ClientServiceServlet extends AbstractComponentServlet {


    @Reference
    private ClientServiceImpl clientService;


    @Override
    protected void processGet(ComponentServletRequest request) throws ServletException, IOException {

        Clients clients =  clientService.getAllClients();


        SlingHttpServletResponse response = request.getSlingResponse();

        response.setStatus(200);
        writeJsonResponse(response, clients);


    }
}
