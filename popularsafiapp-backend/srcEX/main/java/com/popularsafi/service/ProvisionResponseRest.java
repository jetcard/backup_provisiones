package com.popularsafi.service;

public class ProvisionResponseRest extends ResponseRest{

    private ProvisionResponse provisionResponse = new ProvisionResponse();

    public ProvisionResponse getProvisionResponse() {
        return provisionResponse;
    }

    public void setProvisionResponse(ProvisionResponse provisionResponse) {
        this.provisionResponse = provisionResponse;
    }
}
