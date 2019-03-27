package com.wipro.assessment.cartservice.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;


@Data
@EqualsAndHashCode
@ToString
@Builder
@Document
public class Product implements Serializable {

    @Id
    private Long id;

    private Cart cart;

    private String name;

    private String description;

    private BigDecimal cost;
}
