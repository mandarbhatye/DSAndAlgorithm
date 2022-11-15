package Amazon;

import java.util.HashMap;
import java.util.Map;

public class LRUCacheImpl {
    static class doublyLinkedListNode {
        String key;
        int value;
        doublyLinkedListNode next;
        doublyLinkedListNode prev;

        public doublyLinkedListNode(String key, int val) {
            this.key = key;
            this.value = val;
            next = null;
            prev = null;
        }

        public void removeBinding() {
            if (prev != null) {
                prev.next = next;
            }
            if (next != null) {
                next.prev = prev;
            }
            prev = null;
            next = null;
        }
    }

    static class doublyLinkedList {

        public doublyLinkedListNode head;
        public doublyLinkedListNode tail;

        public doublyLinkedList() {
            head = null;
            tail = null;
        }

        public void setHeadTo(doublyLinkedListNode node) {
            if (head == node) return;

            if (head == null) {
                head = node;
                tail = node;
            } else if (head == tail) {
                tail.prev = node;
                head = node;
                head.next = tail;
            } else {
                if (tail == head)
                    removeTail();
                node.removeBinding();
                head.prev = node;
                node.next = head;
                head = node;
            }
        }

        public void removeTail() {
            if (tail == null) return;

            if (tail == head) {
                head = null;
                tail = null;
                return;
            }
            tail = tail.prev;
            tail.next = null;
        }
    }

    public static class LRUCache {
        int maxSize;
        int currentSize = 0;
        Map<String, doublyLinkedListNode> lruMap = new HashMap<>();
        doublyLinkedList listOfMostRecent = new doublyLinkedList();

        public LRUCache(int maxSize) {
            this.maxSize = maxSize > 1 ? maxSize : 1;
        }

        public void insertKeyValuePair(String key, int value) {
            if (!lruMap.containsKey(key)) {
                if (currentSize == maxSize) {
                    evictLestRecent();
                } else {
                    currentSize += 1;
                }
                lruMap.put(key, new doublyLinkedListNode(key, value));
            } else {
                replaceKey(key, value);
            }

            updateMostRecent(lruMap.get(key));
        }

        public void updateMostRecent(doublyLinkedListNode node) {
            listOfMostRecent.setHeadTo(node);
        }

        public void evictLestRecent() {
            String KeyToRemove = listOfMostRecent.tail.key;
            listOfMostRecent.removeTail();
            lruMap.remove(KeyToRemove);
        }

        public void replaceKey(String key, int value) {
            if (!lruMap.containsKey(key)) {
                //throw new Exception("The provide key is not present in the map....");
            }
            lruMap.get(key).value = value;
        }

        public LRUResult getValueFromKey(String key) {
            if (!lruMap.containsKey(key)) return new LRUResult(false, 0);

            updateMostRecent(lruMap.get(key));
            return new LRUResult(true, lruMap.get(key).value);
        }

        public String getMostRecentKey() {
            return listOfMostRecent.head.key;
        }
    }

    static class LRUResult {
        boolean found;
        int value;

        public LRUResult(boolean found, int value) {
            this.found = found;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(4);
        lruCache.insertKeyValuePair("a", 1);
        lruCache.insertKeyValuePair("b", 2);
        lruCache.insertKeyValuePair("c", 3);
        lruCache.insertKeyValuePair("d", 4);

        System.out.println(lruCache.getValueFromKey("a").value);

        lruCache.insertKeyValuePair("e", 5);

        System.out.println(lruCache.getValueFromKey("a").value);
        System.out.println(lruCache.getValueFromKey("b").value);
        System.out.println(lruCache.getValueFromKey("c").value);


        lruCache.insertKeyValuePair("f", 5);

        System.out.println(lruCache.getValueFromKey("c").value);
    }
}

