package org.slzdevsnp.restclients.common.model;

import lombok.Data;

@Data

public abstract class ClientSingleResponse<T> extends ClientResponse<T> {
    private T entity;
}
