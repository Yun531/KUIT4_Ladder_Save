public class Position {
    private int left;
    private int right;
    private int row;

    public Position(int row, int left, int right){
        this.row = row;
        this.left = left;
        this.right = right;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public int getRow() {
        return row;
    }
}
