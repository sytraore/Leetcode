package LinkedList;

public class MergeSortedList {
    // Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        /**
            Have two pointers, each pointing at one list head
            go through each list
            compare each node value and add the smallest value to a new list
            move the pointer of the node with the smallest value one step to the right
            repeat until all nodes have been visited   
            
            Time complexity: O(n + m) where n and m are the lengths of the two lists
            Space complexity: O(1) if we do not count the new list as extra space
        */
        
        ListNode head = new ListNode(); // this is the head of the new list
        ListNode curr = head;           // this is the pointer used to track newly added nodes

        while(list1 != null && list2 != null){
            if (list1.val < list2.val){
                curr.next = list1;
                list1 = list1.next;
            }

            else if (list1.val > list2.val){
                curr.next = list2;
                list2 = list2.next;
            }
            
            // if value at both nodes are the same
            // add both nodes and move both pointers
            else {
                curr.next = list1;
                list1 = list1.next;
                curr = curr.next;
                curr.next = list2;
                list2 = list2.next;
            }

            // update current pointer of the new list
            curr = curr.next;
        }

        // if one of the lists is not null, it means there are still nodes left in that list
        // simply add the remaining nodes to the new list
        if (list1 != null){
            curr.next = list1;
        }
        else {
            curr.next = list2;
        }
        
        head = head.next;
        return head;
    }

    public static void main(String[] args) {
        // Example usage
        MergeSortedList msl = new MergeSortedList();
        ListNode list1 = msl.new ListNode(1, msl.new ListNode(2, msl.new ListNode(4)));
        ListNode list2 = msl.new ListNode(1, msl.new ListNode(3, msl.new ListNode(4)));
        
        ListNode mergedHead = msl.mergeTwoLists(list1, list2);
        
        // Print merged list
        ListNode current = mergedHead;
        while (current != null) {
            System.out.print(current.val + " "); // Output: 1 1 2 3 4 4
            current = current.next;
        }
    }
}
