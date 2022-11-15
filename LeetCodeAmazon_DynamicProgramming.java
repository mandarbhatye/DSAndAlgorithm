package Amazon;

import java.util.ArrayList;
import java.util.Arrays;

public class LeetCodeAmazon_DynamicProgramming {
    static int frogJumpRecursionAndMemoization(int ind, int[] height, int[] dp) {
        if (ind == 0) return 0;

        if (dp[ind] != -1) return dp[ind];

        int jumpTwo = Integer.MAX_VALUE;
        int jumpOne = frogJumpRecursionAndMemoization(ind - 1, height, dp) + Math.abs(height[ind] - height[ind - 1]);
        if (ind > 1)
            jumpTwo = frogJumpRecursionAndMemoization(ind - 2, height, dp) + Math.abs(height[ind] - height[ind - 2]);

        return dp[ind] = Math.min(jumpOne, jumpTwo);
    }

    public static int frogJumpDP(int[] height) {
        int n = height.length;
        int dp[] = new int[n];
        for (int ind = 1; ind < n; ind++) {
            int jumpTwo = Integer.MAX_VALUE;
            int jumpOne = dp[ind - 1] + Math.abs(height[ind] - height[ind - 1]);
            if (ind > 1)
                jumpTwo = dp[ind - 2] + Math.abs(height[ind] - height[ind - 2]);

            dp[ind] = Math.min(jumpOne, jumpTwo);
        }
        return dp[n - 1];
    }

    public static int frogJumpDPAndSpaceOptimized(int[] height, int[] dp) {
        int n = height.length;
        int prev = 0;
        int prev2 = 0;
        for (int i = 1; i < n; i++) {
            int jumpTwo = Integer.MAX_VALUE;
            int jumpOne = prev + Math.abs(height[i] - height[i - 1]);
            if (i > 1)
                jumpTwo = prev2 + Math.abs(height[i] - height[i - 2]);

            int cur_i = Math.min(jumpOne, jumpTwo);
            prev2 = prev;
            prev = cur_i;
        }
        return prev;
    }

    static int frogJumpKJumpsRecursionAndMemoizationHelper(int ind, int[] height, int[] dp, int k) {
        if (ind == 0) return 0;
        if (dp[ind] != -1) return dp[ind];

        int mmSteps = Integer.MAX_VALUE;

        for (int j = 1; j <= k; j++) {
            if (ind - j >= 0) {
                int jump = frogJumpKJumpsRecursionAndMemoizationHelper(ind - j, height, dp, k) + Math.abs(height[ind] - height[ind - j]);
                mmSteps = Math.min(jump, mmSteps);
            }
        }
        return dp[ind] = mmSteps;
    }

    static int frogJumpKJumpsRecursionAndMemoization(int n, int[] height, int k) {
        int dp[] = new int[n];
        Arrays.fill(dp, -1);
        return frogJumpKJumpsRecursionAndMemoizationHelper(n - 1, height, dp, k);
    }

    static int frogJumpKJumpsDPHelper(int n, int[] height, int k) {
        int dp[] = new int[n];
        for (int i = 1; i < n; i++) {
            int mmSteps = Integer.MAX_VALUE;

            for (int j = 1; j <= k; j++) {
                if (i - j >= 0) {
                    int jump = dp[i - j] + Math.abs(height[i] - height[i - j]);
                    mmSteps = Math.min(jump, mmSteps);
                }
            }
            dp[i] = mmSteps;
        }
        return dp[n - 1];
    }

    static int frogJumpKJumpsDP(int n, int[] height, int k) {
        return frogJumpKJumpsDPHelper(n, height, k);
    }

    static int maximumsumOfNonAdjacentElementsHelper(int ind, int[] arr, int[] dp) {
        if (ind < 0) return 0;
        if (ind == 0) return arr[ind];

        if (dp[ind] != -1) return dp[ind];

        int pick = arr[ind] + maximumsumOfNonAdjacentElementsHelper(ind - 2, arr, dp);
        int nonPick = 0 + maximumsumOfNonAdjacentElementsHelper(ind - 1, arr, dp);

        return dp[ind] = Math.max(pick, nonPick);
    }

    static int maximumsumOfNonAdjacentElements(int n, int[] arr) {
        int dp[] = new int[n];
        Arrays.fill(dp, -1);
        return maximumsumOfNonAdjacentElementsHelper(n - 1, arr, dp);
    }

    static int maximumsumOfNonAdjacentElementsDPHelper(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        dp[0] = arr[0];

        for (int i = 1; i < n; i++) {
            int pick = arr[i];
            if (i > 1)
                pick += dp[i - 2];
            int nonPick = 0 + dp[i - 1];

            dp[i] = Math.max(pick, nonPick);
        }
        return dp[n - 1];
    }

    static long robStreetHelper(ArrayList<Integer> arr) {
        int n = arr.size();
        int[] array = arr.stream().mapToInt(i -> i).toArray();

        int dp[] = new int[n];
        Arrays.fill(dp, -1);

        return maximumsumOfNonAdjacentElementsHelper(n - 1, array, dp);
    }

