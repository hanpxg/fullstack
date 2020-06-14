package com.ibm.w3.controller;

import com.ibm.w3.entity.CompanyEntity;
import com.ibm.w3.entity.IPODetailEntity;
import com.ibm.w3.entity.StockPriceEntity;
import com.ibm.w3.repository.CompanyRepository;
import com.ibm.w3.repository.IPORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CompanyHandler {
    @Autowired
    private CompanyRepository repository;
    @Autowired
    private IPORepository ipoRepository;

    @GetMapping("/findAll")
    public List<CompanyEntity> findAll() {
        System.out.println("findAll()");
        return repository.findAll();
    }

    @GetMapping("/findById/{id}")
    public CompanyEntity findById(@PathVariable("id") Long id) {
        return repository.getById(id);
    }

    @PostMapping(value={"/save","/update"})
    public CompanyEntity save(@RequestBody CompanyEntity company) {
//        if (company != null && company.getId() != null) {
//            company.setSector();
//        }
        return repository.save(company);
    }

    @GetMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        repository.deleteById(id);
    }

    @GetMapping("/stock_price/{id}")
    public List<StockPriceEntity> getCompanyStockPrice(@PathVariable("id") Long id) {
//        String jpql = "";
        return repository.getById(id).getPrices();
//        return null;
    }
    @GetMapping("/match_company/{key}")
    public List<CompanyEntity> getMatchingCompanies(@PathVariable("key") String key) {
        return null;
    }

    @GetMapping("/ipo_detail/{id}")
    public List<IPODetailEntity> getIPODetail(@PathVariable("id") Long id) {
//        String jpql = "";
        return repository.getById(id).getDetails();
//        return null;
    }

    @GetMapping("/ipo_detail_all")
    public List<IPODetailEntity> getAllIPODetail() {
//        String jpql = "";
        return ipoRepository.findAll();
//        return null;
    }

}
