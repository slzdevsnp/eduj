package org.slzdevsnp.restclients.common.model;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString(callSuper = true)
public abstract class ClientListResponse<T> extends ClientResponse {
    private List<T> entity;
}
