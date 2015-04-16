package com.icfi.clientapp.servlets.clientservice;

import com.citytechinc.aem.bedrock.api.request.ComponentServletRequest;
import com.citytechinc.aem.bedrock.core.components.AbstractComponent;
import com.citytechinc.aem.bedrock.core.servlets.AbstractComponentServlet;
import com.icfi.clientapp.domain.client.Client;
import com.icfi.clientapp.services.clientservice.ClientService;
import com.icfi.clientapp.webservice.exceptions.ClientsServiceException;
import org.apache.commons.lang3.StringUtils;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Created by 35243 on 4/14/15.
 */
@SlingServlet(paths = "/bin/clientapp/addclient", methods = {"POST"})
public class AddClientServlet extends AbstractComponentServlet{

    @Reference
    private ClientService clientService;

    @Override
    protected void processPost(ComponentServletRequest request) throws ServletException, IOException {

        SlingHttpServletResponse response = request.getSlingResponse();

        Client client = null;

        String addClientResponse = "Default";

        String name = request.getRequestParameter("name", StringUtils.EMPTY);
        String clientSince = request.getRequestParameter("since", StringUtils.EMPTY);
        String industry = request.getRequestParameter("industry", StringUtils.EMPTY);
        String aemVersion = request.getRequestParameter("aemVersion", StringUtils.EMPTY);



        client = new Client(name,clientSince,industry,aemVersion);

      // response.setStatus(200);
       //writeJsonResponse(response, client.getName());

        try {
            addClientResponse = clientService.addClient(client);

            response.setStatus(200);
            writeJsonResponse(response, addClientResponse);
        } catch (ClientsServiceException e) {
            e.printStackTrace();
        }


    }

}
