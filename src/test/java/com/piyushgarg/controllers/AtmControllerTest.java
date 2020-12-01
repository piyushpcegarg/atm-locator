package com.piyushgarg.controllers;

import com.piyushgarg.model.Address;
import com.piyushgarg.model.ApiResponseObject;
import com.piyushgarg.model.INGAtmLocation;
import com.piyushgarg.services.AtmLocator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class AtmControllerTest {

    @InjectMocks
    AtmController atmController;

    @Mock
    AtmLocator atmLocator;

    private List<INGAtmLocation> atmLocationList;

    @BeforeEach
    public void setUp() throws Exception {

        MockitoAnnotations.openMocks(this);

        atmLocationList = new ArrayList<>();
        INGAtmLocation atmLocation = new INGAtmLocation();
        Address address = new Address();
        address.setCity("GELDROP");
        address.setHousenumber("1");

        atmLocation.setAddress(address);

        INGAtmLocation atmLocation1 = new INGAtmLocation();
        Address address1 = new Address();
        address1.setHousenumber("2");
        address1.setCity("ROTTERDAM");

        atmLocation1.setAddress(address1);

        atmLocationList.add(atmLocation);
        atmLocationList.add(atmLocation1);
    }

    @Test
    public void testGetATMLocationsfromING() throws Exception {

        when(atmLocator.getAtmLocationsfromING()).thenReturn(atmLocationList);
        ApiResponseObject<INGAtmLocation> atmLocationsfromING = atmController.getATMLocationsfromING();
        assertEquals(atmLocationsfromING.getList().size(),2);

    }

    @Test
    public void testGetATMLocationsfromINGByCity() throws Exception {

        when(atmLocator.getAtmLocationsfromINGByCity(Mockito.anyString())).thenReturn(atmLocationList);
        ApiResponseObject<INGAtmLocation> locationsfromINGByCity = atmController.getATMLocationsfromINGByCity("ANY_CITY");
        assertEquals(locationsfromINGByCity.getList().size(),2);

    }
}