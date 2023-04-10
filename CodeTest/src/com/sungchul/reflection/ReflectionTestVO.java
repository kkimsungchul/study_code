package com.sungchul.reflection;

import java.util.ArrayList;
import java.util.List;

public class ReflectionTestVO {

    private String serverName;
    private String hostName;
    private String serverIP;
    private List<String> serverDisk = new ArrayList<String>();
    private String serverDiskPart;
    private String serverDiskTotal;
    private String serverDiskUsed;
    private String serverDiskFree;
    private String serverMemoryTotal;
    private String serverMemoryUsed;
    private String serverMemoryFree;

    public String getServerName() {
        return serverName;
    }

    public void serverName(String serverName) {
        this.serverName = serverName;
    }

    public String getHostName() {
        return hostName;
    }

    public void hostName(String hostName) {
        this.hostName = hostName;
    }

    public String getServerIP() {
        return serverIP;
    }

    public void serverIP(String serverIP) {
        this.serverIP = serverIP;
    }

    public List<String> getServerDisk() {
        return serverDisk;
    }

    public String getServerDiskPart() {
        return serverDiskPart;
    }

    public void serverDiskPart(String serverDiskPart) {
        this.serverDiskPart = serverDiskPart;
    }

    public void serverDisk(String serverDisk) {
        this.serverDisk.add(serverDisk);
    }

    public String getServerDiskTotal() {
        return serverDiskTotal;
    }

    public void serverDiskTotal(String serverDiskTotal) {
        this.serverDiskTotal = serverDiskTotal;
    }

    public String getServerDiskUsed() {
        return serverDiskUsed;
    }

    public void serverDiskUsed(String serverDiskUsed) {
        this.serverDiskUsed = serverDiskUsed;
    }

    public String getServerDiskFree() {
        return serverDiskFree;
    }

    public void serverDiskFree(String serverDiskFree) {
        this.serverDiskFree = serverDiskFree;
    }

    public String getServerMemoryTotal() {
        return serverMemoryTotal;
    }

    public void serverMemoryTotal(String serverMemoryTotal) {
        this.serverMemoryTotal = serverMemoryTotal;
    }

    public String getServerMemoryUsed() {
        return serverMemoryUsed;
    }

    public void serverMemoryUsed(String serverMemoryUsed) {
        this.serverMemoryUsed = serverMemoryUsed;
    }

    public String getServerMemoryFree() {
        return serverMemoryFree;
    }

    public void serverMemoryFree(String serverMemoryFree) {
        this.serverMemoryFree = serverMemoryFree;
    }

    @Override
    public String toString() {
        return "ReflectionTestVO{" +
                "serverName='" + serverName + '\'' +
                ", hostName='" + hostName + '\'' +
                ", serverIP='" + serverIP + '\'' +
                ", serverDisk=" + serverDisk +
                ", serverDiskPart='" + serverDiskPart + '\'' +
                ", serverDiskTotal='" + serverDiskTotal + '\'' +
                ", serverDiskUsed='" + serverDiskUsed + '\'' +
                ", serverDiskFree='" + serverDiskFree + '\'' +
                ", serverMemoryTotal='" + serverMemoryTotal + '\'' +
                ", serverMemoryUsed='" + serverMemoryUsed + '\'' +
                ", serverMemoryFree='" + serverMemoryFree + '\'' +
                '}';
    }
}
