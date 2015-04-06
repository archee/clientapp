package com.icfi.clientapp.domain.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by 35243 on 3/27/15.
 */
public class Client {

    private int id;
    private String name;
    private String clientSince;
    private String industry;
    private String aemVersion;

    public Client(int id, String name, String clientSince, String industry, String aemVersion) {
        this.id = id;
        this.name = name;
        this.clientSince = clientSince;
        this.industry = industry;
        this.aemVersion = aemVersion;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getClientSince() {
        return clientSince;
    }

    public String getIndustry() {
        return industry;
    }

    public String getAemVersion() {
        return aemVersion;
    }

    @JsonCreator
    public static Client create(@JsonProperty("id") final int id,
                                @JsonProperty("name") final String name,
                                @JsonProperty("client-since") final String clientSince,
                                @JsonProperty("industry") final String industry,
                                @JsonProperty("aem-version") final String aemVersion){

        return new Client(id,name,clientSince,industry,aemVersion);

    }
}
