package com.epam.task.four.taxistation.xmlreader;

public enum CabListTagName {
    CABLIST, CAB, SPEED, FUELCONSUMPTION, PRICE;

    public static CabListTagName getElementTagName(String element) {
        switch (element) {
        case "cab":
            return CAB;
        case "speed":
            return SPEED;
        case "fuelConsumption":
            return FUELCONSUMPTION;
        case "price":
            return PRICE;
        default:
            return CABLIST;
        }
    }
}
