package com.example.MCardSpring.Service;

import com.example.MCardSpring.Exception.OpportunityNotFoundException;
import com.example.MCardSpring.MainModel.Opportunity;
import com.example.MCardSpring.Repository.OpportunityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OpportunityService {
    /**
     * Service içinde kullanılacak opportunity repository için instance oluşturmadan constructor ile çağrılır
     */
    OpportunityRepository opportunityRepository;

    public OpportunityService(OpportunityRepository opportunityRepository) {
        this.opportunityRepository = opportunityRepository;
    }

    /**
     * Yeni opportunity oluşturma metodu
     *
     * @param opportunity: yeni opportunity nesnesi
     * @return: depoya kayıt döner
     */
    public Opportunity createOpportunity(Opportunity opportunity) {
        return opportunityRepository.save(opportunity);
    }

    /**
     * Opportunity silme metodu
     *
     * @param id: silinecek olanak id si
     */
    public void deleteOpportunity(Long id) {
        opportunityRepository.deleteById(id);
    }

    /**
     * Belirli olanak alma metodu
     *
     * @param id: istenen olanağın id si
     * @return: 'id' li olanağı döner
     */
    public Opportunity getOpportunityById(Long id) {
        return opportunityRepository.findById(id)
                .orElseThrow(() -> new OpportunityNotFoundException(id));
    }

    /**
     * Olanak güncelleme metodu
     *
     * @param newOpportunity: güncel olanak verisi
     * @param id:             güncellencek olanağın id si
     */
    public void updateOpportunity(Opportunity newOpportunity, Long id) {
        Opportunity opportunity = opportunityRepository.findById(id).get();
        opportunity.setName(newOpportunity.getName());

    }

    /**
     * Tüm olanakları listeleme metodu
     *
     * @return: tüm olanakları döner
     */
    public List<Opportunity> listOpportunities() {
        return new ArrayList<>(opportunityRepository.findAll());
    }

}
