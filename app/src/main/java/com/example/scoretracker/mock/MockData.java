package com.example.scoretracker.mock;

import com.example.scoretracker.model.CricketMatch;

import java.util.ArrayList;
import java.util.List;

public class MockData {

    public static final List<CricketMatch> matches = new ArrayList<>();

    static {
        matches.add(new CricketMatch("IT", "CS", 200, "IT", 120, 5, 20 , 5));
        matches.add(new CricketMatch("EE", "CV", 120, "CV", 80, 10, 20, 7));
    }

}
