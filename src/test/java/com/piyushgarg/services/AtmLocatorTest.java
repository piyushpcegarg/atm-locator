package com.piyushgarg.services;

import com.piyushgarg.model.Address;
import com.piyushgarg.model.INGAtmLocation;
import com.piyushgarg.repositories.AtmDataPopulator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class AtmLocatorTest {

    @InjectMocks
    AtmLocator atmLocator;

    @Mock
    AtmDataPopulator atmDataPopulator;

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
    public void testGetAtmLocationsfromING() throws Exception {

        when(atmDataPopulator.getDataFromInG()).thenReturn(atmLocationList);
        List<INGAtmLocation> atmLocations = atmLocator.getAtmLocationsfromING();
        assertEquals(atmLocations.size(), 2);

    }

    @Test
    public void testGetAtmLocationsfromINGByCity() throws Exception {

        when(atmDataPopulator.getDataFromInG()).thenReturn(atmLocationList);
        List<INGAtmLocation> atmLocationsfromINGByCity = atmLocator.getAtmLocationsfromINGByCity("GELDROP");
        assertEquals(atmLocationsfromINGByCity.size(), 1);
    }
}