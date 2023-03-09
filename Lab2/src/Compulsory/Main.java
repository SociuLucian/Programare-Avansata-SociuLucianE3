package Compulsory;

public class Main {
    public static void main(String args[]) {
        Location c1 = new Location("Bucharest");
        c1.setType(Location.LocationType.CITY);
        c1.setX(0.0);
        c1.setY(0.0);
        Location c2 = new Location("Iasi");
        c2.setType(Location.LocationType.CITY);
        c2.setX(0.0);
        c2.setY(0.0);
        Location c3 = new Location("Iasi");
        c3.setType(Location.LocationType.CITY);
        c3.setX(0.0);
        c3.setY(0.0);
        System.out.println(c3);
        System.out.println(c2);
        System.out.println(c1);
        Road r1 = new Road("Dn24", Road.RoadType.EXPRESS, c1, c2);
        r1.setLength(100);
        r1.setSpeed_limit(100);
        if(r1.getLength()<Math.sqrt(((c2.getX()-c1.getX())*(c2.getX()-c1.getX()))+((c2.getY()-c1.getY())*(c2.getY()-c1.getY()))))
        {
            System.out.println("the value of Road Length is too short ");
        }
        System.out.println(r1);
    }
}
