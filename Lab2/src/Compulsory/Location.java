package Compulsory;

public class Location {
    private String name;

    public enum LocationType {
        CITY,
        AIRPORT,
        GAS_STATION;
    }

    public Location(String name) {
        this.name = name;
    }

    private LocationType type;
    private Double x;
    private Double y;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(LocationType type) {
        this.type = type;
    }

    public LocationType getType() {
        return type;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null || !(obj instanceof Location)){
            return false;}
        Location other = (Location) obj;
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Location{" +
                "name ='" + name + "', " +
                "type ='" + type + "', " +
                "x = " + x + ", " +
                "y = " + y + ", " +
                '}';
    }
}
