package com.tpspringcloud.kafkaconsumerservice;

import com.opencsv.CSVWriter;
import com.tpspringcloud.kafkaconsumerservice.deserializers.KafkaFactureDeserializer;
import com.tpspringcloud.kafkaconsumerservice.entities.Facture;
import com.tpspringcloud.kafkaconsumerservice.repositories.FactureRepository;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableKafka
public class KafkaConsumerServiceApplication {

    private final String KAFKA_BROKER_URL = "localhost:9092";
    private final String TOPIC_NAME = "FACTURATION";
    private final String CLIENT_ID = "client_consum_1";
    private final String GROUP_ID = "sample-facturation-group";


    public static void main(String[] args) {
        SpringApplication.run(KafkaConsumerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner startConsumer(FactureRepository factureRepository){
        return args -> {
            Properties properties = new Properties();
            properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BROKER_URL);
            properties.put(ConsumerConfig.CLIENT_ID_CONFIG, CLIENT_ID);
            properties.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
            properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
            properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
            properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
            properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class.getName());
            properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaFactureDeserializer.class.getName());

            KafkaConsumer<Integer, Facture> kafkaConsumer = new KafkaConsumer<>(properties);
            kafkaConsumer.subscribe(Collections.singletonList(TOPIC_NAME));

            Executors.newScheduledThreadPool(1).scheduleAtFixedRate(()->{
                System.out.println("------------------------------------");
                ConsumerRecords<Integer, Facture> consumerRecords=kafkaConsumer.poll(Duration.ofMillis(10));
                try {
                    CSVWriter csvWriter = new CSVWriter(new FileWriter("src/factures.csv", true));
                    consumerRecords.forEach(consumerRecord->{
                        System.out.println("Key=>"+consumerRecord.key()+", Facture=>"+consumerRecord.value()+", offsets=>"+consumerRecord.offset());
                        Facture facture = factureRepository.save(consumerRecord.value());
                        csvWriter.writeNext(new String[]{String.valueOf(facture.getId()), facture.getNomClient(), String.valueOf(facture.getTotal())}, false);
                    });
                    csvWriter.flush();
                    csvWriter.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            },1,1, TimeUnit.SECONDS);
        };
    }

}
