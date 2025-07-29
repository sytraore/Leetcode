package Stack;

import java.util.Arrays;
import java.util.Stack;

public class CarFleet {
    public static int carFleet(int target, int[] position, int[] speed) {
        /*
            to know if a car will pass the car in from of it, we need to calculate the time it will take
            for each car to reach the target destination.
            if the time of the closest (relative to the target) car (let's call it car A) 
            is bigger than the time of the furthest car (let's call it car B),
            it means that car B will get to target before car A which also implies that car B will pass car A
            but we are given the constraint that one car cannot pass another car
            so in this case, car B will not pass car A but it will rather drive with car A till they both reach target.
            we need to find a way to store the time of the cars in such a way that it will be easy to track the time that belongs to each car.
            based on the time it takes to get to target, we can find out which cars will drive together as a fleet

            a stack data structure can help us achieve this goal.
            the stack will store only the amount of time needed for each car to arrive at target
            In the current position, it will be hard to track which car time will overcome the other.
            but if the cars were sorted from the furthest to the closest, we can easily track the amount of time for each car and therefore determinate which cars will pass which cars before making it to target.
            with the fact that a car cannot pass another car, but only reduce to the same speed, 
            if the furthest car has the smallest amount of time, it will eventually slow down by the first car it meets on the road.
            meaning that the order matters a lot in our algorithm


            Time complexity: O(nlogn)
            Space complexity: O(n)
        */

        Stack<Double> stack = new Stack<>();

        // we need the cars to be sorted in decreasing order of position because it will enable us to tarck
        // cars that are likely to pass
        // since the speed array element positions depends on the position array element positions,
        // if we sort position, we must sort speed as well.
        // only issue is that speed cannot be sort independently
        // we can combine both car position and speed and sort them altogether in descending order
        int[][] race = new int[position.length][2];
        
        // fill the race
        for (int i = 0; i < position.length; i++){
            race[i][0] = position[i];
            race[i][1] = speed[i];
        }

        // sort the race
        Arrays.sort(race, (array1, array2) -> Integer.compare(array2[0], array1[0]));

        // go through the race
        // for each car, compute the amount of time it will take to reach the target
        // once computed, add it to stack
        // if a computed value is less tha

        for (int i = 0; i < race.length; i++){
            double time = (double)(target - race[i][0]) / race[i][1];
            double prevCar = 0.0;
            // get the time of the most recent car which is now the previous car
            if (!stack.empty()){
                prevCar = stack.peek();
            }
            // add current car time
            stack.push(time);

            // check if the current car will pass the car in front of it (the previous car)
            // if the current car does pass the previous one, they will get to target as a car fleet
            // we can count the previous car as one with the current car
            // so no need of the current car time to stay in the stack
            if (stack.size() >= 2 && stack.peek() <= prevCar){
                stack.pop();
            }

        }
        
        // the size of the stack reflects the number of car fleet
        return stack.size();
    }

    public static void main(String[] args) {
        int target = 12;
        int[] position = {10, 8, 0, 5, 3};
        int[] speed = {2, 4, 1, 1, 3};

        int result = carFleet(target, position, speed);
        System.out.println("Number of car fleets: " + result); // Expected output: 3
    }
}
