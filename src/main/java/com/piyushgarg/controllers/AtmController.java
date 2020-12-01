package com.piyushgarg.controllers;

import com.piyushgarg.model.ApiResponseObject;
import com.piyushgarg.model.INGAtmLocation;
import com.piyushgarg.services.AtmLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AtmController {

    @Autowired
    private AtmLocator atmLocator;

    @GetMapping(value = "/locations")
    public ApiResponseObject<INGAtmLocation> getATMLocationsfromING() throws Exception {
        ApiResponseObject<INGAtmLocation> responseObject= new ApiResponseObject<>();
        responseObject.setList(atmLocator.getAtmLocationsfromING());
        return responseObject;
    }

    @GetMapping(value = "/locations/{city}")
    public ApiResponseObject<INGAtmLocation> getATMLocationsfromINGByCity(@PathVariable("city") String city) throws Exception {
        ApiResponseObject<INGAtmLocation> responseObject= new ApiResponseObject<>();
        responseObject.setList(atmLocator.getAtmLocationsfromINGByCity(city));
        return responseObject;
    }

}
