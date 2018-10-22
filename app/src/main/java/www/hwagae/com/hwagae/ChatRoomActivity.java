package www.hwagae.com.hwagae;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChatRoomActivity extends AppCompatActivity {

    ListView lvChatlist;

    ArrayAdapter<String> adapter;

    // 채팅방에 키값 나오는거 수정
    // 파이어베이스 RealTime Database를 이용하기 위해서 선언해줘야함 !
    // -> Bundle Gradle(App level 15.0.1 확인)
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    // FirebaseDatabase로 부터 database 호출 ( Reference : 호출 )
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lvChatlist = findViewById(R.id.lvChatlist);

        adapter
                = new ArrayAdapter<String>(this, R.layout.item, R.id.tvChat);
        /*
        showChatList() ?
        > 채팅방 목록을 보여주게 할 수 있는 ListView가 보여지도록 Adapter를 만든다.
        item.xml 보면 TextView 하나만 있다. 여기있는 텍스트 뷰를 이용해서
        ChatActivity.java에서 DataSnapshot으로 저장시킨 Key, Value들을 호출해서
        adapter에 Key를 저장시킨다.

        >> Key : 유저 이름, Value : 메세지들
         */
        showChatList();

    }

    private void showChatList() {
        // 채팅방 목록을 받아올 리스트 어댑터 세팅
        // 리스트뷰에는 상대방의 이름이 들어와야 한다.

        lvChatlist.setAdapter(adapter);
        ChatActivity chatActivity = new ChatActivity();
        final String PARTNER_NAME = chatActivity.PARTNER_NAME;
        // ChatActivity에 있는 PARTNER_NAME을 child의 이름으로 설정

        // 데이터 받아오기 및 어댑터 데이터 추가 및 삭제, 리스너 관리

        databaseReference.child("message").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                /*Log.d("LOG", "dataSnapshot.getKey() : " + dataSnapshot.getKey());*/
                ChatData data = dataSnapshot.getValue(ChatData.class);
                adapter.add(databaseReference.child("message").child(PARTNER_NAME).getKey());
                Log.d("LOG : " , "dataSnapshot.getChildren() : " + dataSnapshot.getChildren());
                // adapter.add(dataSnapshot.getChildren().toString());
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_room, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.toList) {
            AlertDialog.Builder builder = new AlertDialog.Builder(ChatRoomActivity.this);
            builder.setTitle("대화 종료 상자")
                    .setMessage("채팅방을 나가시겠습니까?\n채팅 내용은 저장되지 않습니다.")
                    .setCancelable(false)
                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

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
        return super.onContextItemSelected(item);
    }

}