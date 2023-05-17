package binarysearchtree;

public class BinarySearchTree<T extends Comparable<T>> {

    private Node<T> root;
    private final int ONE_NODE_LEFT = 1;
    private final int ONE_NODE_RIGTH = 2;
    private final int TWO_NODES = 3;

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

    public int heigth(Node<T> nodo) {
        int heigth = 0;

        if (isInternal(nodo)) {

            if (nodo.getLeft() != null) {
                heigth = Math.max(heigth, heigth(nodo.getLeft()));
            }

            if (nodo.getRight() != null) {
                heigth = Math.max(heigth, heigth(nodo.getRight()));
            }

            heigth++;
        }

        return heigth;
    }

    public int depth(Node<T> nodo) {
        int depth = 0;

        if (nodo != getRoot()) {
            depth = 1 + depth(nodo.getParent());
        }

        return depth;
    }

    public void remove(Node<T> nodo) {

        if (isEmpty()) {

            System.out.println("No se puede eliminar, árbol vacio");

        } else if (isLeaf(nodo)) {

            removeLeaf(nodo);

        } else if (nodo.getRight() != null && nodo.getLeft() == null) {

            removeWhitChild(nodo, ONE_NODE_RIGTH);

        } else if (nodo.getRight() == null && nodo.getLeft() != null) {

            removeWhitChild(nodo, ONE_NODE_LEFT);

        } else {

            removeWhitChild(nodo, TWO_NODES);

        }
    }

    private void removeLeaf(Node<T> nodo) {
        if (isRoot(nodo)) {
            root = null;
        } else {
            Node<T> parent = nodo.getParent();

            if (parent.getLeft() == nodo) {
                parent.setLeft(null);
            }

            if (parent.getRight() == nodo) {
                parent.setRight(null);
            }

            nodo = null;
        }
    }

    private void removeWhitChild(Node<T> nodo, int typeNode) {

        Node<T> siguiente = null;

        switch (typeNode) {
            case ONE_NODE_LEFT:
                siguiente = nodo.getLeft(); // Asigna el hijo izquierdo como siguiente nodo a enlazar
                break;

            case ONE_NODE_RIGTH:
                siguiente = minSubTree(nodo.getRight()); // Busca el nodo más pequeño en el subárbol derecho y lo asigna
                                                         // como siguiente nodo a enlazar
                break;

            case TWO_NODES:
                siguiente = minSubTree(nodo.getRight()); // Busca el nodo más pequeño en el subárbol derecho y lo asigna
                                                         // como siguiente nodo a enlazar
                if (!isRoot(siguiente.getParent())) { // Si el padre de siguiente no es la raíz
                    nodo.getLeft().setParent(siguiente); // Establece siguiente como el padre del hijo izquierdo de nodo
                    nodo.getRight().setParent(siguiente); // Establece siguiente como el padre del hijo derecho de nodo

                    if (siguiente.getParent().getLeft() == siguiente) { // Si siguiente es el hijo izquierdo del padre
                        siguiente.getParent().setLeft(null); // Establece el hijo izquierdo del padre como null
                    } else if (siguiente.getParent().getRight() == siguiente) { // Si siguiente es el hijo derecho del
                                                                                // padre
                        siguiente.getParent().setRight(null); // Establece el hijo derecho del padre como null
                    }
                }
                break;

            default:
                break;
        }

        siguiente.setParent(nodo.getParent()); // Establece el padre de siguiente como el padre de nodo

        if (!isRoot(nodo)) { // Si nodo no es la raíz
            if (nodo.getParent().getLeft() == nodo) { // Si nodo es el hijo izquierdo de su padre
                nodo.getParent().setLeft(siguiente); // Establece siguiente como el nuevo hijo izquierdo del padre
            } else if (nodo.getParent().getRight() == nodo) { // Si nodo es el hijo derecho de su padre
                nodo.getParent().setRight(siguiente); // Establece siguiente como el nuevo hijo derecho del padre
            }
        } else {
            root = siguiente; // Si nodo es la raíz, establece siguiente como la nueva raíz del árbol
        }

        if (nodo.getRight() != null && nodo.getRight() != siguiente) { // Si nodo tiene un hijo derecho distinto de
                                                                       // siguiente
            siguiente.setRight(nodo.getRight()); // Establece el hijo derecho de nodo como el hijo derecho de siguiente
        }

        if (nodo.getLeft() != null && nodo.getLeft() != siguiente) { // Si nodo tiene un hijo izquierdo distinto de
                                                                     // siguiente
            siguiente.setLeft(nodo.getLeft()); // Establece el hijo izquierdo de nodo como el hijo izquierdo de
                                               // siguiente
        }

        nodo = null; // Se elimina la referencia al nodo original

    }

    private Node<T> minSubTree(Node<T> nodo) {
        if (nodo != null && nodo.getLeft() != null) {
            while (!isLeaf(nodo)) {
                nodo = minSubTree(nodo.getLeft());
            }
        }
        return nodo;
    }

}
