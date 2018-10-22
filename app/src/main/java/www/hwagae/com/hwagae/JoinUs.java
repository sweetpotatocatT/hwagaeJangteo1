package www.hwagae.com.hwagae;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.Profile;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class JoinUs extends AppCompatActivity {

    TextView tvgetId, tvgetName;
    EditText edsetName;
    Button btnSave, btnUpdate, btnSubmit;

    String name, id;
    int code = 0;

    FirebaseDatabase fbdata = FirebaseDatabase.getInstance();
    DatabaseReference dbref = fbdata.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_us);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvgetId = findViewById(R.id.tvgetId);
        tvgetName = findViewById(R.id.tvgetName);
        edsetName = findViewById(R.id.edsetName);
        btnSave = findViewById(R.id.btnSave);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnSubmit = findViewById(R.id.btnSubmit);

        name = (Profile.getCurrentProfile().getName()).toString();
        id = (Profile.getCurrentProfile().getId()).toString();

        setTextView(name, id);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toEdit();
                btnSubmit.setVisibility(View.VISIBLE);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = putData(tvgetName.getText().toString(), tvgetId.getText().toString());
                if(result == -1) {
                    Toast.makeText(JoinUs.this, "등록 실패! 조금 뒤 다시 시도해주세요. code number : -1", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(JoinUs.this, "등록 완료!", Toast.LENGTH_SHORT).show();
                    Intent it = new Intent(JoinUs.this, MainViewActivity.class);
                    startActivity(it);
                    finish();
                }

            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                edsetName.setVisibility(View.INVISIBLE);
                tvgetName.setText(edsetName.getText().toString());
                tvgetName.setVisibility(View.VISIBLE);
            }
        });
    }

    // textview에  default 이름 세팅하기
    public void setTextView(String name, String id) {

        tvgetId.setText(id);
        tvgetName.setText(name);
    }

    // 수정 누르면 editText로 변환시키기
    public void toEdit() {
        tvgetName.setVisibility(View.INVISIBLE);

        edsetName.setVisibility(View.VISIBLE);

    }

    // 저장 누르면 textview에 적혀있는 내용을 db에 넣기
    public int putData(String name, String id) {

        DatabaseReference ref = dbref.child("User");

        User user = new User(name, id);
        ref.child("User").push().setValue(user)    // 임의의 키 값으로 넣어진다. ( 구성요소 : userName, userId )
        .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                code = 1;
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                code = -1;
            }
        });
        Log.d("getKey(): ", ref.child("User").child(id).toString());
        return code; // 상태를 알려주는 코드 반환
    }

}
