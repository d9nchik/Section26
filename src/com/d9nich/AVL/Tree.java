package com.d9nich.AVL;

import java.util.Collection;

public interface Tree<E> extends Collection<E> {
    /**
     * Return true if the element is in the tree
     */
    boolean search(E e);

    /**
     * Insert element e into the binary tree
     * Return true if the element is inserted successfully
     */

    boolean insert(E e);

    /**
     * Delete the specified element from the tree
     * Return true if the element is deleted successfully
     */
    boolean delete(E e);

    /**
     * Get the number of elements in the tree
     */
    int getSize();

    /**
     * Inorder traversal from the root
     */
    void inorder();

    /**
     * Postorder traversal from the root
     */
    void postorder();

    /* Preorder traversal from the root */
    void preorder();

    @Override
    /* Return true if the tree is empty */
    default boolean isEmpty() {
        return size() == 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    default boolean contains(Object e) {
        return search((E) e);
    }

    @Override
    default boolean add(E e) {
        return insert(e);
    }

    @Override
    @SuppressWarnings("unchecked")
    default boolean remove(Object e) {
        return delete((E) e);
    }

    @Override
    default int size() {
        return getSize();
    }

    @Override
    default boolean containsAll(Collection<?> c) {
        for (Object e : c)
            if (!contains(e))
                return false;
        return true;
    }

    @Override
    default boolean addAll(Collection<? extends E> c) {
        c.forEach(this::add);
        return true;
    }

    @Override
    default boolean removeAll(Collection<?> c) {
        c.forEach(this::remove);
        return true;
    }

    @Override
    default boolean retainAll(Collection<?> c) {
        forEach(e -> {
            if (!c.contains(e))
                remove(e);
        });
        return true;
    }

    @Override
    default Object[] toArray() {
        Object[] objects = new Object[size()];
        int i = 0;
        for (E e : this)
            objects[i++] = e;
        return objects;
    }

    @SuppressWarnings("unchecked")
    @Override
    default <T> T[] toArray(T[] array) {
        if (array.length < size())
            array = (T[]) new Object[size()];
        int i = 0;
        for (E e : this)
            array[i++] = (T) e;
        return array;
    }
}