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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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


    private ClientServiceImpl csi = new ClientServiceImpl();

    Clients clients;


    public ArrayList<String> getVersion(){return list;}
   // public ArrayList<String> getVersion(){return getAemVersionList();}

    ArrayList<String> list = new ArrayList<String>();

    @Override
    public void init(ComponentRequest request) {

        searchBy = get("searchBy", "All");
        list = getAemVersionList();


    }

    public String getString(){

        return "5.6";

    }

    public ArrayList<String> getAemVersionList(){

        ArrayList<String> list = new ArrayList<String>();


        list.add("5.5");
        list.add("5.6.1");
        list.add("6.0");

        return list;
    }

}
