package com.example.MCardSpring;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MCardSpringApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class MCardApplicationTests {

    private static final String APPLICANT_ENDPOINT = "http://localhost:8080/applicants/";
    private static final String CARD_ENDPOINT = "http://localhost:8080/cards/";

    private TestRestTemplate template;

    @Test
    public void whenSaveManyToManyRelationship_thenCorrect() {

    }

}
