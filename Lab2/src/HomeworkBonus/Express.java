package HomeworkBonus;

public class Express extends Road{

    public Express(String name, Location a, Location b) {
        super(name, a, b);
    }

    @Override
    public long getLength() {
        return super.getLength();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public int getSpeedLimit() {
        return super.getSpeedLimit();
    }

    @Override
    public void setLength(long length) {
        super.setLength(length);
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public void setSpeedLimit(int speedLimit) {
        super.setSpeedLimit(speedLimit);
    }

    @Override
    public String toString() {
        return "Express{} " + super.toString();
    }
}
