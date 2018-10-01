package www.hwagae.com.hwagae;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

public class ClickitemActivity extends AppCompatActivity {
    //ScrollView scrollView;
    ImageView imgView,btnbinheart;
    TextView tvViewName,tvViewPrice,tvViewContents,tvUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clickitem);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //find
        imgView =findViewById(R.id.imgView);
        btnbinheart=findViewById(R.id.btnbinheart);
        tvViewName=findViewById(R.id.tvViewName);
        tvViewContents=findViewById(R.id.tvViewContents);
        tvViewPrice=findViewById(R.id.tvViewPrice);
        tvUser=findViewById(R.id.tvUser);
        // scrollView=findViewById(R.id.scrollView);

        //WriteActivity로부터 받은정보를 받은것.

        Intent intent = getIntent();
        String Contents ="내용　: "+intent.getStringExtra("Contents");
        String Itemname="상품명  :"+intent.getStringExtra("Itemname");
        String Price="가격　: "+intent.getStringExtra("Price");

        tvViewName.setText(Itemname);
        tvViewContents.setText(Contents);
        tvViewPrice.setText(Price);



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
