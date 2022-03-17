import java.util.List;

public class Computer extends Node implements Identifiable, Storage {
    private String ip;
    public long StoragecapGB,StoragecapMB,StoragecapKB;
     public Computer(String nume,String ip, int storagecapacity){
        super(nume);
        this.ip=ip;
        this.StoragecapGB=storagecapacity;
        this.StoragecapMB=storagecapacity*1024;
        this.StoragecapKB=storagecapacity*1024*1024;

    }


    @Override
    public void setName(String nume) {
        super.setName(nume);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    public String getIP(){
        return ip;
    }
    public void setIp(String ip)
    {
        this.ip=ip;
    }
    public int getStorageCapacity(){
        return (int) StoragecapGB;
    }

    public void setStoragecapacity(int storagecap){
        this.StoragecapGB=storagecap;
    }

    @Override
    public String toString() {
        return  "\n Computer { "+"nume : " + nume +
                " ip : " + ip +
                " Storage Capacity in GB= " + StoragecapGB +
                " Storage Capacity in MB= " + StoragecapMB +
                " Storage Capacity in KB= " + StoragecapKB +
                "}";
    }
}


