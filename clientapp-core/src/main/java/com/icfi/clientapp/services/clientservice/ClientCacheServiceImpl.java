package com.icfi.clientapp.services.clientservice;

import com.icfi.clientapp.domain.client.Client;
import org.apache.felix.scr.annotations.*;

import java.util.ArrayList;

/**
 * Created by 35243 on 3/27/15.
 */
@Component(label = "Client Cache Service", description = "Responsible for maintaining caches and requesting refreshed dmv file.", metatype = true)
@Service
@Properties(value = {@Property()})
public class ClientCacheServiceImpl implements ClientCacheService {

    private Cache<Long, DmvStateInformationList> clientListCache;

    @Property(label = "Cache expiration time", value = Constants.DMV_LANDING_PAGE_CACHE_SERVICE_DEFAULT_EXPIRATION_TIME, description = "Cache expiration time in minutes.")
    private static final String CACHE_EXPIRATION_TIME_PROPERTY = "dmvCacheExpirationTime";
    private Integer cacheExpirationTime;

    @Property(label = "Cache concurrency level", value = Constants.DMV_LANDING_PAGE_CACHE_SERVICE_DEFAULT_CONCURRENCY_LEVEL, description = "Guides the allowed concurrency among update operations.")
    private static final String CACHE_CONCURRENCY_LEVEL_PROPERTY = "dmvCacheConcurrencyLevel";
    private Integer cacheConcurrencyLevel;

    @Property(label = "Cache initial capacity", value = Constants.DMV_LANDING_PAGE_CACHE_SERVICE_DEFAULT_INITIAL_CAPACITY, description = "Sets the minimum total size for the internal hash tables.")
    private static final String CACHE_INITIAL_CAPACITY_PROPERTY = "dmvCacheInitialCapacity";
    private Integer cacheInitialCapacity;

    @Property(label = "Cache max size", value = Constants.DMV_LANDING_PAGE_CACHE_SERVICE_DEFAULT_MAX_SIZE, description = "Specifies the maximum number of entries the cache may contain.")
    private static final String CACHE_MAX_SIZE_PROPERTY = "dmvCacheMaxSize";
    private Long cacheMaxSize;

    @Reference
    ClientTransformationService clientTransformationService;


    @Override
    public ArrayList<Client> getAllClients(){

        ArrayList<Client> clientList = new ArrayList<Client>();


        return clientList;
    }
}
