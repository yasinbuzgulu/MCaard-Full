package com.example.MCardSpring.StepDefs;

import com.example.MCardSpring.MainModel.Applicant;
import com.example.MCardSpring.MainModel.ERole;
import com.example.MCardSpring.MainModel.Role;
import com.example.MCardSpring.MainModel.User;
import com.example.MCardSpring.Payload.Response.JwtResponse;
import com.example.MCardSpring.Repository.RoleRepository;
import com.example.MCardSpring.Repository.UserRepository;
import com.example.MCardSpring.Security.Jwt.JwtUtils;
import com.example.MCardSpring.Service.ApplicantService;
import com.example.MCardSpring.TestConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.ParseException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@CucumberContextConfiguration
@AutoConfigureMockMvc
@ContextConfiguration(classes = TestConfiguration.class)
public class ApplicantStepDefs {

    private final Applicant fuatB = new Applicant("Fuat", "Basgil",
            "01/12/1893", 14513286998L, "Yasli",
            "Sivil");
    ResultActions action;

    Map<String, Object> contex = new HashMap<>();
    @Autowired
    ApplicantService applicantService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private MockMvc mvc;
    private String reqBody = null;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    // Birinci  Senaryo
    @Given("Başvuranın tüm bilgileri var iken")
    public void basvuraninTumBilgileriVarIken() throws Throwable {

    }

