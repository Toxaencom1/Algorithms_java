class ColorTree {
    private Node root;

    static class  Node {
        int value;
        Node left;
        Node right;
        Color color;
        @Override
        public String toString(){
            return " - Node { " +
                    "value = " + value +
                    ", color = " + color +
                    " }\n" + " left: " + left + "\n" +" right: " + right;
        }
    }

    enum Color {
        BLACK,
        RED
    }

    public void insert(int value) {
        if (root == null) {
            root = new Node();
            root.value = value;
        } else {
            insert(root, value);
            root = balance(root);
        }
        root.color = Color.BLACK;

    }

    private void insert(Node node, int value) {
        if (node.value != value) {
            if (node.value < value) {
                if (node.right == null) {
                    node.right = new Node();
                    node.right.value = value;
                    node.right.color = Color.RED;
                } else {
                    insert(node.right, value);
                    node.right = balance(node.right);
                }
            } else {
                if (node.left == null) {
                    node.left = new Node();
                    node.left.value = value;
                    node.left.color = Color.RED;
                } else {
                    insert(node.left, value);
                    node.left = balance(node.left);
                }
            }
        }
    }

    private Node rightTurn(Node node) {
        Node leftChild = node.left;
        Node orphan = leftChild.right;
        leftChild.right = node;
        node.left = orphan;
        leftChild.color = node.color;
        node.color = Color.RED;
        return leftChild;
    }

    private Node leftTurn(Node node) {
        Node rightChild = node.right;
        Node orphan = rightChild.left;
        rightChild.left = node;
        node.right = orphan;
        rightChild.color = node.color;
        node.color = Color.RED;
        return rightChild;
    }

    private void colorSwap(Node node) {
        node.right.color = Color.BLACK;
        node.left.color = Color.BLACK;
        if (node != root) {
            node.color = Color.RED;
        }
    }

    private Node balance(Node node) {
        Node result = node;
        boolean flag;
        do {
            flag = false;

            if (result.right != null && result.right.color == Color.RED &&
                    (result.left == null || result.left.color == Color.BLACK)) {
                result = leftTurn(result);
                flag = true;
            }
            if (result.left != null && result.left.color == Color.RED &&
                    result.left.left != null && result.left.left.color == Color.RED) {
                result = rightTurn(result);
                flag = true;
            }
            if (result.left != null && result.left.color == Color.RED &&
                    result.right != null && result.right.color == Color.RED) {
                colorSwap(result);
                flag = true;
            }
        } while (flag);
        return result;
    }

    public Node find(int value) {
        return find(root, value);
    }

    private Node find(Node node, int value) {
        if (node == null) {
            return null;
        }
        if (node.value == value) {
            return node;
        }
        if (node.value < value) {
            return find(node.right, value);
        } else {
            return find(node.left, value);
        }
    }
    public String toString(){
        return "Color tree = " + root;
    }
}

