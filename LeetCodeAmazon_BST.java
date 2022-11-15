package Amazon;

import java.util.*;
import java.util.stream.IntStream;

public class LeetCodeAmazon_BST {
    public static class BinaryTreeNode {
        int value;
        BinaryTreeNode left;
        BinaryTreeNode right;

        public BinaryTreeNode(int data, BinaryTreeNode left, BinaryTreeNode right) {
            this.value = data;
            this.left = left;
            this.right = right;
        }

        public BinaryTreeNode(int data) {
            this.value = data;
        }
    }

    public static class Pair {
        BinaryTreeNode node;
        int state;

        int minhl, maxhl = 0;

        public Pair(BinaryTreeNode node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public static void displayBinaryTree(BinaryTreeNode node) {
        if (node == null) {
            return;
        }
        String str = "";

        str += node.left == null ? "." : node.left.value + "";
        str += " <- " + node.value + " -> ";
        str += node.right == null ? "." : node.right.value + "";

        System.out.println(str);

        displayBinaryTree(node.left);
        displayBinaryTree(node.right);
    }

    public static boolean validateBST(BinaryTreeNode tree, Integer min, Integer max) {
        // Empty trees are valid BSTs.
        if (tree == null) return true;
        // The current node's value must be between low and high.
        if (tree.value >= max || tree.value <= min) return false;

        boolean leftIsValid = validateBST(tree.left, min, tree.value);
        boolean rightIsValid = validateBST(tree.right, tree.value, max);

        return leftIsValid && rightIsValid;
    }

    public static int size(BinaryTreeNode node) {
        if (node == null) {
            return 0;
        }
        int ls = size(node.left);
        int rs = size(node.right);
        int ts = ls + rs + 1;

        return ts;
    }

    public static int sum(BinaryTreeNode node) {
        if (node == null) {
            return 0;
        }
        int lsum = sum(node.left);
        int rsum = sum(node.right);
        int tsum = lsum + rsum + node.value;

        return tsum;
    }

    public static int max(BinaryTreeNode node) {
        if (node == null) {
            return Integer.MIN_VALUE;
        }

        int lmax = max(node.left);
        int rmax = max(node.right);
        int max = Math.max(lmax, rmax);

        return Math.max(max, node.value);
    }

    public static int height(BinaryTreeNode node) {
        if (node == null) {
            return 0;
        }
        int lh = height(node.left);
        int rh = height(node.right);

        return Math.max(lh, rh) + 1;
    }

    public static int width(BinaryTreeNode node, int horizontalLevel, int[] array) {
        if (node == null) return 0;

        array[0] = Math.min(array[0], horizontalLevel);
        array[1] = Math.max(array[1], horizontalLevel);

        width(node.left, horizontalLevel - 1, array);
        width(node.right, horizontalLevel + 1, array);

        return array[1] - array[0] + 1;
    }

    public static boolean isSymmetric(BinaryTreeNode root) {
        return isMirror(root, root);
    }

    public static boolean isMirror(BinaryTreeNode t1, BinaryTreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;

        boolean isLeftMirror = isMirror(t1.left, t2.right);
        boolean isRightMirror = isMirror(t1.right, t2.left);

        return (t1.value == t2.value) && isLeftMirror && isRightMirror;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                Binary Tree Traversals - Starts                                 //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static List<Integer> preOrderTraverse(BinaryTreeNode tree, List<Integer> array) {
        if (tree == null) return array;

        array.add(tree.value);
        preOrderTraverse(tree.left, array);
        preOrderTraverse(tree.right, array);
        return array;
    }

    public static List<Integer> inOrderTraverse(BinaryTreeNode tree, List<Integer> array) {
        if (tree == null) return array;

        inOrderTraverse(tree.left, array);
        array.add(tree.value);
        inOrderTraverse(tree.right, array);
        return array;
    }

    public static List<Integer> postOrderTraverse(BinaryTreeNode tree, List<Integer> array) {
        if (tree == null) return array;

        postOrderTraverse(tree.left, array);
        postOrderTraverse(tree.right, array);
        array.add(tree.value);

        return array;
    }

    //[[50], [25, 75], [12, 37, 62, 87], [30, 45, 60, 70]]
    public static List<List<Integer>> levelOrder(BinaryTreeNode root) {
        List<List<Integer>> answer = new ArrayList<>();
        List<Integer> subList = new ArrayList<>();

        if (root == null) {
            return answer;
        }

        Queue<BinaryTreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(null);

        while (!q.isEmpty()) {
            BinaryTreeNode n = q.poll();

            if (n == null) {
                answer.add(subList);
                subList = new ArrayList<>();

                if (q.isEmpty()) return answer;
                q.add(null);
                continue;
            }
            subList.add(n.value);

            if (n.left != null)
                q.add(n.left);
            if (n.right != null)
                q.add(n.right);
        }
        return answer;
    }

    //left to right
    //[[50], [75, 25], [12, 37, 62, 87], [70, 60, 45, 30]]
    //right tyo left
    //[[50], [25, 75], [87, 62, 37, 12], [30, 45, 60, 70]]
    //Just change default value of isLeftToRight.
    public static List<List<Integer>> zigzagLevelOrder(BinaryTreeNode root) {
        List<List<Integer>> answer = new ArrayList<>();
        Queue<BinaryTreeNode> q = new LinkedList<>();
        q.add(root);

        boolean isLeftToRight = false;

        while (!q.isEmpty()) {
            int size = q.size();
            LinkedList<Integer> subList = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                BinaryTreeNode top = q.poll();

                if (top.left != null) {
                    q.add(top.left);
                }
                if (top.right != null) {
                    q.add(top.right);
                }

                if (isLeftToRight) {
                    //Add to last
                    subList.add(top.value);
                } else {
                    //Add to first
                    subList.addFirst(top.value);
                }
            }
            answer.add(subList);
            isLeftToRight = !isLeftToRight;
        }
        return answer;
    }

