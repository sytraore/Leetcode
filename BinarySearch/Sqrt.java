package BinarySearch;

public class Sqrt {
    public int mySqrt(int x) {
        /*
        * use a classical binary search algorithm
        time complexity: O(log n)
        space complexity: O(1)
        */

        int l = 0, r = x;
        int res = 0;

        while (l <= r){
            int m = l + (r - l) / 2;

            if ((long)m * m < x){
                l = m + 1;
                // we store the value of m in res
                // since it is the last value that is less than x
                res = m;
            }
            else if ((long)m * m > x){
                // we do not store the value of m in res
                // since it is greater than x
                r = m - 1;
            }
            else{
                // if m * m is equal to x, we return m
                return m;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        Sqrt sqrt = new Sqrt();
        System.out.println(sqrt.mySqrt(4)); // Output: 2
        System.out.println(sqrt.mySqrt(8)); // Output: 2
        System.out.println(sqrt.mySqrt(16)); // Output: 4
        System.out.println(sqrt.mySqrt(0)); // Output: 0
        System.out.println(sqrt.mySqrt(1)); // Output: 1
    }
}
