package Amazon;

import jdk.nashorn.internal.ir.WhileNode;
import sun.awt.image.ImageWatched;

import java.util.*;

public class Practice_BinaryTree {

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

   private static int size(BinaryTreeNode node){
        if(node == null) return 0;

        int ls = size(node.left);
        int rs = size(node.right);

        return ls+ rs +1;
   }

   private static int sum(BinaryTreeNode node){
        if(node == null) return 0;

        int lsum = sum(node.left);
        int rsum = sum(node.right);

        return lsum + rsum + node.value;
   }

   private static int max(BinaryTreeNode node){
        if(node == null) return 0;

        int lmax = max(node.left);
        int rmax = max(node.right);

        return Math.max(lmax,Math.max(rmax, node.value));
   }
   private static int height(BinaryTreeNode node){
        if(node == null) return 0;

        int lh = height(node.left);
        int rh = height(node.right);

        return Math.max(lh,rh) +1;
   }
   private static int width(BinaryTreeNode node, int verticalLevel, int[] widthArray){
        if(node == null) return 0;

        widthArray[0] = Math.min(widthArray[0], verticalLevel);
        widthArray[1] = Math.max(widthArray[0],verticalLevel);

        int left  = width(node.left,verticalLevel - 1,widthArray);
        int right = width(node.right,verticalLevel + 1,widthArray);

        return widthArray[1] - widthArray[0] +1;
   }

   private static boolean validateBST(BinaryTreeNode node, int min, int max){
        if(node == null) return true;

        if(node.value >= max || node.value <= min) return false;

        boolean isLeftValid  = validateBST(node.left,  min ,node.value);
        boolean isRightValid = validateBST(node.right, node.value,max);

        return isLeftValid && isRightValid;
   }

   private static boolean isSymmetric(BinaryTreeNode node){
        return isMirror(node, node);
   }

   private static boolean isMirror(BinaryTreeNode node1, BinaryTreeNode node2){
        if(node1 == null && node2 == null) return true;
        if(node1 == null || node2 == null) return false;

        boolean isLeftSame  = isMirror(node1.left, node2.right);
        boolean isRightSame = isMirror(node1.right, node2.left);

        return (node1.value == node2.value && isLeftSame && isRightSame);
   }

   private static void preOrderTraverse(BinaryTreeNode node, List<Integer> ans){
        if(node == null) return;
        ans.add(node.value);
        preOrderTraverse(node.left, ans);
        preOrderTraverse(node.right, ans);
   }

    private static void inOrderTraverse(BinaryTreeNode node, List<Integer> ans){
        if(node == null) return;

        inOrderTraverse(node.left, ans);
        ans.add(node.value);
        inOrderTraverse(node.right, ans);
    }

    private static BinaryTreeNode getRight(BinaryTreeNode node, BinaryTreeNode curr){
        while(node.right != null && node.right!= curr)
            node= node.right;

        return node;
    }
    private static void inOrderMorrisTraverse(BinaryTreeNode root, List<Integer> ans){
        BinaryTreeNode current = root;

        while(current != null){
            BinaryTreeNode left = current.left;

            if(left == null){
                ans.add(current.value);
                current = current.right;
            }else{
                BinaryTreeNode rightMostNode = getRight(left, current);
                if(rightMostNode.right == null){
                    rightMostNode.right = current;
                    current = current.left ;
                }else{
                    rightMostNode.right = null;
                    ans.add(current.value);
                    current = current.right;
                }
            }
        }
    }
    private static void postOrderTraverse(BinaryTreeNode node, List<Integer> ans){
        if(node == null) return;

        postOrderTraverse(node.left, ans);
        postOrderTraverse(node.right, ans);
        ans.add(node.value);
    }

    private static List<List<Integer>> levelOrder(BinaryTreeNode root){
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> subList = new ArrayList<>();
        Queue<BinaryTreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(null);
        while(!q.isEmpty()){
            BinaryTreeNode node = q.poll();

            if(node == null){
                result.add(subList);
                if(q.isEmpty()) return result;
                subList = new ArrayList<>();
                q.add(null);
                continue;
            }
            if(node.left  != null) q.add(node.left);
            if(node.right != null) q.add(node.right);
            subList.add(node.value);
        }
        return result;
    }

