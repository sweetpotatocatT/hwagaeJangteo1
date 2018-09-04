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
import android.view.Menu;
import android.view.MenuItem;
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

    private String USER_NAME = "USER_NAME";
    private String USER_ID = "USER_ID";
    private String PARTNER_NAME = "PARTNER_NAME";

    private LinearLayout linearRequest;
    private ListView lvChat;
    private EditText rqText;
    private Button btnRequest;
    private Button rqBalance, rqAddress, rqFinish;

    // 채팅 ArrayAdapter 설정 -> 리스트뷰에 넣어주기 위함
    ArrayAdapter<String> adapter;

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

        adapter =
                new ArrayAdapter<>(this, R.layout.item, R.id.tvChat);
        lvChat.setAdapter(adapter);

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
//        openChat(USER_NAME);

        // 채팅전송 버튼 온클릭 리스터
        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 입력된 것이 아무것도 없으면
                if(rqText.getText().toString().equals("")) {
                    return;
                }

                // USER_NAME에 해당하는 사람이 친 채팅을 chat에 넣기
                ChatData chat = new ChatData(USER_NAME, rqText.getText().toString());
                // Database에 message라는 root로 chat 푸시
                databaseReference.child("message").push().setValue(chat);
                rqText.setText("");         // 입력창 초기화


                // RealTime Database에 Chat - USER_NAME 넣고 채팅친 것 value로 넣기
                // 기본적으로 데이터베이스에 넣을 때 형태는 Key, Value 맵 형태로 넣어진다.

            }
        });

        // 데이터 받아오기 및 어댑터에 데이터 추가 및 삭제 등 .. 리스터 관리
        databaseReference.child("message").addChildEventListener(new ChildEventListener() {
            // Child 값 넣는 메소드
            // Child : 데이터베이스에서 하위 항목들
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ChatData chatData = dataSnapshot.getValue(ChatData.class);
                adapter.add(chatData.getUserName() + " : " + chatData.getMessage());
                // addMessage(dataSnapshot, adapter);
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

        // 계좌번호 전송
        rqBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 내 정보 클래스에서 저장된 계좌번호가 없다면 계좌번호를 입력해달라는 Toast 넣기
                Toast.makeText(ChatActivity.this, "계좌번호를 입력해주세요.",Toast.LENGTH_SHORT).show();
            }
        });

        // 주소 전송
        rqAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 내 정보 클래스에서 저장된 주소가 없다면 주소를 입력해달라는 Toast 넣기
                Toast.makeText(ChatActivity.this, "주소를 입력해주세요.",Toast.LENGTH_SHORT).show();
            }
        });

        // 거래 완료
        rqFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 상대방과의 대화를 끝내기 -> EditText 창과 전송Button 없애기
                AlertDialog.Builder builder = new AlertDialog.Builder(ChatActivity.this);
                Log.d("Partner Name : ",PARTNER_NAME);
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
                // 다이얼로그 생성
                AlertDialog alertDialog = builder.create();

                // 다이얼로그 보여주기
                alertDialog.show();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_chat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.toMain) {
            Intent it = new Intent(ChatActivity.this, MainViewActivity.class);
            startActivity(it);
            finish();
        }
        if(id == R.id.ChatList) {
            Intent it = new Intent(ChatActivity.this, ChatRoomActivity.class);
            startActivity(it);
            finish();
        }
        if(id == R.id.outChatroom) {
            AlertDialog.Builder builder = new AlertDialog.Builder(ChatActivity.this);
            builder.setTitle("대화 종료 상자")
                    .setMessage(PARTNER_NAME + "과의 채팅방을 나가시겠습니까?\n채팅 내용은 저장되지 않습니다.")
                    .setCancelable(false)
                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // 채팅방삭제 기능 구현
                            Toast.makeText(ChatActivity.this,"채팅방 나가기 기능 구현", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int whichButton) {
                            // 취소 버튼 클릭시 설정
                            dialog.cancel();
                        }
                    });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        return super.onOptionsItemSelected(item);
    }
}
