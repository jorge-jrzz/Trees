package binarysearchtree;

public class BinarySearchTree<T extends Comparable<T>> {

    private Node<T> root;

    public boolean isEmpty() {
        return root == null;
    }

    public Node<T> getRoot() {
        return root;
    }

    public boolean isRoot(Node<T> nodo) {
        return root == nodo;
    }

    public boolean isLeaf(Node<T> nodo) {
        return nodo.getLeft() == null && nodo.getRight() == null;
    }

    public boolean isInternal(Node<T> nodo) {
        return !isLeaf(nodo);
    }

    // Recursivo
    public Node<T> add(Node<T> origen, T element) {
        Node<T> nodo = null;

        if (isEmpty()) {
            nodo = new Node<>(element);
            root = nodo;
        } else if (origen == null) {
            System.out.println("El origen es null");
        } else {

            // Comparamos los elementos
            // Si el nodo del origen es mayor que el elemento pasado, pasa a la izquierda
            if (origen.getElement().compareTo(element) > 0) {

                // Si tiene nodo izquierdo, hago la llamada recursiva
                if (origen.getLeft() != null) {
                    nodo = add(origen.getLeft(), element);
                } else {

                    // Creo el nodo
                    nodo = new Node<>(element);
                    // Indico que el padre del nodo creado
                    nodo.setParent(origen);
                    // Indico que el nodo es el nodo izquierdo del origen
                    origen.setLeft(nodo);
                }
            } else { // Sino pasa a la derecha

                // Si tiene nodo derecho, hago la llamada recursiva
                if (origen.getRight() != null) {
                    nodo = add(origen.getRight(), element);
                } else {

                    // Creo el nodo
                    nodo = new Node<>(element);
                    // Indico que el padre del nodo creado
                    nodo.setParent(origen);
                    // Indico que el nodo es el nodo izquierdo del origen
                    origen.setRight(nodo);
                }
            }
        }
        return nodo;
    }

    public Node<T> add_iterativo(T elemento) {

        Node<T> nodo = null;
        // Si el root es nulo, lo añade el primero
        if (root == null) {
            nodo = new Node<>(elemento);
            root = nodo;
        } else {

            // Creo un nodo auxuliar
            Node<T> aux = root;
            boolean insertado = false;
            // No salgo hasta que este insertado
            while (!insertado) {

                // Comparamos los elementos
                // Si el nodo del origen es mayor que el elemento pasado, pasa a la izquierda
                if (aux.getElement().compareTo(elemento) > 0) {

                    // Si tiene nodo izquierdo, actualizo el aux
                    if (aux.getLeft() != null) {
                        aux = aux.getLeft();
                    } else {
                        // Creo el nodo
                        nodo = new Node<>(elemento);
                        // Indico que el padre del nodo creado
                        nodo.setParent(aux);
                        aux.setLeft(nodo);
                        // indico que lo he insertado
                        insertado = true;
                    }

                } else {

                    if (aux.getRight() != null) {
                        aux = aux.getRight();
                    } else {
                        // Creo el nodo
                        nodo = new Node<>(elemento);
                        // Indico que el padre del nodo creado
                        nodo.setParent(aux);
                        aux.setRight(nodo);
                        // indico que lo he insertado
                        insertado = true;
                    }

                }

            }

        }
        return nodo;
    }

    public void preorden(Node<T> nodo) {

        // Muestro el primer nodo, la raíz en la primera iteración
        System.out.println(nodo.getElement().toString());

        // Recorre todas las raices de la izquierda
        if (nodo.getLeft() != null) {
            preorden(nodo.getLeft());
        }

        // Recorre todas las raices de la derecha
        if (nodo.getRight() != null) {
            preorden(nodo.getRight());
        }
    }

    public void inorden(Node<T> nodo) {

        // Priemro se asegura que se encuentre en la hoja del extremo izquierdo
        if (nodo.getLeft() != null) {
            inorden(nodo.getLeft());
        }

        System.out.println(nodo.getElement().toString());

        if (nodo.getRight() != null) {
            inorden(nodo.getRight());
        }
    }

    // La raíz siempre es el ultimo en mostrarse
    public void posorden(Node<T> nodo) {

        if (nodo.getLeft() != null) {
            posorden(nodo.getLeft());
        }

        if (nodo.getRight() != null) {
            posorden(nodo.getRight());
        }

        System.out.println(nodo.getElement().toString());
    }

}