    private static List<List<Integer>> zigzagLevelOrder(BinaryTreeNode root){
        List<List<Integer>> result = new ArrayList<>();
        Queue<BinaryTreeNode> q = new LinkedList<>();
        q.add(root);
        boolean isLeftToRight = true;

        while(!q.isEmpty()){
            int size = q.size();
            LinkedList<Integer> subList = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                BinaryTreeNode node = q.poll();

                if(!isLeftToRight){
                    subList.add(node.value);
                }else{
                    subList.addFirst(node.value);
                }
                if(node.left  != null) q.add(node.left);
                if(node.right != null) q.add(node.right);
            }
            result.add(subList);
            isLeftToRight = !isLeftToRight;
        }
        return result;
    }

    static class vPair{
        BinaryTreeNode node;
        int verticalLevel;

        public vPair(BinaryTreeNode node, int vLevel){
            this.node = node;
            this.verticalLevel = vLevel;
        }
    }

    private static List<List<Integer>> verticalOrderTraversal(BinaryTreeNode root){
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer,List<Integer>> map = new HashMap<>();
        Queue<vPair> q = new LinkedList<>();
        q.add(new vPair(root,0));

        int minVL =0;
        int maxVL =0;
        while (!q.isEmpty()){
            vPair pair = q.poll();
            int vLevel = pair.verticalLevel;
            BinaryTreeNode nd = pair.node;

            minVL = Math.min(minVL, vLevel);
            maxVL = Math.max(maxVL, vLevel);

            if(map.containsKey(vLevel)){
                map.get(vLevel).add(nd.value);
            }else{
                List<Integer> lst = new ArrayList<>();
                lst.add(nd.value);
                map.put(vLevel, lst);
            }

            if(nd.left  != null) q.add(new vPair(nd.left, vLevel - 1));
            if(nd.right != null) q.add(new vPair(nd.right, vLevel + 1));
        }

        for (int i = minVL; i <= maxVL ; i++) {
            result.add(map.get(i));
        }
        return result;
    }

    private static List<List<Integer>> diagonalOrderTraversal(BinaryTreeNode root){
        List<List<Integer>> result = new ArrayList<>();
        Queue<BinaryTreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()){
            int size = q.size();
            LinkedList<Integer> list = new LinkedList<>();

            for (int i = 0; i < size ; i++) {
                BinaryTreeNode node = q.poll();

                while(node != null){
                    list.add(node.value);
                    if(node.left != null) q.add(node.left);
                    node = node.right;
                }
            }
            result.add(list);
        }
        return result;
    }

    private static List<Integer> boundryTraversal(BinaryTreeNode root){
        List<Integer> result = new ArrayList<>();
        if(!isLeaf(root)) result.add(root.value);
        //Left side add
        addLeftBoundry(root,result);
        //Leaf add
        addLeafBoundry(root,result);
        //right side add
        addRightBoundry(root,result);

        return result;
    }

    private static boolean isLeaf(BinaryTreeNode root){
        return root.left == null && root.right== null;
    }

    private static void addLeftBoundry(BinaryTreeNode root, List<Integer> ans){
        BinaryTreeNode node = root.left;

        while (node != null) {
            if(!isLeaf(node)) ans.add(node.value);
            if(node.left != null) node= node.left;
            else node = node.right;
        }
    }

    private static void addRightBoundry(BinaryTreeNode root, List<Integer> ans){
        BinaryTreeNode node = root.right;
        while (node != null) {
            if(!isLeaf(node)) ans.add(node.value);
            if(node.right != null) node= node.right;
            else node = node.left;
        }
    }

    private static void addLeafBoundry(BinaryTreeNode root, List<Integer> ans){
        if(isLeaf(root)) {
            ans.add(root.value);
            return;
        }
        if(root.left != null)
            addLeafBoundry(root.left,ans);
        if(root.right != null)
            addLeafBoundry(root.right,ans);
    }

    private static List<Integer> topViewOfBST(BinaryTreeNode node){
        List<Integer> result = new ArrayList<>();
        int[] width = new int[2];

        int len = width(node,0,width);

        for (int i = 0; i < len ; i++) {
            result.add(i, Integer.MAX_VALUE);
        }

        Queue<vPair> q = new LinkedList<>();
        q.add(new vPair(node, Math.abs(width[0])));

        while(!q.isEmpty()){
            int size = q.size();

            for (int i = 0; i < size; i++) {
                vPair pair = q.poll();
                BinaryTreeNode n = pair.node;
                int vLevel = pair.verticalLevel;

                if(result.get(vLevel) == Integer.MAX_VALUE)
                    result.set(vLevel,n.value);

                if(n.left != null) q.add(new vPair(n.left,vLevel - 1));
                if(n.right != null) q.add(new vPair(n.right,vLevel + 1));
            }
        }
        return  result;
    }

    private static List<Integer> bottomViewOfBST(BinaryTreeNode node){
        List<Integer> result = new ArrayList<>();
        int[] width = new int[2];
        int len = getWidth(node,0,width);

        for (int i = 0; i < len; i++) {
            result.add(i, Integer.MAX_VALUE);
        }

        Queue<vPair> q = new LinkedList<>();
        q.add(new vPair(node, Math.abs(width[0])));

        while(!q.isEmpty()){
            int size = q.size();

            for (int i = 0; i < size ; i++) {
                vPair pair = q.poll();
                BinaryTreeNode n = pair.node;
                int vLevel = pair.verticalLevel;

                result.set(vLevel, n.value);

                if(n.left != null) q.add(new vPair(n.left, vLevel - 1));
                if(n.right != null) q.add(new vPair(n.right, vLevel + 1));
            }
        }
        return result;
    }

    private static int getWidth(BinaryTreeNode node, int vLevel, int[] width){
        if(node == null) return 0;

        width[0] = Math.min(width[0], vLevel);
        width[1] = Math.max(width[1], vLevel);

        if(node.left  != null) getWidth(node.left, vLevel -1, width);
        if(node.right !=null) getWidth(node.right, vLevel +1, width);

        return width[1]-width[0] +1;
    }

    private static List<Integer> leftViewOfBST(BinaryTreeNode node){
        List<Integer> result = new ArrayList<>();
        LinkedList<BinaryTreeNode> q = new LinkedList<>();
        q.add(node);

        while(!q.isEmpty()){
            int size = q.size();
            result.add(q.getFirst().value);

            for (int i = 0; i < size ; i++) {
                BinaryTreeNode n = q.poll();

                if(n.left != null) q.add(n.left);
                if(n.right != null) q.add(n.right);
            }
        }
        return result;
    }

    private static List<Integer> rightViewOfBST(BinaryTreeNode node){
        List<Integer> result = new ArrayList<>();
        LinkedList<BinaryTreeNode> q= new LinkedList<>();
        q.add(node);

        while(!q.isEmpty()){
            int size = q.size();
            result.add(q.getFirst().value);
            for (int i = 0; i < size; i++) {
                BinaryTreeNode n = q.poll();

                if(n.right != null)
                    q.add(n.right);
                if(n.left!= null)
                    q.add(n.left);
            }
        }
        return result;
    }

    private static BinaryTreeNode lowestCommonAncestor(BinaryTreeNode root, int p, int q){
        if(root == null || root.value == p || root.value == q) return root;

        BinaryTreeNode left  = lowestCommonAncestor(root.left, p,q);
        BinaryTreeNode right = lowestCommonAncestor(root.right,p,q);

        if(left == null){
            return right;
        }else if(right == null){
            return left;
        }else
            return root;
    }
    private static int counter =0;
    public static void kSmallestElement(BinaryTreeNode root, int k, int[] answer){
        if(root == null) return;

        kSmallestElement(root.left,k, answer);
        counter++;
        if(counter == k){
            answer[0] = root.value;
        }
        kSmallestElement(root.right,k, answer);
    }

    public static void kLargestElement(BinaryTreeNode root, int k, int[] answer){
        if(root == null) return;

        kLargestElement(root.right,k, answer);
        counter++;
        if(counter == k){
            answer[0] = root.value;
        }
        kLargestElement(root.left,k, answer);
    }
    public static class DiaPair {
        int height;
        int diameter;
    }

    public static DiaPair getDiameterII(BinaryTreeNode node) {
        if (node == null) {
            DiaPair basePair = new DiaPair();
            basePair.height = -1;
            basePair.diameter = 0;
            return basePair;
        }

        DiaPair lp = getDiameterII(node.left);
        DiaPair rp = getDiameterII(node.right);

        DiaPair dp = new DiaPair();
        dp.height = Math.max(lp.height, rp.height) + 1;

        int fes = lp.height + rp.height + 2;
        dp.diameter = Math.max(fes, Math.max(lp.diameter, rp.diameter));

        return dp;
    }

    public static int getDiameter(BinaryTreeNode node, int[] maxHeight){
        if(node == null) return 0;

        int lh = getDiameter(node.left,maxHeight);
        int rh = getDiameter(node.left,maxHeight);

        maxHeight[0] = Math.max(maxHeight[0], lh + rh);

        return Math.max(lh,rh) +1;
    }

    private static boolean isTreeSame(BinaryTreeNode root1, BinaryTreeNode root2){
        if(root1 == null || root2 == null)
            return root1 == root2;
        boolean isLeftSame = isTreeSame(root1.left, root2.left);
        boolean isRighSame = isTreeSame(root1.right,root2.right);

        return (root1.value == root2.value) && isLeftSame && isRighSame;
    }

    private static boolean nodeToRootPath(BinaryTreeNode root,int elementToFind, List<Integer> path){
        if(root == null) return false;

        if(root.value == elementToFind){
            path.add(root.value);
            return true;
        }

        boolean findInLeft = nodeToRootPath(root.left, elementToFind, path);
        if(findInLeft){
            path.add(root.value);
            return true;
        }

        boolean findInRight = nodeToRootPath(root.right, elementToFind, path);
        if(findInRight){
            path.add(root.value);
            return true;
        }
        return false;
    }

    private static int isTreeBalanced(BinaryTreeNode root){
        if(root == null) return 0;

        int lh = isTreeBalanced(root.left);
        if(lh == -1)return -1;

        int rh = isTreeBalanced(root.right);
        if(rh == -1)return -1;

        int gap = Math.abs(lh -rh);
        if(gap > 1)
            return -1;

        return Math.max(lh,rh) +1;
    }
    private static List<Integer> DistanceK(BinaryTreeNode node, int targetNode ,int k){
        List<Integer> result = new ArrayList<>();

        List<BinaryTreeNode> nodeToRootPath = new ArrayList<>();
        nodeToRoot(node,targetNode,nodeToRootPath);

        List<Integer> kDown = new ArrayList<>();
        for (int i = 0; i < nodeToRootPath.size() ; i++) {
            BinaryTreeNode blockingNode = i >0? nodeToRootPath.get(i-1): null;
            printKLevelDown(nodeToRootPath.get(i),k-i, kDown, blockingNode);
        }
        result.addAll(kDown);

        return result;
    }

    private static void printKLevelDown(BinaryTreeNode root, int k, List<Integer> path, BinaryTreeNode blocker){
        if(root == null || k < 0 || root == blocker) return;

        if(k ==0){
            path.add(root.value);
        }
        printKLevelDown(root.left, k-1, path, blocker);
        printKLevelDown(root.right, k-1, path, blocker);
    }
    private static boolean nodeToRoot(BinaryTreeNode root, int data, List<BinaryTreeNode> ans){
        if(root == null) return false;

        if(root.value == data){
            ans.add(root);
            return true;
        }
        boolean findInLeft = nodeToRoot(root.left, data,ans);
        if(findInLeft){
            ans.add(root);
            return true;
        }

        boolean findInRight = nodeToRoot(root.right, data,ans);
        if(findInRight){
            ans.add(root);
            return true;
        }
        return false;
    }
    private static BinaryTreeNode prev = null;
    public static void flattenBinaryTreeToLinkedList(BinaryTreeNode root) {
        if(root == null) return;

        flattenBinaryTreeToLinkedList(root.right);
        flattenBinaryTreeToLinkedList(root.left);

        root.right = prev;
        root.left = null;
        prev= root;
    }

    private static BinaryTreeNode head = null;
    public static void flattenBinaryTreeToDoublyLinkedList(BinaryTreeNode root){
        if(root == null) return;

        flattenBinaryTreeToDoublyLinkedList(root.left);

        if(prev == null){
            head = root;
        }else{
            root.left = prev;
            prev.right = root;
        }
        prev = root;
        flattenBinaryTreeToDoublyLinkedList(root.right);
    }

    private static BinaryTreeNode getRightMostNode(BinaryTreeNode node, BinaryTreeNode current){
        while(node.right != null && node.right != current)
            node = node.right;

        return node;
    }
    private static BinaryTreeNode flattenBinaryTreeDoublyToLinkedList_Morriss(BinaryTreeNode root){
        BinaryTreeNode dummy = new BinaryTreeNode(-1);
        BinaryTreeNode prev= dummy;
        BinaryTreeNode current = root;

        while(current != null){
            BinaryTreeNode left = current.left;

            if(left == null){
                prev.right = current;
                current.left = prev;
                prev = current;

                current = current.right;
            }else{
                BinaryTreeNode rightMost = getRightMostNode(left, current);

                if(rightMost.right == null){ //Create Thread
                    rightMost.right = current;
                    current = current.left;
                }else{
                    rightMost.right = null;
                    prev.right = current;
                    current.left = prev;
                    prev = current;

                    current = current.right;
                }
            }
        }
        BinaryTreeNode head = dummy.right;
        dummy.right = head.left  = null;

        return head;
    }
    public static void printList(BinaryTreeNode root) {
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
    public static void main(String[] args) {
        //Integer[] arr = {50, 25, 12, null, null, 37, 30, null, null, 40, null, null, 75, 62, 60, null, null, 70, null, null, 87, null, null};
        //Integer[] arr = {50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null, 87, null, null};
        Integer[] arr = {50, 25, 12, null, null, 37, 30, null, null, 45, null, null, 75, 62, 60, null, null, 70, null, null, 87, null, null};

        BinaryTreeNode root = generateBinaryTree(arr);
        displayBinaryTree(root);

        arr = new Integer[]{50, 25, 12, null, null, 37, 30, null, null, 45, null, null, 75, 62, 60, null, null, 70, null, null, 87, null, null};
        BinaryTreeNode root2 = generateBinaryTree(arr);
        displayBinaryTree(root2);

        System.out.println("============================= Size/Sum/Max/Height of BST =================================");
        System.out.println("Tree size is: " + size(root)); //Tree size is: 11
        System.out.println("Tree sum is: " + sum(root)); //Tree sum is: 553
        System.out.println("Tree max is: " + max(root)); //Tree max is: 87
        System.out.println("Tree height is: " + height(root));//Tree height is: 4
        int[] num = new int[2];
        System.out.println("Tree width is: " + width(root, 0, num)); //Tree width is: 5


        System.out.println("================================== Is Valid Binary Tree ==================================");
        boolean isBST = validateBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.out.println(isBST); //true

        System.out.println("============================= Symmetric Binary Tree ======================================");
        boolean isTreeSymetric = isSymmetric(root);
        System.out.println("Is Binary Tree Symmetric : " + isTreeSymetric); //Is Binary Tree Symmetric : false

        System.out.println("//////////////////////// Binary Tree Traversals - Starts /////////////////////////////////");
        System.out.println();

        System.out.println("=========================== Binary Tree PreOrder Traversal ============================");
        List<Integer> ans = new ArrayList<>();
        preOrderTraverse(root, ans);
        System.out.println(ans); //[50, 25, 12, 37, 30, 45, 75, 62, 60, 70, 87]

        System.out.println("=========================== Binary Tree InOrder Traversal ============================");
        ans = new ArrayList<>();
        inOrderTraverse(root, ans);
        System.out.println(ans);//[12, 25, 30, 37, 45, 50, 60, 62, 70, 75, 87]

        System.out.println("=========================== Binary Tree InOrder/Morris Traversal ============================");
        ans = new ArrayList<>();
        inOrderMorrisTraverse(root, ans);
        System.out.println(ans);//[12, 25, 30, 37, 45, 50, 60, 62, 70, 75, 87]

        System.out.println("=========================== Binary Tree PosOrder Traversal ============================");
        ans = new ArrayList<>();
        postOrderTraverse(root, ans);
        System.out.println(ans);//[12, 30, 45, 37, 25, 60, 70, 62, 87, 75, 50]

        System.out.println("=========================== Binary Tree Level Order Traversal ============================");
        List<List<Integer>> answer = levelOrder(root);
        System.out.println(answer); //[[50], [25, 75], [12, 37, 62, 87], [30, 45, 60, 70]]

        System.out.println("======================== Binary Tree ZigZag Level Order Traversal ========================");
        answer = zigzagLevelOrder(root);
        System.out.println(answer);
        //left to right
        //[[50], [75, 25], [12, 37, 62, 87], [70, 60, 45, 30]]
        //right tyo left
        //[[50], [25, 75], [87, 62, 37, 12], [30, 45, 60, 70]]

        System.out.println("========================= Binary Tree Vertical Order Traversal ===========================");
        answer = verticalOrderTraversal(root);
        System.out.println(answer);//[[12], [25, 30, 60], [50, 37, 62], [75, 45, 70], [87]]

        System.out.println("========================= Binary Tree Diagonal Order Traversal ===========================");
        answer = diagonalOrderTraversal(root);
        System.out.println(answer); //[[50, 75, 87], [25, 37, 45, 62, 70], [12, 30, 60]]

        System.out.println("============================ Binary Tree Boundry Traversal ===============================");
        List<Integer> boundryElements = boundryTraversal(root);
        System.out.println(boundryElements);//[50, 25, 12, 30, 45, 60, 70, 87, 75]

        System.out.println();
        System.out.println("////////////////////////// Binary Tree Traversals - Ends /////////////////////////////////");

        System.out.println("/////////////////////////// Binary Tree Views - Starts ///////////////////////////////////");
        System.out.println();
        System.out.println("================================ Binary Tree Top View ====================================");
        ans = topViewOfBST(root);
        System.out.println(ans); //[12, 25, 50, 75, 87]

        System.out.println("============================== Binary Tree Bottom View ===================================");
        ans = bottomViewOfBST(root);
        System.out.println(ans);//[12, 60, 62, 70, 87]

        System.out.println("============================== Binary Tree Left View =====================================");
        ans = leftViewOfBST(root);
        System.out.println(ans);//[50, 25, 12, 30]

        System.out.println("============================= Binary Tree Right View =====================================");
        ans = rightViewOfBST(root);
        System.out.println(ans);//[50, 75, 87, 70]

        System.out.println();

        System.out.println("//////////////////////////// Binary Tree Views - Ends ////////////////////////////////////");

//        System.out.println("============================ Binary Tree Maximum Path Sum ================================");
//        int maxPath = maxPathSum(root);
//        System.out.println("Max Path Sum for Binary Tree is : " + maxPath);

        System.out.println("================================ Lowest Common Ancestor =================================");
        BinaryTreeNode n = lowestCommonAncestor(root, 60, 87);
        System.out.println("Lowest Common Ancestor for values is: " + n.value);

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
        int[] max = new int[1];
        getDiameter(root,max);
        System.out.println("Largest Diameter of BST is : " + max[0]);

        System.out.println("=================================== BST Diameter II =======================================");

        DiaPair p = getDiameterII(root);
        System.out.println("Largest Diameter of BST is : " + p.diameter);

        System.out.println("================================== Are both BST Same =====================================");
        arr = new Integer[] {1, 2, 3,null,null,4, null,null,5,null,6,null,null};
        //BinaryTreeNode tempRoot = generateBinaryTree(arr);
        boolean areTreeSame = isTreeSame(root, root2);
        System.out.println("Are both Trees same :" + areTreeSame);

        System.out.println("======================== Print Root to Node Path in Binary Tree ==========================");
        List<Integer> path = new ArrayList<>();
        nodeToRootPath(root, 70, path);
        System.out.println("Root to Node Path in BST :" + path);

        System.out.println("================================ Is Binary Tree Balanced =================================");
        int height = isTreeBalanced(root);
        System.out.println("Is BST Balanced :" + (height == -1)); //Is BST Balanced :false

        System.out.println("============================== Binary Tree = K Distance ==================================");
        ans = DistanceK(root, 75, 4);
        System.out.println("K Distance for BST is: " + ans); //K Distance for BST is: [30, 45, 60, 70]

//        System.out.println("================================ Is Binary Tree Balanced =================================");
//        int height = isTreeBalanced(root);
//        System.out.println("Is BST Balanced :" + (height == -1));


        System.out.println("========================= Binary Tree Flatten to Linked List  =============================");
//        arr = new Integer[] {1, 2, 3,null,null,4, null,null,5,null,6,7,null,null,null};
        arr = new Integer[] {1, 2, 3,null,null,4, null,null,5,null,6,null,null};

        BinaryTreeNode root1 = generateBinaryTree(arr);
        displayBinaryTree(root1);

        flattenBinaryTreeToLinkedList(root1);
        printList(root1);

        System.out.println("========================= Binary Tree to Doubly Linked List  =============================");
        arr = new Integer[] {25, 20, 10,5,null, null,12, null,null,22,null,null,36,30,28,null,null,null,40,38,null,null,48,null,null};

        BinaryTreeNode btRoot = generateBinaryTree(arr);
        displayBinaryTree(btRoot);
        prev = null;
        flattenBinaryTreeToDoublyLinkedList(btRoot);
        printDoublyLinkedList(head);

        System.out.println("========================= Binary Tree to Doubly Linked List/Morris  =============================");
        BinaryTreeNode morrisRoot = generateBinaryTree(arr);
        BinaryTreeNode newHead = flattenBinaryTreeDoublyToLinkedList_Morriss(morrisRoot);
        printDoublyLinkedList(newHead);
    }
}
