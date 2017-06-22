package de.dhbwka.java.exercise.collections;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by floriankling on 22.06.17.
 */
public class BinaryTree<T extends Comparable<T>> {

    T value;
    BinaryTree<T> left;
    BinaryTree<T> right;

    public BinaryTree() {
    }

    public BinaryTree(T value) {
        this.value = value;
    }

    public static void main(String[] args) {
        BinaryTree<Integer> myBinaryTree = new BinaryTree<>(7);
        myBinaryTree.add(0);
        myBinaryTree.add(12);
        myBinaryTree.add(5);
        myBinaryTree.add(9);
        myBinaryTree.add(3);
        myBinaryTree.add(8);
        myBinaryTree.add(2);
        myBinaryTree.add(13);
        myBinaryTree.add(10);
        myBinaryTree.add(15);
        myBinaryTree.add(1);

        System.out.println(myBinaryTree.traverse());
    }

    public boolean add(T newValue) {

        if (value == null)
            throw new RuntimeException("Geht nicht du Pisser");

        if (newValue.compareTo(value) > 0) {
            if (left == null) {
                left = new BinaryTree<T>(newValue);
            } else {
                left.add(newValue);
            }
            return true;
        }

        if (newValue.compareTo(value) < 0) {
            if (right == null) {
                right = new BinaryTree<T>(newValue);
            } else {
                right.add(newValue);
            }
            return true;
        }


        if (newValue.compareTo(value) == 0) {
            throw new RuntimeException("Gleicher WERT");
        }

        System.out.println("returning false \t" + newValue);
        return false;
    }

    public T getValue() {
        return value;
    }

    private boolean hasLeft(BinaryTree<T> tree) {
        return tree.left != null && tree.left.getValue() != null;
    }

    private boolean hasRight(BinaryTree<T> tree) {
        return tree.right != null && tree.right.getValue() != null;
    }

    public List<T> traverse() {
        List<T> traversedList = new ArrayList<>();

        BinaryTree<T> leftTree = left;
        while (hasLeft(leftTree)) {
            traversedList.add(leftTree.getValue());
            leftTree = leftTree.left;
        }

        traversedList.add(value);


        BinaryTree<T> rightTree = right;
        while (hasRight(rightTree)) {
            traversedList.add(rightTree.getValue());
            rightTree = rightTree.right;
        }

        return traversedList;
    }

}
