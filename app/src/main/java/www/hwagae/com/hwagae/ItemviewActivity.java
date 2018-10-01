package www.hwagae.com.hwagae;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ItemviewActivity extends AppCompatActivity {
    ListView lvBoard;
    Button btNew;

    ArrayList<WriteData> boardData = new ArrayList<>();
    BoardAdapter boardAdapter;


    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lvBoard = findViewById(R.id.lvBoard);

        // 글쓰기 버튼을 누르면 글쓰기 화면으로이동
        btNew = findViewById(R.id.btNew);
        btNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ItemviewActivity.this, WriteActivity.class);
                startActivity(it);

            }
        });

        boardAdapter = new BoardAdapter(this, boardData);
        lvBoard.setAdapter(boardAdapter);

        showBoardlist();
    }


    private void showBoardlist() {
        databaseReference.child("board").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                // LOG 나중에 삭제
                Log.d("LOG", "dataSnapshot.getKey() : " + dataSnapshot.getKey());
                WriteData data = dataSnapshot.getValue(WriteData.class);
                Log.d("LOG", "dataSnapshot.getValue() : " + dataSnapshot.getValue());
                Log.d("LOG", "data : " + data);
                boardData.add(data);
                boardAdapter.notifyDataSetChanged();
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
