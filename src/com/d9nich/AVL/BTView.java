package com.d9nich.AVL;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class BTView extends Pane {
    private final double vGap = 50; // Gap between two levels in a tree
    private final AVLTree<Integer> tree;

    public BTView(AVLTree<Integer> tree) {
        this.tree = tree;
        setStatus("Tree is empty");
    }

    public void setStatus(String msg) {
        getChildren().add(new Text(20, 20, msg));
    }

    public void displayTree() {
        this.getChildren().clear(); // Clear the pane
        if (tree.getRoot() != null) {
            // Display tree recursively
            displayTree((AVLTree.AVLTreeNode<Integer>) tree.getRoot(), getWidth() / 2, vGap,
                    getWidth() / 4);
        }
    }

    /**
     * Display a subtree rooted at position (x, y)
     */
    private void displayTree(AVLTree.AVLTreeNode<Integer> root,
                             double x, double y, double hGap) {
        if (root.left != null) {
            // Draw a line to the left node
            getChildren().add(new Line(x - hGap, y + vGap, x, y));
            // Draw the left subtree recursively
            displayTree((AVLTree.AVLTreeNode<Integer>) root.left, x - hGap, y + vGap, hGap / 2);
        }

        if (root.right != null) {
            // Draw a line to the right node
            getChildren().add(new Line(x + hGap, y + vGap, x, y));
            // Draw the right subtree recursively
            displayTree((AVLTree.AVLTreeNode<Integer>) root.right, x + hGap, y + vGap, hGap / 2);
        }

        // Display a node
        // Tree node radius
        final double radius = 15;
        Circle circle = new Circle(x, y, radius);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        getChildren().addAll(circle,
                new Text(x - 4, y + 4, root.element + ""), new Text(x + 20, y + 4, balanceFactor(root) + ""));
    }

    /**
     * Return the balance factor of the node
     */
    private int balanceFactor(AVLTree.AVLTreeNode<Integer> node) {
        if (node.right == null) // node has no right subtree
            return -node.height;
        else if (node.left == null) // node has no left subtree
            return +node.height;
        else
            return ((AVLTree.AVLTreeNode<Integer>) node.right).height -
                    ((AVLTree.AVLTreeNode<Integer>) node.left).height;
    }
}

