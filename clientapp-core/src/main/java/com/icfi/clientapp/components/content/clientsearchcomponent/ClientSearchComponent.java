package com.icfi.clientapp.components.content.clientsearchcomponent;

import com.citytechinc.aem.bedrock.api.request.ComponentRequest;
import com.citytechinc.aem.bedrock.core.components.AbstractComponent;
import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.google.common.base.Joiner;
import com.icfi.clientapp.domain.client.Client;
import com.icfi.clientapp.domain.client.Clients;
import com.icfi.clientapp.services.clientservice.ClientService;
import com.icfi.clientapp.services.clientservice.ClientServiceImpl;
import com.icfi.clientapp.webservice.exceptions.ClientsServiceException;
import org.apache.felix.scr.annotations.Reference;

import java.util.*;

/**
 * Created by 35243 on 4/6/15.
 */
@Component(value = "client search")
public class ClientSearchComponent extends AbstractComponent{


    @Selection(type = "select", options = {
            @Option(text = "All" , value = "All"),
            @Option(text = "Id" , value = "Id"),
            @Option(text = "Name", value =  "Name"),
            @Option(text = "Industry", value =  "Industry"),
            @Option(text = "AEM Version", value =  "AEM Version"),
            @Option(text = "Client Since", value =  "Client Since"),

    })
    @DialogField(fieldLabel = "Search Criteria", fieldDescription = "Criteria for searching clients")
    private String searchBy;


    private ClientService csi;

    Clients clients;

    SortedSet<String> versionList = new TreeSet<String>();
    public Set<String> getVersion(){return versionList;}


    SortedSet<String> industryList = new TreeSet<String>();
   public Set<String> getIndustryList(){return industryList;}


    @Override
    public void init(ComponentRequest request) {

        csi = getService(ClientService.class);

        try {
            clients = csi.getAllClients();
        } catch (ClientsServiceException e) {
            e.printStackTrace();
        }

        for(Client c : clients.getClientList()){

            industryList.add(c.getIndustry());
            versionList.add(c.getAemVersion());

        }


        searchBy = get("searchBy", "All");



    }





}
