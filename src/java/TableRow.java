/**
 *
 * @author reget.kalamees
 */
public class TableRow {

    private String text;
    private String id;

    public TableRow(String id, String text) {
        this.id = id;
        this.text = text;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

}
