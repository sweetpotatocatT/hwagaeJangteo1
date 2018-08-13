package www.hwagae.com.hwagae;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class PrLoginActivity extends AppCompatActivity {
// 로그인 전 화면 : 페이스북 로그인 화면으로 넘겨주는 액티비티

    TextView tvFb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pr_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("화개장터");

        tvFb = findViewById(R.id.tvFb);

        tvFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(PrLoginActivity.this, LoginActivity.class);
                startActivity(it);
                finish();
            }
        });
    }

}
