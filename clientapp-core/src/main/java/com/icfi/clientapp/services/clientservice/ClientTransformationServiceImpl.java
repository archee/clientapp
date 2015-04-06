package com.icfi.clientapp.services.clientservice;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.icfi.clientapp.domain.client.Clients;
import com.icfi.clientapp.webservice.exceptions.ClientsServiceException;
import org.apache.commons.lang.StringUtils;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(label = "Client Transformation Service", description = "Transforms raw JSON strings to Java Objects and returns it to calling code.")
@Service
public class ClientTransformationServiceImpl implements ClientTransformationService {

    private static final Logger LOG = LoggerFactory.getLogger(ClientTransformationServiceImpl.class);

    @Reference
    ClientConnectionService clientConnectionService;

    @Override
    public Clients getAllClients() throws ClientsServiceException {
        String response = clientConnectionService.getAllClients();

        return mapResponseToObjects(response, Clients.class);
    }

    private static <T> T mapResponseToObjects(final String jsonResponse, Class<T> clazz) {

        T result = null;

        if (StringUtils.isNotBlank(jsonResponse)) {

            try {

                final ObjectMapper objectMapper = new ObjectMapper();
                final JsonFactory jsonFactory = objectMapper.getJsonFactory();
                final JsonParser jsonParser = jsonFactory.createJsonParser(jsonResponse);

                final TypeFactory typeFactory = TypeFactory.defaultInstance();
                result = objectMapper.readValue(jsonParser, typeFactory.constructType(clazz));

            } catch (final Exception exception) {
                LOG.error("An exception has occurred.", exception);
            }

        } else {
            LOG.warn("JSON response is null");
        }

        return result;
    }
}
