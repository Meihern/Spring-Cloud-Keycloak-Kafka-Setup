package com.tpspringcloud.kafkaproducerservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @AllArgsConstructor @NoArgsConstructor @ToString(exclude = "id")
public class Facture {
    private Long id;
    private String nomClient;
    private double total;
}
