package com.popularsafi.service;

import com.popularsafi.model.ReporteProvision;
import lombok.Data;

import java.util.List;

@Data
public class ProvisionResponse {

    private List<ReporteProvision> provision;

    public List<ReporteProvision> getProvision() {
        return provision;
    }

    public void setProvision(List<ReporteProvision> provision) {
        this.provision = provision;
    }
}