    static long robStreet(int n, ArrayList<Integer> arr) {
        ArrayList<Integer> arr1 = new ArrayList<>();
        ArrayList<Integer> arr2 = new ArrayList<>();

        if (n == 1)
            return arr.get(0);

        for (int i = 0; i < n; i++) {
            if (i != 0) arr1.add(arr.get(i));
            if (i != n - 1) arr2.add(arr.get(i));
        }

        long ans1 = robStreetHelper(arr1);
        long ans2 = robStreetHelper(arr2);

        return Math.max(ans1, ans2);
    }

    public static int ninjaTraining(int n, int points[][]) {
        int[][] dp = new int[n][3];
        for (int[] tmp : dp) {
            Arrays.fill(tmp, -1);
        }
        int merit = ninjaTrainingRecursive(n - 1, 2, points, dp);
        return merit;
    }

    public static int ninjaTrainingRecursive(int day, int lastTask, int[][] points, int[][] dp) {
        if (day == 0) {
            int maxMerit = 0;
            for (int task = 0; task < 3; task++) {
                if (task == lastTask) continue;
                maxMerit = Math.max(maxMerit, points[0][task]);
            }
            return maxMerit;
        }

        int maxMerit = 0;

        if (dp[day][lastTask] != -1) return dp[day][lastTask];

        for (int task = 0; task < 3; task++) {
            if (task == lastTask) continue;
            int point = points[day][task] + ninjaTrainingRecursive(day - 1, task, points, dp);
            maxMerit = Math.max(maxMerit, point);
        }
        return dp[day][lastTask] = maxMerit;
    }

    public static int ninjaTrainingDP(int n, int points[][]) {
        int[][] dp = new int[n][4];

        dp[0][0] = Math.max(points[0][1], points[0][2]);
        dp[0][1] = Math.max(points[0][0], points[0][2]);
        dp[0][2] = Math.max(points[0][0], points[0][1]);

        for (int day = 1; day < n; day++) {
            for (int last = 0; last < 4; last++) {
                for (int task = 0; task < 3; task++) {
                    if (task == last) continue;
                    int point = points[day][task] + dp[day - 1][task];
                    dp[day][last] = Math.max(dp[day][last], point);
                }
            }
        }
        return dp[n - 1][3];
    }

