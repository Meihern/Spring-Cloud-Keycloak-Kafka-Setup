package com.tpspringcloud.kafkastreamservice.services;

import com.tpspringcloud.kafkastreamservice.models.Facture;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class FacturationService {
    @Bean
    public Function<KStream<Integer, Facture>, KStream<String, Double>> realTimeFactureTotal(){
        return (inputStream)-> inputStream
                .map((k,v)->new KeyValue<>(v.getNomClient(), v.getTotal()))
                .groupByKey(Grouped.with(Serdes.String(), Serdes.Double()))
                .reduce(Double::sum)
                .toStream();
    }
}
