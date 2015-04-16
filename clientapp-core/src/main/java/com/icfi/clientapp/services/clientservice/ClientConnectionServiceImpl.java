package com.icfi.clientapp.services.clientservice;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icfi.clientapp.domain.client.Client;
import com.icfi.clientapp.webservice.exceptions.ClientsServiceException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.felix.scr.annotations.*;
import org.apache.sling.commons.osgi.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@Component(name = "Client Connection Service", label = "Service to connect to the client web service")
@Service
public class ClientConnectionServiceImpl implements ClientConnectionService {

    private static final Logger LOG = LoggerFactory.getLogger(ClientConnectionServiceImpl.class);

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

        host = PropertiesUtil.toString(properties.get(HOST_PROPERTY), "http://icfi-client-store.herokuapp.com");
        timeout = PropertiesUtil.toInteger(properties.get(TIMEOUT_PROPERTY), 5000);
        getClientsEndpoint = PropertiesUtil.toString(properties.get(CLIENTS_ENDPOINT_PROPERTY), "/clients");


        connectionManager.getParams()
                .setConnectionTimeout(timeout);
    }

    @Override
    public String getAllClients() throws ClientsServiceException {

        //TODO: execute GET request and convert response stream into a String and return it
        GetMethod getMethod = new GetMethod(host + getClientsEndpoint);

        String response = "";

        try {
            httpClient.executeMethod(getMethod);

            if (getMethod.getStatusCode() != 200) {
                throw new ClientsServiceException("Invalid response code");
            }

            response = getMethod.getResponseBodyAsString();

        } catch (IOException e) {
            LOG.error("There was an IO error", e);
        }finally {
            getMethod.releaseConnection();
        }

        return response;
    }

    @Override
    public String addClient(Client client)throws ClientsServiceException {

        final StringWriter writer = new StringWriter();
        final ObjectMapper mapper = new ObjectMapper();
        StringRequestEntity requestEntity = null;

        final PostMethod postMethod = new PostMethod(" http://icfi-client-store.herokuapp.com/clients/add");

        String response = " ";


        try {
            final JsonGenerator jsonGenerator = mapper.getFactory().createGenerator(writer);
            mapper.writeValue(jsonGenerator, client);
        } catch (final Exception exception) {
            LOG.error("An error has occurred attempting to marshall to JSON.", exception);
        }


        String jsonString = writer.toString();

        String formattedString = jsonString.substring(8);
        formattedString = formattedString.replace("clientSince", "client-since");
        formattedString = formattedString.replace("aemVersion", "aem-version");
        formattedString = "{" + formattedString;




       // return formattedString;

        try {
            requestEntity = new StringRequestEntity(formattedString, "application/json", "UTF-8");
            postMethod.setRequestEntity(requestEntity);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String content = requestEntity.getContent();

        //return content;

        try {
            httpClient.executeMethod(postMethod);
            response = postMethod.getResponseBodyAsString();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            postMethod.releaseConnection();
        }
        return response;
    }







}
