
public class Main {
    public static void main(String[] args) {
        ColorTree tree = new ColorTree();
        for(int i=1; i<=20; i++)
            tree.insert(i);
        System.out.println(tree);
    }
}