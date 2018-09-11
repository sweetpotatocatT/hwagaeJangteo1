package www.hwagae.com.hwagae;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
    Button rpAccount, rpAddress;
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
        rpId = findViewById(R.id.rpId);
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


    }


}