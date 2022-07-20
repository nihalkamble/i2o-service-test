package com.corecompete.i2o.controller;

import com.corecompete.i2o.constants.ErrorMessageConstants;
import com.corecompete.i2o.models.EventDashboard;
import com.corecompete.i2o.models.EventsList;
import com.corecompete.i2o.models.EventsPerDay;
import com.corecompete.i2o.services.EventServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(value = "/events")
@Slf4j
public class EventsController extends BaseController {

    @Autowired
    EventServices eventServices;

    @GetMapping(value = "/get-events-per-day")
    public ResponseEntity getEventsPerDay() {
        try {
            log.info("getEventsPerDay Request invoked- ");
            return new ResponseEntity<ArrayList<EventsPerDay>>(this.eventServices.getNumberOfEventsPerDay(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("chart data exception", e);
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/get-events-list")
    public ResponseEntity getEventsList() {
        try {
            log.info("getEventsList Request invoked- ");
            return new ResponseEntity<ArrayList<EventsList>>(this.eventServices.getEventsList(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("chart data exception", e);
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/get-event-dashboard-data")
    public ResponseEntity getEventDashboardData() {
        try {
            log.info("getEventDashboardData Request invoked- ");
            return new ResponseEntity<ArrayList<EventDashboard>>(this.eventServices.getEventDashboardData(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("chart data exception", e);
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
