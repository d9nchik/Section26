package com.d9nich.AVL;

public class AVLTree<E extends Comparable<E>> extends BST<E> {
    /**
     * Create an empty AVL tree
     */
    public AVLTree() {
    }

    public AVLTree(E[] objects) {
        super(objects);
    }

    @Override
    /* Override createNewNode to create an AVLTreeNode */
    protected AVLTreeNode<E> createNewNode(E e) {
        return new AVLTreeNode<>(e);
    }

    @Override
    /* Insert an element and rebalance if necessary */
    public boolean insert(E e) {
        boolean successful = super.insert(e);
        if (!successful)
            return false; // e is already in the tree
        else {
            balancePath(e); // Balance from e to the root if necessary
        }

        return true; // e is inserted
    }

    /**
     * Update the height of a specified node
     */
    private void updateHeight(AVLTreeNode<E> node) {
        if (node.left == null && node.right == null) // node is a leaf
            node.height = 0;
        else if (node.left == null) // node has no left subtree
            node.height = 1 + ((AVLTreeNode<E>) (node.right)).height;
        else if (node.right == null) // node has no right subtree
            node.height = 1 + ((AVLTreeNode<E>) (node.left)).height;
        else
            node.height = 1 +
                    Math.max(((AVLTreeNode<E>) (node.right)).height,
                            ((AVLTreeNode<E>) (node.left)).height);
    }

    /**
     * Balance the nodes in the path from the specified
     * 43 * node to the root if necessary
     */
    private void balancePath(E e) {
        java.util.ArrayList<TreeNode<E>> path = path(e);
        for (int i = path.size() - 1; i >= 0; i--) {
            AVLTreeNode<E> A = (AVLTreeNode<E>) (path.get(i));
            updateHeight(A);

            switch (balanceFactor(A)) {
                case -2 -> {
                    if (balanceFactor((AVLTreeNode<E>) A.left) <= 0) {
                        balanceLL(A); // Perform LL rotation
                    } else {
                        balanceLR(A); // Perform LR rotation
                    }
                    return;
                }
                case +2 -> {
                    if (balanceFactor((AVLTreeNode<E>) A.right) >= 0) {
                        balanceRR(A); // Perform RR rotation
                    } else {
                        balanceRL(A); // Perform RL rotation
                    }
                    return;
                }
            }
        }
    }

    /**
     * Return the balance factor of the node
     */
    private int balanceFactor(AVLTreeNode<E> node) {
        if (node.right == null) // node has no right subtree
            return -node.height;
        else if (node.left == null) // node has no left subtree
            return +node.height;
        else
            return ((AVLTreeNode<E>) node.right).height -
                    ((AVLTreeNode<E>) node.left).height;
    }

    /**
     * Balance LL (see Figure 26.3)
     */
    private void balanceLL(TreeNode<E> A) {
        TreeNode<E> B = A.left; // A is left-heavy and B is left−heavy

        commonForLeafHeavy(A, B);

        A.left = B.right; // Make T2 the left subtree of A
        if (A.left != null)
            A.left.parent = A;
        B.right = A; // Make A the left child of B
        A.parent = B;
        updateHeight((AVLTreeNode<E>) A);
        updateHeight((AVLTreeNode<E>) B);
    }

    private void commonForLeafHeavy(TreeNode<E> A, TreeNode<E> B) {
        if (A == root) {
            root = B;
            B.parent = null;
        } else {
            if (A.parent.left == A) {
                A.parent.left = B;
            } else {
                A.parent.right = B;
            }
            B.parent = A.parent;
        }
    }

    /**
     * Balance LR (see Figure 26.5)
     */
    private void balanceLR(TreeNode<E> A) {
        TreeNode<E> B = A.left; // A is left−heavy
        TreeNode<E> C = B.right; // B is right−heavy

        rotateOfTriade(A, C);


        A.left = C.right; // Make T3 the left subtree of A
        if (A.left != null)
            A.left.parent = A;
        B.right = C.left; // Make T2 the right subtree of B
        if (B.right != null)
            B.right.parent = B;
        C.left = B;
        B.parent = C;
        C.right = A;
        A.parent = C;

        // Adjust heights
        updateHeight((AVLTreeNode<E>) A);
        updateHeight((AVLTreeNode<E>) B);
        updateHeight((AVLTreeNode<E>) C);
    }

