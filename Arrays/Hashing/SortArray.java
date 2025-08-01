package Arrays.Hashing;

public class SortArray {
    // swap two numbers in an array
    private void swap(int[] nums, int l, int pivot){
        int temp = nums[l];
        nums[l] = nums[pivot];
        nums[pivot] = temp;
    }

    // partition the array and returns the index of the pivot new position
    private int partition(int[] nums, int l, int r, int pivot){
    
        while (l <= r){
            // look for any value that is greater than the pivot
            // if found, its index will be stored in l
            // and it will be swapped with a value that is less than the pivot
            while (nums[l] < pivot){
                l++;
            }

            // look for any value that is less than the pivot
            // if found, its index will be stored in r
            // and it will be swapped with a value that is greater than the pivot
            while (r >= l && nums[r] >= pivot){
                r--;
            }

            // this is where we swap the values according to their order relative to
            // the pivot
            if (r > l){
                swap(nums, l, r);
            }
        }
        // l will store the index of the new pivot => the index at which all values
        // on the left are less than the value at l
        // and all values on the right are greater than the value at l
        return l;
    }

    // check if the array contains only duplicates
    // this is used for optimization
    // if the array contains only duplicates, we don't need to sort it
    private boolean containDuplicates(int[] nums, int l, int r){
        // base case
        if (l >= r){
            return true;
        }

        // this is the first element in the array
        // if all the other element in the array has this value, 
        // it means the array contains only duplicates
        int val = nums[l];

        // check if every element in array is the same
        for (int i = l + 1; i <= r; i++){
            // find a non-duplicate
            if (val != nums[i]){
                return false;
            }
        }

        return true;
    }

    // recursively sort the array
    private void quicksort(int[] nums, int l, int r){
        // first check for duplicates
        // used for optimization
        if (containDuplicates(nums, l, r)){
            return;
        }

        // find pivot index
        int pivotIndx = (l + r) / 2;
        // put pivot at the end of the array
        swap(nums, pivotIndx, r);
        int pivot = nums[r];
        // partition the array
        int newL = partition(nums, l, r - 1, pivot);
        // put the pivot to its new sorted position
        swap(nums, newL, r);
        // all elements on the left of the pivot are less than the pivot
        // all elements on the right of the pivot are greater or equal than the pivot

        // sort left partion
        // make sure the subarray length is at least 2
        if ((newL - l) > 1) {
            quicksort(nums, l, newL - 1);
        }
        // sort right partition
        // make sure the subarray length is at least 2
        if ((r - newL) > 1){
            quicksort(nums, newL + 1, r);
        }
    }

    public int[] sortArray(int[] nums) {
        int l = 0, r = nums.length - 1;
        quicksort(nums, l, r);
        return nums;
    }

    public static void main(String[] args) {
        SortArray sorter = new SortArray();
        int[] nums = {5, 2, 3, 1, 4};
        int[] sortedArray = sorter.sortArray(nums);
        for (int num : sortedArray) {
            System.out.print(num + " ");
        }
    }
}
