package Java;

public class MoveZeroes{
    public void moveZeroes(int[] nums) {
        // move all the zeroes at the end
        // keep order of nonzeroes

        // case 1: [0] => [0]
        // case 2: [1,0] => [1,0]
        // case3: [0,1,3] => [1,3,0]

        // case 4: [0,0,1,2] => [1,2,0,0]
        // case 5: [0,1,0,2,0,3] => [1,2,3,0,0,0]

        // 2 pointers

        /*
        pt1 points to 1st element
        pt2 is one step ahead of pt1
        check if value at pt1 == 0
            yes => check if value at pt2 != 0
                yes => swap values at both pointers and move pointers   forward
                no => only move the second pointer
            no => just move both pointers forward

        time complexity => O(n)
        space complexity => O(1)
        */

        // use a while loop with the condition that the value of the pt2 i lss than the length of the array

        int pt1 = 0;
        int pt2 = 1;

        while(pt2 < nums.length){
            // check value at first pointer
            if (nums[pt1] == 0){
                if (nums[pt2] != 0){
                    int temp = nums[pt1];
                    nums[pt1] = nums[pt2];
                    nums[pt2] = temp;
                    pt1++;
                    pt2++;
                }
                else{
                    pt2++;
                }
            }
            else{
                pt1++;
                pt2++;
            }
        }
    }

}