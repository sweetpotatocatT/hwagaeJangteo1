package www.hwagae.com.hwagae;

public class WriteData {
    private long key;
    private String id;
    private String name;
    private long date;
    private String title;
    private String content;
    private String price;
    private String isDelete;

    public WriteData() {
    }

    // key, id, date 추가.
    public WriteData(long key, String id, String name,String title, String content, String price, long date, String isDelete) {
        this.key = key;
        this.id = id;
        this.name = name;
        this.title = title;
        this.content = content;
        this.price = price;
        this.date = date;
        this.isDelete = isDelete;
    }

    // set
    public void setKey(long key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    // get
    public long getKey() {
        return key;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getPrice() {
        return price;
    }

    public long getDate() {
        return date;
    }

    public String getIsDelete() {
        return isDelete;
    }
}