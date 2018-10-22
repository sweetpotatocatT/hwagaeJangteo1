package www.hwagae.com.hwagae;

import android.accounts.Account;
import android.arch.lifecycle.ViewModelProvider;
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

    Intent it;
    com.facebook.login.widget.ProfilePictureView pfPicture;
    TextView rpId, rpName, rpBank;
    Button rpAccount, rpAddress, rpChatting, rpSave, rpSave2;
    LinearLayout lvAccount, lvAddress;
    ListView lvMyWrite, lvMyLike;
    EditText rpAccountnumber, rpAddress2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*
        이름과 아이디
         */
        lvAccount = findViewById(R.id.lvAccount);
        lvAddress = findViewById(R.id.lvAddress);
        rpBank = findViewById(R.id.rpBank);
        rpName = findViewById(R.id.rpName);
        rpId = findViewById(R.id.rpId);
        pfPicture = findViewById(R.id.pfPicture);
        rpAccount = findViewById(R.id.rpAccount);
        rpAccountnumber = findViewById(R.id.rpAccountnumber);
        rpSave = findViewById(R.id.rpSave);
        rpAddress2 = findViewById(R.id.rpAddress2);
        rpSave2 = findViewById(R.id.rpSave2);
        rpName.setText( Profile.getCurrentProfile().getFirstName() + Profile.getCurrentProfile().getLastName());
        rpId.setText( Profile.getCurrentProfile().getId());
        pfPicture.setProfileId(Profile.getCurrentProfile().getId());
        rpAddress = findViewById(R.id.rpAddress);
        String Pid = Profile.getCurrentProfile().getId().toString();
        SharedPreferences preferences1 = getSharedPreferences(Pid, MODE_PRIVATE);
        String Addressinfo1 = preferences1.getString("Addressinfo", "");
        String Accountinfo1 = preferences1.getString("Accountinfo", "");

        rpAddress2.setText(Addressinfo1);
        String Bankinfo2 = preferences1.getString("Bankinfo1", "");


        if(Bankinfo2 != "은행"){
            rpBank.setText(Bankinfo2);
        }
        if(Addressinfo1 != "주소"){
            rpAddress2.setText(Addressinfo1);
        }
        if(Accountinfo1 != "계좌"){
            rpAccountnumber.setText(Accountinfo1);
        }

        rpAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lvAccount.setVisibility(View.VISIBLE);
                lvAddress.setVisibility(View.GONE);
//계좌누르면 계좌창 나오기




            }
        });
        rpAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lvAddress.setVisibility(View.VISIBLE);
                lvAccount.setVisibility(View.GONE);
                String Pid = Profile.getCurrentProfile().getId().toString();
                SharedPreferences preferences1 = getSharedPreferences(Pid, MODE_PRIVATE);
                String Addressinfo1 = preferences1.getString("Addressinfo", "");
                String Accountinfo1 = preferences1.getString("Accountinfo", "");

                rpAddress2.setText(Addressinfo1);
                String Bankinfo2 = preferences1.getString("Bankinfo1", "");

//주소누르면 주소창 나오기

            }
        });

        rpSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String m = "계좌";
                String Pid = Profile.getCurrentProfile().getId().toString();
                String Acc = rpAccountnumber.getText().toString();
                SharedPreferences preferences = getSharedPreferences(Pid, MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();


                editor.putString("Accountinfo", Acc);


                editor.commit();
                SharedPreferences preferences1 = getSharedPreferences(Pid, MODE_PRIVATE);
                String Addressinfo1 = preferences1.getString("Addressinfo", "");
                String Accountinfo1 = preferences1.getString("Accountinfo", "");
                String Bankinfo2 = preferences1.getString("Bankinfo1", "");

                if(rpAccountnumber.getText().toString() != Accountinfo1){
                    rpAccountnumber.setText(Accountinfo1);
                }

                //은행정보 가져오기

                String Accountinfo = rpAccountnumber.getText().toString();
//계좌은행저장

                String info = rpAccountnumber.getText().toString() + rpBank.getText().toString();
                Toast.makeText(MypageActivity.this, "저장되었습니다.", Toast.LENGTH_SHORT).

                        show();


            }
        });
        rpSave2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Pid = Profile.getCurrentProfile().getId().toString();
                SharedPreferences preferences = getSharedPreferences(Pid, MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

                String Ac = rpAddress2.getText().toString();

                editor.putString("Addressinfo", Ac);

                editor.commit();
                String m = "주소";

                SharedPreferences preferences1 = getSharedPreferences(Pid, MODE_PRIVATE);
                String Addressinfo1 = preferences1.getString("Addressinfo", "");
                String Accountinfo1 = preferences1.getString("Accountinfo", "");
                String Bankinfo2 = preferences1.getString("Bankinfo1", "");
                rpAddress2.setText(Addressinfo1);
                if(Addressinfo1 != m){
                    rpAddress2.setText(Addressinfo1);
                    //Toast.makeText(MypageActivity.this, "이미 저장되었습니다.", Toast.LENGTH_SHORT).

                    //show();
                    // finish();

                }

                Toast.makeText(MypageActivity.this, "저장되었습니다.", Toast.LENGTH_SHORT).show();
                rpAddress2.setText(Addressinfo1);
                String Addressinfo = rpAddress2.getText().toString();

                String Pid2 = Profile.getCurrentProfile().getId().toString();

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










