//Silver 2021 Jan 2 NoTimeToPaint

//For a candidate range (a,b), it suffices to compute the minimum number of strokes for the prefix of length a−1 and suffix of length N−b independently and add them up. Now let's describe how to compute the minimum number of strokes for each prefix (suffixes are computed similarly).
//
//There are a few ways to accomplish this. Perhaps the easiest is to scan the input from left to right while maintaining a stack of "active brush strokes". Every time we see a higher color than the one on top of the stack, we push it onto the stack (so the stack will contain ascending colors from bottom to top). Every time we see a color c, we pop from the stack every color larger than c, since those brush strokes need to be ended for color c to be visible. The aggregate number of pushes onto the stack tells us the number of brush strokes required for each prefix. Here is Brian Dean's code that implements this idea, running in O(N+Q) time:

/*
SAMPLE INPUT:
8 2
ABBAABCB
3 6
1 4
SAMPLE OUTPUT:
4
3
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
 
public class S202101_2_NoTimeToPaint {
 
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(tokenizer.nextToken());
        int m = Integer.parseInt(tokenizer.nextToken());
        String colors = " " + in.readLine();
        int[] last = new int[26];
        int[] prefixes = new int[n + 1];
        for (int j = 1; j <= n; j++) {
            prefixes[j] = prefixes[j - 1];
            int letter = colors.charAt(j) - 'A';
            boolean isLeft = last[letter] == 0;
            for (int lighter = 0; lighter < letter; lighter++) {
                if (last[lighter] > last[letter]) {
                    isLeft = true;
                }
            }
            if (isLeft) {
                prefixes[j]++;
            }
            last[letter] = j;
        }
        Arrays.fill(last, n + 1);
        int[] suffixes = new int[n + 2];
        for (int j = n; j >= 1; j--) {
            suffixes[j] = suffixes[j + 1];
            int letter = colors.charAt(j) - 'A';
            boolean isRight = last[letter] == n + 1;
            for (int lighter = 0; lighter < letter; lighter++) {
                if (last[lighter] < last[letter]) {
                    isRight = true;
                }
            }
            if (isRight) {
                suffixes[j]++;
            }
            last[letter] = j;
        }
        StringBuilder out = new StringBuilder();
        for (int j = 1; j <= m; j++) {
            tokenizer = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            out.append(prefixes[a - 1] + suffixes[b + 1]).append('\n');
        }
        System.out.print(out);
    }
}
