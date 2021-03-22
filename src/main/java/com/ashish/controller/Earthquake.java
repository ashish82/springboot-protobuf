package com.ashish.controller;

import com.ashish.protobuf.EarthquakeOuterClass;
import com.ashish.service.EarthquakeService;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ashishsr on 22/03/21
 */
@RestController
public class Earthquake {
    @Autowired
    private EarthquakeService earthquakeService;

    @GetMapping("/earthquakesInformationJson")
    public String getProto() {
        String jsonString = null;
        try {
            jsonString = JsonFormat.printer().print(earthquakeService.getEarthquakes());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    @GetMapping(path = "/earthquakesInformationProto", produces = "application/x-protobuf")
    public EarthquakeOuterClass.Earthquakes getProtoResponse() {
        return earthquakeService.getEarthquakes();
    }

}
