package src;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class DistanceUtilities {

    public static int roundToNearest25(int distance){
        return (int)Math.round(distance/25.0)*25;
    }

    //Returns all the factors of a given distance that are divisible by 25
    public static ArrayList<Integer> get25Factors(int distance){
        ArrayList<Integer> div = new ArrayList<Integer>();
        for (int i = 1; i <= distance; i++){
            if (distance%i == 0 && i%25 == 0 ){
                div.add(i);
            }
        }
        return div;
    }
    public static int random25Factor(int distance){
        ArrayList<Integer> div = get25Factors(distance);
        Random rand = new Random();
        return div.get(rand.nextInt(div.size()));
    }

    public static int integerDivideBy25(int distance){
        return (int)Math.round(distance/25.0);
    }

    public static ArrayList<Integer> separateTo25s(int distance){
        int maxNumberOfComponents = integerDivideBy25(distance);
        int numberComponentsAdded = 0;
        ArrayList<Integer> array = new ArrayList<>();
        while (numberComponentsAdded < maxNumberOfComponents){
            int rand = new Random().nextInt(maxNumberOfComponents-numberComponentsAdded)+1;
            array.add(rand*25);
            numberComponentsAdded += rand;
        }
        Collections.shuffle(array);
        return array;
        
    }
}