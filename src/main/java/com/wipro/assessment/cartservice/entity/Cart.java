package com.wipro.assessment.cartservice.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;


@Data
@EqualsAndHashCode
@ToString
@Document
public class Cart implements Serializable {

    @Id
    private String id;

    @Transient
    private List<Product> products;
}
