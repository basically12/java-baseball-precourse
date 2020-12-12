package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomUtils {
    private static final Random RANDOM = new Random();

    private RandomUtils() {}

    public static int nextInt(final int startInclusive, final int endInclusive) {
        if (startInclusive > endInclusive) {
            throw new IllegalArgumentException();
        }

        if (startInclusive < 0) {
            throw new IllegalArgumentException();
        }

        if (startInclusive == endInclusive) {
            return startInclusive;
        }

        return startInclusive + RANDOM.nextInt(endInclusive - startInclusive + 1);
    }

    
    public static List<Integer> makeGoal()
    {
        int numberOfNum = 3;
        int startNum = 1;
        int endNum = 9;
        
        List<Integer> goal= new ArrayList<>();
        
        for(int i = 0; i < numberOfNum; )
        {
            int number = nextInt(startNum, endNum);
            
            if(!goal.contains(number))
            {
                goal.add(number);
                i++;
            }
            
        }
        
        return goal;
    }
     
   
}
