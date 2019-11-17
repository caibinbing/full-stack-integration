package main.java.test;

import java.util.concurrent.locks.ReentrantLock;

public class TestLeetCode {
    public static void main(String[] args) {
        new ReentrantLock(false);
    }

    private static boolean isSymmetric(TreeNode root) {
        return false;
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
