package com.piyushgarg.repositories;

import com.piyushgarg.model.INGAtmLocation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class AtmDataPopulator {

    private Logger atmDataPopulatorLogger = LoggerFactory.getLogger(AtmDataPopulator.class);
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public AtmDataPopulator(RestTemplateBuilder restTemplateBuilder, ObjectMapper objectMapper) {
        this.restTemplate = restTemplateBuilder.build();
        this.objectMapper = objectMapper;
    }

    public List<INGAtmLocation> getDataFromInG() throws Exception {


        String response = restTemplate.getForObject("https://www.ing.nl/api/locator/atms/", String.class);

        atmDataPopulatorLogger.debug("GARBAGE IN RESPONSE:" + "\n\n" + response.substring(0, 5) + "\n\n");
        String toBeParsed = response.substring(6, response.length());
        atmDataPopulatorLogger.debug("TO BE PARSED RESPONSE:" + "\n\n" + toBeParsed + "\n\n");
        INGAtmLocation[] ingAtmLocations = objectMapper.readValue(toBeParsed, INGAtmLocation[].class);
        atmDataPopulatorLogger.debug("PARSED RESPONSE:" + "\n\n" + ingAtmLocations.toString() + "\n\n");

        return Arrays.asList(ingAtmLocations);
    }

}
