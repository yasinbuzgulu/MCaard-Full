package com.example.MCardSpring.Controller;

import com.example.MCardSpring.MainModel.CityOpportunity;
import com.example.MCardSpring.Repository.CityOpportunityRepository;
import com.example.MCardSpring.Service.CityOpportunityService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Şehir-olanak kontrolleri yapılır (CRUD işlemleri )
 */
@RestController
public class CityOpportunityController {
    /**
     * Controller içinde kullanılacak cityOpportunity serivisi instance oluşturmadan constructor ile çağrılır
     */
    CityOpportunityService cityOpportunityService;
    CityOpportunityRepository cityOpportunityRepository;

    public CityOpportunityController(CityOpportunityService cityOpportunityService,
                                     CityOpportunityRepository cityOpportunityRepository) {
        this.cityOpportunityService = cityOpportunityService;
        this.cityOpportunityRepository = cityOpportunityRepository;
    }

    /**
     * GET ile tüm şehir-olanaklar çağırılır
     *
     * @return: tüm şehir-olanakları döner
     */
    @GetMapping("/city-opportunities")
    public ResponseEntity<List<CityOpportunity>> listCityOpportunities() {
        List<CityOpportunity> cityOpportunities = cityOpportunityService.listCityOpportunities();
        return ResponseEntity.ok().body(cityOpportunities);
    }

    /**
     * GET request i URI da id ile kullanılır ve sadece bir şehir-olanak çağırılır
     *
     * @param id: çağırılan şehir-olanak kaydının id si
     * @return: id sine sahip şehir-olanak kaydını döner (status 200 ve "OK" ile)
     */
    @GetMapping("/city-opportunities/{id}")
    public ResponseEntity<CityOpportunity> getCityOpportunityById(@PathVariable Long id) {
        CityOpportunity cityOpportunity = cityOpportunityService.getCityOpportunityById(id);
        return new ResponseEntity<>(cityOpportunity, HttpStatus.OK);
    }

    /**
     * POST ile yeni bir şehir-olanak kaydı oluşturulur
     *
     * @param cityOpportunity: oluşturulacak yeni şehir-olanak kaydu
     * @return: yeni şehir-olanak kayıt edilmiş status u 201 "CREATED" döner
     */
    @PostMapping("city-opportunities")
    public ResponseEntity<CityOpportunity> createCityOpportunity(@RequestBody CityOpportunity cityOpportunity) {
        cityOpportunity = cityOpportunityService.createCityOpportunity(cityOpportunity);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("cityOpportunity", "/cityOpportunities/" + cityOpportunity.getId().toString());
        return new ResponseEntity<>(cityOpportunity, httpHeaders, HttpStatus.CREATED);
    }

    /**
     * PUT ile,ilgili ID ye sahip şehir-olanak kaydı düzenlenir
     *
     * @param cityOpportunity: düzenlenecek şehir-olanak kaydı
     * @param id:              düzenlemenin yapılcağı şehir-olanak kaydının id si
     * @return: güncelenmiş şehir-olanak kaydı döndürülür
     */
    @PutMapping("/city-opportunities/{id}")
    public ResponseEntity<CityOpportunity> updateCityOpportunity(@RequestBody CityOpportunity cityOpportunity, @PathVariable("id") Long id) {
        cityOpportunityService.updateCityOpportunity(cityOpportunity, id);
        return new ResponseEntity<>(cityOpportunityService.getCityOpportunityById(id), HttpStatus.OK);
    }

    /**
     * DELETE ile, id ye sahip şehir-olanak kaydı silinir
     *
     * @param id: Silinecek şehir-olanka kaydının id si
     * @return: status u 204 döner ("No Content")
     */
    @DeleteMapping("city-opportunities/{id}")
    public ResponseEntity<CityOpportunity> deleteCityOpportunity(@PathVariable("id") Long id) {

        cityOpportunityService.deleteCityOpportunity(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
