package HomeworkBonus;
import java.util.*;
public abstract class Algorithm {
    protected Problem pb;

    public Algorithm(Problem pb) {
        this.pb = pb;
    }
    public Solution solve() {
        Location start = pb.locations.get(0);
        Location end = pb.locations.get(1);
        Map<Location, Integer> distances = new HashMap<>();
        Map<Location, Location> previousLocations = new HashMap<>();
        PriorityQueue<Location> queue = new PriorityQueue<>(Comparator.comparingInt(distances::get));

        distances.put(start, 0);
        queue.add(start);

        while (!queue.isEmpty()) {
            Location currentLocation = queue.poll();

            if (currentLocation.equals(end)) {
                break;
            }

            for (Road road : pb.roads) {
                Location neighborLocation = road.getEnd();
                int distance = (int) (distances.get(currentLocation) + road.getLength());

                if (!distances.containsKey(neighborLocation) || distance < distances.get(neighborLocation)) {
                    distances.put(neighborLocation, distance);
                    previousLocations.put(neighborLocation, currentLocation);
                    queue.add(neighborLocation);
                }
            }
        }

        List<Location> shortestPath = new ArrayList<>();
        Location currentLocation = end;


        while (currentLocation != null) {
            shortestPath.add(currentLocation);
            currentLocation = previousLocations.get(currentLocation);
        }

        Collections.reverse(shortestPath);
        Solution solution = new Solution(shortestPath);

        return  solution;
    }
}

