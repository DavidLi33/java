//Silver 2021 Jan 1 Dance Mooves

//For the first two subtasks, we can just simulate. NK minutes will suffice because the sequence of positions of an individual cow will start repeating within that time.
//
//Now, onto the full solution. After the first K swaps, we compute where each cow ends up. If a cow started at the i-th position, let its new position be pi. Let’s also track the set si of all positions that cow i reached during the K swaps. This does not take too much memory because the sum of the sizes of all sets si is bounded by 2K+N (every swap, at most two cows move, thus adding at most two elements to the sets).
//
//Let’s build a directed graph on the positions that shows how the cows move every K swaps. We have N directed edges from all i to pi (1≤i≤N). This graph is a bunch of cycles because after K swaps, there is exactly one cow in each position, so the outdegree and indegree of each node is one. Therefore, the graph is a bunch of disjoint cycles (as with Swapity Swapity Swap).
//
//We claim that the answers for all cows in the same cycle are the same. Because everything repeats every K swaps, if two cows have ever been in the same position after a multiple of K swaps, they would visit the same positions eventually. After K swaps, cow i goes to position pi, so the answers for cow i and cow pi are the same. This logic extends to every cow in the cycle (the answer for cow pi is equal to cow ppi and so on).
//
//So, what exactly is the answer for some cycle? It’s the size of the union of all the sets si for each cow i in the cycle. In other words, the answer is the number of unique positions in all the sets in the cycle. The complexity of this solution is O(N+K).
//		

/*
SAMPLE INPUT:
5 4
1 3
1 2
2 3
2 4
SAMPLE OUTPUT:
4
4
3
4
1
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class S202101_1_DanceMooves_SetList {
 
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(tokenizer.nextToken());
        int k = Integer.parseInt(tokenizer.nextToken());
        int[] cows = new int[n + 1];
        List<Integer>[] viewed = new List[n + 1];
        for (int j = 1; j <= n; j++) {
            cows[j] = j;
            viewed[j] = new ArrayList<>();
            viewed[j].add(j);
        }
        for (long t = 1; t <= k; t++) {
            tokenizer = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            int c = cows[a];
            int d = cows[b];
            cows[a] = d;
            cows[b] = c;
            viewed[cows[a]].add(a);
            viewed[cows[b]].add(b);
        }
        int[] answer = new int[n + 1];
        for (int r = 1; r <= n; r++) {
            if (cows[r] != 0) {
                List<Integer> cycle = new ArrayList<>();
                int j = r;
                while (cows[j] != 0) {
                    cycle.add(j);
                    j = cows[j];
                    cows[cycle.get(cycle.size() - 1)] = 0;
                }
                Set<Integer> viewedHere = new HashSet<>();
                for (int cow : cycle) {
                    viewedHere.addAll(viewed[cow]);
                }
                for (int cow : cycle) {
                    answer[cow] = viewedHere.size();
                }
            }
        }
        StringBuilder out = new StringBuilder();
        for (int j = 1; j <= n; j++) {
            out.append(answer[j]).append('\n');
        }
        System.out.print(out);
    }
}