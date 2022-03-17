import java.util.List;

public class Main {
 public static void main(String[] args) {
  Network x = new Network();
  Node v1 = new Computer("v1 Computer A", "123.20.12", 10);
  Node v2 = new Computer("v2 Computer B", "111.23.45", 7);
  Node v3 = new Router("v3 Router A", "145.32.44");
  Node v4 = new Switch("v4 Switch A");
  Node v5 = new Router("v5 Router B", "167.16.01");
  Node v6 = new Switch("v6 Switch B");
  Node v7 = new Computer("v7 Computer C", "213.56.24", 2);
  x.addNode(v1);
  x.setCost(v1,20);
  x.addNode(v2);
  x.setCost(v2,30);
  x.addNode(v3);
  x.setCost(v3,15);
  x.addNode(v4);
  x.setCost(v4,10);
  x.addNode(v5);
  x.setCost(v5,25);
  x.addNode(v6);
  x.setCost(v6,23);
  x.addNode(v7);
  x.setCost(v7,16);

  System.out.println(x);

  x.Display();

 }
}