    public static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return traverseMatrix(m - 1, n - 1, dp);
    }

    public static int traverseMatrix(int row, int col, int[][] dp) {
        if (row == 0 && col == 0) return 1;
        if (row < 0 || col < 0) return 0;

        if (dp[row][col] != -1) return dp[row][col];

        int upDirection   = traverseMatrix(row - 1, col, dp);
        int downDirection = traverseMatrix(row, col - 1, dp);

        return dp[row][col] = upDirection + downDirection;
    }

    public static int uniquePathsDP(int m, int n) {
        int[][] dp = new int[m][n];

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                    continue;
                }
                int up = 0;
                int down = 0;

                if (i > 0)
                    up = dp[i - 1][j];
                if (j > 0)
                    down = dp[i][j - 1];

                dp[i][j] = up + down;
            }
        }
        return dp[m - 1][n - 1];
    }

    static int minJumps(int arr[]) {
        if (arr.length <= 1)
            return 0;

        // Return -1 if not possible to jump
        if (arr[0] == 0)
            return -1;

        // initialization
        int maxReach = arr[0];
        int step = arr[0];
        int jump = 1;

        // Start traversing array
        for (int i = 1; i < arr.length; i++) {
            // Check if we have reached the end of the array
            if (i == arr.length - 1)
                return jump;

            // updating maxReach
            maxReach = Math.max(maxReach, i + arr[i]);

            // we use a step to get to the current index
            step--;

            // If no further steps left
            if (step == 0) {
                // we must have used a jump
                jump++;

                // Check if the current index position or lesser index
                // is the maximum reach point from the previous indexes
                if (i >= maxReach)
                    return -1;

                // re-initialize the steps to the amount
                // of steps to reach maxReach from position i.
                step = maxReach - i;
            }
        }

        return -1;
    }

    public static int minimumJumps(int[] arr, int N) {
        int[] dp = new int[N];
        Arrays.fill(dp, -1);

        int ans = minimumJumpsHelper(0, arr, N, dp);

        if (ans == Integer.MAX_VALUE) {
            ans = -1;
        }
        return ans;
    }

    public static int minimumJumpsHelper(int i, int[] arr, int n, int[] dp) {
        if (i == (n - 1)) return 0;

        if (dp[i] != -1) return dp[i];

        long ans = Integer.MAX_VALUE;

        for (int j = i + 1; j <= Math.min(i + arr[i], n - 1); j++) {
            ans = Math.min(ans, 1L + minimumJumpsHelper(j, arr, n, dp));
        }

        return dp[i] = (int) ans;
    }

    public static int minimumJumpsDP(int[] arr, int N) {
        int[] dp = new int[N];
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[N - 1] = 0;

        for (int i = N - 2; i >= 0; i--) {
            for (int j = i + 1; j <= Math.min(N - 1, i + arr[i]); j++) {
                if (dp[j] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], 1 + dp[j]);
                }
            }
        }
        if (dp[0] == Integer.MAX_VALUE) {
            return -1;
        }
        return dp[0];
    }

    public static int minSumPath(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] dp = new int[n][m];
        for (int[] tmp : dp) {
            Arrays.fill(tmp, -1);
        }
        int jumps = minSumPathHelper(n - 1, m - 1, grid, dp);

        return jumps;
    }

    public static int minSumPathHelper(int i, int j, int[][] grid, int[][] dp) {
        if (i == 0 && j == 0) return grid[i][j];
        if (i < 0 || j < 0) return 10000000;

        if (dp[i][j] != -1) return dp[i][j];

        int up   = grid[i][j] + minSumPathHelper(i - 1, j, grid, dp);
        int down = grid[i][j] + minSumPathHelper(i, j - 1, grid, dp);

        return dp[i][j] = Math.min(down, up);
    }

    static int minSumPathDP(int n, int m, int[][] matrix) {
        int dp[][] = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = matrix[i][j];
                    continue;
                }
                int up = matrix[i][j];
                if (i > 0) up += dp[i - 1][j];
                else up += 10000000;

                int down = matrix[i][j];
                if (j > 0) down += dp[i][j - 1];
                else down += 10000000;
                dp[i][j] = Math.min(up, down);

            }
        }
        return dp[n - 1][m - 1];
    }

    static int minimumPathTriangleSumRecursive(int[][] triangle, int n) {
        int dp[][] = new int[n][n];
        for (int row[] : dp)
            Arrays.fill(row, -1);
        return minimumPathTriangleSumRecursiveHelper(0, 0, triangle, n, dp);
    }

    static int minimumPathTriangleSumRecursiveHelper(int i, int j, int[][] triangle, int n, int[][] dp) {
        if (i == n - 1) return triangle[i][j];

        if (dp[i][j] != -1) return dp[i][j];

        int down     = triangle[i][j] + minimumPathTriangleSumRecursiveHelper(i + 1, j, triangle, n, dp);
        int diagonal = triangle[i][j] + minimumPathTriangleSumRecursiveHelper(i + 1, j + 1, triangle, n, dp);

        return dp[i][j] = Math.min(down, diagonal);
    }

    static int minimumPathTriangleSumDP(int[][] triangle, int n) {
        int dp[][] = new int[n][n];

        for (int j = 0; j < n; j++) {
            dp[n - 1][j] = triangle[n - 1][j];
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                int down     = triangle[i][j] + dp[i + 1][j];
                int diagonal = triangle[i][j] + dp[i + 1][j + 1];

                dp[i][j] = Math.min(down, diagonal);
            }
        }
        return dp[0][0];
    }

    //Time Complexity: O(N*N)
    //Reason: At max, there will be M*N calls of recursion to solve a new problem
    //Space Complexity: O(N) + O(N*M)
    static int getMaxFallingPathSumRecursive(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        int dp[][] = new int[n][m];
        for (int row[] : dp)
            Arrays.fill(row, -1);

        int maxi = Integer.MIN_VALUE;

        for (int j = 0; j < m; j++) {
            //int ans = getMaxFallingPathSumRecursiveHelper(n - 1, j, matrix, dp);
            int ans = getMaxFallingPathSumRecursiveHelperII(0,j,matrix,dp);
            maxi = Math.max(maxi, ans);
        }
        return maxi;
    }

    static int getMaxFallingPathSumRecursiveHelper(int i, int j, int[][] matrix, int[][] dp) {
        if (j < 0 || j >= matrix[0].length)
            return (int) Math.pow(-10, 9);
        if (i == 0)
            return matrix[i][j];

        if (dp[i][j] != -1) return dp[i][j];

        int up            = matrix[i][j] + getMaxFallingPathSumRecursiveHelper(i - 1, j, matrix, dp);
        int leftDiagonal  = matrix[i][j] + getMaxFallingPathSumRecursiveHelper(i - 1, j - 1, matrix, dp);
        int rightDiagonal = matrix[i][j] + getMaxFallingPathSumRecursiveHelper(i - 1, j + 1, matrix, dp);

        return dp[i][j] = Math.max(up, Math.max(leftDiagonal, rightDiagonal));
    }

    static int getMaxFallingPathSumRecursiveHelperII(int i, int j, int[][] matrix, int[][] dp) {
        if (j < 0 || j >= matrix[0].length)
            return Integer.MIN_VALUE;
        if (i == matrix.length)
            return 0;

        if (dp[i][j] != -1) return dp[i][j];

        int down            = matrix[i][j] + getMaxFallingPathSumRecursiveHelperII(i + 1, j, matrix, dp);
        int rightDiagonal  = matrix[i][j] + getMaxFallingPathSumRecursiveHelperII(i + 1, j + 1, matrix, dp);
        int leftDiagonal = matrix[i][j] + getMaxFallingPathSumRecursiveHelperII(i + 1, j - 1, matrix, dp);

        return dp[i][j] = Math.max(down, Math.max(leftDiagonal, rightDiagonal));
    }

    static int getMaxFallingPathSumDP(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        int dp[][] = new int[n][m];

        // Initializing first row - base condition
        for (int j = 0; j < m; j++) {
            dp[0][j] = matrix[0][j];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {

                int down = matrix[i][j] + dp[i - 1][j];

                int leftDiagonal = matrix[i][j];
                if (j > 0) leftDiagonal += dp[i - 1][j - 1];
                else leftDiagonal += (int) Math.pow(-10, 9);

                int rightDiagonal = matrix[i][j];
                if (j +1 < m) rightDiagonal += dp[i - 1][j + 1];
                else rightDiagonal += (int) Math.pow(-10, 9);

                dp[i][j] = Math.max(down, Math.max(leftDiagonal, rightDiagonal));
            }
        }
        int maxi = Integer.MIN_VALUE;

        for (int j = 0; j < m; j++) {
            maxi = Math.max(maxi, dp[n - 1][j]);
        }
        return maxi;
    }

    static int maxChocolate(int n, int m, int[][] grid) {
        int dp[][][] = new int[n][m][m];

        for (int row1[][] : dp) {
            for (int row2[] : row1) {
                Arrays.fill(row2, -1);
            }
        }
        return maxChocolateRecursion(0, 0, m - 1, n, m, grid, dp);
    }

    static int maxChocolateRecursion(int i, int j1, int j2, int n, int m, int[][] grid, int[][][] dp) {
        if (j1 < 0 || j1 >= m || j2 < 0 || j2 >= m) return (int) (Math.pow(-10, 9));

        if (i == n - 1) {
            if (j1 == j2) {
                return grid[i][j1];
            } else {
                return grid[i][j1] + grid[i][j2];
            }
        }

        int maxChocolate = Integer.MIN_VALUE;
        if (dp[i][j1][j2] != -1) return dp[i][j1][j2];

        for (int di = -1; di <= 1; di++) {
            for (int dj = -1; dj <= 1; dj++) {
                int ans = 0;
                if (j1 == j2) {
                    ans = grid[i][j1] + maxChocolateRecursion(i + 1, j1 + di, j2 + dj, n, m, grid, dp);
                } else {
                    ans = grid[i][j1] + grid[i][j2] + maxChocolateRecursion(i + 1, j1 + di, j2 + dj, n, m, grid, dp);
                }
                maxChocolate = Math.max(maxChocolate, ans);
            }
        }
        return dp[i][j1][j2] = maxChocolate;
    }

    static int maximumChocolatesDP(int n, int m, int[][] grid) {
        int dp[][][] = new int[n][m][m];

        for (int j1 = 0; j1 < m; j1++) {
            for (int j2 = 0; j2 < m; j2++) {
                if (j1 == j2)
                    dp[n - 1][j1][j2] = grid[n - 1][j1];
                else
                    dp[n - 1][j1][j2] = grid[n - 1][j1] + grid[n - 1][j2];
            }
        }

        //Outer Nested Loops for traversing DP Array
        for (int i = n - 2; i >= 0; i--) {
            for (int j1 = 0; j1 < m; j1++) {
                for (int j2 = 0; j2 < m; j2++) {
                    int maxi = Integer.MIN_VALUE;
                    //Inner nested loops to try out 9 options
                    for (int di = -1; di <= 1; di++) {
                        for (int dj = -1; dj <= 1; dj++) {
                            int ans;

                            if (j1 == j2)
                                ans = grid[i][j1];
                            else
                                ans = grid[i][j1] + grid[i][j2];

                            if ((j1 + di < 0 || j1 + di >= m) || (j2 + dj < 0 || j2 + dj >= m))
                                ans += (int) Math.pow(-10, 9);
                            else
                                ans += dp[i + 1][j1 + di][j2 + dj];

                            maxi = Math.max(ans, maxi);
                        }
                    }
                    dp[i][j1][j2] = maxi;
                }
            }
        }
        return dp[0][0][m - 1];
    }

    public static boolean SubsetSumEqualToTargetRecursion(int n, int target, int[] arr) {
        int dp[][] = new int[n][target + 1];

        for (int row[] : dp)
            Arrays.fill(row, -1);

        return SubsetSumEqualToTargetRecursionHelper(arr, n - 1, target, dp);
    }

    public static boolean SubsetSumEqualToTargetRecursionHelper(int[] arr, int index, int target, int[][] dp) {
        if (target == 0) return true;

        if (index == 0) return arr[0] == target;

        if (dp[index][target] != -1) return dp[index][target] == 0 ? false : true;

        boolean notTaken = SubsetSumEqualToTargetRecursionHelper(arr, index - 1, target, dp);

        boolean taken = false;
        if (arr[index] <= target)
            taken = SubsetSumEqualToTargetRecursionHelper(arr, index - 1, target - arr[index], dp);

        dp[index][target] = notTaken || taken ? 1 : 0;

        return notTaken || taken;
    }

    static boolean SubsetSumEqualToTargetDP(int n, int k, int[] arr) {
        boolean dp[][] = new boolean[n][k + 1];

        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }

        if (arr[0] <= k)
            dp[0][arr[0]] = true;

        for (int ind = 1; ind < n; ind++) {
            for (int target = 1; target <= k; target++) {
                boolean notTaken = dp[ind - 1][target];
                boolean taken = false;
                if (arr[ind] <= target)
                    taken = dp[ind - 1][target - arr[ind]];

                dp[ind][target] = notTaken || taken;
            }
        }
        return dp[n - 1][k];
    }

    public static ArrayList<ArrayList<Integer>> findSubsetsThatSumToK(ArrayList<Integer> arr, int n, int k) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> curSubset = new ArrayList<>();

        findSubsetsThatSumToKHelper(arr, ans, curSubset, k, 0);

        return ans;
    }

    public static void findSubsetsThatSumToKHelper(ArrayList<Integer> arr, ArrayList<ArrayList<Integer>> ans, ArrayList<Integer> curSubset, int k, int idx) {
        if (idx == arr.size()) {
            if (k == 0 && curSubset.size() != 0) {
                ans.add(curSubset);
            }
            return;
        }

        findSubsetsThatSumToKHelper(arr, ans, curSubset, k, idx + 1);

        ArrayList<Integer> curSubset1 = new ArrayList<>(curSubset);
        curSubset1.add(arr.get(idx));

        findSubsetsThatSumToKHelper(arr, ans, curSubset1, k - arr.get(idx), idx + 1);
    }



    static boolean canPartition(int n, int[] arr) {
        int totSum = Arrays.stream(arr).sum();

        if (totSum % 2 == 1) return false;
        else {
            int k = totSum / 2;
            int dp[][] = new int[n][k + 1];
            for (int row[] : dp)
                Arrays.fill(row, -1);
            return SubsetSumEqualToTargetRecursionHelper(arr, n - 1, k, dp);
        }
    }

    private static int countsSubsetsWithSumKRecursion(int[] arr, int sum) {
        int n = arr.length;
        int[][] dp = new int[n][sum + 1];

        for (int[] tmp : dp) {
            Arrays.fill(tmp, -1);
        }
        return countsSubsetsWithSumKRecursionHelper(n - 1, sum, arr, dp);
    }

    private static int countsSubsetsWithSumKRecursionHelper(int index, int sum, int[] arr, int[][] dp) {
        if (sum == 0) return 1;

        if (index == 0) {
            return arr[index] == sum ? 1 : 0;
        }

        if (dp[index][sum] != -1) return dp[index][sum];

        int notPick = countsSubsetsWithSumKRecursionHelper(index - 1, sum, arr, dp);
        int pick = 0;
        if (arr[index] <= sum) pick += countsSubsetsWithSumKRecursionHelper(index - 1, sum - arr[index], arr, dp);

        return dp[index][sum] = pick + notPick;
    }

    private static int countsSubsetsWithSumKDP(int tar, int[] arr) {
        int[][] dp = new int[arr.length][tar + 1];

        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = 1;
        }

        if (arr[0] <= tar) dp[0][arr[0]] = 1;

        for (int ind = 1; ind < dp.length; ind++) {
            for (int sum = 1; sum <= tar; sum++) {
                int notPick = dp[ind - 1][sum];
                int pick = 0;
                if (arr[ind] <= sum) pick = dp[ind - 1][sum - arr[ind]];

                dp[ind][sum] = pick + notPick;
            }
        }
        return dp[arr.length - 1][tar];
    }

    static int countsSubsetsWithSumKDPSpaceOptimized(int tar, int[] arr) {
        int n = arr.length;
        int prev[] = new int[tar + 1];
        prev[0] = 1;

        if (arr[0] <= tar)
            prev[arr[0]] = 1;

        for (int ind = 1; ind < n; ind++) {
            int cur[] = new int[tar + 1];
            cur[0] = 1;
            for (int target = 1; target <= tar; target++) {
                int notTaken = prev[target];
                int taken = 0;
                if (arr[ind] <= target)
                    taken = prev[target - arr[ind]];

                cur[target] = notTaken + taken;
            }
            prev = cur;
        }
        return prev[tar];

    }

    //W is max capacity
    private static int knapsackRecursion(int[] wt, int[] val, int n, int W) {
        int dp[][] = new int[n][W + 1];
        for (int row[] : dp)
            Arrays.fill(row, -1);
        return knapsackRecursionHelper(wt, val, n - 1, W, dp);
    }

    private static int knapsackRecursionHelper(int[] wt, int[] val, int n, int w, int[][] dp) {
        if (n == 0) {
            if (wt[0] <= w) return val[0];
            else return 0;
        }

        if (dp[n][w] != -1) return dp[n][w];

        int notPickup = 0 + knapsackRecursionHelper(wt, val, n - 1, w, dp);

        int pickUp = Integer.MIN_VALUE;
        if (wt[n] <= w) pickUp = val[n] + knapsackRecursionHelper(wt, val, n - 1, w - wt[n], dp);

        return dp[n][w] = Math.max(pickUp, notPickup);
    }

    static int knapsackDP(int[] wt, int[] val, int n, int W) {
        int dp[][] = new int[n][W + 1];

        for (int i = 0; i <= W; i++) {
            dp[0][i] = val[0];
        }

        for (int i = 1; i < n; i++) {
            for (int capacity = 0; capacity <= W; capacity++) {
                int notPickup = 0 + dp[i - 1][capacity];

                int pickUp = Integer.MIN_VALUE;
                if (wt[i] <= capacity) pickUp = val[i] + dp[i - 1][capacity - wt[i]];

                dp[i][capacity] = Math.max(pickUp, notPickup);
            }
        }
        return dp[n - 1][W];
    }

    //infinity
    private static long coinChangeRecursion(int[] arr, int n, int T) {
        long dp[][] = new long[n][T + 1];
        for (long row[] : dp)
            Arrays.fill(row, -1);
        return coinChangeRecursionHelper(arr, n - 1, T, dp);
    }


    private static long coinChangeRecursionHelper(int[] arr, int ind, int T, long[][] dp) {
        if (ind == 0) {
            return T % arr[0] == 0 ? 1 : 0;
        }

        if (dp[ind][T] != -1) return dp[ind][T];

        long notTaken = coinChangeRecursionHelper(arr, ind - 1, T, dp);

        long taken = 0;
        if (arr[ind] <= T)
            taken = coinChangeRecursionHelper(arr, ind, T - arr[ind], dp);

        return dp[ind][T] = notTaken + taken;
    }

    static long coinChangeDP(int[] arr, int n, int T) {
        long dp[][] = new long[n][T + 1];

        //Initializing base condition
        for (int i = 0; i <= T; i++) {
            if (i % arr[0] == 0)
                dp[0][i] = 1;
            // Else condition is automatically fulfilled,
            // as dp array is initialized to zero
        }

        for (int ind = 1; ind < n; ind++) {
            for (int target = 0; target <= T; target++) {
                long notTaken = dp[ind - 1][target];

                long taken = 0;
                if (arr[ind] <= target)
                    taken = dp[ind][target - arr[ind]];

                dp[ind][target] = notTaken + taken;
            }
        }
        return dp[n - 1][T];
    }

    static long coinChangeDPOptiomized(int[] arr, int n, int T) {
        long[] prev = new long[T + 1];
        //Initializing base condition
        for (int i = 0; i <= T; i++) {
            if (i % arr[0] == 0)
                prev[i] = 1;
            // Else condition is automatically fulfilled,
            // as prev array is initialized to zero
        }

        for (int ind = 1; ind < n; ind++) {
            long cur[] = new long[T + 1];
            for (int target = 0; target <= T; target++) {
                long notTaken = prev[target];

                long taken = 0;
                if (arr[ind] <= target)
                    taken = cur[target - arr[ind]];

                cur[target] = notTaken + taken;
            }
            prev = cur;
        }
        return prev[T];
    }

    private static int knapsackUnBoundedRecursion(int[] wt, int[] val, int n, int W) {
        int dp[][] = new int[n][W + 1];
        for (int row[] : dp)
            Arrays.fill(row, -1);
        return knapsackUnBoundedRecursionHelper(wt, val, n - 1, W, dp);
    }

    private static int knapsackUnBoundedRecursionHelper(int[] wt, int[] val, int n, int w, int[][] dp) {
        if (n == 0) {
            return ((int) (w / wt[0])) * val[0];
        }

        if (dp[n][w] != -1) return dp[n][w];

        int notPick = 0 + knapsackUnBoundedRecursionHelper(wt, val, n - 1, w, dp);

        int pickUP = Integer.MIN_VALUE;
        if (wt[n] <= w) pickUP = val[n] + knapsackUnBoundedRecursionHelper(wt, val, n, w - wt[n], dp);

        return dp[n][w] = Math.max(notPick, pickUP);
    }

    private static int knapsackUnBoundedDP(int[] wt, int[] val, int W) {
        int n = val.length;
        int[][] dp = new int[n][W + 1];

        for (int weight = wt[0]; weight <= W; weight++) {
            dp[0][weight] = ((int) (weight / wt[0])) * val[0];
        }

        for (int ind = 1; ind < n; ind++) {
            for (int weight = 0; weight <= W; weight++) {
                int notPick = 0 + dp[ind - 1][weight];

                int pickUP = Integer.MIN_VALUE;
                if (wt[ind] <= weight) pickUP = val[ind] + dp[ind][weight - wt[ind]];

                dp[ind][weight] = Math.max(notPick, pickUP);
            }
        }
        return dp[n - 1][W];
    }

    private static int cutRod(int[] price, int N) {
        int dp[][] = new int[price.length][N + 1];
        for (int[] tmp : dp)
            Arrays.fill(tmp, -1);

        return cutRodRecursion(price, N - 1, N, dp);
    }

    private static int cutRodRecursion(int[] price, int index, int N, int[][] dp) {
        if (index == 0) {
            return N * price[0];
        }
        if (dp[index][N] != -1) return dp[index][N];

        int notTake = 0 + cutRodRecursion(price, index - 1, N, dp);
        int take = Integer.MIN_VALUE;
        int rodLength = index + 1;
        if (rodLength <= N) take = price[index] + cutRodRecursion(price, index, N - rodLength, dp);

        return dp[index][N] = Math.max(take, notTake);
    }

    private static int cutRodDP(int[] price, int N) {
        int[][] dp = new int[price.length][N + 1];

        for (int row[] : dp)
            Arrays.fill(row, -1);

        for (int i = 0; i <= N; i++) {
            dp[0][i] = i * price[0];
        }

        for (int ind = 1; ind < N; ind++) {
            for (int length = 0; length <= N; length++) {
                int notTake = 0 + dp[ind - 1][length];
                int take = Integer.MIN_VALUE;
                int rodLength = ind + 1;
                if (rodLength <= length) take = price[ind] + dp[ind][length - rodLength];

                dp[ind][length] = Math.max(take, notTake);
            }
        }
        return dp[N - 1][N];
    }

    private static int cutRodDPSpaceOptiomized(int[] price, int N) {
        int[] current = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            current[i] = i * price[0];
        }

        for (int ind = 1; ind < N; ind++) {
            for (int length = 0; length <= N; length++) {
                int notTake = 0 + current[length];
                int take = Integer.MIN_VALUE;
                int rodLength = ind + 1;
                if (rodLength <= length) take = price[ind] + current[length - rodLength];

                current[length] = Math.max(take, notTake);
            }
        }
        return current[N];
    }


    public static void main(String[] args) {
        System.out.println("============================= Frog Jump - recursion/Memoize ==============================");
        int height[] = {30, 10, 60, 10, 60, 50};
        int n = height.length;
        int dp[] = new int[n];
        Arrays.fill(dp, -1);
        System.out.println("Frog jump with minimum energy with Recursion is : " + frogJumpRecursionAndMemoization(n - 1, height, dp));

        System.out.println("=================================== Frog Jump - DP =======================================");
        System.out.println("Frog jump with minimum energy with DP is : " + frogJumpDP(height));

        System.out.println("======================== Frog Jump - DP/Space Optimized ==================================");
        System.out.println("Frog jump with minimum energy with DP is : " + frogJumpDPAndSpaceOptimized(height, dp));

        System.out.println("======================== Frog K Jump - recursion/Memoize =================================");
        System.out.println("Frog K jump with minimum energy with Recursion is : " + frogJumpKJumpsRecursionAndMemoization(n, height, 4));

        System.out.println("======================== Frog K Jump - DP/Space Optimized ================================");
        System.out.println("Frog K jump with minimum energy with DP is : " + frogJumpKJumpsDP(n, height, 2));

        System.out.println("============= Maximum sum of non-adjacent elements - recursion/Memoize ===================");
        int arr[] = {2, 1, 4, 9};
        n = arr.length;
        System.out.println("Maximum sum of non-adjacent elements with Recursion/Memoization is : " + maximumsumOfNonAdjacentElements(n, arr));

        System.out.println("======================== Maximum sum of non-adjacent elements - DP =======================");
        System.out.println("Maximum sum of non-adjacent elements with DP is : " + maximumsumOfNonAdjacentElementsDPHelper(arr));

        System.out.println("=============================== House Robber - recursion/Memoize =========================");
        ArrayList<Integer> arrLst = new ArrayList<>();
        arrLst.add(1);
        arrLst.add(5);
        arrLst.add(1);
        arrLst.add(2);
        arrLst.add(6);
        n = arrLst.size();
        System.out.println("Maximum amount of money that the thief can rob without alerting the police is: " + robStreet(n, arrLst));

        System.out.println("======================== Ninja Training - recursion/Memoize ==============================");
        int[][] points = new int[][]{{94, 74, 84}, {71, 4, 68}, {70, 12, 17}, {7, 84, 58}, {59, 69, 2}, {57, 21, 62}, {74, 54, 15}, {15, 83, 49}, {97, 70, 90}, {8, 71, 42}};
        //Output : 762
        int ninjaMerit = ninjaTraining(10, points);
        System.out.println("Ninja Merit : Recursive: " + ninjaMerit);

        System.out.println("=============================== Ninja Training - DP ======================================");
        ninjaMerit = ninjaTrainingDP(10, points);
        System.out.println("Ninja Merit : DP: " + ninjaMerit);

        System.out.println("============================ Unique Paths - Recursive ====================================");
        //4 4
        //3 5
        //1 6
        //5 4
        //3 9
        //Output
        //20
        //15
        //1
        //35
        //45
        int paths = uniquePaths(3, 2);
        System.out.println("Unique Paths for given array with Recursion solution is  " + paths);

        System.out.println("================================== Unique Paths - DP =====================================");

        paths = uniquePathsDP(3, 2);
        System.out.println("Unique Paths for given array with DP solution is : " + paths);

        System.out.println("============================ Min jump /GFG - Jumping on Index ============================");

        arr = new int[]{1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9};
        // calling minJumps method
        System.out.println(minJumps(arr));

        System.out.println("=============================== Minimum jumps : Recursive ================================");

        int[] shops = new int[]{5, 9, 6, 5, 0, 5, 7, 4, 10, 7};
        int jumpsCount = minimumJumps(shops, 10);
        System.out.println("Total jumps require to reach last shop with Recursion solution is: " + jumpsCount);

        System.out.println("=================================== Minimum jumps : DP ===================================");
        jumpsCount = minimumJumpsDP(shops, 10);
        System.out.println("Total jumps require to reach last shop with DP solution is: " + jumpsCount);

        System.out.println("===================== Minimum Path Sum : Recursive/Memoization ===========================");

        int[][] jumps = {{8, 1, 6}, {4, 4, 16}, {2, 7, 20}, {20, 7, 20}};
        int totalJump = minSumPath(jumps);
        System.out.println("Minimum jumps required with Recursive/Memoization solution are: " + totalJump);

        System.out.println("================================= Minimum Path Sum : DP ==================================");
        totalJump = minSumPathDP(jumps.length, jumps[0].length, jumps);
        System.out.println("Minimum jumps required with Dp solution are : " + totalJump);

        System.out.println("============= Minimum path sum in Triangular Grid : Recursion/Memoization ================");
        int triangle[][] = {{1},
                {2, 3},
                {12, 6, 7},
                {8, 9, 6, 10}};
        n = triangle.length;
        System.out.println("Minimum path sum in Triangular Grid : Recursion/Memoization is : " + minimumPathTriangleSumRecursive(triangle, n));

        System.out.println("===================== Minimum path sum in Triangular Grid : DP ===========================");
        System.out.println("Minimum path sum in Triangular Grid : DP is : " + minimumPathTriangleSumDP(triangle, n));

        System.out.println("================== Maximum Falling path sum : Recursion/Memoization ======================");
        int matrix[][] = {{1, 2, 10, 4},
                {100, 3, 2, 1},
                {1, 1, 20, 2},
                {1, 2, 2, 1}};
        System.out.println("Maximum Falling path sum : Recursion/Memoization is: " + getMaxFallingPathSumRecursive(matrix));

        System.out.println("=========================== Maximum Falling path sum : DP ================================");
        System.out.println("Maximum Falling path sum : DP is: " + getMaxFallingPathSumDP(matrix));

        System.out.println("======= Cherry Pickup/Max Chocolate for Alice and Bob : Recursion/Memoization ============");
        int mat[][] = {{2, 3, 1, 2},
                       {3, 4, 2, 2},
                       {5, 6, 3, 5}};
        n = mat.length;
        int m = mat[0].length;

        System.out.println("Maximum Chocolate : Recursion/Memoization is: " + maxChocolate(n, m, mat));

        System.out.println("Maximum Chocolate : DP is: " + maximumChocolatesDP(n, m, mat));

        System.out.println("=================== Subset sum equal to target / Recursion-Memoization ===================");
        int subsetArr[] = {1, 2, 4};
        int target = 4;
        n = subsetArr.length;

        if (SubsetSumEqualToTargetRecursion(n, target, subsetArr))
            System.out.println("Subset with given target : " + target + " found in Array : " + Arrays.toString(subsetArr));
        else
            System.out.println("Subset with given target : " + target + " not found in Array : " + Arrays.toString(subsetArr));

        System.out.println("======================== Subset sum equal to target / DP =================================");
        if (SubsetSumEqualToTargetDP(n, target, subsetArr))
            System.out.println("Subset with given target : " + target + " found in Array : " + Arrays.toString(subsetArr));
        else
            System.out.println("Subset with given target : " + target + " not found in Array : " + Arrays.toString(subsetArr));

        System.out.println("============================= Partition Equal Subset Sum =================================");
        subsetArr = new int[]{2, 3, 3, 3, 4, 5};
        n = subsetArr.length;

        if (canPartition(n, subsetArr))
            System.out.println("The Array can be partitioned into two equal subsets");
        else
            System.out.println("The Array cannot be partitioned into two equal subsets");

        System.out.println("============= Counts Subsets with Sum K / Recursion/Memoization ==========================");

        subsetArr = new int[]{1, 2, 3};
        int sum = 3;
        System.out.println("Counts Subsets with Sum K :  Recursion/Memoization is: " + countsSubsetsWithSumKRecursion(subsetArr, sum));

        System.out.println("Counts Subsets with Sum K :  DP is: " + countsSubsetsWithSumKDP(sum, subsetArr));

        System.out.println("======================= Knapsack 0 - 1 / Recursion/Memoization ==========================");

        int wt[] = {1, 2, 4, 5};
        int val[] = {5, 4, 8, 6};
        int W = 5;
        n = wt.length;

        System.out.println("The Maximum value of items, thief can steal with recursion is " + knapsackRecursion(wt, val, n, W));

        System.out.println("======================= Knapsack 0 - 1 / Dp ==========================");

        System.out.println("The Maximum value of items, thief can steal with DP is :" + knapsackDP(wt, val, n, W));

        System.out.println("=========================== Coin Change / Recursion/Memoization ==========================");

        int coinArr[] = {1,2,3};
        target = 5; //Output 5

        n = coinArr.length;
        System.out.println("The total number of ways to change coin with recursion is : " + coinChangeRecursion(coinArr, n, target));

        System.out.println("=========================== Coin Change / DP ==========================");

        System.out.println("The total number of ways to change coin with DP is :" + coinChangeDP(coinArr, n, target));

        System.out.println("=================== Un Bounded Knapsack / Recursion/Memoization ==========================");

        wt = new int[]{2, 4, 6};
        val = new int[]{5, 11, 13};
        W = 10; //Output 27

        n = wt.length;

        System.out.println("The Maximum value of items, thief can steal is:  " + knapsackUnBoundedRecursion(wt, val, n, W));

        System.out.println("=================== Un Bounded Knapsack / DP ==========================");

        System.out.println("The Maximum value of items, thief can steal is:  " + knapsackUnBoundedDP(wt, val, W));

        System.out.println("========================= Rod Cutting / Recursion-Memoization ============================");

        int price[] = {2, 5, 7, 8, 10};
        n = price.length;

        System.out.println("The Maximum price generated by cutting rod with Recursion is " + cutRod(price, n));

        System.out.println("================================ Rod Cutting / DP =======================================");

        System.out.println("The Maximum price generated by cutting rod with DP is " + cutRodDP(price, n));

        System.out.println("======================= Rod Cutting /  - Space Optimized =================================");

        System.out.println("The Maximum price generated by cutting rod with DP Space Optimized is " + cutRodDPSpaceOptiomized(price, n));


    }
}