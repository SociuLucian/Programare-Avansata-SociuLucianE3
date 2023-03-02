package Compulsory;

public class Road {
    private String name;
    public enum RoadType{
        EXPRESS,
        HIGHWAY;
    }
    private Location start;
    private Location end;
    private RoadType type;
    private int length;
    private int speed_limit;
    public Road(String name, RoadType type, Location a, Location b){
        this.name=name;
        this.type=type;
        this.start = a;
        this.end = b;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getStart() {
        return start;
    }

    public void setStart(Location start) {
        this.start = start;
    }

    public RoadType getType() {
        return type;
    }

    public Location getEnd() {
        return end;
    }

    public void setType(RoadType type) {
        this.type = type;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {

        this.length = length;
    }

    public int getSpeed_limit() {
        return speed_limit;
    }

    public void setSpeed_limit(int speed_limit) {
        this.speed_limit = speed_limit;
    }

    public void setEnd(Location end) {
        this.end = end;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null || !(obj instanceof Road))
            return false;
        Road other = (Road) obj;
            return super.equals(obj);

        }

    @Override
    public String toString() {
        return "Road{" +
                "name='" + name + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", type=" + type +
                ", length=" + length +
                ", speed_limit=" + speed_limit +
                '}';
    }
}
