package com.tpspringcloud.kafkaconsumerservice.restcontrollers;

import com.tpspringcloud.kafkaconsumerservice.entities.Facture;
import com.tpspringcloud.kafkaconsumerservice.repositories.FactureRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FactureRestController {
    private final FactureRepository factureRepository;

    public FactureRestController(FactureRepository factureRepository) {
        this.factureRepository = factureRepository;
    }

    @GetMapping("/factures")
    public List<Facture> getFactures(){
        return factureRepository.findAll();
    }

    @GetMapping("/factures/{id}")
    public Facture getFacture(@PathVariable Long id){
        return factureRepository.getOne(id);
    }

}
