package www.hwagae.com.hwagae;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WriteActivity extends AppCompatActivity {
    private EditText etItemname,etPrice,etContents;
    private TextView tvItemname,tvPrice;
    private Button btnUpload;
    private ImageView imgUpimg;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        etContents =findViewById(R.id.etContents);
        etItemname=findViewById(R.id.etItemname);
        etPrice=findViewById(R.id.etPrice);
        tvItemname=findViewById(R.id.tvItemname);
        tvPrice=findViewById(R.id.tvPrice);
        imgUpimg=findViewById(R.id.imgUpimg);
        btnUpload=findViewById(R.id.btnUpload);    }

}
