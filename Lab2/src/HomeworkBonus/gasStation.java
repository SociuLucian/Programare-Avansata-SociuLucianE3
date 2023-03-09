package HomeworkBonus;

public class gasStation extends Location{
    private double gasPrice;
    private int carsCapacity;
    public gasStation(String name,double gasPrice,int carsCapacity) {
        super(name);
        this.carsCapacity = carsCapacity;
        this.gasPrice= gasPrice;
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

    public int getCarsCapacity() {
        return carsCapacity;
    }

    public void setCarsCapacity(int carsCapacity) {
        this.carsCapacity = carsCapacity;
    }

    public double getGasPrice() {
        return gasPrice;
    }

    public void setGasPrice(double gasPrice) {
        this.gasPrice = gasPrice;
    }

    @Override
    public String toString() {
        return "gasStation{" +
                "gasPrice=" + gasPrice +
                ", carsCapacity=" + carsCapacity +
                "} " + super.toString();
    }
}
