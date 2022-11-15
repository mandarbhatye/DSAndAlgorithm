package Amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Graph_Practise {

    public class sample{
        private int data1;
        private int data2;
        private String strData;

        public sample(int data1, int data2, String strData) {
            this.data1 = data1;
            this.data2 = data2;
            this.strData = strData;
        }

        public int getData1() {
            return data1;
        }

        public void setData1(int data1) {
            this.data1 = data1;
        }

        public int getData2() {
            return data2;
        }

        public void setData2(int data2) {
            this.data2 = data2;
        }

        public String getStrData() {
            return strData;
        }

        public void setStrData(String strData) {
            this.strData = strData;
        }
    }
    public static void findMissingElements(){
        //Find one missing element
        int totalXOR = 0;
        int arrXOR  = 0;

        int[] num = {1,2,3,4,6,7,8,9,10};//Missing 5

        for(int i =1; i <= num.length +1; i++)
            totalXOR ^= i;

        for (int i = 0; i < num.length ; i++)
            arrXOR ^= num[i];

        System.out.println("Missing element is: " + (totalXOR ^ arrXOR));


        //Find two missing element
        num = new int[] {1,2,3,6,7,8,9,10}; // Missing 4,5

        int len = num.length +2;

        long totalSum = IntStream.rangeClosed(1,10).sum();
        long arrSum = Arrays.stream(num).sum();
        int pivot = (int) ((totalSum - arrSum)/2);

        int totalLeftXOR = 0;
        int totalRightXOR = 0;
        int arrLeftXOR = 0;
        int arrRightXOR = 0;

        for(int i=1; i<= pivot;i++) totalLeftXOR ^=i ;
        for(int i=pivot+1; i<= len;i++) totalRightXOR ^=i ;

        for(int i: num){
            if(i <= pivot) arrLeftXOR ^= i;
            else arrRightXOR ^=i;
        }

        int firstMissing = totalLeftXOR ^ arrLeftXOR;
        int secondMissing = totalRightXOR ^ arrRightXOR;
        System.out.println("Missing elements are : " + firstMissing + " , " + secondMissing);
    }



    public static void bitwiseComplement(){
        int n =10;
        int ans =0;
        int multi =1;
        while(n > 0){
            int currentBit = n&1;
            System.out.print(currentBit);
            //System.out.println((1-currentBit));
            n /= 2; //Right shifting which is dividing by 2
            //ans += multi * (1- currentBit);
            //multi <<=1; //Left shifting which is multiply by 2
        }
        //System.out.println("Complement is: " + ans);
    }

    public static void main(String[] args) {
        findMissingElements();

        //bitwiseComplement();
    }
}
