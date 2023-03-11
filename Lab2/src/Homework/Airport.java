package Homework;

public class Airport extends Location {
    private int terminals;
    private long airplanesCapacity;
    public Airport(String name,int terminals,int airplanesCapacity) {
        super(name);
        this.airplanesCapacity = airplanesCapacity;
        this.terminals = terminals;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public double getX() {
        return super.getX();
    }

    @Override
    public double getY() {
        return super.getY();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public void setX(double x) {
        super.setX(x);
    }

    @Override
    public void setY(double y) {
        super.setY(y);
    }

    public int getTerminals() {
        return terminals;
    }

    public void setTerminals(int terminals) {
        this.terminals = terminals;
    }

    public long getAirplanesCapacity() {
        return airplanesCapacity;
    }

    public void setAirplanesCapacity(long airplanesCapacity) {
        this.airplanesCapacity = airplanesCapacity;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "terminals=" + terminals +
                ", airplanesCapacity=" + airplanesCapacity +
                "} " + super.toString();
    }
}
