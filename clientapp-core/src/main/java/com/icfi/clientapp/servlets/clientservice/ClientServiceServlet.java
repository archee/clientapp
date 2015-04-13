package com.icfi.clientapp.servlets.clientservice;

import com.citytechinc.aem.bedrock.api.request.ComponentServletRequest;
import com.citytechinc.aem.bedrock.core.servlets.AbstractComponentServlet;
import com.icfi.clientapp.domain.client.Client;
import com.icfi.clientapp.domain.client.Clients;
import com.icfi.clientapp.services.clientservice.ClientService;
import com.icfi.clientapp.services.clientservice.ClientServiceImpl;
import com.icfi.clientapp.webservice.exceptions.ClientsServiceException;
import org.apache.commons.lang3.StringUtils;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by 35243 on 4/6/15.
 */
@SlingServlet(paths = "/bin/clientapp/clients", methods = {"POST", "GET"})
public class ClientServiceServlet extends AbstractComponentServlet {


    @Reference
    private ClientService clientService;

    @Override
    protected void processPost(ComponentServletRequest request) throws ServletException, IOException {

        SlingHttpServletResponse response = request.getSlingResponse();

        Clients clients = null;

        String allClients = request.getRequestParameter("all", StringUtils.EMPTY);
        String idNumber = request.getRequestParameter("id", StringUtils.EMPTY);
        String name = request.getRequestParameter("name", StringUtils.EMPTY);
        String industry = request.getRequestParameter("industry", StringUtils.EMPTY);
        String aemVersion = request.getRequestParameter("aemVersion", StringUtils.EMPTY);
        String clientSince = request.getRequestParameter("since", StringUtils.EMPTY);

        if(!allClients.equals(StringUtils.EMPTY)) {

            //Clients clients = null;

            try {

                clients = clientService.getAllClients();
                response.setStatus(200);
                writeJsonResponse(response, clients);

            } catch (ClientsServiceException e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }


        }

        if(!idNumber.equals(StringUtils.EMPTY)) {

            int id = Integer.parseInt(idNumber);
            ArrayList<Client> clientList = new ArrayList<Client>();

            Client client = null;

            try {

                client = clientService.getClientById(id);
                clientList.add(client);
                clients =new Clients(clientList);
                response.setStatus(200);
                writeJsonResponse(response, clients);

            } catch (ClientsServiceException e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }

        if(!name.equals(StringUtils.EMPTY)) {
            ArrayList<Client> clientList = new ArrayList<Client>();

            Client client = null;

            try {

                client = clientService.getClientByName(name);
                clientList.add(client);
                clients =new Clients(clientList);
                response.setStatus(200);
                writeJsonResponse(response, clients);

            } catch (ClientsServiceException e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }

        if(!industry.equals(StringUtils.EMPTY)) {

            //Clients clients = null;

            try {

                clients = clientService.getClientsByIndustry(industry);
                response.setStatus(200);
                writeJsonResponse(response, clients);

            } catch (ClientsServiceException e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }

        if(!aemVersion.equals(StringUtils.EMPTY)) {

           // Clients clients = null;

            try {

                clients = clientService.getClientsByAemVersion(aemVersion);
                response.setStatus(200);
                writeJsonResponse(response, clients);

            } catch (ClientsServiceException e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }

        if(!clientSince.equals(StringUtils.EMPTY)) {

            //Clients clients = null;

            try {

                clients = clientService.getClientsByClientSince(clientSince);
                response.setStatus(200);
                writeJsonResponse(response, clients);

            } catch (ClientsServiceException e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }



    }

    
}
