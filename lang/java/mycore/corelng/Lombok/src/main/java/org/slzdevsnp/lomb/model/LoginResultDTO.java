package org.slzdevsnp.lomb.model;


import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.net.URL;
import java.time.Duration;
import java.time.Instant;

@RequiredArgsConstructor
@Accessors(fluent = true) @Getter

public class LoginResultDTO {

    protected final @NonNull Instant loginTs;
    protected final @NonNull String authToken;
    protected final @NonNull Duration tokenValidity;
    protected final @NonNull URL tokenRefreshUrl;

}
