package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShoppingWay {
    private List <Section> sections;
    private int distance;

    public ShoppingWay(List cities) {
        this.sections = new ArrayList<>(cities);
        Collections.shuffle(this.sections);
    }
    public Section getSection(int index) {
        return sections.get(index);
    }

    public int getTourLength() {
        if (distance != 0) return distance;

        int totalDistance = 0;

        for (int i = 0; i < waySize(); i++) {
            Section start = getSection(i);
            Section end = getSection(i + 1 < waySize() ? i + 1 : 0);
            totalDistance += Formula.distance(start, end);
        }

        distance = totalDistance;
        return totalDistance;
    }

    public ShoppingWay duplicate() {
        return new ShoppingWay(new ArrayList<>(sections));
    }

    public int waySize() {
        return sections.size();
    }

    // Getters and toString()

    @Override
    public String toString(){
        return sections.toString();
    }
}
