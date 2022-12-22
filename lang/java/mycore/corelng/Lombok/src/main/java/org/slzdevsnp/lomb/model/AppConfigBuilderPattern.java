package org.slzdevsnp.lomb.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AppConfigBuilderPattern {

    private String host;
    private int port;
    private boolean useHttps;

    private long connectTimeout;
    private long readTimeout;

    private String username;
    private String password;
}
