package com.datastructures.string;

public class RearrangeString {
    public String reorderSpaces(String text) {
        int spaces = 0;
        String[] arr = text.split("");
        int words = 0;
        for(int i = 0; i < arr.length; i++) {
            if(arr[i].equals(" ")){
                spaces++;
            }
        }
        arr = text.trim().split("\\s+");
        words = arr.length;
        if(words == 1 && spaces == 0)return text;
        int spaceInBetween = words == 1 ? spaces : spaces/(words - 1);
        int spacesDone = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            int j = 0;
            while(j < spaceInBetween && spacesDone < spaces) {
                sb.append(" ");
                j++;
                spacesDone++;
            }
        }
        while(spacesDone < spaces) {
            sb.append(" ");
            spacesDone++;
        }
        return sb.toString();
    }
}
