class Node<Item> {
    private Item data;
    private Node<Item>  nextNode, prevNode;

    Node(Item data, Node<Item> prev, Node<Item> next){
        this.data = data;
        this.prevNode = prev;
        this.nextNode = next;
    }

    public Item getData() {
        return data;
    }

    public void setData(Item data) {
        this.data = data;
    }

    public Node<Item> getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node<Item> nextNode) {
        this.nextNode = nextNode;
    }

    public Node<Item> getPrevNode() {
        return prevNode;
    }

    public void setPrevNode(Node<Item> prevNode) {
        this.prevNode = prevNode;
    }
}
