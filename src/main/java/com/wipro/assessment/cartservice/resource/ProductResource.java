package com.wipro.assessment.cartservice.resource;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.math.BigDecimal;


@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Builder
@Data
@ToString
public class ProductResource {
    private Long id;

    @Size(min = 3, max = 255)
    private String name;

    @Size(min = 3, max = 2000)
    private String description;

    @Min(value = 0)
    private BigDecimal cost;

    @Valid
    private CartResource cart;
}
