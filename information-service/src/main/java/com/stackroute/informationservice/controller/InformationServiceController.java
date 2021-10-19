package com.stackroute.informationservice.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.informationservice.models.LocationStats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.net.URL;


@RestController
@RequestMapping("api/v1/")
@CrossOrigin(value = "*")
public class InformationServiceController {
    Logger logger = LoggerFactory.getLogger(InformationServiceController.class);
    private static String VIRUS_DATA_URL = "https://api.rootnet.in/covid19-in/stats/latest";

    /*This get mapping is used to fetch information from external api*/
    @GetMapping("/information")
    public LocationStats[] getData() {
        try {
            JsonNode jsonNode = new ObjectMapper().readTree(new URL(VIRUS_DATA_URL));
            JsonNode regional = jsonNode.get("data").get("regional");

            ObjectMapper mapper = new ObjectMapper();

            LocationStats[] locationStats = mapper.treeToValue(regional, LocationStats[].class);

            return locationStats;
        } catch (IOException exception)
        {
            logger.error("External API failed");
            return null;
        }
    }



}
