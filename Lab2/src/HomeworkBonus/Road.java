package HomeworkBonus;

import java.util.Objects;

public abstract class Road {
    protected String name;
    protected long length;
    protected int speedLimit;
    Location start,end;

    public Road(String name,Location a,Location b) {
        this.name = name;
        this.start=a;
        this.end=b;
    }

    public Location getEnd() {
        return end;
    }

    public void setEnd(Location end) {
        this.end = end;
    }

    public Location getStart() {
        return start;
    }

    public void setStart(Location start) {
        this.start = start;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public int getSpeedLimit() {
        return speedLimit;
    }

    public void setSpeedLimit(int speedLimit) {
        this.speedLimit = speedLimit;
    }

    @Override
    public String toString() {
        return "Road{" +
                "name='" + name + '\'' +
                ", length=" + length +
                ", speedLimit=" + speedLimit +
                ", start=" + start +
                ", end=" + end +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Road road = (Road) o;
        return length == road.length && speedLimit == road.speedLimit && Objects.equals(name, road.name) && Objects.equals(start, road.start) && Objects.equals(end, road.end);
    }

}
