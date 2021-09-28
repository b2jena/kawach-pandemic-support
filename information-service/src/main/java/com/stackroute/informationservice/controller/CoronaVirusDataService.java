package com.stackroute.informationservice.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.informationservice.models.LocationStats;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.net.URL;


@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class CoronaVirusDataService {
    private static String VIRUS_DATA_URL = "https://api.rootnet.in/covid19-in/stats/latest";

    @GetMapping("/information")
    public LocationStats[] getData() throws IOException {
        JsonNode jsonNode = new ObjectMapper().readTree(new URL(VIRUS_DATA_URL));
        JsonNode regional = jsonNode.get("data").get("regional");

        ObjectMapper mapper = new ObjectMapper();

        LocationStats[] locationStats = mapper.treeToValue(regional, LocationStats[].class);

        return locationStats;
    }



}
