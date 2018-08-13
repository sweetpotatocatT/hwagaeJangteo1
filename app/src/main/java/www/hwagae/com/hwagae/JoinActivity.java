package www.hwagae.com.hwagae;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;


public class JoinActivity extends AppCompatActivity {

/*
// 데이터베이스에 메시지 쓰기
 FirebaseDatabase database =
 FirebaseDatabase.getInstance ();
 DatabaseReference myRef = 데이터베이스 입니다.getReference ( "message" );
 myRef.setValue("Hello, World!");
*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }


}
