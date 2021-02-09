package com.tpspringcloud.kafkastreamservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Facture {
    Long id;
    String nomClient;
    double total;
}
