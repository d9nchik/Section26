package com.d9nich;

import com.d9nich.AVL.AVLTree;

import java.util.Scanner;

public class Exercise26 {
    public static void main(String[] args) {
        AVLTree<Double> tree = new AVLTree<>();
        Scanner input = new Scanner(System.in);
        System.out.print("Enter 6 numbers: ");
        for (int i = 0; i < 6; i++) {
            tree.insert(input.nextDouble());
        }

        System.out.print("Enter k: ");
        int k = input.nextInt();
        System.out.println("The " + k + "th smallest number is "
                + tree.find(k));
    }
}
