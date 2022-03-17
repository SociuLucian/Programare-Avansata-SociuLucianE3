import java.sql.Array;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class Main {
    public static void main(String[] args){
        List<Intersection> nodeList = new ArrayList<>();
        Array nodeArray[]={};
        var nodes = IntStream.rangeClosed(0, 3)
                .mapToObj(i -> new Intersection("v" + i) )
                .toArray(Intersection[]::new);
        nodeList.addAll( Arrays.asList(nodeArray) );

        System.out.println(nodes);
    }
}
