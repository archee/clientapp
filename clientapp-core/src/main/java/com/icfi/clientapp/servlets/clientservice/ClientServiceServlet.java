package com.icfi.clientapp.servlets.clientservice;

import com.citytechinc.aem.bedrock.api.request.ComponentServletRequest;
import com.citytechinc.aem.bedrock.core.servlets.AbstractComponentServlet;
import com.icfi.clientapp.domain.client.Client;
import com.icfi.clientapp.domain.client.Clients;
import com.icfi.clientapp.services.clientservice.ClientService;
import com.icfi.clientapp.services.clientservice.ClientServiceImpl;
import com.icfi.clientapp.webservice.exceptions.ClientsServiceException;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by 35243 on 4/6/15.
 */
@SlingServlet(paths = "/bin/clientapp/clients", methods = {"POST", "GET"})
public class ClientServiceServlet extends AbstractComponentServlet {


    @Reference
    private ClientService clientService;


    @Override
    protected void processGet(ComponentServletRequest request) throws ServletException, IOException {

        SlingHttpServletResponse response = request.getSlingResponse();

        Clients clients = null;
        try {

            clients = clientService.getAllClients();
            response.setStatus(200);
            writeJsonResponse(response, clients);

        } catch (ClientsServiceException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
