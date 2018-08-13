package www.hwagae.com.hwagae;

public class ChatData {

    private String userName;    // 채팅방에 나타날 유저 이름
    private String message;     // 채팅방에 나타날 메세지

    // 기본 생성자
    public ChatData() {
    }

    // 생성자
    public ChatData(String userName, String message) {
        this.userName = userName;
        this.message = message;
    }

    /*
    Getter / Setter 세팅
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserName() {
        return userName;
    }

    public String getMessage() {
        return message;
    }
}