    @Given("{string} kullanıcısı kayıtlı iken")
    public void kullanicisiKayitliIken(String key) throws Throwable {
        User tekin = new User("tekin", "tekin@gmail.com", "tekin1110");
        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN).get();
        Set<Role> roles = new HashSet<>();
        roles.add(adminRole);
        tekin.setRoles(roles);
        this.contex.put(key, tekin);
        mvc.perform(MockMvcRequestBuilders
                .post("http://localhost:8080/api/auth/signup")
                .content(asJsonString(tekin))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    @And("{string} kullanıcısı ile giriş yapılı iken")
    public void kullanicisiIleGirisYapiliIken(String key) throws Throwable {
        User user = (User) contex.get(key);
        action = mvc.perform(MockMvcRequestBuilders
                .post("http://localhost:8080/api/auth/signin")
                .content(asJsonString(user))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        ObjectMapper mapper = new ObjectMapper();
        JwtResponse jwtResponse = mapper.readValue(action.andReturn().getResponse().getContentAsString(), JwtResponse.class);
        this.contex.put("token", jwtResponse.getAccessToken());
    }

    @When("Bu bilgiler post edildiğinde")
    public void buBilgilerPostEdildiginde() throws Throwable {
        String jwtResponse = (String) this.contex.get("token");
        action = mvc.perform(MockMvcRequestBuilders
                .post("http://localhost:8080/applicants")
                .content(asJsonString(fuatB))
                .header("Authorization", "Bearer " + jwtResponse)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

    }

    @Then("Başvuran başarıyla kaydedilir")
    public void basvuranBasariylaKaydedilir() throws Exception {
        action.andExpect(status().is(201));

    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    // İkinci  Senaryo
    @When("Tüm başvuranlar için Get request'i atıldığında")
    public void tumBasvuranlarIcinGetRequestIAtildiginda() throws Exception {
        String jwtResponse = (String) this.contex.get("token");
        action = mvc.perform(MockMvcRequestBuilders
                .get("http://localhost:8080/applicants")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + jwtResponse)
                .accept(MediaType.APPLICATION_JSON));
    }

    @Then("Tüm başvuranlar başarıyla listelenir")
    public void tumBasvuranlarBasariylaListelenir() throws Exception {
        action.andExpect(status().is(200));

    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    // Üçüncü  Senaryo
    @Given("{string} isminde bir başvuran var iken")
    public void ismindeBirBasvuranVarIken(String key) throws Throwable {
        Applicant applicant = applicantService.createApplicant(fuatB);
        this.contex.put(key, applicant);
    }

    @When("{string} başvurana silme isteği atıldığında")
    public void basvuranaSilmeIstegiAtildiginda(String key) throws Throwable {
        Applicant applicant = (Applicant) contex.get(key);
        String jwtResponse = (String) this.contex.get("token");
        long id = applicant.getId();
        action = mvc.perform(MockMvcRequestBuilders
                .delete("http://localhost:8080/applicants/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + jwtResponse)
                .accept(MediaType.APPLICATION_JSON));
    }

    @Then("Başvuran başarıyla silinir.")
    public void basvuranBasariylaSilinir() throws Throwable {
        action.andExpect(status().is(204));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    // Dördüncü  Senaryo
    @When("{string} başvuranına güncellemeler yapıldığında")
    public void basvuraninaGuncellemelerYapildiginda(String key) throws Throwable {
        Applicant applicant = (Applicant) contex.get(key);
        String jwtResponse = (String) this.contex.get("token");
        long id = applicant.getId();
        applicant.setName("Recep");
        applicant.setSurname("Bozkurt");
        applicant.setCitizenNumber(38436949006L);
        action = mvc.perform(MockMvcRequestBuilders
                .put("http://localhost:8080/applicants/" + id)
                .content(asJsonString(applicant))
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + jwtResponse)
                .accept(MediaType.APPLICATION_JSON));
    }

    @Then("Başvuran başarı ile güncellenir")
    public void basvuranBasariIleGuncellenir() throws Throwable {
        action.andExpect(status().is(200));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    // Beşinci  Senaryo
    @Given("Başvuranın isim bilgisi boş olarak girilmiş olmalı")
    public void basvuraninIsimBilgisiBosOlarakGirilmisOlmali() {
        fuatB.setName(null);

    }

    @Then("Hata verilir")
    public void hataVerilir() throws Throwable {
        action.andExpect(status().is(400));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    // Altıncı  Senaryo
    @Given("{string} başvuranı var iken")
    public void soyismiBosOlanBasvuraniVarIken(String key) throws ParseException {
        Applicant applicant = applicantService.createApplicant(fuatB);
        this.contex.put(key, applicant);
    }

    @When("{string} soyismi boş olarak post edildiğinde")
    public void postEdildiginde(String key) throws Throwable {
        Applicant applicant = (Applicant) contex.get(key);
        String jwtResponse = (String) this.contex.get("token");
        applicant.setSurname(null);
        action = mvc.perform(MockMvcRequestBuilders
                .post("http://localhost:8080/applicants")
                .content(asJsonString(applicant))
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + jwtResponse)
                .accept(MediaType.APPLICATION_JSON));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    // Yedinci  Senaryo
    @When("{string} doğum günü boş olarak post edildiğinde")
    public void dogumGunuBosOlarakPostEdildiginde(String key) throws Throwable {
        Applicant applicant = (Applicant) contex.get(key);
        String jwtResponse = (String) this.contex.get("token");
        applicant.setBirthDate(null);
        action = mvc.perform(MockMvcRequestBuilders
                .post("http://localhost:8080/applicants")
                .content(asJsonString(applicant))
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + jwtResponse)
                .accept(MediaType.APPLICATION_JSON));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    // Sekizinci  Senaryo
    @When("{string} tc kimlik numarası boş olarak post edildiğinde")
    public void tcKimlikNumarasiBosOlarakPostEdildiginde(String key) throws Throwable {
        Applicant applicant = (Applicant) contex.get(key);
        String jwtResponse = (String) this.contex.get("token");

        applicant.setCitizenNumber(0);
        action = mvc.perform(MockMvcRequestBuilders
                .post("http://localhost:8080/applicants")
                .content(asJsonString(applicant))
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + jwtResponse)
                .accept(MediaType.APPLICATION_JSON));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    // Dokuzuncu  Senaryo
    @When("{string} \\({long})tc kimlik numarası gerçek dışı olarak post edildiğinde")
    public void lTcKimlikNumarasiGercekDisiOlarakPostEdildiginde(String key, Long citizenshipNumber) throws Throwable {
        Applicant applicant = (Applicant) contex.get(key);
        String jwtResponse = (String) this.contex.get("token");

        applicant.setCitizenNumber(citizenshipNumber);
        action = mvc.perform(MockMvcRequestBuilders
                .post("http://localhost:8080/applicants")
                .content(asJsonString(applicant))
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + jwtResponse)
                .accept(MediaType.APPLICATION_JSON));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    // Onuncu  Senaryo
    @When("{string} \\({long})tc kimlik numarası gerçek olarak post edildiğinde")
    public void lTcKimlikNumarasiGercekOlarakPostEdildiginde(String key, long citizenshipNumber) throws Throwable {
        Applicant applicant = (Applicant) contex.get(key);
        String jwtResponse = (String) this.contex.get("token");
        applicant.setCitizenNumber(citizenshipNumber);
        action = mvc.perform(MockMvcRequestBuilders
                .post("http://localhost:8080/applicants")
                .content(asJsonString(applicant))
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + jwtResponse)
                .accept(MediaType.APPLICATION_JSON));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    // Onbirinci  Senaryo
    @When("{string} \\({int})tc kimlik numarası dokuz hane olarak post edildiğinde")
    public void tcKimlikNumarasiDokuzHaneOlarakPostEdildiginde(String key, int citizenshipNumber) throws Throwable {
        Applicant applicant = (Applicant) contex.get(key);
        String jwtResponse = (String) this.contex.get("token");
        applicant.setCitizenNumber(citizenshipNumber);
        action = mvc.perform(MockMvcRequestBuilders
                .post("http://localhost:8080/applicants")
                .content(asJsonString(applicant))
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + jwtResponse)
                .accept(MediaType.APPLICATION_JSON));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    // Onikinci  Senaryo
    @When("{string} başvuranı yaş grubu boş olarak post edildiğinde")
    public void basvuraniYasGrubuBosOlarakPostEdildiginde(String key) throws Throwable {
        Applicant applicant = (Applicant) contex.get(key);
        String jwtResponse = (String) this.contex.get("token");
        applicant.setTypeBasedOnAge(null);
        action = mvc.perform(MockMvcRequestBuilders
                .post("http://localhost:8080/applicants")
                .content(asJsonString(applicant))
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + jwtResponse)
                .accept(MediaType.APPLICATION_JSON));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    // Onüçüncü  Senaryo
    @When("{string} başvuranı eğitim durumu boş olarak post edildiğinde")
    public void basvuraniEgitimDurumuBosOlarakPostEdildiginde(String key) throws Throwable {
        Applicant applicant = (Applicant) contex.get(key);
        String jwtResponse = (String) this.contex.get("token");
        applicant.setTypeBasedOnEducation(null);
        action = mvc.perform(MockMvcRequestBuilders
                .post("http://localhost:8080/applicants")
                .content(asJsonString(applicant))
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + jwtResponse)
                .accept(MediaType.APPLICATION_JSON));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    // Ondördüncü  Senaryo
    @When("{string} doğum günü yanlış formatta post edildiğinde")
    public void dogumGunuYanlisFormattaPostEdildiginde(String key) throws Throwable {
        Applicant applicant = (Applicant) contex.get(key);
        String jwtResponse = (String) this.contex.get("token");
        applicant.setBirthDate("29/02/2002");
        action = mvc.perform(MockMvcRequestBuilders
                .post("http://localhost:8080/applicants")
                .content(asJsonString(applicant))
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + jwtResponse)
                .accept(MediaType.APPLICATION_JSON));
    }


}
