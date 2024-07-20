package com.houseofpizza.representation;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.EmbeddedWrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseRepresentationModel<ID, D extends RepresentationModel<? extends D>> extends RepresentationModel<D> {

    private ID id;

    private Long version;

    /**
     * The @JsonUnwrapped annotation is required as EmbeddedWrappers
     * are by default serialized in a "_embedded" container that has to be added directly into the top level object.
     */
    @Getter
    @Setter
    @JsonUnwrapped
    private CollectionModel<EmbeddedWrapper> embeddeds;

}

