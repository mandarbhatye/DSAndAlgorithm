package Amazon;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class InterviewKickStart_Practice {

    static class StringEntry
    {
        int count = 1;
        String lexGreatest;
        StringEntry(String lexGreatest)
        {
            this.lexGreatest = lexGreatest;
        }
    }
    static String[] LexicographicalOrderProblem(String[] arr)
    {
        Map<String,StringEntry> map = new HashMap<>();
        for (String input : arr)
        {
            String[] pair = input.split(" ");
            String key = pair[0];
            String val = pair[1];

            if (!map.containsKey(key))
                map.put(key, new StringEntry(val)); // The new entry has count=1.
            else{
                StringEntry entry = map.get(key);
                entry.count++;
                if (val.compareTo(entry.lexGreatest) > 0)
                    entry.lexGreatest = val;
            }
        }

        String[] results = new String[map.size()];
        int i = 0;
        for (Map.Entry e: map.entrySet())
            results[i++] = e.getKey() + ":" + ((StringEntry)e.getValue()).count + "," + ((StringEntry)e.getValue()).lexGreatest;

        return results;
    }

    static String commonPrefix(String str[]){

        int minLen = Arrays.stream(str).min(Comparator.comparingInt(String::length)).get().length();

        String result = ""; // Our resultant string
        char current; // The current character

        for (int i = 0; i < minLen; i++)
        {
            current = str[0].charAt(i);
            for (int j = 1; j < str.length; j++)
            {
                if (str[j].charAt(i) != current) return result;
            }
            result += (current);
        }
        return (result);
    }



    public static void main(String[] args) {
        //Lexicographical Order Problem : https://www.interviewkickstart.com/problems/word-frequency
        String[] arr = {"key1 abcd",
                        "key2 zzz",
                        "key1 hello",
                        "key3 world",
                        "key1 hello"};
        String[] out = LexicographicalOrderProblem(arr);
        System.out.println("Lexicographical Order for given strings are: " + Arrays.toString(out));
        //["key1:3,hello",
        //"key2:1,zzz",
        //"key3:1,world"]


        String[] words = {"geeksforgeeks", "geeks", "geek", "geezer"}; //gee

        String ans = commonPrefix(words);

        if (ans.length() > 0) {
            System.out.println("The longest common prefix is " + ans);
        } else {
            System.out.println("There is no common prefix");
        }

    }
}
