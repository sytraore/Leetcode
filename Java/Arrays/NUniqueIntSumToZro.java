package Java.Arrays;
/*
    Given an integer n, return any array containing n unique integers such that they add up to 0.
    Example 1:
        Input: n = 5
        Output: [-7,-1,1,7,0]
        Explanation: One valid answer is [-7,-1,1,7,0].
    Example 2:
        Input: n = 3
        Output: [-1,0,1]
        Explanation: One valid answer is [-1,0,1].
    Example 3:
        Input: n = 1
        Output: [0]
        Explanation: One valid answer is [0].
*/
public class NUniqueIntSumToZro{
    public int[] sumZero(int n) {  
        /*
            check the value of n
            if n is equal to 1, return [0]
            else,
                => if n is odd, add 0 to the array
                    loop n/2 times and at each iteration, add the iterator value into the array as well as its negative value
                => if n is even, 
                    loop n/2 times and add each iterator value to the array as well as its negative value
            return the resulting array 
        */
        
        int[] result = new int[n];

        if (n == 1){
            result[0] = 0;
            return result;
        }
        boolean isOdd = false;

        if (n % 2 == 1){
            result[0] = 0;
            isOdd = true;
        }
        int index = 0;
        int numbOfIterations = n / 2;
        for (int i = 1; i <= numbOfIterations; i++){
            if (isOdd){
                result[index + 1] = i;
                result[index + 2] = i - (2 * i);
                index += 2;
            }
            else{
                result[index] = i;
                result[index + 1] = i - (2 * i);
                index += 2;
            }
        }
        return result;
    }
}