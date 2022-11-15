package Amazon;

import Datastructures.BrowserHistory;

import java.util.HashMap;
import java.util.Map;

public class LRU_IntervieweKickStart {

    public static class ListNode{
        String key;
        String value;

        ListNode prev;
        ListNode next;

        public ListNode(String key, String value){
            this.key = key;
            this.value = value;
            prev = null;
            next = null;
        }
    }

    public static class LRUCache{
        Map<String,ListNode> map = new HashMap<>();
        int capacity =0;
        ListNode head = null;
        ListNode tail = null;

        public LRUCache(int capacity){
           this.capacity = capacity;
        }

        public void addToFront(String key, String value){
            ListNode temp = new ListNode(key,value);

            if(head == null){
                head = temp;
                tail = temp;
            }else{
                temp.next = head;
                head.prev = temp;
                head = temp;
            }
            map.put(key,temp);
        }

        public void removeLeastRecentlyUsed(){
            String key = tail.key;
            map.remove(key);

            if(head == tail){
                head = tail = null;
            }else{
                tail = tail.prev;
                tail.next = null;
            }

        }

        // Remove given node from the linked list.
        void erase_node(ListNode cur_node)
        {
            ListNode prev_node = cur_node.prev;
            ListNode next_node = cur_node.next;
            // Connect previous node with next node.
            if (prev_node != null)
            {
                prev_node.next = next_node;
            }
            if (next_node != null)
            {
                next_node.prev = prev_node;
            }

            if (head == tail)
            {
                // One element list becomes empty.
                head = tail = null;
            }
            else if (head == cur_node)
            {
                head = next_node;
            }
            else if (tail == cur_node)
            {
                tail = prev_node;
            }
        }

        // If a value for the given key is cached, makes it the MRU and returns it; else returns -1.
        public String get(String key){
            ListNode node = null;

            if(map.containsKey(key)){
                node = map.get(key);
            }

            if(node == null)
                return "";

            String value = node.value;
            // Remove from the original position.
            erase_node(node);
            // Add to the front of the list.
            addToFront(key, value);
            return value;
        }

        public String printAll(){
            ListNode temp = head;
            while(temp !=null){
                System.out.println(temp.value);
                temp=temp.next;
            }
            return"";
        }

        // Adds or updates the cached value for the given key.
        // Evicts the LRU cached value if necessary to avoid exceeding capacity of the cache.
        // Makes the newly added/updated cache entry the MRU one.
        void set(String key, String value){
            if(!map.containsKey(key)){
                if(map.size() > capacity){
                    removeLeastRecentlyUsed();
                }
                addToFront(key, value);
            }else{
                erase_node(map.get(key));
                addToFront(key,value);
            }
            capacity++;
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(4);
        cache.set("google.com", "google.com");
        cache.set("facebook.com", "facebook.com");
        cache.set("youtube.com", "youtube.com");
        cache.set("linkedin.com", "linkedin.com");
        cache.set("google.com", "google.com");

        cache.printAll();

        cache.get("facebook.com");
        System.out.println("====================");
        cache.printAll();

        cache.set("amazon.com", "amazon.com");
        System.out.println("====================");
        cache.printAll();
    }
}
