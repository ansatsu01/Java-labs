public class Tree <T extends Comparable>{
    Node root;
    Tree(){root = null;}
    public void add(T value){
        root = add(root,value);
    }
    public void delete(T value){root = delete(root, value);}
    public boolean find(T value){ return find(root, value);}
    public void goNodeLeftRight(){
        goNodeLeftRight(root);}
    public void goLeftRightNode(){
        goLeftRightNode(root);}
    public void goLeftNodeRight(){
        goLeftNodeRight(root);
    }

    private boolean find(Node curr, T value){
        if (curr == null){return false;}
        if (value.compareTo(curr.value) == 0){return true;}
        if (value.compareTo(curr.value) < 0) {
            return find(curr.left, value);
        } else{
            return find(curr.right, value);
        }
    }
    private Node delete(Node curr, T value){
        if(curr == null){return null;}
        if(value.compareTo(curr.value) < 0){
            curr.left =  delete(curr.left, value);
        } else if (value.compareTo(curr.value) > 0) {
            curr.right = delete(curr.right, value);
        }else{
            if (curr.left == null)
                return curr.right;
            else if (curr.right == null)
                return curr.left;
            curr.value = findMinimalValue(curr.right);
            curr.right = delete(curr.right, curr.value);
        }
        return curr;
    }
    private Node add(Node curr, T value) {
        if (curr == null) {
            return new Node(value);
        }
        if (value.compareTo(curr.value) < 0) {
            curr.left = add(curr.left, value);
        } else if (value.compareTo(curr.value) > 0) {
            curr.right = add(curr.right, value);
        } else {
            return curr;
        }
        return curr;
    }
    private void goNodeLeftRight(Node node){
        if(node == null){return;}
        System.out.println(node.value.toString());
        goNodeLeftRight(node.left);
        goNodeLeftRight(node.right);
    }
    private void goLeftRightNode(Node node){
        if(node == null){return;}
        goLeftRightNode(node.left);
        goLeftRightNode(node.right);
        System.out.println(node.value.toString());
    }
    private void goLeftNodeRight(Node node){
        if(node == null){return;}
        goLeftNodeRight(node.left);
        System.out.println(node.value.toString());
        goLeftNodeRight(node.right);
    }
    private T findMinimalValue(Node root){
        T min = root.value;
        while(root.left != null){
            min = root.left.value;
            root = root.left;
        }
        return min;
    }
    class Node{
        T value;
        Node left, right;
        Node(T value){
            this.value = value;
            left = right = null;
        }
    }
}


