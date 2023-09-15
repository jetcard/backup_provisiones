package com.popularsafi.service;

import com.popularsafi.model.CalculoIC;
import lombok.Data;

import java.util.List;

@Data
public class ProvisionResponse {

    private List<CalculoIC> provision;

    public List<CalculoIC> getProvision() {
        return provision;
    }

    public void setProvision(List<CalculoIC> provision) {
        this.provision = provision;
    }
}

