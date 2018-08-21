
package www.hwagae.com.hwagae;

import android.os.Bundle;
 import android.support.design.widget.FloatingActionButton;
 import android.support.design.widget.Snackbar;
 import android.support.v7.app.AppCompatActivity;
 import android.support.v7.widget.Toolbar;
 import android.view.View;
 import android.widget.Button;
 import android.widget.EditText;
 import android.widget.TextView;
 import android.widget.Toast;

import com.facebook.Profile;

public class MypageActivity extends AppCompatActivity {
 TextView rpName;
 TextView rpId;
 com.facebook.login.widget.ProfilePictureView pfPicture;
 EditText rpBank;
 EditText rpAccountnumber;
 EditText rpAddress2;
 Button rpSave;
  Button rpSave2;
  android.widget.LinearLayout saveAddress;
  android.widget.LinearLayout saveAccount;
    private com.google.firebase.database.FirebaseDatabase firebaseDatabase = com.google.firebase.database.FirebaseDatabase.getInstance();
    // FirebaseDatabase로 부터 database 호출 ( Reference : 호출 )
    private com.google.firebase.database.DatabaseReference databaseReference = firebaseDatabase.getReference();

@override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_mypage);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
   
    rpName = findViewById(R.id.TextViewName);
    rpId = findViewById(R.id.textViewId);
    pfPicture = findViewById(R.id.pfPicture);
    rpBank = findViewById(R.id.rpBank);
    rpAccountnumber = findViewById(R.id.rpAccountnumber);
    rpSave = findViewById(R.id.rpSave);
    rpAddress2 = findViewById(R.id.rpAddress2);
    rpSave2 = findViewById(R.id.rpSave2);
    rpName.setText("이름: " + Profile.getCurrentProfile().getFirstName() + Profile.getCurrentProfile().getLastName());
    rpId.setText("아이디: " + Profile.getCurrentProfile().getId());
    pfPicture.setProfileId(Profile.getCurrentProfile().getId());







}


public void addMessage(com.google.firebase.database.DataSnapshot dataSnapshot, android.widget.ArrayAdapter<String> adapter) {
        AccountData AccountData = dataSnapshot.getValue(AccountData.class);
        adapter.add(AccountData.getAccountinfo() + " : " + AccountData.getBankinfo());



}
public void address(com.google.firebase.database.DataSnapshot dataSnapshot, android.widget.ArrayAdapter<String> adapter) {
        AddressData AddressData = dataSnapshot.getValue(AddressData.class);
        adapter.add(AddressData.getAddressinfo());



}

public void saveAccount(View v){
if((rpAccountnumber.getText().toString() == null) &&  (rpBank.getText().toString() == null) ){
saveAccount.setVisibility(View.VISIBLE);
    

    String Accountinfo = rpAccountnumber.getText().toString();
    AccountData Account = new AccountData(Accountinfo, rpAccountnumber.getText().toString());

                // RealTime Database에 Chat - USER_NAME 넣고 채팅친 것 value로 넣기
                // 기본적으로 데이터베이스에 넣을 때 형태는 Key, Value 맵 형태로 넣어진다.
                databaseReference.child("Account").child(Accountinfo).push().setValue(Account);


                 String Bankinfo = rpBank.getText().toString();
    AccountData Bank = new AccountData(Bankinfo, rpBank.getText().toString());

                // RealTime Database에 Chat - USER_NAME 넣고 채팅친 것 value로 넣기
                // 기본적으로 데이터베이스에 넣을 때 형태는 Key, Value 맵 형태로 넣어진다.
                databaseReference.child("Account").child(Bankinfo).push().setValue(Bank);

}
else{
    String info =  rpAccountnumber.getText().toString() + rpBank.getText().toString();
    Toast.makeText(this,  info , Toast.LENGTH_SHORT).show();
}
    }


private void saveAddress(View v){
if(rpAddress2.getText().toString() == null ){
 saveAddress.setVisibility(View.VISIBLE);
      

    String Addressinfo = rpAddress2.getText().toString();
    AddressData Address = new AddressData(Addressinfo, rpAddress2.getText().toString());

              
                databaseReference.child("Address").child(Addressinfo).push().setValue(Address);


                
}
else{
    String info =  rpAddress2.getText().toString();
    Toast.makeText(this,  info , Toast.LENGTH_SHORT).show();
}
    }


}
