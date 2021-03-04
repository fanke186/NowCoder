package com.leetcode;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @ClassName LC354
 * @Author Fanke
 * @Created 2021/3/4 10:14
 */

/*
* 俄罗斯套娃嵌套信封
* */

/*
* 本质上是一个二维的"最大递增子序列"
* 将[w, h]按w升序排列，遇到w相同的，再按h降序排列，
* 得到序列，按h找一个最大递增子序列即为所求；
*
* 首先我们将所有的信封按照 ww 值第一关键字升序、hh 值第二关键字降序进行排序；
* 随后我们就可以忽略 ww 维度，求出 hh 维度的最长严格递增子序列，其长度即为答案。
* */
public class LC354 {
    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        // 将envelopes按宽度升序排列，如果宽度一样就按高度降序
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] == b[0] ? b[1] - a[1] : a[0] - b[0];
            }
        });

        int[] h = new int[n];
        for(int i = 0; i < n; i++) {
            h[i] = envelopes[i][1];
        }
        int res = lengthOfLIS(h);
        return res;
    }

    // LC300
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        // 初始化dp[i]为nums[i]独自成为LIS的长度
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int res = 1;
        for (int i = 1; i < n; i++) {
            for (int idx = 0; idx < i; idx++) {
                // 能将nums[i]添加到末尾的LIS-
                if (nums[i] > nums[idx]) {
                    // -中，里面的最大
                    dp[i] = Math.max(dp[i], dp[idx]+1);
                }
            }
            // 更新最大的长度值
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public int lengthOfLIS1(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] d = new int[n+1];
        int len = 1;
        d[1] = nums[0];
        for (int i = 1; i < n; ++i) {
            if (nums[i] > d[len]) { // len增加
                d[++len] = nums[i];
            } else { // len不变，二分查找位置进行更新
                int l = 1, r = len, pos = 0;
                while (l <= r) {
                    int mid = (l + r) >> 1;
                    if (d[mid] < nums[i]) {
                        pos = mid;
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                d[pos + 1] = nums[i];
            }
        }
        return len;
    }


}
