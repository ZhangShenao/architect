package william.leetcode;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/15 10:05
 * @Description:https://leetcode.com/problems/count-primes/
 */
public class Solution204 {
    public static void main(String[] args) {
        Solution204 s = new Solution204();
        System.err.println(s.countPrimes(10));
    }

    public int countPrimes(int n) {
        boolean[] isPrime = new boolean[n];
        for (int i = 2; i < n; i++) {
            isPrime[i] = true;
        }
        for (int i = 2; i * i < n; i++) {
            if (!isPrime[i]) continue;
            for (int j = i * i; j < n; j += i) {
                isPrime[j] = false;
            }
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (isPrime[i])
                count++;
        }
        return count;
    }



}
