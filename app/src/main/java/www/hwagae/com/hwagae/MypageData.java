package www.hwagae.com.hwagae;

/**
 * Created by 218 on 2018-10-23.
 */

public class MypageData {






    private String Accountinfo1;
    private String Bankinfo2;// 채팅방에 나타날 메세지

    // 기본 생성자
    public MypageData() {
    }

    // 생성자
    public MypageData(  String Accountinfo1, String Bankinfo2) {


        this.Accountinfo1 = Accountinfo1;
        this.Bankinfo2 = Bankinfo2;

    }

    /*
    Getter / Setter 세팅
     */



    public String getAccountinfo1() {
        return Accountinfo1;
    }

    public void setAccountinfo1(String Accountinfo1) {
        this.Accountinfo1 = Accountinfo1;
    }

    public String getBankinfo2() {
        return Bankinfo2;
    }

    public void setBankinfo2(String Bankinfo2) {
        this.Bankinfo2 = Bankinfo2;
    }
}
