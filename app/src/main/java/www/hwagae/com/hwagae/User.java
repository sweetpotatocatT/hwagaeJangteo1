package www.hwagae.com.hwagae;

public class User {
        /*
        DB에 저장된 유저들(나를 제외한 모든 사람)의 정보를 가져오고, DB에 저장하기 위한 중간 단계
         */

    String userName;
    String userId;

        /*
        기본 생성자 -> 다른 생성자를 정의하기 위해서는 필요하다
        기본 생성자의 역할 ? : User user = new User();
        우리가 알고있는 모양으로 객체를 생성하는 구문이다.
        생성자 안에 인자를 넣는 다는 것은 필드(userName, userId)의 값을 사전에
        초기화를 시킨 다음에 사용을 한다는 의미이다.
        따라서 활용하기 위한 기본생성자가 필요하다. (인자있는 생성자를 적지 않으면 기본생성자가 자동으로 선언됨)
        */

    public User() {
    }

    public User(String userName, String userId) {
        this.userName = userName;
        this.userId = userId;
    }

    public User(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
