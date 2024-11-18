package com.springcloud.ms.controller.algorithm.avltree;

/**
 * AVL Tree示例
 * @author: yaorp
 */
public class AVLTree<T extends Comparable<T>> {
    private AVLTreeNode<T> mRoot;

    class AVLTreeNode<T extends Comparable<T>> {
        T key;
        int height;

    }
}
