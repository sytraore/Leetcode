package Arrays.TwoPointers;

public class ValidPalindrome2 {
    public boolean validPalindrome(String s) {
        /**
            At each iteration, check if characters at both pointers are equals
            in scenarios where they are not equals, we must find which character
            is the one that can be remove and the string will be a palindrome

            Data structure: String
            Algorithm: Two pointers
            Time complexity: O(n)
            Space complexity: O(1)
         */
        int l = 0, r = s.length() - 1;

        while(l < r){
            if (s.charAt(l) != s.charAt(r)){
                // check if we remove either:
                //  => the character at l, will the string still be a palindrome?
                //  => the character at r, will the string still be a palindrome?
                boolean withoutLeftChar = isPalindrome(s, l + 1, r); // this will check if the string is a palindrome without the character at left pointer
                boolean withoutRightChar = isPalindrome(s, l, r - 1); // this will check if the string is a palindrome without the character at right pointer
                return withoutLeftChar || withoutRightChar; // if one is true in either case, we have a palindrome
            }

            // update the pointers when characters are equal at both pointers
            l++;
            r--;
        }
        return true;
    }

    private boolean isPalindrome(String s, int l, int r){

        while (l < r){
            if (s.charAt(l) != s.charAt(r)){
                return false;
            }
            l++;
            r--;
        }
        return true;
    }

    public static void main(String[] args) {
        ValidPalindrome2 vp = new ValidPalindrome2();
        String test1 = "abca";
        String test2 = "racecar";
        String test3 = "abc";

        System.out.println(vp.validPalindrome(test1)); // true
        System.out.println(vp.validPalindrome(test2)); // true
        System.out.println(vp.validPalindrome(test3)); // false
    }
}
