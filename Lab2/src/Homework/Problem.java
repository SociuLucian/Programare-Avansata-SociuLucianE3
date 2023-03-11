package Homework;

import java.util.*;

public class Problem {
   public List<Location> locations;
   public List<Road> roads;
   public Problem() {
      this.locations = new ArrayList<>();
      this.roads = new ArrayList<>();
   }


   public boolean addLocation(Location location) {
      if (locations.contains(location)) {
         return false;
      }
      locations.add(location);
      return true;
   }

   public boolean addRoad(Road road) {
      if (roads.contains(road)) {
         return false;
      }
      roads.add(road);
      return true;
   }


   public boolean isValid() {
      for (Road road : roads) {
         Location start = road.getStart();
         Location end = road.getEnd();
         if (start == null || end == null) {
            return false;
         }
         double distance = Math.sqrt(Math.pow(start.getX() - end.getX(), 2) + Math.pow(start.getY() - end.getY(), 2));
         if (road.getLength() < distance) {
            return false;
         }
      }
      return true;
   }


   public boolean canReach(Location start, Location end) {
      if (!locations.contains(start) || !locations.contains(end)) {
         return false;
      }

      Set<Location> visited = new HashSet<>();
      Queue<Location> toVisit = new LinkedList<>();
      toVisit.add(start);

      while (!toVisit.isEmpty()) {
         Location current = toVisit.poll();
         visited.add(current);

         for (Road road : roads) {
            if (road.getStart().equals(current) && !visited.contains(road.getEnd())) {
               if (road.getEnd().equals(end)) {
                  return true;
               }
               toVisit.add(road.getEnd());
            }
         }
      }

      return false;
   }

   @Override
   public String toString() {
      return "Problem{" +
              "locations=" + locations +
              ", roads=" + roads +
              '}';
   }
   /*private Location getLocationByName(String name) {
      for (Location location : locations) {
         if (location.getName().equals(name)) {
            return location;
         }
      }
      return null;
   }*/

}

