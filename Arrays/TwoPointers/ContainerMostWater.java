package Arrays.TwoPointers;

public class ContainerMostWater {
    public static int maxArea(int[] height) {
        /**
            for each 2 heights, look for the minimum value
            once found, compute the area by using the formula: area = width * height
            the width will be the difference between the iterators.

            int area = 0;

            for (int i = 0; i < height.length; i++){
                for (int j = i + 1; j < height.length; j++){
                    int theArea = Math.min(height[i], height[j]) * (j - i);
                    area = Math.max(area, theArea);
                }
            }

            return area;
        */

        /**
            Two pointer approach
            Pointers are opposite to each other and are moved to meet each other
            if value at the left pointer is the minimum value, we move the left 1 step to the right
            if value at the right pointer is the minimum value, we move the right pointer 1 step to the left
            we compute the area at each iteration and update if necessary
         */

        int area = 0, l = 0, r = height.length - 1;

        while (l < r){
            // if the height at the left pointer is smaller
            // when height at both pointers are the same, we can move either pointers
            // we choose to move the left pointer
            if (height[l] <= height[r]){
                // compute area
                int theArea = height[l] * (r - l);
                area = Math.max(area, theArea);
                l++;    // move pointer to the right
            }
            
            // when height at right pointer is smaller this time
            else{
                // compute area
                int theArea = height[r] * (r - l);
                area = Math.max(area, theArea);
                r--;    // move pointer to the left
            }
        }

        return area;
    }

    public static void main(String[] args) {
        int[] height = {1,8,6,2,5,4,8,3,7};
        int result = maxArea(height);
        System.out.println("The maximum area is: " + result); // Output: 49
    }
}
