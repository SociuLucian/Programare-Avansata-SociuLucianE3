import javax.sql.rowset.serial.SerialArray;
import java.sql.Array;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args){
        List<Intersection> nodeList = new ArrayList<>();
        Map<Intersection, List<Street>> cityMap = new HashMap<>();
        List<Street> streetList = new ArrayList<>();
        List<Street> a = new ArrayList<>();
        Street str1= new Street("Str. Bines",30);
        a.add(0,str1);
        Intersection v0 = new Intersection("v0");
        Intersection v1 = new Intersection("v1");
        Intersection v2 = new Intersection("v2");
        Intersection v3 = new Intersection("v3");
        nodeList.add(v0);
        nodeList.add(v1);
        nodeList.add(v2);
        nodeList.add(v3);
        List<Intersection> newSortedList = nodeList.stream()
                .sorted(Comparator.comparing(Intersection::getName))
                .collect(Collectors.toList());
        cityMap.put(v0, streetList);
        cityMap.put(v1, streetList);
        cityMap.put(v2, streetList);
        cityMap.put(v3, streetList);
        streetList.add(a.get(0));
        streetList.add(a.get(1));
        streetList.add(a.get(2));
        List<Street> target = Arrays.asList(a.get(1), a.get(2));
        List<Intersection> result = nodeList.stream()
                .filter(v -> cityMap.get(v).containsAll(target))
                .collect(Collectors.toList());

    }
}

