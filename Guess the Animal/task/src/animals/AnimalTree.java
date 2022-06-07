package animals;

import com.sun.source.tree.Tree;

public class AnimalTree {

    public class TreeNode {
        private String data;
        private TreeNode left;
        private  TreeNode right;

        public TreeNode(String data) {
            this.data = data;
            this.left = this.right = null;
        }
    }
    TreeNode root;
    public AnimalTree() {
        root = null;
    }

    private TreeNode insert(TreeNode root, String animalName){
        if(root == null){
            return root  = new TreeNode(animalName);
        }
        if(root.data.compareTo(animalName) > 0){
            root.left = insert(root.left, animalName);
        }
        else if(root.data.compareTo(animalName) < 0){
            root.right = insert(root.right, animalName);
        }
         return root;
    }

    private String search(TreeNode root, String data){
        if(root == null || root.data.equalsIgnoreCase(data)){
            return root == null ? "Not Found": root.data;
        }
        if(root.data.compareToIgnoreCase(data) > 0){
            return search(root.left, data);
        }
        return search(root.right, data);
    }

//    Wrapper Classes

    public void insertData(String animal){
        root = insert(root,animal);
    }
    public String searchData(String animal){
        return search(root, animal);
    }
}
