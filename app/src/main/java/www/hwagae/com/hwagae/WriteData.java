package www.hwagae.com.hwagae;

public class WriteData {
    private String key;
    private String id;
    private String date;
    private String title;
    private String content;
    private String price;

    public WriteData() {
    }

    // key, id, date 추가.
    public WriteData(String title, String content , String price) {
//        this.key = key;
//        this.id = id;
//        this.date = date;
        this.title = title;
        this.content = content;
        this.price= price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public  void setPrice(String price){this.price=price;}

    public String getTitle() {
        return title;
    }

    public String getContent() { return content; }

    public String getPrice() {return  price;}


}
