package Amazon;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class Practice_HashMapStackQueues {

    private static void findEmployees(Map<String, String> empMap){
        Map<String, List<String>> managerToEmpMap = new HashMap<>();
        String ceo ="";

        for (String emp : empMap.keySet()){
            String manager = empMap.get(emp);

            if(manager.equals(emp)){
                ceo = manager;
            }else {
                if (managerToEmpMap.containsKey(manager)) {
                    managerToEmpMap.get(manager).add(emp);
                } else {
                    List<String> empList = new ArrayList<>();
                    empList.add(emp);
                    managerToEmpMap.put(manager, empList);
                }
            }
        }

        Map<String, Integer> managerToEmpCount = new HashMap<>();
        getSize(managerToEmpMap,ceo,managerToEmpCount);

        managerToEmpCount.entrySet().stream().forEach(e -> System.out.print(e + " "));
        System.out.println();
    }

    private static int getSize(Map<String, List<String>> managerToEmpMap, String manager, Map<String, Integer> managerToEmpCount){
        if(!managerToEmpMap.containsKey(manager)){
            managerToEmpCount.put(manager,0);
            return 1;
        }

        int size =0;

        for(String employee: managerToEmpMap.get(manager)){
            int cs = getSize(managerToEmpMap,employee,managerToEmpCount);
            size += cs;
        }

        managerToEmpCount.put(manager, size);

        return size;
    }

    private static void constructItenary(HashMap<String, String> sourceToDestMap){
        Map<String, Boolean> potentialSource = new HashMap<>();

        for(String source : sourceToDestMap.keySet()){
            String destination = sourceToDestMap.get(source);

            potentialSource.put(destination,false);
            if(!potentialSource.containsKey(source)){
                potentialSource.put(source, true);
            }else{
                potentialSource.put(source, false);
            }
        }
        String src = "";

       Optional<String> str = potentialSource.entrySet().stream().filter(e-> e.getValue() == true).map(Map.Entry::getKey).collect(Collectors.toList()).stream().findFirst();
       src = str.orElse("");

       while(true){
          if(sourceToDestMap.containsKey(src)){
              System.out.print(src + " -> ");
              src = sourceToDestMap.get(src);
          }else{
              System.out.println(src + ".");
              break;
          }
       }
    }

    private static void previousSmallerElement(int[] arr){
         int n = arr.length;
         int[] prevSmallerElement = new int[n];
        Deque<Integer> st = new ArrayDeque<>();

        for (int i = 0; i < n ; i++) {
            while(!st.isEmpty() && arr[i] <= st.peek()){
                st.pop();
            }
            prevSmallerElement[i] = st.isEmpty()? -1: st.peek();
            st.push(arr[i]);
        }
        System.out.println("Previous Smaller Element array is : " + Arrays.toString(prevSmallerElement));
    }

    private static void nextsmallerElement(int[] arr){
        int n = arr.length;
        int[] nextSmallerElement = new int[n];
        Deque<Integer> st = new ArrayDeque<>();

        for (int i = n-1; i >=0 ; i--) {
            while(!st.isEmpty() && arr[i] <= st.peek()){
                st.pop();
            }
            nextSmallerElement[i] = st.isEmpty() ? -1: st.peek();
            st.push(arr[i]);
        }
        System.out.println("Next  Smaller Element array is : " + Arrays.toString(nextSmallerElement));
    }

    private static void nextGreaterElementToRight(int[] arr){
        int n = arr.length;
        int[] nge = new int[n];
        Deque<Integer> st = new ArrayDeque<>();

        for (int i = n-1; i >=0 ; i--) {
            while(!st.isEmpty() && arr[i] >= st.peek()){
                st.pop();
            }

            nge[i] = st.isEmpty() ? -1: st.peek();
            st.push(arr[i]);
        }
        System.out.println("Next  Greater Element to right array is : " + Arrays.toString(nge));
    }
    private static void largestHistogramArea(int[] hist){
        int n = hist.length;

        int[] rightSmallerElement = new int[n];
        int[] leftSmallerElement  = new int[n];

        Deque<Integer> st = new ArrayDeque<>();

        for (int i = n-1; i >=0 ; i--) {
            while (!st.isEmpty() && hist[i] <= hist[st.peek()]){
                st.pop();
            }

            rightSmallerElement[i] = st.isEmpty() ?  n: st.peek();
            st.push(i);
        }
        st.clear();

        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && hist[i] <= hist[st.peek()]){
                st.pop();
            }

            leftSmallerElement[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }

        int maxArea = Integer.MIN_VALUE;

        for (int i = 0; i < n ; i++) {
            int width = rightSmallerElement[i] - leftSmallerElement[i] -1;
            int area = width * hist[i];

            if(area > maxArea)
                maxArea = area;
        }
        System.out.println("Largest Histogram Area is : " + maxArea);
    }

    //Input: s = "14[ab]7[bc]"
    //Output: "ababababababababababababababbcbcbcbcbcbcbc"
    private static void decodeString(String encodedStr){
        Deque<Character> st = new ArrayDeque<>();

        for (int i = 0; i < encodedStr.length() ; i++) {
            char ch = encodedStr.charAt(i);
            List<Character> decodedString = new ArrayList<>();
            if(ch == ']'){
                while(!st.isEmpty()&& st.peek() != '['){
                    decodedString.add(st.pop());
                }
                st.pop(); //remove [
                //Now process digit i.e.14 from example
                int k=0;
                int base =1;

                while(!st.isEmpty() && Character.isDigit(st.peek())){
                    k = k + (st.pop() - '0') * base;
                    base *=10;
                }

                while (k >0){
                    for (int j = decodedString.size()-1; j >=0 ; j--) {
                        st.push(decodedString.get(j));
                    }
                    k--;
                }
            }else{
                st.push(ch);
            }
        }

        // get the result from stack
        char[] result = new char[st.size()];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = st.pop();
        }
        System.out.println("Decoded string from original : " + encodedStr + " is: " + new String(result));
    }
    public static void main(String[] args) {
        System.out.println("=============================== Manager To Employee count================================");
        HashMap<String, String> map = new HashMap<>();
        map.put("A", "C");
        map.put("B", "C");
        map.put("C", "F");
        map.put("D", "E");
        map.put("E", "F");
        map.put("F", "F");
        findEmployees(map);//A=0 B=0 C=2 D=0 E=1 F=3

        System.out.println("=============================== Build Flight Itenary ================================");
        HashMap<String, String> sourceToDestMap = new HashMap<>();

        sourceToDestMap.put("Chennai", "Bangalore");
        sourceToDestMap.put("Bombay", "Delhi");
        sourceToDestMap.put("Goa", "Chennai");
        sourceToDestMap.put("Delhi", "Goa");

        constructItenary(sourceToDestMap);//Bombay -> Delhi -> Goa -> Chennai -> Bangalore.

        System.out.println("============================== Previous Smaller Element ===================================");
        int[] nums = new int[]{4, 10, 5, 18, 3, 12, 7};
        //output {-1,4,4,5,-1,3,3}
        previousSmallerElement(nums);


        System.out.println("============================== Next Smaller Element ===================================");
        nums = new int[]{3, 10, 5, 1, 15, 10, 7, 6};
        //output {1,5,1,-1,10,7,6,-1}
        nextsmallerElement(nums);

        System.out.println("================================== Next Greater element ==================================");
        int[] arr = {2, 5, 9, 3, 1, 12, 6, 8, 7};  // 5,9,12,12,12,-1,8,-1,-1
        nextGreaterElementToRight(arr);

        System.out.println("================================== Histogram Area ====================================");
        int[] hist = {2,1,5,6,2,3};
        largestHistogramArea(hist); //Largest Histogram Area is : 10

        System.out.println("============================= Decode String 3[a]2[bc] ====================================");
        //Input: s = "3[a]2[bc]"
        //Output: "aaabcbc"
        //Example 2:
        //
        //Input: s = "3[a2[c]]"
        //Output: "accaccacc"
        //Example 3:
        //
        //Input: s = "2[abc]3[cd]ef"
        //Output: "abcabccdcdcdef"

        String s = "14[ab]7[bc]";
        decodeString(s);
    }
}
