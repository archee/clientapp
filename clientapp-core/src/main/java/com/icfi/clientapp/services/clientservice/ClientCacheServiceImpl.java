package com.icfi.clientapp.services.clientservice;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.icfi.clientapp.domain.client.Client;
import com.icfi.clientapp.domain.client.Clients;
import com.icfi.clientapp.webservice.exceptions.ClientsServiceException;
import org.apache.felix.scr.annotations.*;

import org.apache.sling.commons.osgi.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by 35243 on 3/27/15.
 */
@Component(label = "Client Cache Service", description = "Responsible for maintaining caches and requesting clients list.", metatype = true)
@Service

public class ClientCacheServiceImpl implements ClientCacheService {

    private static final Logger LOG = LoggerFactory.getLogger(ClientCacheServiceImpl.class);

    private final String CLIENTS = "clients";

    private Cache<String, Clients> clientListCache;

    @Property(label = "Cache expiration time", intValue = 60, description = "Cache expiration time in minutes.")
    private static final String CACHE_EXPIRATION_TIME_PROPERTY = "cacheExpirationTime";
    private Integer cacheExpirationTime;

    @Property(label = "Cache concurrency level", intValue = 150, description = "Guides the allowed concurrency among update operations.")
    private static final String CACHE_CONCURRENCY_LEVEL_PROPERTY = "cacheConcurrencyLevel";
    private Integer cacheConcurrencyLevel;

    @Property(label = "Cache initial capacity", intValue = 100, description = "Sets the minimum total size for the internal hash tables.")
    private static final String CACHE_INITIAL_CAPACITY_PROPERTY = "cacheInitialCapacity";
    private Integer cacheInitialCapacity;

    @Property(label = "Cache max size", intValue = 1000, description = "Specifies the maximum number of entries the cache may contain.")
    private static final String CACHE_MAX_SIZE_PROPERTY = "cacheMaxSize";
    private Long cacheMaxSize;

    @Reference
    ClientTransformationService clientTransformationService;

    @Activate
    @Modified
    protected void activate(final Map<String, Object> properties) throws Exception {

        cacheExpirationTime = PropertiesUtil.toInteger(properties.get(CACHE_EXPIRATION_TIME_PROPERTY), 60);
        cacheConcurrencyLevel = PropertiesUtil.toInteger(properties.get(CACHE_CONCURRENCY_LEVEL_PROPERTY), 150);
        cacheInitialCapacity = PropertiesUtil.toInteger(properties.get(CACHE_INITIAL_CAPACITY_PROPERTY), 100);
        cacheMaxSize = PropertiesUtil.toLong(properties.get(CACHE_MAX_SIZE_PROPERTY), 1000);

        buildCache();
    }

    private void buildCache() {

                clientListCache= CacheBuilder.newBuilder()
                .expireAfterWrite(cacheExpirationTime, TimeUnit.MINUTES)
                .concurrencyLevel(cacheConcurrencyLevel)
                .initialCapacity(cacheInitialCapacity)
                .maximumSize(cacheMaxSize)
                .build();
    }


    @Override
    public Clients getAllClients() throws ClientsServiceException {

        Clients clients= null;

    try{
        clients = clientListCache.get(CLIENTS,new Callable<Clients>(){
            @Override
            public Clients call() throws ClientsServiceException {
                return clientTransformationService.getAllClients();
            }
        });
    }catch (ExecutionException e){
        LOG.error("An exception has occurred.", e);

        }

        return clients;
    }



}
