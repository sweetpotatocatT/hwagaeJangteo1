package www.hwagae.com.hwagae;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PreLoginActivity extends AppCompatActivity {
// 로그인 전 화면 : 페이스북 로그인 화면으로 넘겨주는 액티비티

    TextView tvFb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_login);

        setTitle("화개장터");

        tvFb = findViewById(R.id.tvFb);

        tvFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(PreLoginActivity.this, LoginActivity.class);
                startActivity(it);
                finish();
            }
        });
    }
}
