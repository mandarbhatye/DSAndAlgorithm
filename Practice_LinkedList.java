package Amazon;

import mandar.MyLinkedList;

public class Practice_LinkedList {
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

    public Node addTwoNumbers_plain(Node l1, Node l2){
        if(l1 == null) return l2;
        if(l2== null) return l1;

        Node dummy = new Node(-1);
        Node itr = dummy;
        Node c1 = l1;
        Node c2 = l2;
        int carry =0;

        while(c1 != null || c2 !=null || carry !=0){
            int val1 = c1 !=null ? c1.data:0;
            int val2 = c2 !=null ? c2.data:0;
            int sum = carry + val1 + val2;
            carry = sum / 10;
            int digit = sum %10;
            itr.next = new Node(digit);
            itr = itr.next;

            if(c1!= null) c1 = c1.next;
            if(c2!= null) c2 = c2.next;
        }
        return dummy.next;
    }

    private Node addTwoNumbers_withreverse(Node l1, Node l2){
        if(l1 == null) return l2;
        if(l2 == null) return l1;

        Node dummy = new Node(-1);
        Node itr = dummy;

        Node c1 = reverse_Recursive(l1);
        Node c2 = reverse_Recursive(l2);

        int carry = 0;

        while(c1!= null || c2!=null|| carry !=0){
            int val1 = c1!=null? c1.data:0;
            int val2 = c2!=null? c2.data:0;

            int sum = carry + val1 + val2;
            carry = sum / 10;
            int digit = sum%10;
            itr.next = new Node(digit);
            itr= itr.next;

            if(c1 !=null) c1 = c1.next;
            if(c2 !=null) c2 = c2.next;
        }

        return reverse_Recursive(dummy.next);
    }

    private Node reverse_Recursive(Node head){
        if(head == null | head.next == null) return head;

        Node temp = reverse_Recursive(head.next);
        head.next.next = head;
        head.next = null;

        return temp;
    }

    private Node reverse_Iterative(Node head){
        if(head == null || head.next == null) return head;
        Node current = head;
        Node previous = null;
        Node forward= null;

        while (current != null) {
            forward = current.next;
            current.next = previous;

            previous = current;
            current = forward;
        }
        return previous;
    }

