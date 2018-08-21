package www.hwagae.com.hwagae;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.Profile;

public class MypageActivity extends AppCompatActivity {

    com.facebook.login.widget.ProfilePictureView pfPicture;
    TextView rpId, rpName;
    Button rpAccount, rpAddress, rpChatting, rpSave, rpSave2;
    ListView lvMyWrite, lvMyLike;
    EditText rpBank, rpAccountnumber, rpAddress2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*
        이름과 아이디
         */
        rpName = findViewById(R.id.rpName);
        rpId =  findViewById(R.id.rpId);
        rpName.setText(Profile.getCurrentProfile().getFirstName() + Profile.getCurrentProfile().getLastName());
        rpId.setText(Profile.getCurrentProfile().getId());

        // 프로필 사진
        pfPicture = findViewById(R.id.pfPicture);
        pfPicture.setProfileId(Profile.getCurrentProfile().getId());

        /*
        계좌, 주소, 1:1채팅
         */
        rpAccount = findViewById(R.id.rpAccount);
        rpAddress = findViewById(R.id.rpAddress);
        rpChatting = findViewById(R.id.rpChatting);

        // 계좌 버튼 클릭
        rpAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // DB에 저장된 값이 없다면, EditText2개랑 Button 보여주기 -> 구현해야함
                // 밑의 예는 저장된 값이 '없을 때' 띄우는 Toast
                Toast.makeText(MypageActivity.this, "계좌번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
            }
        });

        // 주소 버튼 클릭
        rpAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // DB에 저장된 값이 없다면, EditText1개 보여주기 -> 구현해야함
                // 밑의 예는 저장된 값이 '없을 때' 띄우는 Toast
                Toast.makeText(MypageActivity.this, "주소를 입력해주세요.", Toast.LENGTH_SHORT).show();
            }
        });

        // 1:1 채팅 버튼 클릭
        rpChatting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*
               이 부분은 내 자신과 채팅을 못하게 구현해야 한다.
               페이스북 로그인 된 아이디와 1:1 채팅 클릭한 상대방의 아이디가 똑같을 때, (DB에 저장된 값으로)
               '내 자신과 이야기를 나눌 수 없습니다' Toast로 출력
              */


              // 만약 다른 사람과 채팅을 하게 된다면
                Intent it = new Intent(MypageActivity.this, ChatActivity.class);
                it.putExtra("name", rpId.toString());       // ID 넘기기
                startActivity(it);
            }
        });
    }


}
