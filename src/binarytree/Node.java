package binarytree;

public class Node<T> {
    private T element;
    private Node<T> root;
    private Node<T> right;
    private Node<T> left;

    public Node() {
    }

    public Node(T element) {
        this.element = element;
    }

    public Node(T element, Node<T> root, Node<T> right, Node<T> left) {
        this.element = element;
        this.root = root;
        this.right = right;
        this.left = left;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public Node<T> getRoot() {
        return root;
    }

    public void setRoot(Node<T> root) {
        this.root = root;
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
