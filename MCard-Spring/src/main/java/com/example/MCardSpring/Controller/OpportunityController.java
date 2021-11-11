package com.example.MCardSpring.Controller;

import com.example.MCardSpring.MainModel.Opportunity;
import com.example.MCardSpring.Service.OpportunityService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OpportunityController {

    /**
     * Controller içinde kullanılacak opportunity serivisi instance oluşturmadan constructor ile çağrılır (inject edilir)
     */
    OpportunityService opportunityService;

    public OpportunityController(OpportunityService opportunityService) {
        this.opportunityService = opportunityService;
    }

    /**
     * GET ile tüm opportunity ler çağırılır
     *
     * @return: opportunities (kayıtlı tüm olanaklar)
     */
    @GetMapping("/opportunities")
    public ResponseEntity<List<Opportunity>> listOpportunities() {
        List<Opportunity> opportunities = opportunityService.listOpportunities();
        return ResponseEntity.ok().body(opportunities);
    }

    /**
     * GET ile ve URI da id ile sadece bir opportunity çağırılır
     *
     * @param id : çağırılacak opportunity nin ID si
     * @return : URI da girilen id değerine sahip opportunity yi döndürür
     */
    @GetMapping("/opportunities/{id}")
    public ResponseEntity<Opportunity> getOpportunityById(@PathVariable Long id) {
        Opportunity opportunity = opportunityService.getOpportunityById(id);
        return new ResponseEntity<>(opportunity, HttpStatus.OK);
    }

    /**
     * POST ile yeni bir opportunity(olanak) oluşturulur
     *
     * @param opportunity : yeni oluşturulan olanak
     * @return : newOpportunity(yeni kaydedilen opportunity)
     */
    @PostMapping("/opportunities")
    public ResponseEntity<Opportunity> createOpportunity(@RequestBody Opportunity opportunity) {
        opportunity = opportunityService.createOpportunity(opportunity);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("opportunity", "/opportunities/" + opportunity.getId().toString());
        return new ResponseEntity<>(opportunity, httpHeaders, HttpStatus.CREATED);
    }

    /**
     * PUT ile ilgili ID ye sahip opportunity düzenlenir
     *
     * @param opportunity: olanağın düzenlenmiş son hali
     * @param id:          düzenleme işleminin yapılacağı olanak id si
     * @return : girilen id ye sahip olanağın güncellemesini döndürür
     */
    @PutMapping("/opportunities/{id}")
    public ResponseEntity<Opportunity> updateOpportunity(@RequestBody Opportunity opportunity, @PathVariable("id") Long id) {
        opportunityService.updateOpportunity(opportunity, id);
        return new ResponseEntity<>(opportunityService.getOpportunityById(id), HttpStatus.OK);
    }

    /**
     * @param id: silinecek olanağın id ' si
     * @return: status u 204 döner ("No Content")
     */
    @DeleteMapping("/opportunities/{id}")
    public ResponseEntity<Opportunity> deleteOpportunity(@PathVariable("id") Long id) {
        opportunityService.deleteOpportunity(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
