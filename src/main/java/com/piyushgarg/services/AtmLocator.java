package com.piyushgarg.services;

import com.piyushgarg.model.INGAtmLocation;
import com.piyushgarg.repositories.AtmDataPopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class AtmLocator {

    @Autowired
    private AtmDataPopulator atmDataPopulator;

    public List<INGAtmLocation> getAtmLocationsfromING() throws Exception {

        return atmDataPopulator.getDataFromInG();
    }

    public List<INGAtmLocation> getAtmLocationsfromINGByCity(String city) throws Exception {

        List<INGAtmLocation> locations = new ArrayList<>();
        List<INGAtmLocation> atmLocationsfromING = getAtmLocationsfromING();
        for (INGAtmLocation location : atmLocationsfromING) {
            if (city.equalsIgnoreCase(location.getAddress().getCity())) {
                locations.add(location);
            }
        }
        return locations;
    }
}
