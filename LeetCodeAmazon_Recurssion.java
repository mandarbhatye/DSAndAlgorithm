package Amazon;

import java.util.*;
import java.util.stream.Collectors;

public class LeetCodeAmazon_Recurssion {
    public static List<List<Integer>> getPermutations(List<Integer> array) {
        if (array.size() == 0) return new ArrayList<>();
        List<List<Integer>> perms = new ArrayList<>();
        generatePermutation(array, 0, perms);
        return perms;
    }

    public static void generatePermutation(List<Integer> array, int idx, List<List<Integer>> perms) {
        if (idx == array.size() - 1) {
            List<Integer> perm = new ArrayList<>(array);
            perms.add(perm);
            return;
        }

        for (int i = idx; i < array.size(); i++) {
            swap(array, idx, i);
            generatePermutation(array, idx + 1, perms);
            swap(array, idx, i);
        }
    }

    public static void swap(List<Integer> array, int i, int j) {
        int temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
        return;
    }


    public static void stringPermutation(String str, String answer) {
        if (str.length() == 0) {
            System.out.println(answer);
            return;
        }

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            String left = str.substring(0, i);
            String right = str.substring(i + 1);
            String remainingStr = left + right;
            stringPermutation(remainingStr, answer + ch);
        }
    }

    private static Map<Character, String> lettersMap = new HashMap<>();

    public static void keyPadCombination(String keys) {
        lettersMap.put('2', "abc");
        lettersMap.put('3', "def");
        lettersMap.put('4', "ghi");
        lettersMap.put('5', "jkl");
        lettersMap.put('6', "mno");
        lettersMap.put('7', "pqrs");
        lettersMap.put('8', "tuv");
        lettersMap.put('9', "wxyz");
        List<String> answer = new ArrayList<>();

        printKeyPadCombination(keys, "", answer);
        System.out.println(answer);
    }

    public static void printKeyPadCombination(String str, String ans, List<String> ansList) {
        if (str.length() == 0) {
            ansList.add(ans);
            return;
        }
        char ch = str.charAt(0);
        String remaining = str.substring(1);

        String letters = lettersMap.get(ch);
        for (int i = 0; i < letters.length(); i++) {
            char chOption = letters.charAt(i);
            printKeyPadCombination(remaining, ans + chOption, ansList);
        }
    }

    public static void reverseArray(int[] x, int i, int j) {
        if (i < j) {//Swap
            int tmp = x[i];
            x[i] = x[j];
            x[j] = tmp;
            reverseArray(x, ++i, --j);//Recursive
        }
    }

    public static void swap(int[] arr, int left, int right) {
        int tmp = arr[left];
        arr[left] = arr[right];
        arr[right] = tmp;
    }

    public static void reverseArray_IterativeHelper(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            swap(arr, left++, right--);
        }
    }

    static void reverseArrayKGroup(int arr[], int n, int k) {
        for (int i = 0; i < n; i += k) {
            int left = i;

            int right = Math.min(i + k - 1, n - 1);

            while (left < right) {
                arr[left]  = arr[left] ^ arr[right];
                arr[right] = arr[left] ^ arr[right];
                arr[left]  = arr[left] ^ arr[right];
                left++;
                right--;
            }
        }
    }

    public static boolean strinPallindromeCheck(String str, int left, int right) {
        if (left >= str.length() / 2) return true;

        if (str.charAt(left) != str.charAt(right)) return false;
        return strinPallindromeCheck(str, left + 1, right - 1);
    }

    public static int productSum(List<Object> array) {
        return productSumHelper(array, 1);
    }

    public static int productSumHelper(List<Object> array, int multiplier) {
        int sum = 0;
        for (Object element : array) {
            if (element instanceof ArrayList) {
                sum += productSumHelper((ArrayList<Object>) element, multiplier + 1);
            } else {
                sum += (int) element;
            }
        }
        return sum * multiplier;
    }

    public static void generateParanthesis(int count) {
        List<String> answer = new ArrayList<>();
        generateBalanceParathesis("", count, count, answer);
        System.out.println(answer);
    }

    public static void generateBalanceParathesis(String op, int openCount, int closeCount, List<String> answer) {
        if (openCount == 0 && closeCount == 0) {
            answer.add(op);
            return;
        }
        if (openCount == closeCount) {
            generateBalanceParathesis(op + "(", openCount - 1, closeCount, answer);
        } else {
            if (openCount != 0) {
                generateBalanceParathesis(op + "(", openCount - 1, closeCount, answer);
            }
            if (closeCount != 0) {
                generateBalanceParathesis(op + ")", openCount, closeCount - 1, answer);
            }
        }
    }

    public static boolean wordSearch(char[][] board, String word) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        boolean isWordFound = false;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (word.charAt(0) != board[i][j]) continue;
                isWordFound = isWordFound(board, i, j, 0, word, visited);
            }
        }
        return isWordFound;
    }

    public static boolean isWordFound(char[][] board, int row, int col, int wordCount, String word, boolean[][] visited) {
        if (wordCount == word.length()) return true;

        if (row < 0 || col < 0 || row >= board.length || col >= board[0].length || visited[row][col] == true || board[row][col] != word.charAt(wordCount)) {
            return false;
        }

        visited[row][col] = true;
        boolean isFound = isWordFound(board,row + 1, col, wordCount + 1, word, visited) ||
                          isWordFound(board, row, col + 1, wordCount + 1, word, visited) ||
                          isWordFound(board, row - 1, col, wordCount + 1, word, visited) ||
                          isWordFound(board, row, col - 1, wordCount + 1, word, visited);
        visited[row][col] = false;

        return isFound;
    }

    public static int staircaseTraversal(int height, int maxSteps) {
        HashMap<Integer, Integer> memoize = new HashMap<>();
        memoize.put(0, 1);
        memoize.put(1, 1);
        return numberOfWaysToTopDynamic(height, maxSteps);
        //return numberOfWaysToTop(height, maxSteps, memoize);
    }

    //Time Complexity: O(k^n) Space Complexity: O(n)
    public static int numberOfWaysToTop(int height, int maxSteps) {
        if (height <= 1) return 1;

        int numberOfWays = 0;

        for (int i = 1; i < Math.min(maxSteps, height) + 1; i++) {
            numberOfWays += numberOfWaysToTop(height - i, maxSteps);
        }

        return numberOfWays;
    }

    //Time Complexity: O(n * k) Space Complexity: O(n)
    public static int numberOfWaysToTop(int height, int maxSteps, HashMap<Integer, Integer> memoize) {
        if (memoize.containsKey(height))
            return memoize.get(height);

        int numberOfWays = 0;

        for (int i = 1; i < Math.min(maxSteps, height) + 1; i++) {
            numberOfWays += numberOfWaysToTop(height - i, maxSteps,memoize);
        }

        memoize.put(height, numberOfWays);

        return numberOfWays;
    }

    //Time Complexity: O(n * k) Space Complexity: O(n)

    public static int numberOfWaysToTopDynamic(int height, int maxSteps) {
        int[] dp = new int[height + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int currentHeight = 2; currentHeight < height + 1; currentHeight++) {
            int step = 1;
            while (step <= maxSteps && step <= currentHeight) {
                dp[currentHeight] = dp[currentHeight] + dp[currentHeight - step];
                step++;
            }
        }
        return dp[height];
    }

    // Sudoku solution started..
    //7 8 5 4 3 9 1 2 6
    //6 1 2 8 7 5 3 4 9
    //4 9 3 6 2 1 5 7 8
    //8 5 7 9 4 3 2 6 1
    //2 6 1 7 5 8 9 3 4
    //9 3 4 1 6 2 7 8 5
    //5 7 8 3 9 4 6 1 2
    //1 2 6 5 8 7 4 9 3
    //3 4 9 2 1 6 8 5 7
    public static ArrayList<ArrayList<Integer>> solveSudoku(ArrayList<ArrayList<Integer>> board) {
            solvePartialSudoku(board, 0, 0);
            displaySudokuBoard(board);
            return board;
    }

    public static boolean solvePartialSudoku(ArrayList<ArrayList<Integer>> board, int row, int col) {
        int currentRow = row;
        int currentCol = col;

        if (currentCol == board.get(0).size()) {
            currentRow += 1;
            currentCol = 0;

            if (currentRow == board.size())
                return true;
        }

        if (board.get(currentRow).get(currentCol) == 0) {
            return tryDigitsAtPosition(currentRow, currentCol, board);
        }
        return solvePartialSudoku(board, currentRow, currentCol + 1);
    }

    private static boolean tryDigitsAtPosition(int row, int col, ArrayList<ArrayList<Integer>> board) {
        for (int digitOption = 0; digitOption < 10; digitOption++) {
            if (isValidPosition(board, row, col, digitOption)) {
                board.get(row).set(col, digitOption);
                if (solvePartialSudoku(board, row, col))
                    return true;
            }
        }
        board.get(row).set(col, 0);
        return false;
    }

    public static boolean isValidPosition(ArrayList<ArrayList<Integer>> board, int row, int col, int value) {
        //check for Vertical
        for (int i = 0; i < board.size(); i++) {
            if (board.get(i).get(col) == value)
                return false;
        }

        //check for Horizontal
        for (int i = 0; i < board.get(0).size(); i++) {
            if (board.get(row).get(i) == value)
                return false;
        }
        //3 x3 Matrix
        int subGridRow = row / 3 * 3;
        int subGridCol = col / 3 * 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int currentVal = board.get(subGridRow + i).get(subGridCol + j);
                if (value == currentVal)
                    return false;
            }
        }
        return true;
    }

    private static void displaySudokuBoard(ArrayList<ArrayList<Integer>> board) {
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.get(0).size(); j++) {
                System.out.print(board.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    // Sudoku solution finished..

    // N-Queen solution Starts..

    public static int nonAttackingQueens(int n) {
        // Write your code here.
        int[][] board = new int[n][n];
        return placeQueen(board, 0);
    }

    public static int placeQueen(int[][] board, int row) {
        if (row == board.length)
            return 1;

        int validPlacement = 0;
        for (int column = 0; column < board[0].length; column++) {
            if (isQueenSafe(board, row, column)) {
                board[row][column] = 1;
                validPlacement += placeQueen(board, row + 1);
                board[row][column] = 0;
            }
        }
        return validPlacement;
    }

    public static boolean isQueenSafe(int[][] board, int row, int col) {
        //check upward by row
        for (int i = row; i >= 0; i--) {
            if (board[i][col] == 1)
                return false;
        }

        //diagonally left
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1)
                return false;
        }

        //diagonally right
        for (int i = row, j = col; i >= 0 && j < board[0].length; i--, j++) {
            if (board[i][j] == 1)
                return false;
        }
        return true;
    }
    // N-Queen solution finished..

    private static void sum(int n, int sum) {
        if (n < 1) {
            System.out.println(sum);
            return;
        }
        sum(n - 1, sum + n);
    }

    private static int sum(int n) {
        if (n == 0) return 1;
        return n * sum(n - 1);
    }

    public static void main(String[] args) {
        System.out.println("============================ Print Number Permutation ====================================");
        int[] arr = new int[]{1, 2, 3};
        List<Integer> list = Arrays.stream(arr)        // IntStream
                .boxed()          // Stream<Integer>
                .collect(Collectors.toList());
        List<List<Integer>> perms = getPermutations(list);

        System.out.println(perms);

        System.out.println("============================ Print String Permutation ====================================");
        stringPermutation("abc", "");

        System.out.println("============================ Print Keypad Combination ====================================");
        keyPadCombination("23");

        System.out.println("========================== Reverse Array - Recurssion/Itr ================================");
        arr = new int[]{1, 2, 3, 4, 5, 6};
        reverseArray(arr, 0, arr.length - 1);
        System.out.println("Reversing Array - Recursive: " + Arrays.toString(arr));
        arr = new int[]{1, 2, 3, 4, 5, 6};
        reverseArray_IterativeHelper(arr);
        System.out.println("Reversing Array - Iterative: " + Arrays.toString(arr));

        arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        int k = 3;
        reverseArrayKGroup(arr, arr.length, k);
        System.out.println("Reversing Array in: " + k + " groups is: " + Arrays.toString(arr));

        System.out.println("====================== String Pallindrome Check - Recurssion =============================");
        boolean isPallindrom = strinPallindromeCheck("MADAMA", 0, "MADAMA".length() - 1);
        System.out.println(isPallindrom);

        System.out.println("=========================== Product sum with nested Array ================================");

        //arr = new int[]{5, 2, {7, -1}, 3, {6, {-13, 8}, 4}};

        System.out.println("=========================== Generate balanced Parenthesis ================================");
        generateParanthesis(2);

        System.out.println("=================================== Word Search I =======================================");
        char[][] board = new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String searchWord = "ABCB";
        //Output: true
        boolean isWordFound = wordSearch(board, searchWord);
        System.out.println(" Word : " + searchWord + " is found in the board: " + isWordFound);

        System.out.println("================================ Stair case Traversal ====================================");
        //"height": 4,
        //"maxSteps": 2
        //Output: 5
        //  "height": 10,
        //  "maxSteps": 1
        //Output :1
        //  "height": 10,
        //  "maxSteps": 2
        //Output : 89
        int steps = staircaseTraversal(10, 2);
        System.out.println("Total steps count would be : " + steps);

        System.out.println("================================= Solve Sudoku ===========================================");
        ArrayList<ArrayList<Integer>> sudokuBoardList = new ArrayList<>();


        int[] arr1 = new int[]{7, 8, 0, 4, 0, 0, 1, 2, 0};
        List<Integer> lst = Arrays.stream(arr1)        // IntStream
                .boxed()          // Stream<Integer>
                .collect(Collectors.toList());
        sudokuBoardList.add((ArrayList<Integer>) lst);

        arr1 = new int[]{6, 0, 0, 0, 7, 5, 0, 0, 9};
        lst = Arrays.stream(arr1)        // IntStream
                .boxed()          // Stream<Integer>
                .collect(Collectors.toList());
        sudokuBoardList.add((ArrayList<Integer>) lst);

        arr1 = new int[]{0, 0, 0, 6, 0, 1, 0, 7, 8};
        lst = Arrays.stream(arr1)        // IntStream
                .boxed()          // Stream<Integer>
                .collect(Collectors.toList());
        sudokuBoardList.add((ArrayList<Integer>) lst);

        arr1 = new int[]{0, 0, 7, 0, 4, 0, 2, 6, 0};
        lst = Arrays.stream(arr1)        // IntStream
                .boxed()          // Stream<Integer>
                .collect(Collectors.toList());
        sudokuBoardList.add((ArrayList<Integer>) lst);

        arr1 = new int[]{0, 0, 1, 0, 5, 0, 9, 3, 0};
        lst = Arrays.stream(arr1)        // IntStream
                .boxed()          // Stream<Integer>
                .collect(Collectors.toList());
        sudokuBoardList.add((ArrayList<Integer>) lst);

        arr1 = new int[]{9, 0, 4, 0, 6, 0, 0, 0, 5};
        lst = Arrays.stream(arr1)        // IntStream
                .boxed()          // Stream<Integer>
                .collect(Collectors.toList());
        sudokuBoardList.add((ArrayList<Integer>) lst);

        arr1 = new int[]{0, 7, 0, 3, 0, 0, 0, 1, 2};
        lst = Arrays.stream(arr1)        // IntStream
                .boxed()          // Stream<Integer>
                .collect(Collectors.toList());
        sudokuBoardList.add((ArrayList<Integer>) lst);

        arr1 = new int[]{1, 2, 0, 0, 0, 7, 4, 0, 0};
        lst = Arrays.stream(arr1)        // IntStream
                .boxed()          // Stream<Integer>
                .collect(Collectors.toList());
        sudokuBoardList.add((ArrayList<Integer>) lst);

        arr1 = new int[]{0, 4, 9, 2, 0, 6, 0, 0, 7};
        lst = Arrays.stream(arr1)        // IntStream
                .boxed()          // Stream<Integer>
                .collect(Collectors.toList());
        sudokuBoardList.add((ArrayList<Integer>) lst);

        solveSudoku(sudokuBoardList);

        System.out.println("================================== Non-Attacking Queen ===================================");
        int moves = nonAttackingQueens(5);
        System.out.println("Total move counts are: " + moves);

        System.out.println("=================================== Word Search II =======================================");
        board = new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String[] searchWords = new String[]{"oath", "pea", "eat", "rain"};
        //Output: true
        //boolean isWordFound = wordSearch(board, searchWord);
        //System.out.println(" Word : " + searchWord + " is found in the board: " + isWordFound);

        int num = 4;
        int numSum = 0;
        sum(num, numSum);

        numSum = sum(num);
        System.out.println("SUM is: " + numSum);
    }
}
