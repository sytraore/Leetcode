package Arrays.TwoPointers;

public class ValidPalindrome {
    public static boolean isPalindrome(String s) {
        // first, format the string to the correct format:
        //  =>  no uppercase nor non-alphanumeric character (aka punctuations and white space)
        // Two pointers approach
        /*
            One pointer points at the first character and the second pointer points at the last character
            Check if both characters at both pointers are the same. 
            if false => return false
            else => continue iteration
         */

        s = s.toLowerCase().replaceAll("[\\p{Punct}\\s]", "");
        int left = 0, right = s.length() - 1;

        while (left < right){
            if (s.charAt(left) != s.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;


    }
    
    public static void main(String[] args) {
        String test1 = "";
        String test2 = "race a car";
        String test3 = "A man, a plan, a canal: Panama";

        System.out.println(isPalindrome(test1)); // true
        System.out.println(isPalindrome(test2)); // false
        System.out.println(isPalindrome(test3)); // true
    }
}
