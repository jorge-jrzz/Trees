package binarysearchtree;

public class Node<T extends Comparable<T>> {

    private T element;
    private Node<T> parent;
    private Node<T> right;
    private Node<T> left;

    public Node() {
    }

    public Node(T element) {
        this.element = element;
    }

    public Node(T element, Node<T> parent, Node<T> right, Node<T> left) {
        this.element = element;
        this.parent = parent;
        this.right = right;
        this.left = left;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public Node<T> getParent() {
        return parent;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

}
