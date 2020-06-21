package com.d9nich;

import com.d9nich.AVL.AVLTree;
import com.d9nich.AVL.BST;
import com.d9nich.AVL.Tree;

import java.util.Random;

public class TreeTest {
    public static void main(String[] args) {
        System.out.println("Binary tree:");
        test(new BST<>(), generateArray());
        System.out.println("AVL tree:");
        test(new AVLTree<>(), generateArray());
    }

    private static void test(Tree<Integer> tree, int[] array) {

        long startTime = System.currentTimeMillis();
        for (int number : array)
            tree.add(number);
        System.out.println("Time(in millis) to add all elements: " + (System.currentTimeMillis() - startTime));

        shuffle(array);
        startTime = System.currentTimeMillis();
        for (int number : array)
            tree.search(number);
        System.out.println("Time(in millis) to search all elements: " + (System.currentTimeMillis() - startTime));

        shuffle(array);
        startTime = System.currentTimeMillis();
        for (int number : array)
            tree.search(number);
        System.out.println("Time(in millis) to delete all elements: " + (System.currentTimeMillis() - startTime));
    }

    private static int[] generateArray() {
        final int ARRAY_SIZE = 600_000;
        int[] array = new int[ARRAY_SIZE];
        Random random = new Random();
        for (int i = 0; i < ARRAY_SIZE; i++)
            array[i] = random.nextInt();
        return array;
    }

    private static void shuffle(int[] array) {
        Random random = new Random();
        for (int i = 0; i < array.length / 2; i++) {
            int position = random.nextInt(array.length);
            int temp = array[position];
            array[position] = array[i];
            array[i] = temp;
        }
    }
}
