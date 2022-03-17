public class Router extends Node implements Identifiable{
     private  String ip;
    public Router(String nume,String ip) {
        super(nume);
        this.ip=ip;
    }
    public void setIP(String ip)
    {
        this.ip=ip;
    }
    public String getIP(){
        return ip;
    }
    public String getName()
    {
        return nume;
    }


    public void setName(String nume) {
        super.setName(nume);
    }

    @Override
    public String toString() {
        return "\n Router {" + "nume : " + nume+
                " ip : " + ip + "}" ;
    }
}
