package com.tpspringcloud.kafkaproducerservice.models;

import lombok.*;

@Data @AllArgsConstructor @NoArgsConstructor @ToString(exclude = "id")
public class Facture {
    private Long id;
    private String nomClient;
    private double total;
}
