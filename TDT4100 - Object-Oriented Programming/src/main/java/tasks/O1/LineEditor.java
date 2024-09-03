package tasks.O1;

public class LineEditor {
    private String text;
    private int index; 
    
    public LineEditor() {
        this.index = 0;
        this.text = "";
    }

    public void left() {
        if (this.index > 0) this.index--;
    } 

    public void right() {
        if (this.text.length() != this.index) this.index++;
    }

    public void insertString(String s) {
        this.text = this.text.substring(0, this.index) + s + this.text.substring(this.index);
        this.index += s.length();
    }

    public void deleteLeft() {
        if (this.text.length() <= 0) return;

        this.text = this.text.substring(0, this.index - 1) + this.text.substring(this.index);
        this.left();
    } 
    
    public void deleteRight() {
        if (this.index < this.text.length())
            this.text = this.text.substring(0, this.index) + this.text.substring(this.index + 1);

    } 
    
    public String getText() {
        return this.text;
    } 
    
    public void setText(String s) {
        this.text = s;
    } 
    
    public int getInsertionIndex() {
        return this.index;
    } 
    
    public void setInsertionIndex(int i) {
        this.index = i;
    }

    @Override
    public String toString() {
        return this.text.substring(0, this.index) + "|" + this.text.substring(this.index);
    }
}
