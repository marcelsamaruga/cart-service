package com.wipro.assessment.cartservice.resource;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;


@JsonInclude(JsonInclude.Include.NON_EMPTY)
@ToString
@Data
public class CartResource {
    @NotNull
    private String id;
    @Valid
    List<ProductResource> products;
}
