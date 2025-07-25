package LinkedList;

public class ReverseList {
    // Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static ListNode reverseList(ListNode head) {
        /**
            Make sure the linkedlist is not empty
            change the next pointer of the current node to point to the node before
            null -> 1 -> 2 -> null => null <- 1 2 -> null when current node is 1
            Second, move the current node to the next node and repeat the first step
            null <- 1 <- 2 null

            Data structure: LinkedList
            Algorithm: Iterative approach
            Time complexity: O(n)
            Space complexity: O(1)
         */
        if (head == null){
            return null;
        }

        // the node "before" represents the node before the current pointer
        // the node "after" represents the node after the current pointer
        // when we change the next pointer of the current node, there is no way to know
        // which node the current pointer was pointing before
        // that is why we use after to store that node so we can update the current node pointer easily
        ListNode current = head, before = null, after = head.next;

        while(after != null){
            // first step, change the pointer of the current node to the node before it
            current.next = before;
            // move the pointers to repeat the step above
            before = current;
            current = after;
            after = after.next;
        }

        // at this step, the current node is the last node
        // and the node "before" points to the node before the last node
        // simply make current node next pointer points to "before" node completes the reversal 
        current.next = before;
        return current;
    }

    public static void main(String[] args) {
        // Example usage
        ReverseList rl = new ReverseList();
        ListNode head = rl.new ListNode(1, rl.new ListNode(2, rl.new ListNode(3)));
        ListNode reversedHead = reverseList(head);
        
        // Print reversed list
        ListNode current = reversedHead;
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
    }
 
}

