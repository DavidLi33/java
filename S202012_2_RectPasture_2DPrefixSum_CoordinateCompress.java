//Silver 2020 Dec 2 Rectangular Pasture

//describes the process of mapping each value in a list to its index if that
//list was sorted. When we have values from a large range, but we only care about their relative order

/*
SAMPLE INPUT:
4
0 2
1 0
2 3
3 5
SAMPLE OUTPUT:
13
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
 
public class S202012_2_RectPasture_2DPrefixSum_CoordinateCompress {
    static int[][] sums;
 
    static int getSum(int fromX, int toX, int fromY, int toY) {
        return sums[toX][toY] - sums[fromX - 1][toY] - sums[toX][fromY - 1] + sums[fromX - 1][fromY - 1];
    }
 
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] xs = new int[n];
        int[] ys = new int[n];
        Integer[] cows = new Integer[n];
        for (int j = 0; j < n; j++) {
            xs[j] = in.nextInt();
            ys[j] = in.nextInt();
            //Coordinate Compression setup
            cows[j] = j;
        }
      //before the following process, cows -> 0,1,2,3; xs-> 0,1,2,3; ys-> 2,0,3,5
        Arrays.sort(cows, Comparator.comparingInt(j -> xs[j])); 
        //after sort, cows is still 0,1,2,3
        for (int x = 1; x <= n; x++) {
            xs[cows[x - 1]] = x;
        }
        Arrays.sort(cows, Comparator.comparingInt(j -> ys[j])); 
        //after sort, cows is 1,0,2,3
        for (int y = 1; y <= n; y++) {
            ys[cows[y - 1]] = y;
        }
         
        //after the above process, cows is 1,0,2,3; xs is 1,2,3,4; ys is 2,1,3,4
        
        sums = new int[n + 1][n + 1];
        for (int j = 0; j < n; j++) {
            sums[xs[j]][ys[j]]++;
        }
        for (int x = 0; x <= n; x++) {
            for (int y = 0; y <= n; y++) {
                if (x > 0) {
                    sums[x][y] += sums[x - 1][y];
                }
                if (y > 0) {
                    sums[x][y] += sums[x][y - 1];
                }
                if (x > 0 && y > 0) {
                    sums[x][y] -= sums[x - 1][y - 1];
                }
            }
        }
        long answer = n + 1;
        for (int j = 0; j < n; j++) {
            for (int k = j + 1; k < n; k++) {
                answer += getSum(Math.min(xs[j], xs[k]), Math.max(xs[j], xs[k]), 1, Math.min(ys[j], ys[k]))
                        * getSum(Math.min(xs[j], xs[k]), Math.max(xs[j], xs[k]), Math.max(ys[j], ys[k]), n);
            }
        }
        System.out.println(answer);
    }
}