package www.hwagae.com.hwagae;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.facebook.Profile;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChatActivity extends AppCompatActivity {

    private String USER_NAME;
    private String USER_ID;
    private String PARTNER_NAME;

    private LinearLayout linearRequest;
    private ListView lvChat;
    private EditText rqText;
    private Button btnRequest;
    private Button rqBalance, rqAddress, rqFinish;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // FINDVIEWBYID 선언
        lvChat = findViewById(R.id.lvChat);
        rqText = findViewById(R.id.rqText);
        btnRequest = findViewById(R.id.btnRequest);

        linearRequest = findViewById(R.id.linearRequest);
        rqBalance = findViewById(R.id.rqBalance);       // 계좌 전송 : 저장된 계좌 전송
        rqAddress = findViewById(R.id.rqAddress);       // 주소 전송 : 저장된 주소 전송
        rqFinish = findViewById(R.id.rqFinish);         // 거래 완료 : EditText입력창과 전송버튼 안보이게

        /*
        페이스북 연동 로그인 후, 받아온 유저 이름 저장
         */
        USER_NAME
                = (Profile.getCurrentProfile().getFirstName() + Profile.getCurrentProfile().getLastName()).toString();

        /*
        페이스북 연동 로그인 후, 받아온 유저 아이디 저장 -> 이름이 같아도 무결성 유지를 위해
         */
        USER_ID
                = (Profile.getCurrentProfile().getId()).toString();


        // 채팅방 입장 -> 메소드 구현
        openChat(USER_NAME);

        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 입력된 것이 아무것도 없으면
                if(rqText.getText().toString().equals("")) {
                    return;
                }
                // USER_NAME에 해당하는 사람이 친 채팅을 chat에 넣기
                ChatData chat = new ChatData(USER_NAME, rqText.getText().toString());

                // RealTime Database에 Chat - USER_NAME 넣고 채팅친 것 value로 넣기
                // 기본적으로 데이터베이스에 넣을 때 형태는 Key, Value 맵 형태로 넣어진다.
                databaseReference.child("chat").child(USER_NAME).push().setValue(chat);
                rqText.setText("");         // 입력창 초기화
            }
        });

        rqBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 내 정보 클래스에서 저장된 계좌번호가 없다면 계좌번호를 입력해달라는 Toast 넣기
                Toast.makeText(ChatActivity.this, "계좌번호를 입력해주세요.",Toast.LENGTH_SHORT).show();
            }
        });

        rqAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 내 정보 클래스에서 저장된 주소가 없다면 주소를 입력해달라는 Toast 넣기
                Toast.makeText(ChatActivity.this, "주소를 입력해주세요.",Toast.LENGTH_SHORT).show();
            }
        });

        rqFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 상대방과의 대화를 끝내기 -> EditText 창과 전송Button 없애기
                AlertDialog.Builder builder = new AlertDialog.Builder(ChatActivity.this);

                builder.setTitle("대화 종료 상자")
                        .setMessage(PARTNER_NAME + "과의 대화를 종료하시겠습니까?")
                        .setCancelable(false)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 확인 버튼 클릭시 EditText 입력창과 전송버튼 없애기
                                linearRequest.setVisibility(View.GONE);
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int whichButton) {
                                // 취소 버튼 클릭시 설정
                                    dialog.cancel();
                            }
                        });
            }
        });

    }



    /*
    채팅 입력하는 화면
    >> 추후에 USER 본인이면 오른쪽 배치, 아니면 왼쪽 배치 고려할 것
    >> 그리고 유저 이름이랑 채팅 같이 나올 수 있게 ! -> 카톡처럼
     */
    private void addMessage(DataSnapshot dataSnapshot, ArrayAdapter<String> adapter) {
        ChatData chatData = dataSnapshot.getValue(ChatData.class);
        adapter.add(chatData.getUserName() + " : " + chatData.getMessage());
    }

    /*
    채팅 글 지우는 항목도 있었는데 혹시나 발뺌하는 일 생길까봐 일부러 추가 안함
     */

    private void openChat(String chatName) {
        // 리스트 어댑터 세팅
        final ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, R.layout.item, R.id.tvChat);
        lvChat.setAdapter(adapter);

        // 데이터 받아오기 및 어댑터에 데이터 추가 및 삭제 등 .. 리스터 관리
        databaseReference.child("chat").child(chatName).addChildEventListener(new ChildEventListener() {
            // Child 값 넣는 메소드
            // Child : 데이터베이스에서 하위 항목들
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                addMessage(dataSnapshot, adapter);
                Log.d("LOG", "s:" + s);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}