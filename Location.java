public class Location {
    public int row;
    public int column;
    public Location(int r, int c) {
        row = r;
        column = c;
    }
    public int getRow() {
        return row;
    }
    public int getColumn() {
        return column;
    }
    public Location next() {
        if (this.column == 8 && this.row == 8) {
            return null;
        } else if (this.column == 8) {
            return new Location(this.row + 1, 0);
        } else {
            return new Location(this.row, this.column + 1);
        }
    }
    public String toString() {
        return "" + row + ", " + column;
    }
}
