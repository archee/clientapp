package com.icfi.clientapp.services.clientservice;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.felix.scr.annotations.*;
import org.apache.sling.commons.osgi.PropertiesUtil;

import java.io.IOException;
import java.util.Map;

@Component(name = "Client Connection Service", label = "Service to connect to the client web service")
@Service
public class ClientConnectionServiceImpl implements ClientConnectionService {

    private HttpClient httpClient;

    @Property(label = "Host", description = "The host of the web service")
    private static final String HOST_PROPERTY = "host";
    private String host;

    @Property(label = "Get Clients Endpoint", description = "The endpoint to retrieve a list of all clients")
    private static final String CLIENTS_ENDPOINT_PROPERTY = "clientsEndpoint";
    private String getClientsEndpoint;

    @Property(label = "Timeout", description = "How long to wait for web service response before aborting request", intValue = 5000)
    private static final String TIMEOUT_PROPERTY = "timeout";
    private int timeout;

    @Activate
    @Modified
    protected void activate(Map<String, Object> properties) {
        MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
        httpClient = new HttpClient(connectionManager);

        host = PropertiesUtil.toString(properties.get(HOST_PROPERTY), "icfi-client-store.herokuapp.com");
        timeout = PropertiesUtil.toInteger(properties.get(TIMEOUT_PROPERTY), 5000);
        getClientsEndpoint = PropertiesUtil.toString(properties.get(CLIENTS_ENDPOINT_PROPERTY), "/clients");

        connectionManager.getParams()
                .setConnectionTimeout(timeout);
    }

    @Override
    public String getAllClients() {

        //TODO: execute GET request and convert response stream into a String and return it
        GetMethod getMethod = new GetMethod(host + getClientsEndpoint);

        String response = "";

        try {
            httpClient.executeMethod(getMethod);
            response = getMethod.getResponseBodyAsString();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            getMethod.releaseConnection();
        }

        return response;
    }
}
