package com.stackroute.userrevertservice.controller;


import com.stackroute.userrevertservice.model.VolunteerIncoming;
import com.stackroute.userrevertservice.service.VolunteerRevertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class VolunteerRevertController {
    private VolunteerRevertService volunteerRevertService;

    @Autowired
    public VolunteerRevertController(VolunteerRevertService volunteerRevertService) {
        this.volunteerRevertService = volunteerRevertService;
    }

    @PostMapping("/revert")
    public ResponseEntity<String> volunteerRevert(@RequestBody VolunteerIncoming volunteerIncoming) {
        volunteerRevertService.volunteerRevertUpdate(volunteerIncoming);
        return new ResponseEntity<String>("Volunteer details have been updated", HttpStatus.OK);
    }
}