    static class vPair {
        BinaryTreeNode node;
        int horizontalLevel;

        public vPair(BinaryTreeNode node, int horizontalLevel) {
            this.node = node;
            this.horizontalLevel = horizontalLevel;
        }
    }

    public static List<List<Integer>> verticalOrderTraversal(BinaryTreeNode root) {
        List<List<Integer>> answer = new ArrayList<>();
        Map<Integer, List<Integer>> levelToNodeMap = new HashMap<>();
        LinkedList<vPair> q = new LinkedList<>();
        q.add(new vPair(root, 0));

        int minHl = 0;
        int maxHl = 0;
        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                vPair pair = q.poll();

                minHl = Math.min(minHl, pair.horizontalLevel);
                maxHl = Math.max(maxHl, pair.horizontalLevel);

                if (levelToNodeMap.containsKey(pair.horizontalLevel)) {
                    levelToNodeMap.get(pair.horizontalLevel).add(pair.node.value);
                } else {
                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(pair.node.value);
                    levelToNodeMap.put(pair.horizontalLevel, tmp);
                }

                if (pair.node.left != null) q.add(new vPair(pair.node.left, pair.horizontalLevel - 1));
                if (pair.node.right != null) q.add(new vPair(pair.node.right, pair.horizontalLevel + 1));
            }
        }
        for (int i = minHl; i <= maxHl; i++) {
            answer.add(levelToNodeMap.get(i));
        }
        return answer;
    }

    public static List<List<Integer>> diagonalOrderTraversal(BinaryTreeNode root) {
        List<List<Integer>> answer = new ArrayList<>();
        LinkedList<BinaryTreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> subList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                BinaryTreeNode node = q.poll();
                while (node != null) {
                    subList.add(node.value);
                    if (node.left != null) q.add(node.left);
                    node = node.right;
                }
            }
            answer.add(subList);
        }
        return answer;
    }

    public static void addLeftBoundryElements(BinaryTreeNode root, List<Integer> ans) {
        BinaryTreeNode curr = root.left;
        while (curr != null) {
            if (!isleaf(curr)) ans.add(curr.value);
            if (curr.left != null) curr = curr.left;
            else curr = curr.right;
        }
    }

    public static void addRightBoundryElements(BinaryTreeNode root, List<Integer> ans) {
        BinaryTreeNode curr = root.right;
        Stack<Integer> st = new Stack<>();
        while (curr != null) {
            if (!isleaf(curr)) st.push(curr.value);
            if (curr.right != null) curr = curr.right;
            else curr = curr.left;
        }
        while (!st.isEmpty()) {
            ans.add(st.pop());
        }
    }

    public static void addLeafElement(BinaryTreeNode root, List<Integer> ans) {
        if (isleaf(root)) {
            ans.add(root.value);
            return;
        }
        if (root.left != null) addLeafElement(root.left, ans);
        if (root.right != null) addLeafElement(root.right, ans);
    }

    public static boolean isleaf(BinaryTreeNode root) {
        return root.left == null && root.right == null;
    }

    public static List<Integer> boundryTraversal(BinaryTreeNode root) {
        List<Integer> answer = new ArrayList<>();
        if (!isleaf(root)) answer.add(root.value);

        addLeftBoundryElements(root, answer);
        addLeafElement(root, answer);
        addRightBoundryElements(root, answer);

        return answer;
    }

    private static BinaryTreeNode getRightMostNode(BinaryTreeNode node, BinaryTreeNode current){
        while(node.right!= null && node.right != current)
            node = node.right;

        return node;
    }
    private static List<Integer> morrissInOrderTraversal(BinaryTreeNode root){
        List<Integer> result = new ArrayList<>();
        BinaryTreeNode current = root;

        while(current != null){
            BinaryTreeNode left = current.left;
            if(left == null){
                result.add(current.value);
                current = current.right;
            }else{
                BinaryTreeNode rightMost = getRightMostNode(left,current);

                if(rightMost.right == null){//Thread creation area
                    rightMost.right = current;
                    current = current.left;
                }else if(rightMost.right == current){//Thread removal area
                    rightMost.right = null;
                    result.add(current.value);
                    current = current.right;
                }
            }
        }
        return result;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                  Binary Tree Views - Starts                                    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static List<Integer> topViewOfBST(BinaryTreeNode root) {
        int[] widthArray = new int[2];

        int len = width(root, 0, widthArray);
        List<Integer> answer = new ArrayList<>(len);

        for (int i = 0; i < len; i++) {
            answer.add(i, Integer.MAX_VALUE);
        }

        LinkedList<vPair> q = new LinkedList<>();
        q.add(new vPair(root, Math.abs(widthArray[0])));

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                vPair pair = q.poll();
                int hl = pair.horizontalLevel;

                if (answer.get(hl) == Integer.MAX_VALUE) {
                    answer.set(hl, pair.node.value);
                }
                if (pair.node.left != null) q.add(new vPair(pair.node.left, hl - 1));
                if (pair.node.right != null) q.add(new vPair(pair.node.right, hl + 1));
            }
        }
        return answer;
    }

    public static List<Integer> bottomViewOfBST(BinaryTreeNode root) {
        List<Integer> answer = new ArrayList<>();
        int[] arr = new int[2];
        int len = width(root, 0, arr);

        for (int i = 0; i < len; i++) {
            answer.add(i, Integer.MAX_VALUE);
        }

        LinkedList<vPair> q = new LinkedList<>();
        q.add(new vPair(root, Math.abs(arr[0]))); //shift 0th position to right

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                vPair pair = q.poll();
                int hl = pair.horizontalLevel;
                BinaryTreeNode n = pair.node;

                answer.set(hl, n.value);

                if (n.left != null) q.add(new vPair(n.left, hl - 1));
                if (n.right != null) q.add(new vPair(n.right, hl + 1));
            }
        }
        return answer;
    }

    public static List<Integer> leftViewOfBST(BinaryTreeNode root) {
        List<Integer> answer = new ArrayList<>();
        LinkedList<BinaryTreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int size = q.size();
            answer.add(q.getFirst().value);
            for (int i = 0; i < size; i++) {
                BinaryTreeNode node = q.poll();

                if (node.left != null) q.add(node.left);
                if (node.right != null) q.add(node.right);
            }
        }
        return answer;
    }

    public static List<Integer> rightViewOfBST(BinaryTreeNode root) {
        List<Integer> answer = new ArrayList<>();
        LinkedList<BinaryTreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int size = q.size();
            answer.add(q.getFirst().value);
            for (int i = 0; i < size; i++) {
                BinaryTreeNode node = q.poll();

                if (node.right != null) q.add(node.right);
                if (node.left != null) q.add(node.left);
            }
        }
        return answer;
    }
    //=============================================================== Binary Tree Views - Ends ===============================================================


    static void noSiblingRecursion(BinaryTreeNode root,ArrayList<Integer> answer){
        if ( root == null ) return;

        if ( root.left == null && root.right != null) {
            answer.add( root.right.value);
        }
        if ( root.right == null && root.left != null){
            answer.add( root.left.value);
        }

        noSiblingRecursion ( root.left, answer);
        noSiblingRecursion ( root.right,answer);

    }

    private static int max = Integer.MIN_VALUE;

    public static int maxPathSum(BinaryTreeNode root) {
        max = Integer.MIN_VALUE;
        maxPathSumHelper(root);
        return max;
    }

    private static int maxPathSumHelper(BinaryTreeNode root) {
        if (root == null) return 0;

        int leftSum  = Math.max(0, maxPathSumHelper(root.left));
        int rightSum = Math.max(0, maxPathSumHelper(root.right));

        max = Math.max(max, leftSum + rightSum + root.value);

        return Math.max(Math.max(leftSum, rightSum) + root.value, 0);
    }

    //TODO
    public static BinaryTreeNode lowestCommonAncestor(BinaryTreeNode root, int p, int q) {
        if (root == null || root.value == p || root.value == q) return root;

        BinaryTreeNode leftNode  = lowestCommonAncestor(root.left, p, q);
        BinaryTreeNode rightNode = lowestCommonAncestor(root.right, p, q);

        if(leftNode == null){
            return rightNode;
        }else if(rightNode == null){
            return leftNode;
        }else{
            return root;
        }
//        if (leftNode != null && rightNode != null)
//            return root;
//        else
//            return (leftNode != null) ? leftNode : rightNode;
    }

    public static int counter = 0;

    public static void kSmallestElement(BinaryTreeNode root, int k, int[] answer) {
        if (root == null) return;

        kSmallestElement(root.left, k, answer);
        counter++;
        if (counter == k) {
            answer[0] = root.value;
        }
        kSmallestElement(root.right, k, answer);
    }

    public static void kLargestElement(BinaryTreeNode root, int k, int[] answer) {
        if (root == null) return;

        kLargestElement(root.right, k, answer);
        counter++;
        if (counter == k) {
            answer[0] = root.value;
        }
        kLargestElement(root.left, k, answer);
    }

    public static class DiaPair {
        int height;
        int diameter;
    }

    public static DiaPair getDiameter(BinaryTreeNode node) {
        if (node == null) {
            DiaPair basePair = new DiaPair();
            basePair.height = -1;
            basePair.diameter = 0;
            return basePair;
        }

        DiaPair lp = getDiameter(node.left);
        DiaPair rp = getDiameter(node.right);

        DiaPair dp = new DiaPair();
        dp.height = Math.max(lp.height, rp.height) + 1;

        int fes = lp.height + rp.height + 2;
        dp.diameter = Math.max(fes, Math.max(lp.diameter, rp.diameter));

        return dp;
    }

    public static boolean isTreeSame(BinaryTreeNode root1, BinaryTreeNode root2) {
        if (root1 == null || root2 == null)
            return root1 == root2;
        boolean isLeftSame = isTreeSame(root1.left, root2.left);
        boolean isRightSame = isTreeSame(root1.right, root2.right);

        return root1.value == root2.value && isLeftSame && isRightSame;
    }

    public static boolean nodeToRootPath(BinaryTreeNode root, int data, List<Integer> answer) {
        if (root == null) return false;

        if (root.value == data) {
            answer.add(root.value);
            return true;
        }

        boolean filc = nodeToRootPath(root.left, data, answer);
        if (filc) {
            answer.add(root.value);
            return true;
        }

        boolean firc = nodeToRootPath(root.right, data, answer);
        if (firc) {
            answer.add(root.value);
            return true;
        }
        return false;
//        if (root == null) return false;
//
//        answer.add(root.value);
//
//        if (root.value == data) {
//            return true;
//        }
//        if (nodeToRootPath(root.left, data, answer) || nodeToRootPath(root.right, data, answer))
//            return true;
//
//        answer.remove(answer.size() - 1);
//        return false;
    }

    public static int isTreeBalanced(BinaryTreeNode root) {
        if (root == null) return 0;

        int lh = isTreeBalanced(root.left);
        if (lh == -1) return -1;
        int rh = isTreeBalanced(root.right);
        if (rh == -1) return -1;

        int gap = Math.abs(lh - rh);
        if (gap > 1) {
            return -1;
        }

        return Math.max(lh, rh) + 1;
    }

    private static BinaryTreeNode prev = null; //Preserve previous element so that we can provide connection.
    private static BinaryTreeNode head = null; //Dummy node to store head. LeftMost element would be stored as Head.

    //TODO
    public static void flattenBinaryTreeDoublyToLinkedList(BinaryTreeNode root) {
        if (root == null) return;

        flattenBinaryTreeDoublyToLinkedList(root.left);

        //First time prev would be null because it's leftmost element.
        //Make sure to set Head to this element
        if(prev == null){
            head = root;
        }else { //If prev is not null then continue to attaching node to each other.
            root.left = prev;
            prev.right = root;
        }
        prev = root;

        flattenBinaryTreeDoublyToLinkedList(root.right);
    }

    private static BinaryTreeNode flattenBinaryTreeDoublyToLinkedList_Morriss(BinaryTreeNode root){
        BinaryTreeNode dummy = new BinaryTreeNode(-1);
        BinaryTreeNode prev = dummy;
        BinaryTreeNode current = root;

        while(current != null){
            BinaryTreeNode left = current.left;
            if(left == null){
                prev.right = current;
                current.left = prev;
                prev = current;

                //Move
                current = current.right;
            }else{
                BinaryTreeNode rightMost = getRightMostNode(left,current);

                if(rightMost.right == null){//Thread creation area
                    rightMost.right = current;
                    current = current.left;
                }else if(rightMost.right == current){//Thread removal area
                    rightMost.right = null;

                    prev.right = current;
                    current.left = prev;
                    prev = current;

                    current = current.right;
                }
            }
        }
        BinaryTreeNode head = dummy.right;
        //dummy.right = head.left = null;

        return head;
    }
    public static void printDoublyLinkedList(BinaryTreeNode root) {
        if (root == null) {
            System.out.println("List is empty");
            return;
        }
        BinaryTreeNode currNode = root;
        while (currNode != null) {
            System.out.print(currNode.value + " -> ");
            currNode = currNode.right;
        }
        System.out.println("NULL");

        System.out.println("-------------------------------------");

        currNode = root;
        while (currNode.right != null){
            currNode = currNode.right;
        }
        while (currNode != null) {
            System.out.print(currNode.value + " -> ");
            currNode = currNode.left;
        }
        System.out.println("NULL");
    }

    //TODO
    public static void flattenBinaryTreeToLinkedList(BinaryTreeNode root) {
        if (root == null) return;

        flattenBinaryTreeToLinkedList(root.right);
        flattenBinaryTreeToLinkedList(root.left);

        root.right = prev;
        root.left = null;
        prev = root;
    }

    public static void printLinkedListListRight(BinaryTreeNode root) {
        if (root == null) {
            System.out.println("List is empty");
            return;
        }
        BinaryTreeNode currNode = root;
        while (currNode != null) {
            System.out.print(currNode.value + " -> ");
            currNode = currNode.right;
        }
        System.out.println("NULL");
    }

    //PostOrder traversal with BFS
    public static void postorder_traversal(BinaryTreeNode root) {
        List<Integer> postorder = new ArrayList<>();

        if (root == null) return;

        Stack<BinaryTreeNode> helper_stack = new Stack<>();
        helper_stack.push(root);

        while (!helper_stack.empty()) {
            BinaryTreeNode current_node = helper_stack.peek();
            if (current_node.left!= null) {
                helper_stack.push(current_node.left);
                current_node.left = null;
            } else if (current_node.right != null) {
                helper_stack.push(current_node.right);
                current_node.right = null;
            } else {
                postorder.add(current_node.value);
                helper_stack.pop();
            }
        }

        System.out.println("PostOrder with BFS is: " + postorder.toString());
    }

    //Mirror Binary Tree with BFS algo
    public static void mirror_image(BinaryTreeNode root) {
        if (root == null)
            return;

        Queue<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();
        queue.add(root);
        // Do BFS. While doing BFS, keep swapping
        // left and right children
        while(!queue.isEmpty()) {
            // Pop top node from queue.
            BinaryTreeNode cur_node = queue.poll();

            // Swap left child with right child.
            BinaryTreeNode temp = cur_node.left;
            cur_node.left = cur_node.right;
            cur_node.right = temp;

            // Push left and right children.
            if (cur_node.left != null)
                queue.add(cur_node.left);
            if (cur_node.right != null)
                queue.add(cur_node.right);
        }
    }

    //Mirror Binary Tree with DFS algo
    public static BinaryTreeNode mirror_image_util(BinaryTreeNode root){
        if (root == null) return root;

        BinaryTreeNode left  = mirror_image_util(root.left);
        BinaryTreeNode right = mirror_image_util(root.right);

        // Swap the left and right pointers.
        root.left = right;
        root.right = left;
        return root;
    }

    static class BSTPair {
        BinaryTreeNode node;
        boolean isBST = false;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int size = 0;
    }

    public static BSTPair largestBSTSubtree(BinaryTreeNode node) {
        if (node == null) {
            BSTPair basePair = new BSTPair();
            basePair.isBST = true;
            basePair.node = node;
            return basePair;
        }

        BSTPair leftPair = largestBSTSubtree(node.left);
        BSTPair rightPair = largestBSTSubtree(node.right);

        BSTPair pair = new BSTPair();

        pair.isBST = leftPair.isBST && rightPair.isBST && (node.value >= leftPair.max && node.value < rightPair.min);

        pair.min = Math.min(node.value, Math.min(leftPair.min, rightPair.min));
        pair.max = Math.max(node.value, Math.max(leftPair.max, rightPair.max));

        if (pair.isBST) {
            pair.node = node;
            pair.size = leftPair.size + rightPair.size + 1;
        } else if (leftPair.size > rightPair.size) {
            pair.node = leftPair.node;
            pair.size = leftPair.size;
        } else {
            pair.node = rightPair.node;
            pair.size = rightPair.size;
        }
        return pair;
    }

    public static void generateParentLink(BinaryTreeNode root, Map<BinaryTreeNode, BinaryTreeNode> parentTrackMap) {
        LinkedList<BinaryTreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            BinaryTreeNode current = q.poll();
            if (current.left != null) {
                parentTrackMap.put(current.left, current);
                q.offer(current.left);
            }
            if (current.right != null) {
                parentTrackMap.put(current.right, current);
                q.offer(current.right);
            }
        }
    }

    public static List<Integer> DistanceK(BinaryTreeNode node, int target, int k) {
        List<Integer> answer = new ArrayList<>();
        Map<BinaryTreeNode, Boolean> visited = new HashMap<>();
        Map<BinaryTreeNode, BinaryTreeNode> parentTrackMap = new HashMap<>();
        generateParentLink(node, parentTrackMap);
        LinkedList<BinaryTreeNode> q = new LinkedList<>();
        q.offer(node);
        visited.put(node, true);
        int distance = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            if (distance == k) break;
            distance++;
            for (int i = 0; i < size; i++) {
                BinaryTreeNode curr = q.poll();

                if (curr.left != null && visited.get(curr.left) == null) {
                    q.offer(curr.left);
                    visited.put(curr.left, true);
                }

                if (curr.right != null && visited.get(curr.right) == null) {
                    q.offer(curr.right);
                    visited.put(curr.right, true);
                }

                if (parentTrackMap.get(curr) != null && visited.get(parentTrackMap.get(curr)) == null) {
                    q.offer(parentTrackMap.get(curr));
                    visited.put(parentTrackMap.get(curr), true);
                }
            }
        }

        while (!q.isEmpty()) {
            BinaryTreeNode n = q.poll();
            answer.add(n.value);
        }

        return answer;
    }

    static class BSTIterator {
        private Stack<BinaryTreeNode> st = new Stack<>();
        private boolean isReverse;

        public BSTIterator(BinaryTreeNode root, boolean isReverse) {
            this.isReverse = isReverse;
            pushAll(root);
        }

        public boolean hasNext() {
            return !st.isEmpty();
        }

        private int next() {
            BinaryTreeNode temp = st.pop();
            if (isReverse == false) pushAll(temp.right);
            else pushAll(temp.left);

            return temp.value;
        }

        private void pushAll(BinaryTreeNode root) {
            while (root != null) {
                st.push(root);
                if (isReverse) {
                    root = root.right;
                } else {
                    root = root.left;
                }
            }
        }
    }

    public static boolean findTargetInBST(BinaryTreeNode root, int target) {
        BSTIterator left = new BSTIterator(root, false);
        BSTIterator right = new BSTIterator(root, true);

        int i = left.next();
        int j = right.next();

        while (i < j) {
            if (i + j == target) return true;
            if (i + j < target) {
                i = left.next();
            } else {
                j = right.next();
            }
        }

        return false;
    }

    private BinaryTreeNode inOrderSuccessor(BinaryTreeNode node, int p) {
        BinaryTreeNode successor = null;
        while (node != null) {
            if (p >= node.value) {
                node = node.right;
            } else {
                successor = node;
                node = node.left;
            }
        }
        return successor;
    }

    private BinaryTreeNode inOrderPredesessor(BinaryTreeNode node, int p) {
        BinaryTreeNode predesessor = null;
        while (node != null) {
            if (p >= node.value) {
                predesessor = node;
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return predesessor;
    }

    public static BinaryTreeNode generateBinaryTree(Integer[] arr) {
        BinaryTreeNode root = new BinaryTreeNode(arr[0], null, null);
        Pair p = new Pair(root, 1);

        Stack<Pair> st = new Stack<>();
        st.push(p);

        int idx = 0;
        while (st.size() > 0) {
            Pair top = st.peek();

            if (top.state == 1) {
                idx++;
                if (arr[idx] != null) {
                    top.node.left = new BinaryTreeNode(arr[idx], null, null);
                    Pair lp = new Pair(top.node.left, 1);
                    st.push(lp);
                } else {
                    top.node.left = null;
                }
                top.state++;
            } else if (top.state == 2) {
                idx++;
                if (arr[idx] != null) {
                    top.node.right = new BinaryTreeNode(arr[idx], null, null);
                    Pair rp = new Pair(top.node.right, 1);
                    st.push(rp);
                } else {
                    top.node.right = null;
                }
                top.state++;
            } else if (top.state == 3) {
                st.pop();
            }
        }
        return root;
    }

    private static int index = 0;

    public static BinaryTreeNode generateTreeFromPreOrder(int[] arr) {
        return generateTreeFromPreOrder(arr, Integer.MAX_VALUE);
    }

    private static BinaryTreeNode generateTreeFromPreOrder(int[] arr, int bound) {
        if (index >= arr.length || arr[index] > bound) return null;

        BinaryTreeNode node = new BinaryTreeNode(arr[index++]);
        node.left = generateTreeFromPreOrder(arr, node.value);
        node.right = generateTreeFromPreOrder(arr, bound);

        return node;
    }


    private static void serializeBinaryTree(BinaryTreeNode root, StringBuilder sb){
        if(root == null){
            sb.append("null,");
            return;
        }

        sb.append(root.value + ",");
        serializeBinaryTree(root.left,sb);
        serializeBinaryTree(root.right,sb);
    }

    private static int arrayIndex =0;
    private static BinaryTreeNode deSerializeBinaryTree(String[] arr){
        if(arrayIndex > arr.length || arr[arrayIndex].equals("null")){
            arrayIndex++;
            return null;
        }
        BinaryTreeNode node = new BinaryTreeNode(Integer.parseInt(arr[arrayIndex++]));
        node.left  = deSerializeBinaryTree(arr);
        node.right = deSerializeBinaryTree(arr);

        return node;
    }

    public static void main(String[] args) {
        System.out.println("======================= Generate and Display Binary Tree - Starts ========================");

        //Integer[] arr = {50, 25, 12, null, null, 37, 30, null, null, 40, null, null, 75, 62, 60, null, null, 70, null, null, 87, null, null};
        //Integer[] arr = {50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null, 87, null, null};
        Integer[] arr = {50, 25, 12, null, null, 37, 30, null, null, 45, null, null, 75, 62, 60, null, null, 70, null, null, 87, null, null};

        BinaryTreeNode root = generateBinaryTree(arr);
        displayBinaryTree(root);

        arr = new Integer[]{50, 25, 12, null, null, 37, 30, null, null, 45, null, null, 75, 62, 60, null, null, 70, null, null, 87, null, null};
        BinaryTreeNode root2 = generateBinaryTree(arr);
        displayBinaryTree(root2);

        System.out.println("==== === BST from PreOrder Array 50, 25, 12, 4, 15, 37, 30, 45, 75, 62, 60, 70, 87 =======");
        int[] preOrderArr = new int[]{50, 25, 12, 4, 15, 37, 30, 45, 75, 62, 60, 70, 87};
        BinaryTreeNode newRoot = generateTreeFromPreOrder(preOrderArr);
        displayBinaryTree(newRoot);

        System.out.println("======================== Generate and Display Binary Tree - Ends =========================");

        System.out.println("================================== Is Valid Binary Tree ==================================");
        boolean isBST = validateBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.out.println(isBST);

        System.out.println("============================= Size/Sum/Max/Height of BST =================================");
        System.out.println("Tree size is: " + size(root));
        System.out.println("Tree sum is: " + sum(root));
        System.out.println("Tree max is: " + max(root));
        System.out.println("Tree height is: " + height(root));
        int[] num = new int[2];
        System.out.println("Tree width is: " + width(root, 0, num));

        System.out.println("============================= Symmetric Binary Tree ======================================");
        boolean isTreeSymetric = isSymmetric(root);
        System.out.println("Is Binary Tree Symmetric : " + isTreeSymetric);

        System.out.println("//////////////////////// Binary Tree Traversals - Starts /////////////////////////////////");
        System.out.println();

        System.out.println("=========================== Binary Tree PreOrder Traversal ============================");
        List<Integer> ans = new ArrayList<>();
        preOrderTraverse(root, ans);
        System.out.println(ans);

        System.out.println("=========================== Binary Tree InOrder Traversal ============================");
        ans = new ArrayList<>();
        inOrderTraverse(root, ans);
        System.out.println(ans);

        System.out.println("======================= Binary Tree Morris InOrder Traversal  =============================");
        List<Integer> morriss = morrissInOrderTraversal(root);
        System.out.println(morriss);

        System.out.println("=========================== Binary Tree PosOrder Traversal ============================");
        ans = new ArrayList<>();
        postOrderTraverse(root, ans);
        System.out.println(ans);

        System.out.println("=========================== Binary Tree Level Order Traversal ============================");
        List<List<Integer>> answer = levelOrder(root);
        System.out.println(answer);

        System.out.println("======================== Binary Tree ZigZag Level Order Traversal ========================");
        answer = zigzagLevelOrder(root);
        System.out.println(answer);

        System.out.println("========================= Binary Tree Vertical Order Traversal ===========================");
        answer = verticalOrderTraversal(root);
        System.out.println(answer);

        System.out.println("========================= Binary Tree Diagonal Order Traversal ===========================");
        answer = diagonalOrderTraversal(root);
        System.out.println(answer);

        System.out.println("============================ Binary Tree Boundry Traversal ===============================");
        List<Integer> boundryElements = boundryTraversal(root);
        System.out.println(boundryElements);


        System.out.println("////////////////////////// Binary Tree Traversals - Ends /////////////////////////////////");

        System.out.println("/////////////////////////// Binary Tree Views - Starts ///////////////////////////////////");
        System.out.println();
        System.out.println("================================ Binary Tree Top View ====================================");
        ans = topViewOfBST(root);
        System.out.println(ans);

        System.out.println("============================== Binary Tree Bottom View ===================================");
        ans = bottomViewOfBST(root);
        System.out.println(ans);

        System.out.println("============================== Binary Tree Left View =====================================");
        ans = leftViewOfBST(root);
        System.out.println(ans);

        System.out.println("============================= Binary Tree Right View =====================================");
        ans = rightViewOfBST(root);
        System.out.println(ans);

        System.out.println();

        System.out.println("////////////////////////// Binary Tree Views - Ends ////////////////////////////////////");

        System.out.println();

        System.out.println("============================ Binary Tree Maximum Path Sum ================================");
        int maxPath = maxPathSum(root);
        System.out.println("Max Path Sum for Binary Tree is : " + maxPath);

        System.out.println("================================ Lowest Common Ancestor =================================");
        BinaryTreeNode n = lowestCommonAncestor(root, 60, 87);
        System.out.println("Lowest Common Ancestor for values is: " + n.value); //Lowest Common Ancestor for values is: 50

        System.out.println("============================== K Smallest Element in BST =================================");
        int[] kElement = new int[1];
        kSmallestElement(root, 4, kElement);
        System.out.println("K Smallest element in BST is : " + kElement[0]); //K Smallest element in BST is : 37

        System.out.println("=============================== K Largest Element in BST =================================");
        counter = 0;
        kElement = new int[1];
        kLargestElement(root, 1, kElement);
        System.out.println("K Largest element in BST is : " + kElement[0]);//K Largest element in BST is : 87

        System.out.println("===================================== BST Diameter =======================================");
        DiaPair pair = getDiameter(root);
        System.out.println("Largest Diameter of BST is : " + pair.diameter); //Largest Diameter of BST is : 6

        System.out.println("================================== Are both BST Same =====================================");
        boolean areTreeSame = isTreeSame(root, root2);
        System.out.println("Are both Trees same :" + areTreeSame);

        System.out.println("======================== Print Root to Node Path in Binary Tree ==========================");
        List<Integer> path = new ArrayList<>();
        nodeToRootPath(root, 30, path);
        System.out.println("Root to Node Path in BST :" + path);

        System.out.println("================================ Is Binary Tree Balanced =================================");
        int height = isTreeBalanced(root);
        System.out.println("Is BST Balanced :" + (height == -1));

        System.out.println("============================== Binary Tree = K Distance ==================================");
        ans = DistanceK(root, 50, 3);
        System.out.println("K Distance for BST is: " + ans);

        System.out.println("=============================== Two sum in Binary Tree ===================================");
        boolean isTargetSumPresent = findTargetInBST(root, 38);
        System.out.println("Target sum is present : " + isTargetSumPresent);

        System.out.println("========================== Binary Tree PosOrder Traversal - BFS ==========================");
        postorder_traversal(root);

        System.out.println("========================== Binary Tree Flattening to Linked List ==========================");
        arr = new Integer[] {1, 2, 3,null,null,4, null,null,5,null,6,7,null,null,null};

        BinaryTreeNode root1 = generateBinaryTree(arr);
        displayBinaryTree(root1);

        flattenBinaryTreeToLinkedList(root1);
        printLinkedListListRight(root1);

        System.out.println("========================= Binary Tree to Doubly Linked List  =============================");
        arr = new Integer[] {25, 20, 10,5,null, null,12, null,null,22,null,null,36,30,28,null,null,null,40,38,null,null,48,null,null};

        BinaryTreeNode btRoot = generateBinaryTree(arr);
        displayBinaryTree(btRoot);
        prev = null;
        flattenBinaryTreeDoublyToLinkedList(btRoot);
        printDoublyLinkedList(head);

        System.out.println("======================= Binary Tree to Doubly Linked List/Morris  ========================");
        BinaryTreeNode morrisRoot = generateBinaryTree(arr);
        BinaryTreeNode newHead = flattenBinaryTreeDoublyToLinkedList_Morriss(morrisRoot);
        printDoublyLinkedList(newHead);

        System.out.println("========================= Binary Tree Serialize/DeSerialize  =============================");
        arr = new Integer[] {25, 20, 10,5,null, null,12, null,null,22,null,null,36,30,28,null,null,null,40,38,null,null,48,null,null};

        BinaryTreeNode seralizeRoot = generateBinaryTree(arr);
        displayBinaryTree(seralizeRoot);
        StringBuilder sb= new StringBuilder();
        serializeBinaryTree(seralizeRoot,sb);
        System.out.println("Binary Tree after Serialize is: "+ sb.toString());

        String[] strArr = sb.toString().split(",");
        BinaryTreeNode rt = deSerializeBinaryTree(strArr);
        displayBinaryTree(rt);
    }
}
