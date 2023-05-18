package com.nhn.property;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ConfigVO {

    @JsonProperty("host_info")
    private List<HostInfo> hostInfoList;

    private int port;

    public List<HostInfo> getHost() {
        return hostInfoList;
    }

    public void setHost(List<HostInfo> host) {
        this.hostInfoList = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public static class HostInfo {
        @JsonProperty("error_page")
        private ErrorPage errorPage;
        @JsonProperty("home_directory")
        private String homeDirectory;
        @JsonProperty("host_name")
        private String hostName;

        public ErrorPage getErrorPage() {
            return errorPage;
        }

        public void setErrorPage(ErrorPage errorPage) {
            this.errorPage = errorPage;
        }

        public String getHomeDirectory() {
            return homeDirectory;
        }

        public void setHomeDirectory(String homeDirectory) {
            this.homeDirectory = homeDirectory;
        }

        public String getHostName() {
            return hostName;
        }

        public void setHostName(String hostName) {
            this.hostName = hostName;
        }

        @Override
        public String toString() {
            return "Host{" +
                    "errorPage=" + errorPage +
                    ", homeDirectory='" + homeDirectory + '\'' +
                    ", hostName='" + hostName + '\'' +
                    '}';
        }
    }

    public static class ErrorPage {
        private String error500;
        private String error404;
        private String error403;

        public String getError500() {
            return error500;
        }

        public void setError500(String error500) {
            this.error500 = error500;
        }

        public String getError404() {
            return error404;
        }

        public void setError404(String error404) {
            this.error404 = error404;
        }

        public String getError403() {
            return error403;
        }

        public void setError403(String error403) {
            this.error403 = error403;
        }

        @Override
        public String toString() {
            return "ErrorPage{" +
                    "error500='" + error500 + '\'' +
                    ", error404='" + error404 + '\'' +
                    ", error403='" + error403 + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "PropertyVO{" +
                "hostInfoList=" + hostInfoList +
                ", port=" + port +
                '}';
    }
}
