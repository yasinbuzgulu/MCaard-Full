package com.example.MCardSpring.Controller;

import com.example.MCardSpring.MainModel.Card;
import com.example.MCardSpring.Repository.CardRepository;
import com.example.MCardSpring.Service.CardService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Kartımızın kontrolleri yapılır (CRUD işlemleri )
 */
@RestController
public class CardController {
    /**
     * Controller içinde kullanılacak card serivisi instance oluşturmadan constructor ile çağrılır
     */
    CardService cardService;
    CardRepository cardRepository;

    private CardController(CardService cardService, CardRepository cardRepository) {
        this.cardService = cardService;
        this.cardRepository = cardRepository;
    }

    /**
     * GET ile tüm kartlar çağırılır
     *
     * @return: tüm kartları döner
     */
    @GetMapping("/cards")
    public ResponseEntity<List<Card>> listCards() {
        List<Card> cards = cardService.listCards();
        return ResponseEntity.ok().body(cards);
    }

    /**
     * GET request i URI da id ile kullanılır ve sadece bir applicant çağırılır
     *
     * @param id:Çağırılan applicant'ın id si
     * @return:uri daki id ye sahip applicant ı döner.
     */
    @GetMapping("/cards/{id}")
    public ResponseEntity<Card> getCardById(@PathVariable Long id) {
        Card card = cardService.getCardById(id);
        return new ResponseEntity<>(card, HttpStatus.OK);
    }

    /**
     * POST ile yeni bir kart oluşturulur
     *
     * @param card:Ouşturulacak kart nesnesi
     * @return: oluşturulan kartı döner
     */
    @PostMapping("/cards")
    public ResponseEntity<Card> createCard(@RequestBody Card card) {
        card = cardService.createCard(card);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("card", "/cards/" + card.getId().toString());
        return new ResponseEntity<>(card, httpHeaders, HttpStatus.CREATED);
    }

    /**
     * PUT ile,ilgili ID ye sahip kart düzenlenir
     *
     * @param card:düzenleme yapılan kart nesnesş
     * @param id:            düzenleme yapılcak kartın id si
     * @return: girilen id ye sahip kartın güncel halini döner
     */
    @PutMapping("/cards/{id}")
    public ResponseEntity<Card> updateCard(@RequestBody Card card, @PathVariable("id") Long id) {
        cardService.updateCard(card, id);
        return new ResponseEntity<>(cardService.getCardById(id), HttpStatus.OK);
    }

    /**
     * DELETE ile, id ye sahip kart silinir
     *
     * @param id: silinecek kartın id si
     * @return: status u 204 döner ("No Content")
     */
    @DeleteMapping("cards/{id}")
    public ResponseEntity<Card> deleteCard(@PathVariable("id") Long id) {
        cardService.deleteCard(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
