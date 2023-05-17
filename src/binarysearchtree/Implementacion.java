package binarysearchtree;

public class Implementacion {
    public static void main(String[] args) {

        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        tree.add_iterativo(8);
        tree.add_iterativo(4);
        tree.add(tree.getRoot(), 10);
        Node<Integer> t = tree.add(tree.getRoot(), 12);
        tree.add(tree.getRoot(), 15);
        tree.add(tree.getRoot(), 20);
        tree.add_iterativo(1);

        // System.out.println("----Pre Orden----");
        // tree.preorden(tree.getRoot());
        // System.out.println("----In Orden----");
        // tree.inorden(tree.getRoot());
        System.out.println("----Pos Orden----");
        tree.posorden(tree.getRoot());

        // System.out.println(tree.heigth(tree.getRoot()));
        // System.out.println(tree.depth(t));

        tree.remove(t);
        System.out.println("----Pos Orden----");
        tree.posorden(tree.getRoot());

    }

}
