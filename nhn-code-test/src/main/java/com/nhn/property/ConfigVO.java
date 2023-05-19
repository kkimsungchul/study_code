package com.nhn.property;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ConfigVO {

    @JsonProperty("host_info")
    private List<HostInfoVO> hostInfoList;

    private int port;

    public List<HostInfoVO> getHost() {
        return hostInfoList;
    }

    public void setHost(List<HostInfoVO> host) {
        this.hostInfoList = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }



    @Override
    public String toString() {
        return "PropertyVO{" +
                "hostInfoList=" + hostInfoList +
                ", port=" + port +
                '}';
    }
}
