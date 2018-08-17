package www.hwagae.com.hwagae;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.facebook.Profile;

public class MainViewActivity extends AppCompatActivity {
TextView TextViewName;
TextView textViewId;
com.facebook.login.widget.ProfilePictureView profilePictureView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextViewName = findViewById(R.id.TextViewName);
        textViewId =  findViewById(R.id.textViewId);
        profilePictureView = findViewById(R.id.ProfilePictrue);
        TextViewName.setText(Profile.getCurrentProfile().getFirstName() + Profile.getCurrentProfile().getLastName());
        textViewId.setText(Profile.getCurrentProfile().getId());
        profilePictureView.setProfileId(Profile.getCurrentProfile().getId());






    }

}
