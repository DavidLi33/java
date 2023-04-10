//2021 Jan Silver 3 Spaced Out

/*
SAMPLE INPUT:
4
3 3 1 1
1 1 3 1
3 3 1 1
1 1 3 3
SAMPLE OUTPUT:
22
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class S202101_3_SpacedOut {
 
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        long[][] grid = new long[n][n];
        for (int y = 0; y < n; y++) {
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());
            for (int x = 0; x < n; x++) {
                grid[y][x] = Long.parseLong(tokenizer.nextToken());
            }
        }
        long horizontalAnswer = 0;
        for (int y = 0; y < n; y++) {
            long[] sums = new long[2];
            for (int x = 0; x < n; x++) {
                sums[x % 2] += grid[y][x];
            }
            horizontalAnswer += Math.max(sums[0], sums[1]);
        }
        long verticalAnswer = 0;
        for (int x = 0; x < n; x++) {
            long[] sums = new long[2];
            for (int y = 0; y < n; y++) {
                sums[y % 2] += grid[y][x];
            }
            verticalAnswer += Math.max(sums[0], sums[1]);
        }
        System.out.println(Math.max(horizontalAnswer, verticalAnswer));
    }
}