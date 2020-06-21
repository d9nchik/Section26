package com.d9nich.AVL;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>();
        for (int i = 0; i < 100; i++)
            tree.add(i);
        tree.forEach(e -> {
            if (tree.isLeaf(e))
                System.out.println(tree.getPath(e));
        });
    }
}
