package www.hwagae.com.hwagae;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class BankActivity extends AppCompatActivity {
    TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8,
            textView11,textView21,textView31,textView41,textView51,textView61,textView71,textView81,
            textView12,textView22,textView32,textView42,textView52,textView62,textView72,textView82;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);
        textView6 = findViewById(R.id.textView6);
        textView7 = findViewById(R.id.textView7);
        textView8 = findViewById(R.id.textView8);
        textView11 = findViewById(R.id.textView11);
        textView21 = findViewById(R.id.textView21);
        textView31 = findViewById(R.id.textView31);
        textView41 = findViewById(R.id.textView41);
        textView51 = findViewById(R.id.textView51);
        textView61 = findViewById(R.id.textView61);
        textView71 = findViewById(R.id.textView71);
        textView81 = findViewById(R.id.textView81);
        textView12 = findViewById(R.id.textView12);
        textView22 = findViewById(R.id.textView22);
        textView32 = findViewById(R.id.textView32);
        textView42 = findViewById(R.id.textView42);
        textView52 = findViewById(R.id.textView52);
        textView62 = findViewById(R.id.textView62);
        textView72 = findViewById(R.id.textView72);
        textView82 = findViewById(R.id.textView82);



    }


    public void a1(View v){
        String bank = textView1.getText().toString();
        Intent it = new Intent(BankActivity.this, MypageActivity.class);
        it.putExtra("bank",bank);
        startActivity(it);
        finish();
        //은행에서 은행정보가져와서 내정보에 보내기

    }
    public void a2(View v){
        String bank = textView2.getText().toString();
        Intent it = new Intent(BankActivity.this, MypageActivity.class);
        it.putExtra("bank",bank);
        startActivity(it);
        finish();

    }
    public void a3(View v){
        String bank = textView3.getText().toString();
        Intent it = new Intent(BankActivity.this, MypageActivity.class);
        it.putExtra("bank",bank);
        startActivity(it);
        finish();

    }
    public void a4(View v){
        String bank = textView4.getText().toString();
        Intent it = new Intent(BankActivity.this, MypageActivity.class);
        it.putExtra("bank",bank);
        startActivity(it);
        finish();

    }
    public void a5(View v){
        String bank = textView5.getText().toString();
        Intent it = new Intent(BankActivity.this, MypageActivity.class);
        it.putExtra("bank",bank);
        startActivity(it);
        finish();

    }
    public void a6(View v){
        String bank = textView6.getText().toString();
        Intent it = new Intent(BankActivity.this, MypageActivity.class);
        it.putExtra("bank",bank);
        startActivity(it);
        finish();

    }
    public void a7(View v){
        String bank = textView7.getText().toString();
        Intent it = new Intent(BankActivity.this, MypageActivity.class);
        it.putExtra("bank",bank);
        startActivity(it);
        finish();

    }
    public void a8(View v){
        String bank = textView8.getText().toString();
        Intent it = new Intent(BankActivity.this, MypageActivity.class);
        it.putExtra("bank",bank);
        startActivity(it);
        finish();

    }
    public void a9(View v){
        String bank = textView11.getText().toString();
        Intent it = new Intent(BankActivity.this, MypageActivity.class);
        it.putExtra("bank",bank);
        startActivity(it);
        finish();

    }
    public void a10(View v){
        String bank = textView21.getText().toString();
        Intent it = new Intent(BankActivity.this, MypageActivity.class);
        it.putExtra("bank",bank);
        startActivity(it);
        finish();

    }
    public void a11(View v){
        String bank = textView31.getText().toString();
        Intent it = new Intent(BankActivity.this, MypageActivity.class);
        it.putExtra("bank",bank);
        startActivity(it);
        finish();

    }
    public void a12(View v){
        String bank = textView41.getText().toString();
        Intent it = new Intent(BankActivity.this, MypageActivity.class);
        it.putExtra("bank",bank);
        startActivity(it);
        finish();

    }
    public void a13(View v){
        String bank = textView51.getText().toString();
        Intent it = new Intent(BankActivity.this, MypageActivity.class);
        it.putExtra("bank",bank);
        startActivity(it);
        finish();

    }
    public void a14(View v){
        String bank = textView61.getText().toString();
        Intent it = new Intent(BankActivity.this, MypageActivity.class);
        it.putExtra("bank",bank);
        startActivity(it);
        finish();

    }
    public void a15(View v){
        String bank = textView71.getText().toString();
        Intent it = new Intent(BankActivity.this, MypageActivity.class);
        it.putExtra("bank",bank);
        startActivity(it);
        finish();

    }
    public void a16(View v){
        String bank = textView81.getText().toString();
        Intent it = new Intent(BankActivity.this, MypageActivity.class);
        it.putExtra("bank",bank);
        startActivity(it);
        finish();

    }
    public void a17(View v){
        String bank = textView12.getText().toString();
        Intent it = new Intent(BankActivity.this, MypageActivity.class);
        it.putExtra("bank",bank);
        startActivity(it);
        finish();

    }
    public void a18(View v){
        String bank = textView22.getText().toString();
        Intent it = new Intent(BankActivity.this, MypageActivity.class);
        it.putExtra("bank",bank);
        startActivity(it);
        finish();

    }
    public void a19(View v){
        String bank = textView32.getText().toString();
        Intent it = new Intent(BankActivity.this, MypageActivity.class);
        it.putExtra("bank",bank);
        startActivity(it);
        finish();

    }
    public void a20(View v){
        String bank = textView42.getText().toString();
        Intent it = new Intent(BankActivity.this, MypageActivity.class);
        it.putExtra("bank",bank);
        startActivity(it);
        finish();

    }
    public void a21(View v){
        String bank = textView52.getText().toString();
        Intent it = new Intent(BankActivity.this, MypageActivity.class);
        it.putExtra("bank",bank);
        startActivity(it);
        finish();

    }
    public void a22(View v){
        String bank = textView62.getText().toString();
        Intent it = new Intent(BankActivity.this, MypageActivity.class);
        it.putExtra("bank",bank);
        startActivity(it);
        finish();

    }
    public void a23(View v){
        String bank = textView72.getText().toString();
        Intent it = new Intent(BankActivity.this, MypageActivity.class);
        it.putExtra("bank",bank);
        startActivity(it);
        finish();

    }
    public void a24(View v){
        String bank = textView82.getText().toString();
        Intent it = new Intent(BankActivity.this, MypageActivity.class);
        it.putExtra("bank",bank);
        startActivity(it);
        finish();

    }


}
