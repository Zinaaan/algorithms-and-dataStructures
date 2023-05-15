package algorithms.Stack;

import java.util.ArrayList;
import java.util.List;

public class NestedIntegerImplement {
    List<NestedIntegerImplement> list;
    int file;

    // Constructor initializes an empty nested list.
    public NestedIntegerImplement() {
        this.list = new ArrayList<NestedIntegerImplement>();
    }

    // Constructor initializes a single file.
    public NestedIntegerImplement(int value) {
        this.file = value;
    }

    // @return true if this NestedDirectories holds a single file, rather than a nested list.
    public boolean isFile() {
        if (this.file != 0) {
            return true;
        }
        return false;
    }

    // @return the single file that this NestedDirectories holds, if it holds a single file
    // Return null if this NestedDirectories holds a nested list
    public int getFile() {
        return this.file;
    }

    // Set this NestedDirectories to hold a single file.
    public void setFile(int value) {
        this.list = null;
        this.file = value;
    }

    // Set this NestedDirectories to hold a nested list and adds a nested file to it.
    public void add(NestedIntegerImplement ni) {
        if (this.file != 0) {
            this.list = new ArrayList<NestedIntegerImplement>();
            this.list.add(new NestedIntegerImplement(this.file));
            this.file = 0;
        }
        this.list.add(ni);
    }

    // @return the nested list that this NestedDirectories holds, if it holds a nested list
    // Return null if this NestedDirectories holds a single file
    public List<NestedIntegerImplement> getList() {
        return this.list;
    }
}