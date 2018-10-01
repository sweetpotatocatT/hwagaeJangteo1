package www.hwagae.com.hwagae;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ItemList extends AppCompatActivity {

    ImageView clPhoto;
    TextView clItemname,clPrice;
    LinearLayout llBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        clPhoto=findViewById(R.id.clPhoto);
//        clItemname=findViewById(R.id.clItemname);
//        clPrice=findViewById(R.id.clPrice);
//        llBoard=findViewById(R.id.llBoard);


    }

}