    private void rotateOfTriade(TreeNode<E> A, TreeNode<E> C) {
        if (A == root) {
            root = C;
            C.parent = null;
        } else {
            if (A.parent.left == A) {
                A.parent.left = C;
            } else {
                A.parent.right = C;
            }
            C.parent = A.parent;
        }
    }

    /**
     * Balance RR (see Figure 26.4)
     */
    private void balanceRR(TreeNode<E> A) {
        TreeNode<E> B = A.right; // A is right-heavy and B is right-heavy

        commonForLeafHeavy(A, B);

        A.right = B.left; // Make T2 the right subtree of A
        if (A.right != null)
            A.right.parent = A;
        B.left = A;
        A.parent = B;
        updateHeight((AVLTreeNode<E>) A);
        updateHeight((AVLTreeNode<E>) B);
    }

    /**
     * Balance RL (see Figure 26.6)
     */
    private void balanceRL(TreeNode<E> A) {
        TreeNode<E> B = A.right; // A is right-heavy
        TreeNode<E> C = B.left; // B is left-heavy

        rotateOfTriade(A, C);

        A.right = C.left; // Make T2 the right subtree of A
        if (A.right != null)
            A.right.parent = A;
        B.left = C.right; // Make T3 the left subtree of B
        if (B.left != null)
            B.left.parent = B;
        C.left = A;
        A.parent = C;
        C.right = B;
        B.parent = C;
        // Adjust heights
        updateHeight((AVLTreeNode<E>) A);
        updateHeight((AVLTreeNode<E>) B);
        updateHeight((AVLTreeNode<E>) C);
    }

    @Override
    /* Delete an element from the AVL tree.
      Return true if the element is deleted successfully
      Return false if the element is not in the tree */
    public boolean delete(E element) {
        if (root == null)
            return false; // Element is not in the tree

        // Locate the node to be deleted
        TreeNode<E> current = root;
        while (current != null) {
            if (element.compareTo(current.element) < 0)
                current = current.left;
            else if (element.compareTo(current.element) > 0)
                current = current.right;
            else
                break; // Element is in the tree pointed by current
        }

        if (current == null)
            return false; // Element is not in the tree

        // Case 1: current has no left children (see Figure 25.10)
        if (current.left == null) {
            // Connect the parent with the right child of the current node
            if (current.parent == null) {
                root = current.right;
                if (root != null)
                    root.parent = null;
            } else {
                if (element.compareTo(current.parent.element) < 0)
                    current.parent.left = current.right;
                else
                    current.parent.right = current.right;
                if (current.right != null)
                    current.right.parent = current.parent;
                // Balance the tree if necessary
                balancePath(current.parent.element);
            }
        } else {
            // Case 2: The current node has a left child
            // Locate the rightmost node in the left subtree of
            // the current node and also its parent
            TreeNode<E> rightMost = current.left;

            while (rightMost.right != null) {
                rightMost = rightMost.right; // Keep going to the right
            }// Replace the element in current by the element in rightMost
            current.element = rightMost.element;

            // Eliminate rightmost node
            if (rightMost.parent.right == rightMost) {
                rightMost.parent.right = rightMost.left;
            } else {
                // Special case: parentOfRightMost is current
                rightMost.parent.left = rightMost.left;
            }
            if (rightMost.left != null)
                rightMost.left.parent = rightMost.parent;

            // Balance the tree if necessary
            balancePath(rightMost.parent.element);
        }

        size--;
        return true; // Element inserted
    }

    /**
     * AVLTreeNode is TreeNode plus height
     */
    protected static class AVLTreeNode<E> extends TreeNode<E> {
        protected int height = 0; // New data field

        public AVLTreeNode(E e) {
            super(e);
        }
    }
}
