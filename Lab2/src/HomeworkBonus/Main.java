package HomeworkBonus;

public class Main {
    public static void main(String args[])
    {
        Location[] places = new Location[10];
        places[0] = new City("Iasi",50000,10000);
        places[0].setX(0.0);
        places[0].setY(0.0);
        ((City)places[0]).setPopulation(60000);
        places[1] = new Airport("Henri Coanda",5,20);
        places[1].setX(0.0);
        places[1].setY(0.0);
        places[2]= new City("Bucharest",100000,50000);
        places[2].setX(0.0);
        places[2].setY(0.0);
        places[3]=new City("Roman",20000,30000);
        places[3].setY(0.0);
        places[3].setX(0.0);
        places[4] = new City("Pascani",25000,15000);
        places[4].setX(0.0);
        places[4].setY(0.0);
        places[5] = new gasStation("Lukoil",7.45,20);
        places[5].setY(0.0);
        places[5].setX(0.0);
        places[6] = new City("Targu Frumos",30000,30000);
        places[6].setX(0.0);
        places[6].setY(0.0);
        System.out.println(places[0]);
        System.out.println(places[1]);
        System.out.println(places[3]);
        System.out.println(places[4]);
        System.out.println(places[5]);
        System.out.println(places[6]);
        Road[] road = new Road[6];
        road[0] = new Highway("A1", places[3],places[4]);
        (road[0]).setLength(150);
        (road[0]).setSpeedLimit(150);
        road[1] = new Express("E1", places[4],places[5]);
        (road[1]).setLength(100);
        (road[1]).setSpeedLimit(100);
        road[2] =new Country("C1", places[5],places[6]);
        (road[2]).setLength(50);
        (road[2]).setSpeedLimit(80);
        road[4] = new Express("E2",places[4],places[6]);
        (road[4]).setLength(100);
        (road[4]).setSpeedLimit(100);
        road[5] = new Express("E3",places[6],places[0]);
        (road[5]).setLength(50);
        (road[5]).setSpeedLimit(100);
        System.out.println(road[0]);
        System.out.println(road[1]);
        System.out.println(road[2]);
        Problem pb = new Problem();
        pb.addLocation(places[3]);
        pb.addLocation(places[0]);
        pb.addRoad(road[0]);
        pb.addRoad(road[1]);
        pb.addRoad(road[2]);
        pb.addRoad(road[4]);
        pb.addRoad(road[5]);
        System.out.println(pb);
        System.out.println(pb.isValid());
       System.out.println(pb.canReach(places[3],places[0]));
       Algorithm alg = new DijkstraAlg(pb);
       Solution sol = alg.solve();
       System.out.println(sol);
        //System.out.println(places[0].equals(places[2]));

    }
}
