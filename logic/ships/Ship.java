package logic.ships;

import logic.location.Cords;

public class Ship {
    private final int size;
    private Cords[] location;
    private boolean[] hitStates;

    public Ship(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Not a positive size");
        }
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public Cords[] getLocation() {
        return location;
    }

    public void setLocation(Cords[] location) {
        if (location.length != size) {
            throw new IllegalArgumentException("Location length is not equal size");
        }
        this.location = location;
        hitStates = new boolean[size];
    }

    public void hit(Cords cords) {
        for (Cords localCords : location) {

        }
    }

    public boolean isAlive() {
        for (boolean hit : hitStates) {
            if (!hit) {
                return true;
            }
        }
        return false;
    }
}