package Homework;
import java.rmi.MarshalledObject;
import java.util.*;
import java.util.logging.Logger;

public abstract class Algorithm {
    protected Problem pb;

    public Algorithm(Problem pb) {
        this.pb = pb;
    }
    public Solution solve(Location start, Location end) {
        Map<Location, Long> distances = new HashMap<>();
        Map<Location,Location> previous = new HashMap<>();
        List<Location> shortestPath = new ArrayList<>();
        PriorityQueue<Location> queue = new PriorityQueue<>(Comparator.comparingLong(distances::get));
        for (Location location : pb.locations) {
            distances.put(location, Long.MAX_VALUE);
        }
        distances.put(start, 0L);
        queue.offer(start);

        while (!queue.isEmpty()) {
            Location current = queue.poll();
            if (current.equals(end)) {
                // We have found the shortest path, so backtrack from the end node to the start node
                while (previous.containsKey(current)) {
                    shortestPath.add(0, current);
                    current = previous.get(current);
                }
                shortestPath.add(0, start);
                break;
            }
            for (Road road : pb.roads) {
                if (road.getStart().equals(current)) {
                    long distance = distances.get(current) + road.getLength();
                    if (distance < distances.get(road.getEnd())) {
                        distances.put(road.getEnd(), distance);
                        previous.put(road.getEnd(), current);
                        queue.offer(road.getEnd());
                    }
                }
            }
        }
        Solution sol = new Solution(shortestPath);
        sol.setDistance(distances.get(end));
        return sol;
    }
        /* for (Location location : pb.locations) {
            distances.put(location, Long.MAX_VALUE);
            visited.put(location, false);
        }
        distances.put(start, 0L);
        for (Location location : pb.locations) {
            Location u = MinDistance(distances, visited);
            visited.put(u, true);

            for (Location location1 : pb.locations){
                for (Road road : pb.roads) {
                    if(road.getStart() == u && road.getEnd() == location1){
                    if (!visited.get(location1) && (distances.get(u) + road.getLength() < distances.get(location1))) {
                        distances.put(location1, distances.get(u) + road.getLength());
                    }
                    //System.out.println(distances.get(location1));
                }

        }}
       }
        return distances.get(end);
    }
        private Location MinDistance( Map<Location,Long> distances,Map<Location,Boolean> visited)
        {
            long MinDist = Long.MAX_VALUE;
            Location MinDistLocation = null;
            for(Location location : distances.keySet())
            {
                if(!visited.get(location) && distances.get(location)< MinDist)
                {
                    MinDist = distances.get(location);
                    MinDistLocation = location;

                }
            }
            return MinDistLocation;
        }*/
}

