package Amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LeetCodeAmazon_TRIE {

    static class TrieNode {
        HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
        String word = null;
        int count;
        int firstOccurance;
        boolean flag = false;
        public TrieNode() {}

        public boolean containsKey(char ch){
            return children.containsKey(ch);
        }

        public TrieNode get(char ch){
            return children.get(ch);
        }

        public TrieNode put(char ch, TrieNode node){
            children.put(ch, node);
            return node;
        }

        public void setEnd(){
            flag = true;
        }

        public boolean isEnd(){
            return flag;
        }
    }

    static char[][] _board = null;
    static ArrayList<String> _result = new ArrayList<String>();

    public static List<String> wordBoggleII(char[][] board, String[] words) {
        // Step 1). Construct the Trie
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;
            for (Character letter : word.toCharArray()) {
                if (node.containsKey(letter)) {
                    node = node.get(letter);
                } else {
                    TrieNode newNode = new TrieNode();
                    node = node.put(letter, newNode);
                    //node = newNode;
                }
            }
            node.word = word;  // store words in Trie
        }

        _board = board;
        // Step 2). Backtracking starting for each cell in the board
        for (int row = 0; row < board.length; ++row) {
            for (int col = 0; col < board[row].length; ++col) {
                if (root.containsKey(board[row][col])) {
                    backtracking(row, col, root);
                }
            }
        }

        return _result;
    }

    private static void backtracking(int row, int col, TrieNode parent) {
        Character letter = _board[row][col];
        TrieNode currNode = parent.get(letter);

        // check if there is any match
        if (currNode.word != null) {
            _result.add(currNode.word);
            currNode.word = null;
        }

        // mark the current letter before the EXPLORATION
        _board[row][col] = '#';

        // explore neighbor cells in around-clock directions: up, right, down, left
        int[] rowOffset = {-1, 0, 1, 0};
        int[] colOffset = {0, 1, 0, -1};

        for (int i = 0; i < 4; ++i) {
            int newRow = row + rowOffset[i];
            int newCol = col + colOffset[i];
            if (newRow < 0 || newRow >= _board.length || newCol < 0 || newCol >= _board[0].length) {
                continue;
            }
            if (currNode.containsKey(_board[newRow][newCol])) {
                backtracking(newRow, newCol, currNode);
            }
        }
        // End of EXPLORATION, restore the original letter in the board.
        _board[row][col] = letter;

        // Optimization: incrementally remove the leaf nodes
//        if (currNode.children.isEmpty()) {
//            parent.children.remove(letter);
//        }
    }



    static TrieNode root = new TrieNode();
    static String frequent;
    static int maxCount = Integer.MIN_VALUE;
    static int index =0;

    public static String mostFrequentWord(String arr[],int n) {
        for (int i = 0; i < n; i++) {
            insertWords(arr[i], i);
        }
        return frequent;
    }

    static void insertWords(String str, int ind){
        TrieNode current = root;
        for(int i=0;i<str.length();i++){
            Character ch = str.charAt(i);
            if((current.children.size()==0) || (!current.children.containsKey(ch))){
                current.children.put(ch, new TrieNode());
            }
            TrieNode child = current.children.get(ch);
            current = child;
        }

        current.count++;
        current.flag = true;
        if(current.firstOccurance == -1)
            current.firstOccurance = ind;

        if((maxCount == current.count) && (index < current.firstOccurance)){
            maxCount = current.count;
            frequent = str;
            index = current.firstOccurance;
        }

        else if((maxCount < current.count)){
            index = current.firstOccurance;
            maxCount = current.count;
            frequent = str;
        }
    }

    public static void main(String[] args) {
        System.out.println("=================================== Word Ladder II ========================================");
        //Output: ["eat","oath"]
        char[][] board =  {{'o','a','a','n'},
                           {'e','t','a','e'},
                           {'i','h','k','r'},
                           {'i','f','l','v'}};
        String[] words = new String[]{"oath","pea","eat","rain"};
        List<String> result = wordBoggleII(board,words);
        System.out.println(result);

        System.out.println("========================= Most frequent word in an array of strings ======================");
        //https://practice.geeksforgeeks.org/problems/most-frequent-word-in-an-array-of-strings3528/1?page=1&category[]=Trie&sortBy=submissions
        int N = 3;
        String arr[] = {"geeks","for","geeks"};
        //Output: geeks
        String res = mostFrequentWord(arr,N);
        System.out.println("Most frequent word is: " + res);
    }
}
