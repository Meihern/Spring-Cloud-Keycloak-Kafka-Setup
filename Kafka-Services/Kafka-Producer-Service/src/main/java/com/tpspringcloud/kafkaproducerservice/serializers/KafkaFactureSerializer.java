package com.tpspringcloud.kafkaproducerservice.serializers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tpspringcloud.kafkaproducerservice.models.Facture;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class KafkaFactureSerializer implements Serializer<Facture> {

    @Override
    public void configure(Map configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String s, Facture facture) {
        byte[] retValue = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try{
         retValue = objectMapper.writeValueAsBytes(facture);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return retValue;
    }

    @Override
    public void close() {

    }
}
