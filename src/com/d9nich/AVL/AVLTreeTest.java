package com.d9nich.AVL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AVLTreeTest {

    @org.junit.jupiter.api.Test
    void createNewNode() {
        AVLTree<Integer> tree = new AVLTree<>();
        AVLTree.AVLTreeNode<Integer> node = tree.createNewNode(20);
        assertEquals(0, node.height);
        assertEquals(20, node.element);
    }

    @org.junit.jupiter.api.Test
    void insert() {
        AVLTree<Integer> avlTree = new AVLTree<>();
        avlTree.addAll(Arrays.asList(25, 20, 5));
        assertEquals(new LinkedList<>(Arrays.asList(5, 25, 20)), avlTree.postOrderList(), "check LL rotation");
        assertEquals(3, avlTree.size);

        avlTree.addAll(Arrays.asList(34, 50));
        assertEquals(new LinkedList<>(Arrays.asList(5, 25, 50, 34, 20)), avlTree.postOrderList(),
                "check RR rotation");
        assertEquals(5, avlTree.size);

        avlTree.add(30);
        assertEquals(new LinkedList<>(Arrays.asList(5, 20, 30, 50, 34, 25)), avlTree.postOrderList(),
                "check RL rotation");
        assertEquals(6, avlTree.size);

        avlTree.add(10);
        assertEquals(new LinkedList<>(Arrays.asList(5, 20, 10, 30, 50, 34, 25)), avlTree.postOrderList(),
                "check LR rotation");
        assertEquals(7, avlTree.size);

        assertThrows(NullPointerException.class, () -> avlTree.add(null));
    }

    @org.junit.jupiter.api.Test
    void delete() {
        AVLTree<Integer> avlTree = new AVLTree<>();
        avlTree.addAll(Arrays.asList(25, 10, 34, 5, 20, 30, 50));

        avlTree.removeAll(Arrays.asList(34, 30, 50));
        assertEquals(new LinkedList<>(Arrays.asList(5, 20, 25, 10)), avlTree.postOrderList(), "check LL rotation");
        assertEquals(4, avlTree.size);

        avlTree.remove(5);
        assertEquals(new LinkedList<>(Arrays.asList(10, 25, 20)), avlTree.postOrderList(), "check RL rotation");
        assertEquals(3, avlTree.size);

        avlTree.clear();
        avlTree.addAll(Arrays.asList(25, 10, 34, 5, 20, 30, 50));
        avlTree.removeAll(Arrays.asList(10, 20, 5));
        assertEquals(new LinkedList<>(Arrays.asList(30, 25, 50, 34)), avlTree.postOrderList(), "check RR rotation");
        assertEquals(4, avlTree.size);

        avlTree.remove(50);
        assertEquals(new LinkedList<>(Arrays.asList(25, 34, 30)), avlTree.postOrderList(), "check RL rotation");
        assertEquals(3, avlTree.size);

        avlTree.remove(-1);
        assertThrows(NullPointerException.class, () -> avlTree.remove(null));
    }
}