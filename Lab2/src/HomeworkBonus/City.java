package HomeworkBonus;

public class City extends Location {
    private long Population;
    private long Surface;

    public City(String name,long Population, long Surface) {
        super(name);
        this.Population = Population;
        this.Surface = Surface;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public double getX() {
        return super.getX();
    }

    @Override
    public void setX(double x) {
        super.setX(x);
    }

    @Override
    public double getY() {
        return super.getY();
    }

    @Override
    public void setY(double y) {
        super.setY(y);
    }

    public long getPopulation() {
        return Population;
    }

    public void setPopulation(long Population) {
        this.Population = Population;
    }

    public long getSurface() {
        return Surface;
    }

    public void setSurface(long Surface) {
        this.Surface = Surface;
    }

    @Override
    public String toString() {
        return "City{" +
                "population=" + Population +
                ", surface=" + Surface +
                "} " + super.toString();
    }
}
