package com.tpspringcloud.kafkaproducerservice;

import com.mifmif.common.regex.Generex;
import com.tpspringcloud.kafkaproducerservice.models.Facture;
import com.tpspringcloud.kafkaproducerservice.serializers.KafkaFactureSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class KafkaProducerServiceApplication {
    private int counter;
    private final String KAFKA_BROKER_URL = "localhost:9092";
    private final String TOPIC_NAME = "FACTURATION";
    private final String CLIENT_ID = "facturation_prod_1";

    public static void main(String[] args) {
        SpringApplication.run(KafkaProducerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner startProducer(){
        return args -> {

            Properties properties = new Properties();
            properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BROKER_URL);
            properties.put(ProducerConfig.CLIENT_ID_CONFIG, CLIENT_ID);
            properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
            properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaFactureSerializer.class.getName());

            KafkaProducer<Integer, Facture> kafkaProducer = new KafkaProducer<>(properties);
            Generex generex = new Generex("Youssef ACHIR|Ouiam KHATTACH|Yasser CHIHAB|Oussama AIT ALLA|Hajar LABLAOUI");

            Executors.newScheduledThreadPool(1).scheduleAtFixedRate(()->{
                ++counter;
                Facture facture = new Facture();
                facture.setNomClient(generex.random());
                facture.setTotal(Math.random()*1000);
                System.out.println(facture);
                kafkaProducer.send(new ProducerRecord<>(TOPIC_NAME, counter, facture), ((recordMetadata, e) -> {

                }));
            }, 1, 1, TimeUnit.SECONDS);

        };
    }

}
