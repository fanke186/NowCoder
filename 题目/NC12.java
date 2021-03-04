package com.nowcoder;

import java.util.Arrays;

/**
 * @ClassName Solution
 * @Author Fanke
 * @Created 2021/3/3 9:07
 */




/*
* 给出前序和中序序列，重构一棵二叉树；
* */
public class NC12 {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        int n = pre.length;
        if (n == 0) {
            return null;
        }
        // 根节点
        TreeNode root = new TreeNode(pre[0]);
        // 找到根节点在中序遍历的下标idx
        int idx = 0;
        for (int i = 0; i < n; i++) {
            if (in[i] == pre[0]) {
                idx = i;
                break;
            }
        }
        // 左右子树
        // Arrays.copyOfRange() 左闭右开
        root.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, idx+1), Arrays.copyOfRange(in, 0, idx));
        root.right = reConstructBinaryTree(Arrays.copyOfRange(pre, idx+1, n), Arrays.copyOfRange(in, idx+1, n));
        return root;
    }
}

