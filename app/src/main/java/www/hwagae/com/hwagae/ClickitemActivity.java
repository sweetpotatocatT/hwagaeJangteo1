package www.hwagae.com.hwagae;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ClickitemActivity extends AppCompatActivity {
    //ScrollView scrollView;
    ImageView imgView,btnbinheart;
    TextView tvViewName,tvViewPrice,tvViewContents,tvUser,tvViewUserName,tvViewUserid,tvViewtime,tvDelete;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    long key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clickitem);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tvViewUserName = findViewById(R.id.tvViewUserName);
        tvViewUserid = findViewById(R.id.tvViewUserid);
        tvViewtime = findViewById(R.id.tvViewtime);
        //find
        imgView =findViewById(R.id.imgView);
        btnbinheart=findViewById(R.id.btnbinheart);
        tvViewName=findViewById(R.id.tvViewName);
        tvViewContents=findViewById(R.id.tvViewContents);
        tvViewPrice=findViewById(R.id.tvViewPrice);
        tvUser=findViewById(R.id.tvUser);
        tvDelete = findViewById(R.id.tvDelete);


        // scrollView=findViewById(R.id.scrollView);


        //WriteActivity로부터 받은정보를 받은것.

        Intent intent = getIntent();
        String Contents ="내용　: "+intent.getStringExtra("contents");
        String Itemname="상품명  :"+intent.getStringExtra("title");
        String name="작성자명  :"+intent.getStringExtra("name");
        String id="작성자아이디  :"+intent.getStringExtra("id");
        String date="작성일자  :"+intent.getStringExtra("date");
        String Price="가격　: "+intent.getStringExtra("price");
        key = intent.getLongExtra("key", -1);

        tvViewName.setText(Itemname);
        tvViewUserName.setText(name);
        tvViewUserid.setText(id);
        tvViewtime.setText(date);
        tvViewContents.setText(Contents);
        tvViewPrice.setText(Price);
        tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ?대떦 寃뚯떆湲 李얘린
                databaseReference.child("board").equalTo(key);
                Toast.makeText(ClickitemActivity.this, "key媛?: " + databaseReference.child("board").child("key").equalTo(key), Toast.LENGTH_SHORT).show();
//                Log.d("LOG", "??젣?뚮?????key媛? + databaseReference.child("board").child("key").equalTo(key));
            }
        });
    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.change) {
            //글 수정 되어야함
        }
        if (id == R.id.delete) {
            //글삭제되어야함
        }


        return super.onOptionsItemSelected(item);
    }
}
