package Amazon;

import java.util.HashMap;
import java.util.Map;

public class LeetCode_LRUCache {

    class Node {
        int key;
        int val;
        Node next;
        Node prev;

        Node(int key, int value) {
            this.key = key;
            this.val = value;
        }
    }
    class LinkedNodeList{
        Node dummyHead;
        Node dummyTail;

        LinkedNodeList(){
            dummyHead = new Node(0,0);
            dummyTail = new Node(0,0);
            dummyHead.next = dummyTail;
            dummyTail.prev = dummyHead;
        }

        void moveToHead(Node node){
            node.prev.next = node.next;
            node.next.prev = node.prev;
            addToHead(node);
        }

        void addToHead(Node node){
            Node tmp = dummyHead.next ;
            dummyHead.next = node;
            node.next = tmp;
            node.prev = dummyHead;
            tmp.prev = node;
        }

        void removeTail(){
            Node newTail = dummyTail.prev.prev;
            newTail.next = dummyTail;
            dummyTail.prev = newTail;
        }

        Node getTail(){
            return dummyTail.prev;
        }

    }

    LinkedNodeList list;
    Map<Integer, Node> map;
    Integer cap;
    public LeetCode_LRUCache(int capacity) {
        list = new LinkedNodeList();
        map = new HashMap(capacity);
        cap = capacity;
    }

    public int get(int key) {
        Node node = map.get(key);
        if(node == null){
            return -1;
        }
        list.moveToHead(node);
        return node.val;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if(node != null){
            list.moveToHead(node);
            node.val = value;

        }else{
            Node newNode = new Node(key, value);
            if(map.size() == cap){
                Node tail = list.getTail();
                map.remove(tail.key);
                list.removeTail();
            }
            map.put(key, newNode);
            list.addToHead(newNode);
        }
    }

    public static void main(String[] args) {
        LeetCode_LRUCache lRUCache = new LeetCode_LRUCache(2);
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        lRUCache.get(1);    // return 1
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        lRUCache.get(2);    // returns -1 (not found)
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        lRUCache.get(1);    // return -1 (not found)
        lRUCache.get(3);    // return 3
        lRUCache.get(4);    // return 4
    }
}