    private Node getMiddleOfLinkedList(Node head){
        if(head == null || head.next == null) return head;

        Node fast = head;
        Node slow = head;

        while(fast.next!=null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
    private boolean isLinkedListPallindrome(Node head){
        if(head == null || head.next == null) return true;

        Node middle = getMiddleOfLinkedList(head);
        Node secondHalf = reverse_Iterative(middle.next);
        Node current = head;

        while(secondHalf != null){
            if(current.data != secondHalf.data) return false;

            if(current != null) current = current.next;
            secondHalf = secondHalf.next;
        }
        return true;
    }

    private Node mergeTwoLinkedList(Node l1, Node l2){
        if(l1 == null) return l2;
        if(l2 == null) return l1;

        Node dummy = new Node(-1);
        Node itr = dummy;

        Node c1 = l1;
        Node c2 = l2;

        while(c1!= null && c2 != null){
            if(c1.data < c2.data){
                itr.next = c1;
                c1 = c1.next;
            }else{
                itr.next = c2;
                c2 = c2.next;
            }
            itr = itr.next;
        }
       if(c1!= null) itr.next = c1;
       if(c2!= null) itr.next = c2;

        return dummy.next;
    }

    private Node detectCycleAndReturnCycleStarPoint(Node head){
        if(head == null || head.next == null) return head;

        Node fast = head;
        Node slow = head;

        do {
            if(fast == null || fast.next == null)
                return null;

            fast = fast.next.next;
            slow= slow.next;

        }while(fast != slow);

        slow = head;

        while(fast != slow){
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }

    private Node evenOddList(Node head){
        if(head == null || head.next == null) return head;

        Node dummyEven = new Node(-1);
        Node dummyEvenItr = dummyEven;
        Node dummyOdd = new Node(-1);
        Node dummyOddItr = dummyOdd;
        Node current = head;

        while(current != null){
            if(current.data % 2 == 1){
                dummyOddItr.next = current;
                dummyOddItr = dummyOddItr.next;
            }else{
                dummyEvenItr.next = current;
                dummyEvenItr = dummyEvenItr.next;
            }
            current = current.next;
        }
        dummyEvenItr.next = dummyOdd.next;
        dummyOddItr.next = null;

        return dummyEven.next;
    }

    private Node seggregateZeroAndOneFromLinkeedList(Node head){
        if(head == null || head.next == null) return head;

        Node dummyOne = new Node(-1);
        Node dummyOneItr = dummyOne;

        Node dummyZero = new Node(-1);
        Node dummyZeroItr = dummyZero;

        Node current = head;

        while(current != null){
            if(current.data ==1){
                dummyOneItr.next = current;
                dummyOneItr = dummyOneItr.next;
            }
            if(current.data ==0){
                dummyZeroItr.next = current;
                dummyZeroItr= dummyZeroItr.next;
            }
            current = current.next;
        }

        dummyOneItr.next = dummyZero.next;
        dummyZeroItr.next = null;

        return dummyOne.next;
    }

    private int getLength(Node head){
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
        if(head == null || head.next == null || k == 1) return head;

        int len = getLength(head);

        Node dummy = new Node(-1);
        dummy.next = head;

        Node curr = dummy;
        Node prev = dummy;
        Node next = dummy;

        while(len >= k){
            curr = prev.next;
            next = curr.next;

            for (int i = 1; i < k; i++) {
                curr.next = next.next;
                next.next = prev.next;

                prev.next = next;
                next = curr.next;
            }
            prev = curr;
            len -=k;
        }
        return dummy.next;
    }

    private Node removeNNode(Node head, int k){
        if(head == null || head.next == null) return head;

        Node fast = head;
        Node slow = head;

        for (int i = 0; i < k ; i++) {
            if(fast.next == null){
                Node next = head.next;
                head.next = null;
                head = next;

                return head;
            }else{
                fast = fast.next;
            }
        }

        while(fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;

        return head;
    }
    private Node rotateLinkedList(Node head, int k){
        if(head == null || head.next == null || k==0) return head;
        int len = 1;
        Node temp = head;

        while(temp.next != null){
            temp = temp.next;
            len++;
        }
        k = k % len;

        if(k == len || k ==0)
            return head;

        temp.next = head;

        temp= head;
        for (int i = 0; i < Math.abs(len-k-1) ; i++) {
            temp= temp.next;
        }

        head = temp.next;
        temp.next = null;

        return head;
    }

    private Node mergeKList(Node[] nodeList){
        if (nodeList.length == 0) return null;
        return mergeKList(nodeList, 0, nodeList.length - 1);
    }

    private Node mergeKList(Node[] nodeList, int startId, int endId){
        if(startId > endId) return null;
        if(startId == endId) return nodeList[startId];

        int mid = (startId + endId)/2;

        Node l1 = mergeKList(nodeList, startId, mid);
        Node l2 = mergeKList(nodeList, mid +1, endId);

        return mergeTwoLinkedList(l1,l2);
    }

    public static Node flattenList(Node head){
        if(head == null)
            return null;

        Node next = head.next;

        head.next = flattenList(head.down);

        Node tail = head;
        while(tail.next != null)
            tail = tail.next;

        tail.next = flattenList(next);

        return head;
    }

    public static void flattenList_v2(Node head){
        if(head == null) return;

        Node temp = null;
        Node current = head;

        Node tail = head;
        while(tail.next != null) tail = tail.next;

        while(current != tail){
            if(current.down != null){
                tail.next = current.down;

                temp = current.down;
                while(temp.next != null){
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
    public static Node[] growLinkedList(Node head, Node tail, Node current){
        Node newHead = head;
        Node newTail = current;

        if(newHead == null)
            newHead = current;
        if(tail !=null)
            tail.next = current;

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
        Node secondHalf = reverse_Iterative(middle.next);
        middle.next = null;

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

    private Node unZipLinkedList(Node head) {
        if(head == null || head.next == null) return null;

        Node firstHead = head;  //first head
        Node firstPrev = firstHead;// first previous pointer

        Node secondHead = head.next; // second head
        Node secondPrev = secondHead;//second previous pointer

        while(secondPrev!=  null && secondPrev.next != null){
            firstPrev.next   = secondPrev.next;
            secondPrev.next = secondPrev.next.next;

            firstPrev = firstPrev.next;
            secondPrev = secondPrev.next;
        }
        firstPrev.next = null;

        secondHead = reverse_Recursive(secondHead);
        firstPrev.next = secondHead;

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

    public static void main(String[] args) {
        System.out.println("======================== Addition of LinkedList - Without reverse =========================");
        Practice_LinkedList l1 = new Practice_LinkedList();
        l1.addFirst(7);
        l1.addLast(5);
        l1.addLast(9);
        l1.addLast(4);
        l1.addLast(6);
        //l1.addLast(-1);


        Practice_LinkedList l2 = new Practice_LinkedList();
        l2.addLast(8);//remove
        l2.addLast(4);
        // l2.addLast(-1);
        Node addition = l1.addTwoNumbers_plain(l1.head, l2.head); //5 -> 0 -> 0 -> 5 -> 6 -> NULL
        l1.printList(addition);

        System.out.println("======================== Addition of LinkedList - With Reverse =========================");
        Node newAddNode = l1.addTwoNumbers_withreverse(l1.head, l2.head); //7 -> 6 -> 0 -> 3 -> 0 -> NULL
        l1.printList(newAddNode);

        System.out.println("============================== LinkedList Palindrome Check ==============================");
        Practice_LinkedList l3 = new Practice_LinkedList();
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
        l1 = new Practice_LinkedList();
        l1.addFirst(2);
        l1.addLast(4);
        l1.addLast(3);
        l2 = new Practice_LinkedList();
        l2.addLast(5);//remove
        l2.addLast(6);
        l2.addLast(4);
        Node merged = l1.mergeTwoLinkedList(l1.head, l2.head);
        l1.printList(merged); //2 -> 4 -> 3 -> 5 -> 6 -> 4 -> NULL

        System.out.println("============================ Detect Cycle in Linked List =================================");
        l1 = new Practice_LinkedList();
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
        System.out.println("Linked List has cycle detected at element : " + cycle.data); //Linked List has cycle detected at element : 3

        System.out.println("================================= Even/Odd Linked List ===================================");
        l1 = new Practice_LinkedList();
        l1.addFirst(1);
        l1.addLast(2);
        l1.addLast(4);
        l1.addLast(6);
        l1.addLast(5);
        l1.addLast(7);
        l1.addLast(8);
        Node evenOdd = l1.evenOddList(l1.head);
        l1.printList(evenOdd); //2 -> 4 -> 6 -> 8 -> 1 -> 5 -> 7 -> NULL

        System.out.println("========================== Segreggate Zero and One Linked List ============================");

        l1.addFirst(1);
        l1.addLast(0);
        l1.addLast(0);
        l1.addLast(1);
        l1.addLast(1);
        l1.addLast(0);
        l1.addLast(1);
        Node segreggatedLL = l1.seggregateZeroAndOneFromLinkeedList(l1.head);
        l1.printList(segreggatedLL);//1 -> 1 -> 1 -> 1 -> 1 -> 0 -> 0 -> 0 -> NULL

        System.out.println("========================== Reverse Linked List in K groups ===============================");
        l1 = new Practice_LinkedList();
        l1.addFirst(1);
        l1.addLast(2);
        l1.addLast(4);
        l1.addLast(6);
        l1.addLast(5);
        l1.addLast(7);
        l1.addLast(8);

        Node kLinkedList = l1.reverseLinkedListInKGroup(l1.head, 3);
        l1.printList(kLinkedList);//4->2->1->7->->5->6->8->NULL


        System.out.println("======================== Remove N node from emd Linked List ==============================");
        l1 = new Practice_LinkedList();
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
        Node finalNode = l1.removeNNode(l1.head, 10);
        l1.printList(finalNode);

        System.out.println("======================== Rotate Linked list with K itetration ===========================-");
        Practice_LinkedList list = new Practice_LinkedList();
        list.addFirst(0);
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);

        list.head = list.rotateLinkedList(list.head,9);
        list.printList(list.head); //3 -> 4 -> 5 -> 0 -> 1 -> 2 -> NULL

        System.out.println("============================== Merge k Sorted Lists ======================================");
        l1 = new Practice_LinkedList();
        l1.addFirst(1);
        l1.addLast(4);
        l1.addLast(5);
        l2 = new Practice_LinkedList();
        l2.addLast(1);
        l2.addLast(3);
        l2.addLast(4);
        l3 = new Practice_LinkedList();
        l3.addLast(2);
        l3.addLast(6);
        Node[] nodeList = new Node[]{l1.head, l2.head, l3.head};
        Node mergedList = l1.mergeKList(nodeList);
        l1.printList(mergedList); ////1 -> 1 -> 2 -> 3 -> 4 -> 4 -> 5 -> 6 -> NULL

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
        l1 = new Practice_LinkedList();
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
        l1 = new Practice_LinkedList();
        l1.addFirst(1);
        l1.addLast(2);
        l1.addLast(3);
        l1.addLast(4);
        l1.addLast(5);
        l1.addLast(6);
        //1->6->2->5->3->4
        Node zip = l1.zipLinkedList(l1.head);
        l1.printList(zip);

        System.out.println("================================= UNZIP Linked list  =======================================");
        l1 = new Practice_LinkedList();
        l1.addFirst(1);
        l1.addLast(6);
        l1.addLast(2);
        l1.addLast(5);
        l1.addLast(3);
        l1.addLast(4);
        //1->2->3->4->5->6
        Node unZip = l1.unZipLinkedList(l1.head);
        l1.printList(unZip);

        System.out.println("========================== Remove Duplicates from Linked list ============================");
        l1 = new Practice_LinkedList();
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


        //======================= Reverse Linked list between defined  nodes =======================
        //10 -> 20 -> 30 -> 33 -> 77 -> 99 -> 101 -> 103 -> 107 -> NULL
        //10 -> 20 -> 30 -> 33 -> 103 -> 101 -> 99 -> 77 -> 107 -> NULL
    }
}
