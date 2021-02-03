package com.tpspringcloud.kafkaconsumerservice.deserializers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tpspringcloud.kafkaconsumerservice.entities.Facture;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.util.Map;

public class KafkaFactureDeserializer implements Deserializer<Facture> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public Facture deserialize(String s, byte[] bytes) {
        ObjectMapper mapper = new ObjectMapper();
        Facture facture = null;
        try {
            facture = mapper.readValue(bytes, Facture.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return facture;
    }

    @Override
    public void close() {

    }
}
