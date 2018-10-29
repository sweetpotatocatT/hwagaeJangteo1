package www.hwagae.com.hwagae;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.Profile;

public class UserPageActivity extends AppCompatActivity {
    String userName, userId;
    TextView rpName, rpId;
    Button rpChatting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rpName = findViewById(R.id.rpName);
        rpId = findViewById(R.id.rpId);
        rpChatting = findViewById(R.id.rpChatting);

        userName = rpName.toString();
        userId = rpId.toString();

        rpChatting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(UserPageActivity.this, ChatActivity.class);
                it.putExtra("name", userName);
                it.putExtra("id", userId);
                startActivity(it);
            }
        });
    }

    public void GoChatting(View view) {
        Intent it = new Intent(UserPageActivity.this, ChatActivity.class);
        // it.putExtra("PARTNER_NAME", );
    }

}
