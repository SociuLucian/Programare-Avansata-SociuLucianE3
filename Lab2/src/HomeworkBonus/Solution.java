package HomeworkBonus;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private List<Location> route;

    public Solution(List<Location> route) {
        route = new ArrayList<>();
    }

    public List<Location> getRoute() {
        return route;
    }

    @Override
    public String toString() {
        return "Solution{" +
                "route=" + route +
                '}';
    }
}
