package xzz.library.pojo;

import java.util.ArrayList;
import java.util.List;

public class BookType {
    String value;
    String label;
    List<BookType> children;

    public BookType(String value, String label){
        this.value = value;
        this.label = label;
    }

    public BookType(Classification classification) {
        this.value = classification.getClassifyChar();
        if (classification.getClassifyNum() != null)
            this.value += classification.getClassifyNum();
        this.label = this.value + " " + classification.getClassifyBooks();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<BookType> getChildren() {
        return children;
    }

    public void setChildren(List<BookType> children) {
        this.children = children;
    }

    public void addChildren(BookType bookType){
        if (this.children == null) {
            children = new ArrayList<>();
            children.add(new BookType(value, label));
        }
        this.children.add(bookType);
    }

}
