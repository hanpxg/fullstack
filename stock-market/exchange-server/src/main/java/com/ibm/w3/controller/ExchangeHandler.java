package com.ibm.w3.controller;

import com.ibm.w3.entity.CompanyEntity;
import com.ibm.w3.entity.StockExchangeEntity;
import com.ibm.w3.repository.StockExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExchangeHandler {
    @Autowired
    private StockExchangeRepository repository;

    @GetMapping("/findAll")
    public List<StockExchangeEntity> findAll() {
        return repository.findAll();
    }

    @PostMapping(value={"/save","/update"})
    public StockExchangeEntity save(@RequestBody StockExchangeEntity exchange) {
        return repository.save(exchange);
    }

    @GetMapping("/companyById/{id}")
    public List<CompanyEntity> getCompanyById(@PathVariable("id") Long id) {
        return repository.getById(id).getCompanies();
    }
}
