package Amazon;

import sun.reflect.generics.scope.DummyScope;

import java.util.*;

class Node {
    int data = 0;
    Node next;
    Node down;

    Node random;
    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}
public class LeetCodeAmazon_LinkedList {
    Node head;
    public void addFirst(int data) {
        Node newNode = new Node(data);

        if (head == null) {
            head = newNode;
            return;
        }
        newNode.next = head;
        head = newNode;
    }

    public void addLast(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        Node currNode = head;
        while (currNode.next != null) {
            currNode = currNode.next;
        }

        currNode.next = newNode;
    }

    public void addLast(Node node) {
        if (head == null) {
            head = node;
            return;
        }
        Node currNode = head;
        while (currNode.next != null) {
            currNode = currNode.next;
        }

        currNode.next = node;
    }

    public void printList(Node root) {
        if (root == null) {
            System.out.println("List is empty");
            return;
        }
        Node currNode = root;
        while (currNode != null) {
            System.out.print(currNode.data + " -> ");
            currNode = currNode.next;
        }
        System.out.println("NULL");
    }
    public static void printOriginalList(Node head)
    {
        if (head == null) return;
        System.out.print(" " + head.data + " ");

        if (head.down != null)
        {
            System.out.print("[");
            printOriginalList(head.down);
            System.out.print("]");
        }
        printOriginalList(head.next);
    }

    private Node addTwoNumbers_plain(Node l1, Node l2){
        if(l1== null) return l1== null? l1:l2;

        Node dummy = new Node(-1);
        Node dummyItr = dummy;

        Node c1 = l1;
        Node c2 = l2;
        int carry =0;

        while(c1 != null || c2 != null || carry!=0){
            int val1 = c1 != null? c1.data:0;
            int val2 = c2 != null? c2.data:0;
            int sum = carry + val1 + val2;
            int digit = sum % 10;
            carry = sum/10;

            dummyItr.next = new Node(digit);

            dummyItr = dummyItr.next;
            if(c1 != null) c1 = c1.next;
            if(c2 != null) c2 = c2.next;
        }
        return dummy.next;
    }

    private Node reverse_Recursion(Node head){
        if(head == null || head.next == null) return head;

        Node temp = reverse_Recursion(head.next);
        head.next.next = head;
        head.next= null;

        return temp;
    }
    private Node addTwoNumbers_withreverse(Node l1, Node l2){
        if(l1 == null) return l1 != null? l1:l2;

        Node dummy = new Node(-1);
        Node dummyItr = dummy;

        Node c1 = reverse_Recursion(l1);
        Node c2 = reverse_Recursion(l2);
        int carry =0;

        while(c1 != null || c2 !=null || carry!=0){
            int val1 = c1 != null? c1.data: 0;
            int val2 = c2 != null? c2.data: 0;
            int sum = carry + val1 + val2;
            int digit = sum % 10;
            carry = sum/10;

            dummyItr.next = new Node(digit);
            dummyItr = dummyItr.next;

            if(c1 != null) c1 = c1.next;
            if(c2 != null) c2 = c2.next;
        }
        return reverse_Recursion(dummy.next);
    }

