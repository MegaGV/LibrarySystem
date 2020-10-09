package xzz.library.pojo;

import java.io.Serializable;

public class Classification implements Serializable {
    private Integer id;

    private String classifyChar;

    private Integer classifyNum;

    private String classifyBooks;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassifyChar() {
        return classifyChar;
    }

    public void setClassifyChar(String classifyChar) {
        this.classifyChar = classifyChar == null ? null : classifyChar.trim();
    }

    public Integer getClassifyNum() {
        return classifyNum;
    }

    public void setClassifyNum(Integer classifyNum) {
        this.classifyNum = classifyNum;
    }

    public String getClassifyBooks() {
        return classifyBooks;
    }

    public void setClassifyBooks(String classifyBooks) {
        this.classifyBooks = classifyBooks == null ? null : classifyBooks.trim();
    }
}