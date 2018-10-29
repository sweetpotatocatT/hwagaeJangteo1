package www.hwagae.com.hwagae;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class ItemviewActivity extends AppCompatActivity {
    ListView lvBoard;
    Button btNew;
    TextView tv_board_title;

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


        // 湲?곌린 踰꾪듉???꾨Ⅴ硫?湲?곌린 ?붾㈃?쇰줈?대룞
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

        lvBoard.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(ItemviewActivity.this, "title : " + boardData.get(position).getTitle().toString(), Toast.LENGTH_SHORT).show();
                Intent it = new Intent(ItemviewActivity.this, ClickitemActivity.class);
                it.putExtra("key", boardData.get(position).getKey());
                it.putExtra("title", boardData.get(position).getTitle().toString());
                it.putExtra("content", boardData.get(position).getContent().toString());
                it.putExtra("price", boardData.get(position).getPrice().toString());
                it.putExtra("date", boardData.get(position).getDate());
                it.putExtra("name", boardData.get(position).getName().toString());
                it.putExtra("id", boardData.get(position).getId().toString());
                startActivity(it);

            }
        });
    }

    private void showBoardlist() {
//        Query query = databaseReference.child("board").orderByChild("isDelete").equalTo("true").limitToFirst(5);
        Query query = databaseReference.child("board").orderByChild("date").limitToLast(10);
//        Query query = databaseReference.child("board").orderByChild("key").limitToLast(5);
        boardData.clear();
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                // LOG ?섏쨷????젣
//                Log.d("LOG", "dataSnapshot.getKey() : " + dataSnapshot.getKey());

//                Log.d("LOG", "dataSnapshot.getValue() : " + dataSnapshot.getValue());
//                Log.d("LOG", "data : " + data);
                WriteData data = dataSnapshot.getValue(WriteData.class);
                boardData.add(data);
//                Collections.reverse(boardData);

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
