package com.example.MCardSpring.Repository;

import com.example.MCardSpring.MainModel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Default bilgiilerin @Config ve @Bean ile yüklendiği sınıf
 */
@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    /**
     * Şifrenin encode - decode u için injection
     */
    @Autowired
    PasswordEncoder encoder;

    /**
     * İlk yapılması planlanan default tanımlamalar kümesi
     * @param applicantRepository: başvuran deposu
     * @param cityRepository: şehir deposu
     * @param opportunityRepository: olanak deposu
     * @param cityOpportunityRepository: şehir-olanak deposu
     * @param cardRepository: kart deposu
     * @param roleRepository: rol deposu
     * @param userRepository: kullanıcı deposu
     * @return: default kayıt değerlerini döner
     */
    @Bean
    CommandLineRunner initDatabase(ApplicantRepository applicantRepository, CityRepository cityRepository,
                                   OpportunityRepository opportunityRepository,
                                   CityOpportunityRepository cityOpportunityRepository, CardRepository cardRepository,
                                   RoleRepository roleRepository, UserRepository userRepository) {
        return args -> {
            // Default applicant verileri ve depoya kaydedilmesi
            Applicant yasinB = new Applicant("YASIN", "BUZGULU",
                    "05/09/1997", 53452312702L, "Normal",
                    "Öğrenci");
            yasinB = applicantRepository.save(yasinB);

            Applicant fuatB = new Applicant("FUAT", "BASGIL",
                    "01/12/1893", 14513286998L, "Yaşlı",
                    "Sivil");
            fuatB = applicantRepository.save(fuatB);

            // Default şehir verileri ve depoya kaydedilmesi
            City istanbul = new City();
            istanbul.setCityName("ISTANBUL");
            istanbul = cityRepository.save(istanbul);

            City adana = new City();
            adana.setCityName("ADANA");
            adana = cityRepository.save(adana);

            City ankara = new City();
            ankara.setCityName("ANKARA");
            ankara = cityRepository.save(ankara);

            City izmir = new City();
            izmir.setCityName("IZMIR");
            izmir = cityRepository.save(izmir);

            City bursa = new City();
            bursa.setCityName("BURSA");
            bursa = cityRepository.save(bursa);

            // Default olanak verileri ve depoya kaydı
            Opportunity parking = new Opportunity();
            parking.setName("PARKING");
            parking = opportunityRepository.save(parking);

            Opportunity library = new Opportunity();
            library.setName("LIBRARY");
            library = opportunityRepository.save(library);

            Opportunity museum = new Opportunity();
            museum.setName("MUSEUM");
            museum = opportunityRepository.save(museum);

            Opportunity theatre = new Opportunity();
            theatre.setName("THEATRE");
            theatre = opportunityRepository.save(theatre);

            // İstanbul şehri için şehir-olanak veri oluşturma ve kaydı
            CityOpportunity istanbulMuseum = new CityOpportunity(istanbul, museum, 250);
            istanbulMuseum = cityOpportunityRepository.save(istanbulMuseum);
            CityOpportunity istanbulParking = new CityOpportunity(istanbul, parking, 50);
            istanbulParking = cityOpportunityRepository.save(istanbulParking);
            CityOpportunity istanbulTheatre = new CityOpportunity(istanbul, theatre, 250);
            istanbulTheatre = cityOpportunityRepository.save(istanbulTheatre);
            CityOpportunity istanbulLibrary = new CityOpportunity(istanbul, library, 250);
            istanbulLibrary = cityOpportunityRepository.save(istanbulLibrary);

            // Bursa şehri için şehir-olanak veri oluşturma ve kaydı
            CityOpportunity bursaTheatre = new CityOpportunity(bursa, theatre, 400);
            bursaTheatre = cityOpportunityRepository.save(bursaTheatre);
            CityOpportunity bursaLibrary = new CityOpportunity(bursa, library, 400);
            bursaLibrary = cityOpportunityRepository.save(bursaLibrary);
            CityOpportunity bursaMuseum = new CityOpportunity(bursa, museum, 400);
            bursaMuseum = cityOpportunityRepository.save(bursaMuseum);

            // Ankara şehri için şehir-olanak veri oluşturma ve kaydı
            CityOpportunity ankaraTheatre = new CityOpportunity(ankara, theatre, 400);
            ankaraTheatre = cityOpportunityRepository.save(ankaraTheatre);
            CityOpportunity ankaraLibrary = new CityOpportunity(ankara, library, 400);
            ankaraLibrary = cityOpportunityRepository.save(ankaraLibrary);
            CityOpportunity ankaraParking = new CityOpportunity(ankara, parking, 400);
            ankaraParking = cityOpportunityRepository.save(ankaraParking);
            CityOpportunity ankaraMuseum = new CityOpportunity(ankara, museum, 400);
            ankaraMuseum = cityOpportunityRepository.save(ankaraMuseum);

            // İzmir şehri için şehir-olanak veri oluşturma ve kaydı
            CityOpportunity izmirMuseum = new CityOpportunity(izmir, museum, 400);
            izmirMuseum = cityOpportunityRepository.save(izmirMuseum);
            CityOpportunity izmirTheatre = new CityOpportunity(izmir, theatre, 400);
            izmirTheatre = cityOpportunityRepository.save(izmirTheatre);

            // Adana şehri için şehir-olanak veri oluşturma ve kaydı
            CityOpportunity adanaParking = new CityOpportunity(adana, parking, 400);
            adanaParking = cityOpportunityRepository.save(adanaParking);
            CityOpportunity adanaMuseum = new CityOpportunity(adana, museum, 400);
            adanaMuseum = cityOpportunityRepository.save(adanaMuseum);
            CityOpportunity adanaLibrary = new CityOpportunity(adana, library, 400);
            adanaLibrary = cityOpportunityRepository.save(adanaLibrary);

            // İstanbul şehir-olanaklarından istenenlerin bir listeye eklenmesi
            List<CityOpportunity> istanbulOpportunityList = new ArrayList<>();
            istanbulOpportunityList.add(istanbulMuseum);
            istanbulOpportunityList.add(istanbulParking);
            istanbulOpportunityList.add(istanbulTheatre);
            istanbulOpportunityList.add(istanbulLibrary);

            // Ankara şehir-olanaklarından istenenlerin bir listeye eklenmesi
            List<CityOpportunity> ankaraOpportunityList = new ArrayList<>();
            ankaraOpportunityList.add(ankaraTheatre);
            ankaraOpportunityList.add(ankaraLibrary);
            ankaraOpportunityList.add(ankaraParking);
            ankaraOpportunityList.add(ankaraMuseum);

            // Adana şehir-olanaklarından istenenlerin bir listeye eklenmesi
            List<CityOpportunity> adanaOpportunityList = new ArrayList<>();
            adanaOpportunityList.add(adanaMuseum);
            adanaOpportunityList.add(adanaParking);
            adanaOpportunityList.add(adanaLibrary);

            // bursa şehir-olanaklarından istenenlerin bir listeye eklenmesi
            List<CityOpportunity> bursaOpportunityList = new ArrayList<>();
            bursaOpportunityList.add(bursaTheatre);
            bursaOpportunityList.add(bursaLibrary);
            bursaOpportunityList.add(bursaMuseum);

            // İzmir şehir-olanaklarından istenenlerin bir listeye eklenmesi
            List<CityOpportunity> izmirOpportunityList = new ArrayList<>();
            izmirOpportunityList.add(izmirTheatre);
            izmirOpportunityList.add(izmirMuseum);

            //  Yukarıdaki veriler ile kart oluşturması ve kaydı
            Card testCard = new Card(250, "09/09/2023", yasinB,
                    bursaOpportunityList, 4);
            testCard = cardRepository.save(testCard);

            Card testCard2 = new Card(20, "09/12/2024", fuatB,
                    istanbulOpportunityList, 5);
            cardRepository.save(testCard2);

            // Role belirlenmesi ve kaydı
            Set<Role> roles = new HashSet<>();
            Set<Role> userRoles = new HashSet<>();
            Role adminRole = new Role(ERole.ROLE_ADMIN);
            Role userRole = new Role(ERole.ROLE_USER);
            adminRole = roleRepository.save(adminRole);
            userRole = roleRepository.save(userRole);
            roles.add(adminRole);

            // Yeni kullanıcı oluşturulması ve kaydı
            User yasin = new User("admin", "admin@admin.com", encoder.encode("admin24"));
            User admin = new User("admin2", "admin2@admin.com", encoder.encode("admin24"));
            User murat = new User("hasan", "hasan@gmail.com", encoder.encode("hasder"));
            yasin.setRoles(roles);
            admin.setRoles(roles);
            murat.setRoles(userRoles);
            userRepository.save(yasin);
            userRepository.save(admin);
            userRepository.save(murat);
        };

    }

}
