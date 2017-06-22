package de.dhbwka.java.exercise.collections;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by floriankling on 22.06.17.
 */
public class BinaryTree<T> {

    T value;
    BinaryTree<T> left;
    BinaryTree<T> right;

    public boolean add(T newValue) {
        if (!(newValue instanceof Comparable)) {
            throw new RuntimeException("T muss vergleichbar sein");
        }

        if (value == null)
            throw new RuntimeException("Geht nicht du Pisser");

        if (((Comparable) newValue).compareTo(value) > 1) {
            left = new BinaryTree<T>();
            left.add(newValue);
            return true;
        }

        if (((Comparable) newValue).compareTo(value) < 1) {
            right = new BinaryTree<T>();
            right.add(newValue);
            return true;
        }
        return false;
    }

    public T getValue() {
        return value;
    }

    public List<T> traverse() {
        List<T> traversedList = new ArrayList<>();


        BinaryTree<T> leftTree = left;
        while (leftTree.left != null && leftTree.getValue() != null) {
            traversedList.add(leftTree.getValue());
            leftTree = leftTree.left;
        }

        leftTree = left;
        while (leftTree.left != null && leftTree.getValue() != null) {
            traversedList.add(leftTree.getValue());
            leftTree = leftTree.left;
        }
        traversedList.add(value);

        return traversedList;
    }

}