    private Node getMiddleOfLinkedList(Node head){
        if(head == null || head.next == null) return head;

        Node fast = head;
        Node slow = head;

        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    private Node reverse_Iteration(Node head){
        if(head == null || head.next == null) return head;

        Node current = head;
        Node previous = null;
        Node forward = null;

        while(current != null){
            forward = current.next;
            current.next = previous;

            previous = current;
            current = forward;
        }
        return previous;
    }
    private boolean isLinkedListPallindrome(Node head){
        if(head == null || head.next == null) return true;

        Node middle = getMiddleOfLinkedList(head);
        Node secondHalf = reverse_Iteration(middle.next);
        Node current = head;

        while(current != null && secondHalf != null){
            if(secondHalf.data != current.data) return false;

            secondHalf = secondHalf.next;
            current = current.next;
        }
        return true;
    }

    private Node mergeTwoLinkedList(Node l1, Node l2){
        if(l1 == null) return l2 != null? l2:l1;

        Node dummy = new Node(-1);
        Node dummyItr = dummy;

        Node c1 = l1;
        Node c2 = l2;

        while(c1 != null && c2 != null){
            int c1Data = c1!=null? c1.data: 0;
            int c2Data = c2!=null? c2.data: 0;

            if(c1Data < c2Data){
                dummyItr.next = c1;
                c1 = c1.next;
            }else{
                dummyItr.next = c2;
                c2 = c2.next;
            }
            dummyItr = dummyItr.next;
        }

        if(c1 != null) dummyItr.next = c1;
        if(c2 != null) dummyItr.next = c2;

        return dummy.next;
    }

    private Node detectCycleAndReturnCycleStarPoint(Node head){
        if(head == null || head.next == null) return head;

        Node fast = head;
        Node slow = head;

        do{
            if(fast== null || fast.next == null) return null;

            fast = fast.next.next;
            slow = slow.next;
        }while(fast != slow);

        slow = head;

        while(fast != slow){
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    private Node evenOddList( Node head){
        if(head == null || head.next == null) return head;

        Node dummyEven = new Node(-1);
        Node evenItr = dummyEven;

        Node dummyOdd = new Node(-1);
        Node oddItr = dummyOdd;

        Node current = head;

        while(current != null){
            if(current.data % 2 == 0){
                evenItr.next = current;
                evenItr = evenItr.next;
            }else{
                oddItr.next = current;
                oddItr = oddItr.next;
            }
            current = current.next;
        }

        evenItr.next = dummyOdd.next;
        oddItr.next = null;

        return dummyEven.next;
    }

    private Node seggregateZeroAndOneFromLinkeedList(Node head){
        if(head == null || head.next == null) return head;

        Node dummyOne = new Node(-1);
        Node oneItr = dummyOne;

        Node dummyZero = new Node(-1);
        Node zeroItr = dummyZero;

        Node current = head;

        while(current != null){
            if(current.data == 1){
                oneItr.next = current;
                oneItr = oneItr.next;
            }else if(current.data == 0){
                zeroItr.next = current;
                zeroItr =zeroItr.next;
            }
            current = current.next;
        }

        oneItr.next = dummyZero.next;
        zeroItr.next = null;

        return dummyOne.next;
    }

    private int getLinkedListLength(Node head){
        if(head == null) return 0;

        int len =0;
        Node current = head;

        while(current != null){
            current = current.next;
            len++;
        }
        return len;
    }
    private Node reverseLinkedListInKGroup(Node head, int k){
        if(head == null || head.next == null || k==1) return head;

        int len = getLinkedListLength(head);

        Node dummy = new Node(-1);
        dummy.next = head;

        Node current = dummy;
        Node previous = dummy;
        Node next = dummy;

        while (len >=k){
            current = previous.next;
            next = current.next;

            for(int i =1; i< k; i++){
                current.next = next.next;
                next.next = previous.next;

                previous.next = next;
                next = current.next;
            }
            previous = current;
            len -=k;
        }
        return dummy.next;
    }

    private Node removeNNode(Node head, int k){
        if(head == null || head.next == null) return head;

        Node fast = head;
        Node slow = head;
        Node current = head;

        for (int i = 0; i < k ; i++) {
            if(fast.next == null) {
                Node next = head.next;
                head.next = null;
                head = next;
                return head;
            }
            fast= fast.next;
        }

        while(fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;

        return head;
    }

    private Node mergeKList(Node[] nodeList){
        if (nodeList.length == 0) return null;
        return mergekList_recursion(nodeList, 0, nodeList.length-1);
    }

    private Node mergekList_recursion(Node[] nodeList, int low, int high){
        if(low > high) return null;
        if(low == high) return nodeList[low];

        int mid = (low + high) /2;
        Node left = mergekList_recursion(nodeList,low, mid);
        Node right = mergekList_recursion(nodeList,mid+1, high);

        return mergeTwoLinkedList(left, right);
    }

    private Node rotateLinkedList(Node head, int rotationCount){
        if(head == null || head.next == null || rotationCount == 0) return head;

        Node temp = head;
        int len =1;
        while(temp.next != null){
            temp = temp.next;
            len++;
        }
        rotationCount = rotationCount % len;
        if(rotationCount == 0 || rotationCount == len)return head;

        temp.next = head;

        temp = head;

        for (int i = 0; i < len-rotationCount-1 ; i++) {
          temp = temp.next;
        }

        head = temp.next;
        temp.next = null;

        return head;
    }

    private static Node flattenList(Node head){
        if(head == null) return head;

        Node next = head.next;

        head.next = flattenList(head.down);

        Node tail = head;
        while(tail.next != null)
            tail = tail.next;

        tail.next = flattenList(next);

        return head;
    }

    private static void flattenList_v2(Node head){
        if(head == null) return;

        Node temp = null;
        Node tail = head;
        Node current = head;

        while(tail.next != null)
            tail = tail.next;

        while(current != tail){
            if(current.down != null) {
                tail.next = current.down;

                temp = current.down;
                while (temp.next != null) {
                    temp = temp.next;
                }
                tail = temp;
            }
            current = current.next;
        }
    }

    private Node rearrangeLinkedList(Node head, int k){
        if(head == null || k ==0 )return head;

        Node smallerHead = null;
        Node smallerTail = null;
        Node greaterHead = null;
        Node greaterTail = null;
        Node equalHead   = null;
        Node equalTail   = null;

        Node current = head;
        Node previous = null;

        while (current != null){
            if(current.data < k){
                Node[] lst1 = growLinkedList(smallerHead,smallerTail, current);
                smallerHead = lst1[0];
                smallerTail = lst1[1];
            }else if(current.data > k){
                Node[] lst2 = growLinkedList(greaterHead,greaterTail, current);
                greaterHead = lst2[0];
                greaterTail = lst2[1];
            }else{
                Node[] lst3 = growLinkedList(equalHead,equalTail, current);
                equalHead = lst3[0];
                equalTail = lst3[1];
            }
            previous = current;
            current = current.next;
            previous.next = null;
        }
        Node[] connectedList1 = connectLinkedList(smallerHead, smallerTail, equalHead, equalTail);
        Node firstHead = connectedList1[0];
        Node firstTail = connectedList1[1];
        Node[] connectedList2  = connectLinkedList(firstHead, firstTail, greaterHead,greaterTail);

        return connectedList2[0];
    }
    public static Node[] growLinkedList(Node head, Node tail, Node node){
        Node newHead = head;
        Node newTail = node;

        if(newHead == null)
            newHead = node;
        if(tail !=null)
            tail.next = node;

        return new Node[] {newHead, newTail};
    }

    public static Node[] connectLinkedList(Node headOne,Node tailOne,Node headTwo, Node tailTwo){
        Node newHead = (headOne == null)  ? headTwo: headOne;
        Node newTail = (tailTwo == null) ? tailOne: tailTwo;

        if(tailOne !=null)
            tailOne.next = headTwo;

        return new Node[] {newHead, newTail};
    }

    private Node zipLinkedList(Node head){
        if(head == null) return head;

        Node middle = getMiddleOfLinkedList(head);
        Node secondHalf = reverse_Iteration(middle.next);
        middle.next = null;
        Node current = head;

        Node c1 = head;
        Node c2 = secondHalf;

        while(c1 != null && c2 != null){
            Node temp1 = c1.next;
            Node temp2 = c2.next;

            c1.next = c2;
            c2.next = temp1;

            c1 = temp1;
            c2 = temp2;
        }

        return head;
    }

    public Node removeDuplicatesFromLinkedList(Node linkedList) {
        Node temp = linkedList;

        while (temp.next != null) {
            if(temp.data == temp.next.data){
                temp.next = temp.next.next;
            }else{
                temp = temp.next;
            }
        }
        return linkedList;
    }

    public Node reverseBetweenRecurr(Node head, int start,int end){
        if(head == null || start == end) return head;

        Node dummy = new Node(-1);
        dummy.next = head;

        Node nodeBeforeSubList = dummy;
        int pos = 1;

        while(pos < start){
            nodeBeforeSubList = nodeBeforeSubList.next;
            pos++;
        }

        Node workingPtr = nodeBeforeSubList.next;
        while (start < end){
            Node nodeToBeExtracted = workingPtr.next;
            workingPtr.next = nodeToBeExtracted.next;

            nodeToBeExtracted.next = nodeBeforeSubList.next;
            nodeBeforeSubList.next = nodeToBeExtracted;
            start++;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        //Input: l1 = [2,4,3], l2 = [5,6,4]
        //Output: [7,0,8]
        System.out.println("======================== Addition of LinkedList - Without reverse =========================");
        LeetCodeAmazon_LinkedList l1 = new LeetCodeAmazon_LinkedList();
        l1.addFirst(7);
        l1.addLast(5);
        l1.addLast(9);
        l1.addLast(4);
        l1.addLast(6);
        //l1.addLast(-1);


        LeetCodeAmazon_LinkedList l2 = new LeetCodeAmazon_LinkedList();
        l2.addLast(8);//remove
        l2.addLast(4);
       // l2.addLast(-1);
        Node addition = l1.addTwoNumbers_plain(l1.head, l2.head);
        l1.printList(addition);

        System.out.println("======================== Addition of LinkedList - With Reverse =========================");
        Node newAddNode = l1.addTwoNumbers_withreverse(l1.head, l2.head);
        l1.printList(newAddNode);

        System.out.println("======================== Addition of LinkedList - With Reverse =========================");
        LeetCodeAmazon_LinkedList l3 = new LeetCodeAmazon_LinkedList();
        l3.addLast(4);//remove
        l3.addLast(3);
        l3.addLast(1);
        l3.addLast(2);
        l3.addLast(1);
        l3.addLast(3);
        l3.addLast(4);
        boolean isPallindrome = l1.isLinkedListPallindrome(l3.head);
        System.out.println("Linked list is Pallindrome : " + isPallindrome);

        System.out.println("================================ Merge 2 Linked List =====================================");
        l1 = new LeetCodeAmazon_LinkedList();
        l1.addFirst(2);
        l1.addLast(4);
        l1.addLast(3);
        l2 = new LeetCodeAmazon_LinkedList();
        l2.addLast(5);//remove
        l2.addLast(6);
        l2.addLast(4);
        Node merged = l1.mergeTwoLinkedList(l1.head, l2.head);
        l1.printList(merged);

        System.out.println("============================ Detect Cycle in Linked List =================================");
        l1 = new LeetCodeAmazon_LinkedList();
        l1.addFirst(1);
        l1.addLast(2);
        l1.addLast(3);
        l1.addLast(4);
        l1.addLast(5);
        l1.addLast(6);
        l1.addLast(7);

        //Create loop in Linked List
        Node curr = l1.head;
        Node curr2 = l1.head;
        while (curr != null) {
            if (curr.data == 2) {
                break;
            }
            curr = curr.next;
        }
        while (curr2.next != null) {
            curr2 = curr2.next;
        }
        curr2.next = curr;
        Node cycle = l1.detectCycleAndReturnCycleStarPoint(l1.head);
        System.out.println("Linked List has cycle detected at element : " + cycle.data);

        System.out.println("================================= Even/Odd Linked List ===================================");
        l1 = new LeetCodeAmazon_LinkedList();
        l1.addFirst(1);
        l1.addLast(2);
        l1.addLast(4);
        l1.addLast(6);
        l1.addLast(5);
        l1.addLast(7);
        l1.addLast(8);
        Node evenOdd = l1.evenOddList(l1.head);
        l1.printList(evenOdd);

        System.out.println("========================== Segreggate Zero and One Linked List ============================");

        l1.addFirst(1);
        l1.addLast(0);
        l1.addLast(0);
        l1.addLast(1);
        l1.addLast(1);
        l1.addLast(0);
        l1.addLast(1);
        Node segreggatedLL = l1.seggregateZeroAndOneFromLinkeedList(l1.head);
        l1.printList(segreggatedLL);

        System.out.println("========================== Reverse Linked List in K groups ===============================");
        l1 = new LeetCodeAmazon_LinkedList();
        l1.addFirst(1);
        l1.addLast(2);
        l1.addLast(4);
        l1.addLast(6);
        l1.addLast(5);
        l1.addLast(7);
        l1.addLast(8);
        //4->2->1->7->->5->6->8->NULL
        Node kLinkedList = l1.reverseLinkedListInKGroup(l1.head, 3);
        l1.printList(kLinkedList);

        System.out.println("======================== Remove N node from emd Linked List ==============================");
        l1 = new LeetCodeAmazon_LinkedList();
        l1.addFirst(0);
        l1.addLast(1);
        l1.addLast(2);
        l1.addLast(3);
        l1.addLast(4);
        l1.addLast(5);
        l1.addLast(6);
        l1.addLast(7);
        l1.addLast(8);
        l1.addLast(9);

        //1->2->4->6->7->8
        Node finalNode = l1.removeNNode(l1.head, 3);
        l1.printList(finalNode);

        System.out.println("============================== Merge k Sorted Lists ======================================");
        l1 = new LeetCodeAmazon_LinkedList();
        l1.addFirst(1);
        l1.addLast(4);
        l1.addLast(5);
        l2 = new LeetCodeAmazon_LinkedList();
        l2.addLast(1);
        l2.addLast(3);
        l2.addLast(4);
        l3 = new LeetCodeAmazon_LinkedList();
        l3.addLast(2);
        l3.addLast(6);
        Node[] nodeList = new Node[]{l1.head, l2.head, l3.head};
        Node mergedList = l1.mergeKList(nodeList);
        l1.printList(mergedList);

        System.out.println("======================== Rotate Linked list with K itetration ===========================-");
        LeetCodeAmazon_LinkedList list = new LeetCodeAmazon_LinkedList();
        list.addFirst(0);
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);

        list.head = list.rotateLinkedList(list.head,1);
        list.printList(list.head);////3 -> 4 -> 5 -> 0 -> 1 -> 2 -> NULL

        System.out.println("========================== Flatten a multilevel linked list ============================= ");
        // create individual nodes and link them together later
        Node one = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);
        Node four = new Node(4);
        Node five = new Node(5);
        Node six = new Node(6);
        Node seven = new Node(7);
        Node eight = new Node(8);
        Node nine = new Node(9);
        Node ten = new Node(10);
        Node eleven = new Node(11);
        Node twelve = new Node(12);
        Node thirteen = new Node(13);
        Node fourteen = new Node(14);
        Node fifteen = new Node(15);

        // set head node
        Node head = one;

        // set next pointers
        one.next = four;
        four.next = fourteen;
        fourteen.next = fifteen;
        five.next = nine;
        nine.next = ten;
        seven.next = eight;
        eleven.next = thirteen;

        // set down pointers
        one.down = two;
        two.down = three;
        four.down = five;
        five.down = six;
        six.down = seven;
        ten.down = eleven;
        eleven.down = twelve;

        System.out.println("The original list is :");
        l1.printOriginalList(head);

        flattenList(head);
        System.out.println("\nThe flattened list is :");
        l1.printList(head);

        System.out.println("======================== Flatten a multilevel linked list V2 =========================== ");
        // create individual nodes and link them together later
        one = new Node(1);
        two = new Node(2);
        three = new Node(3);
        four = new Node(4);
        five = new Node(5);
        six = new Node(6);
        seven = new Node(7);
        eight = new Node(8);
        nine = new Node(9);
        ten = new Node(10);
        eleven = new Node(11);
        twelve = new Node(12);
        thirteen = new Node(13);
        fourteen = new Node(14);
        fifteen = new Node(15);
        Node sixteen = new Node(16);
        Node seventeen = new Node(17);

        // set head node
        head = one;

        // set next pointers
        one.down = six;
        seven.down = eleven;
        eight.down = twelve;
        twelve.down = fifteen;
        four.down = nine;
        nine.down = thirteen;
        thirteen.down = sixteen;

        // set down pointers
        one.next = two;
        two.next = three;
        three.next = four;
        four.next = five;
        six.next = seven;
        seven.next = eight;
        nine.next = ten;
        thirteen.next = fourteen;
        sixteen.next = seventeen;

        System.out.println("The original list is :");
        l1.printOriginalList(head);

        flattenList_v2(head);
        System.out.println("\nThe flattened list is :");
        l1.printList(head);

        System.out.println("======================== Rearrange Linked list with K value ==============================");
        l1 = new LeetCodeAmazon_LinkedList();
        l1.addFirst(3);
        l1.addLast(0);
        l1.addLast(5);
        l1.addLast(2);
        l1.addLast(1);
        l1.addLast(4);

        //0->2->1->3->5->4
        Node arrangedNodes = l1.rearrangeLinkedList(l1.head, 3);
        l1.printList(arrangedNodes);

        System.out.println("================================= ZIP Linked list  =======================================");
        l1 = new LeetCodeAmazon_LinkedList();
        l1.addFirst(1);
        l1.addLast(2);
        l1.addLast(3);
        l1.addLast(4);
        l1.addLast(5);
        l1.addLast(6);
        //1->6->2->5->3->4
        Node zip = l1.zipLinkedList(l1.head);
        l1.printList(zip);

        System.out.println("========================== Remove Duplicates from Linked list ============================");
        l1 = new LeetCodeAmazon_LinkedList();
        l1.addFirst(1);
        l1.addLast(1);
        l1.addLast(3);
        l1.addLast(4);
        l1.addLast(4);
        l1.addLast(4);
        l1.addLast(5);
        l1.addLast(6);
        l1.addLast(6);

        //1->3->4->5->6
        Node duplicate = l1.removeDuplicatesFromLinkedList(l1.head);
        l1.printList(duplicate);

        System.out.println("======================= Reverse Linked list between defined  nodes =======================");
        l1 = new LeetCodeAmazon_LinkedList();
        l1.addFirst(10);
        l1.addLast(20);
        l1.addLast(30);
        l1.addLast(33);//remove
        l1.addLast(77);
        l1.addLast(99);
        l1.addLast(101);//remove
        l1.addLast(103);
        l1.addLast(107);

        l1.printList(l1.head);
        Node revered = l1.reverseBetweenRecurr(l1.head,5,8);
        l1.printList(revered); //10 -> 20 -> 30 -> 33 -> 103 -> 101 -> 99 -> 77 -> 107 -> NULL
    }
}
