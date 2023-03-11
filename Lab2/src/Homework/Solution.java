package Homework;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private List<Location> route;
    private long distance;

    public Solution(List<Location> route) {
        this.route=route;
    }

    public List<Location> getRoute() {
        return route;
    }

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Solution{" +
                "route=" + route +
                ", distance=" + distance +
                '}';
    }
}
