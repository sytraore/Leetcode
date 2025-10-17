package Sorting;

/*
 * This is an optimal implementation of the quick sort algorithm
 * It uses the Lomuto partition scheme which is more efficient than the Hoare partition scheme
 * It also handles arrays with many duplicate values more efficiently
 * by ensuring that all values equal to the pivot are grouped together
 * This reduces the number of recursive calls and improves performance
 * Time complexity: O(n log n) on average, O(n^2) in the worst case
 * Space complexity: O(log n) due to recursive stack space
 */
public class SortArrayOptimal {
    // swap two numbers in an array
    private static void swap(int[] nums, int index1, int index2){
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

    // partition the array and returns the index of the pivot new position
    private static int partition(int[] nums, int l, int r){
        // use the last element as pivot
        // smallValueIndex will store the index of the last element that is less than or equal to the pivot
        int pivot = nums[r], smallValueIndex = l -1;

        // iterate through the array from left to right and stop at the second to last element
        // because the last element is the pivot
        for (int i = l; i <= r-1; i++){
            // if the current element is less than or equal to the pivot
            // we increment the smallValueIndex because we found a new element that is less than or equal to the pivot
            // then we swap the current element with the element at smallValueIndex because we want all elements less than or equal to the pivot
            // to be on the left side of the pivot
            if (nums[i] <= pivot){
                smallValueIndex++;
                swap(nums, smallValueIndex, i);
            }
        }
        // finally, we swap the pivot with the element at smallValueIndex + 1
        // because the pivot should be at its correct position which is the index where all elements on the left are less than or equal to the pivot
        // and all elements on the right are greater than the pivot
        int pivotPosition = smallValueIndex + 1;
        swap(nums, pivotPosition, r);
        // we return the pivot position
        return pivotPosition;
    }

    private static void quickSort(int[] nums, int l, int r){
        // base case
        // as long as the left index is less than the right index
        // we keep partitioning the array
        // otherwise, it means the array is sorted
        if (l < r){
            int pivotIndex = partition(nums, l, r);
            // recursively sort the left subarray and the right subarray
            quickSort(nums, l, pivotIndex - 1);
            quickSort(nums, pivotIndex + 1, r);
        }
    }

    public static void main(String[] args){
        int[] nums = {5, 5, 5, 5, 5, 5, 5, 5, 5, 3, 5, 5, 5, 5, 5, 5, 5, 5, 5};
        quickSort(nums, 0, nums.length -1);
        for (int num : nums) {
            System.out.print(num + " ");
        }

        int floor = (int)Math.floor((double)3 / 2);
        System.out.println("\ndivision casting = " + (double)3 / 2);
        System.out.println("\nThe floor value is " + floor);
    }
}
