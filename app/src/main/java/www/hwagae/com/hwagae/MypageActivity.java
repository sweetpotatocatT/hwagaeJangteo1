package www.hwagae.com.hwagae;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.Profile;

public class MypageActivity extends AppCompatActivity {

    com.facebook.login.widget.ProfilePictureView pfPicture;
    TextView rpId, rpName, rpBank;
    Button rpAccount, rpAddress, rpSave, rpSave2;
    LinearLayout lvAccount, lvAddress;
    ListView lvMyWrite, lvMyLike;
    EditText rpAccountnumber, rpAddress2;
    private com.google.firebase.database.FirebaseDatabase firebaseDatabase = com.google.firebase.database.FirebaseDatabase.getInstance();
    private com.google.firebase.database.DatabaseReference databaseReference = firebaseDatabase.getReference();
    Intent it;
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
        rpSave = findViewById(R.id.rpSave);
        rpSave2 = findViewById(R.id.rpSave2);


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
                // Toast.makeText(MypageActivity.this, "계좌번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                lvAccount.setVisibility(View.VISIBLE);
            }
        });

        // 주소 버튼 클릭
        rpAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // DB에 저장된 값이 없다면, EditText1개 보여주기 -> 구현해야함
                // 밑의 예는 저장된 값이 '없을 때' 띄우는 Toast
                // Toast.makeText(MypageActivity.this, "주소를 입력해주세요.", Toast.LENGTH_SHORT).show();
                lvAddress.setVisibility(View.VISIBLE);
            }
        });

        String Bankinfo1 = it.getStringExtra("bank");
        rpBank.setText(Bankinfo1);
        rpSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String m = "은행";
                String Pid = Profile.getCurrentProfile().getId().toString();
                SharedPreferences preferences1 = getSharedPreferences(Pid, MODE_PRIVATE);
                String Addressinfo1 = preferences1.getString("Addressinfo", "");
                String Accountinfo1 = preferences1.getString("Accountinfo", "");
                String Bankinfo2 = preferences1.getString("Bankinfo1", "");
                if(Bankinfo2 != m) {
                    Toast.makeText(MypageActivity.this, "이미 저장되었습니다.", Toast.LENGTH_SHORT).

                            show();
                    finish();

                }
                it = getIntent();
                String Bankinfo1 = it.getStringExtra("bank");
                rpBank.setText(Bankinfo1);
                //은행정보 가져오기

                String Accountinfo = rpAccountnumber.getText().toString();
//아이디받아오기

                SharedPreferences preferences = getSharedPreferences(Pid, MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("Accountinfo", Accountinfo);
                editor.putString("Bankinfo1", Bankinfo1);

                editor.commit();
//계좌은행저장

                String info = rpAccountnumber.getText().toString() + rpBank.getText().toString();
                Toast.makeText(MypageActivity.this, "저장되었습니다.", Toast.LENGTH_SHORT).

                        show();


            }
        });
        rpSave2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String Addressinfo = rpAddress2.getText().toString();

                String Pid = Profile.getCurrentProfile().getId().toString();


                SharedPreferences preferences = getSharedPreferences(Pid, MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
//주소저장
                editor.putString("Addressinfo", Addressinfo);


                editor.commit();
                String m = "주소";

                SharedPreferences preferences1 = getSharedPreferences(Pid, MODE_PRIVATE);
                String Addressinfo1 = preferences1.getString("Addressinfo", "");
                String Accountinfo1 = preferences1.getString("Accountinfo", "");
                String Bankinfo2 = preferences1.getString("Bankinfo1", "");
                if(Addressinfo1 != m){
                    Toast.makeText(MypageActivity.this, "이미 저장되었습니다.", Toast.LENGTH_SHORT).

                            show();
                    finish();

                }

                Toast.makeText(MypageActivity.this, "저장되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });


    }


    //  public View.OnClickListener btnBank =
    // new View.OnClickListener() {
    // @Override
    // public void onClick(View v) {
    //  Intent it = new Intent(MypageActivity.this, BankActivity.class);
    // startActivity(it);
    // finish();
    //  }
    //  }

    //  ;
    public void btnBank(View v){
        Intent it = new Intent(MypageActivity.this, BankActivity.class);
        it.putExtra("Bank", "bank");
        startActivity(it);

        //은행선택
    }

}
