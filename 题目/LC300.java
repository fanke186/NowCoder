package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

/**
 * @ClassName LC300
 * @Author Fanke
 * @Created 2021/3/4 10:20
 */

/*
* 最长递增子序列
* [2,3,7,101]是[10,9,2,5,3,7,101,18]的最长递增子序列
*/


public class LC300 {
    /*
     * 动态规划
     * dp[i]记录以nums[i]为结尾的LIS的长度，所求就是其中的max
     * 因为是递增，最大值肯定是nums[i]
     * dp[k] 就等于以[0,k-1]结尾且满足nums[k]>nums[i]的LIS的最大长度
     * */
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

    /*
    * 贪心 + 二分查找
    * 贪心：想要得到最长的上升子序列，贪心地想，
    *       每次添加到子序列的数字应当尽可能的小。
    * 维护一个数组d[i],存储的是所有长度为i的上升子序列中，最小的末尾元素值；
    * 维护len，用以记录当前最长上升子序列的长度；
    * 初始化：
    *       d[1]=nums[0], len=1;
    * 显然，数组d应当是递增的；
    * 更新策略：
    *       1.if nums[i] > d[len]，则len++; d[len]=nums[i]；
    *       2.if not，则在dp[1..len]中寻找满足d[k-1] < nums[i] < d[k]的下标k，更新d[k]=nums[i]；
    *           若找不到满足的，就说明长度为1~len的子序列中的末尾值都大于nums[i]，此时需要更新d[1]=nums[i]；
    *       （因为d数组单调，可以用二分法加速这个下标查找过程）
    * 最后len就是所求
    * */
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




