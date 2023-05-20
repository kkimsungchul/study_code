package com.nhn.property;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * config.json을 VO로 변환한 클래스
 * @author 김성철
 */
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
