package com.tae.inmigrantform.model;

import java.util.List;

/**
 * Created by Eduardo on 14/12/2015.
 */
public class ImmigrantManager {

    private static ImmigrantManager instance;

    private ImmigrantManager() {

    }

    public static ImmigrantManager getInstance() {
        if (instance == null) {
            instance = new ImmigrantManager();
        }
        return instance;
    }

    public Immigrant generateImmigrant(List<String> immigrantData) {
        return new Immigrant(
                immigrantData.get(0),
                immigrantData.get(1),
                immigrantData.get(2),
                immigrantData.get(3),
                immigrantData.get(4),
                immigrantData.get(5),
                immigrantData.get(6),
                immigrantData.get(7),
                immigrantData.get(8)
        );
    }
}
