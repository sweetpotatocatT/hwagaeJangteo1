package www.hwagae.com.hwagae;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChatRoomActivity extends AppCompatActivity {

    private ListView lvChatlist;

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
        final ArrayAdapter<String> adapter
                = new ArrayAdapter<String>(this, R.layout.item, R.id.tvChat);
        lvChatlist.setAdapter(adapter);

        // 데이터 받아오기 및 어댑터 데이터 추가 및 삭제 .. 리스너 관리
        databaseReference.child("chat").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d("LOG", "dataSnapshot.getKey() : " + dataSnapshot.getKey());
                adapter.add(dataSnapshot.getKey());
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
