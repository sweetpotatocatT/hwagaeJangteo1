package www.hwagae.com.hwagae;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.Profile;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class WriteActivity extends AppCompatActivity {
    private static final String TAG = "WriteActivity";



    private static final int PICK_FROM_CAMERA = 0;
    private static final int PICK_FROM_ALBUM = 1;
    String Pid = Profile.getCurrentProfile().getId().toString();
    long key=0;
    private Uri imgUri, photoURI, albumURI,filePath;
    private String mCurrentPhotoPath;
    private String absoultePath;
    private int id_view;
    private EditText etItemname, etPrice, etContents;
    private TextView tvItemname, tvPrice,tvViewtime;
    private Button btnUpload, btnUpimg;
    private ImageView imgUpimg;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvViewtime = findViewById(R.id.tvViewtime);


        etContents = findViewById(R.id.etContents);
        etItemname = findViewById(R.id.etItemname);
        etPrice = findViewById(R.id.etPrice);
        tvItemname = findViewById(R.id.tvItemname);
        tvPrice = findViewById(R.id.tvPrice);
        btnUpload = findViewById(R.id.btnUpload);
        btnUpimg = findViewById(R.id.btnUpimg);

        imgUpimg = findViewById(R.id.imgUpimg);
       /* imgUpimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 1);


            }
        });*/



        //리스트뷰에 넣는 코드
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFile();
                // 입력 없을 시 처리 추가
                Date now = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
                // key를 랜덤값으로
                long date = Long.parseLong(sdf.format(now));

                Random rnd = new Random();
                long key = date-rnd.nextInt();
                String name = Profile.getCurrentProfile().getFirstName() + Profile.getCurrentProfile().getLastName();
                date = -1 * date;

                WriteData write = new WriteData(key, Pid, name,etItemname.getText().toString(),
                        etContents.getText().toString(),
                        etPrice.getText().toString(), date, "true");
                databaseReference.child("board").push().setValue(write);

                Intent intent = new Intent(WriteActivity.this, ClickitemActivity.class);
                intent.putExtra("id", Pid);
                intent.putExtra("name", Profile.getCurrentProfile().getFirstName() + Profile.getCurrentProfile().getLastName());
                intent.putExtra("content", etContents.getText().toString());
                intent.putExtra("title", etItemname.getText().toString());
                intent.putExtra("price", etPrice.getText().toString());
                intent.putExtra("date", date);
                startActivity(intent);
                finish();
            }
        });


    }




    /*****************  여기서부턴 카메라 기능 *****************/

    //btnUpimg에 있는 온클릭속성. 사진을 누르면 Dialog가 뜨는 소스
    public void ImgUp(View v) {
        id_view = v.getId();
        if (v.getId() == R.id.btnUpimg) { //사진선택 버튼을 누르면
            DialogInterface.OnClickListener cameraListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    PhotoAction(); //**사진찍는거**
                }
            };

            DialogInterface.OnClickListener albumListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    PhotoAlbumAction(); //**사진을 앨범에서 선택하는거**

                }
            };

            DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss(); //**취소**

                }
            };

            new AlertDialog.Builder(this)
                    .setTitle("이미지 선택")
                    .setPositiveButton("사진촬영", cameraListener)
                    .setNeutralButton("앨범선택", albumListener)
                    .setNegativeButton("취소", cancelListener)
                    .show();

        }

    }




    //**************★카메라에서 사진촬영 함수
    public void PhotoAction() {
        Uri ImageCaptureUri;
        //촬영 후 이미지 가져옴
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //?꾩떆濡??ъ슜???뚯씪??寃쎈줈?앹꽦
        String url = "tmp_" + String.valueOf(System.currentTimeMillis()) + ".jpg";
        ImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), url));

        intent.putExtra(MediaStore.EXTRA_OUTPUT, ImageCaptureUri);
        startActivityForResult(intent, PICK_FROM_CAMERA);


    }


    //촬영한 이미지 저장
    public void galleryAddPic(){

        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);

        File f = new File(mCurrentPhotoPath);

        Uri contentUri = Uri.fromFile(f);

        mediaScanIntent.setData(contentUri);

        sendBroadcast(mediaScanIntent);

        Toast.makeText(this,"사진이 저장되었습니다",Toast.LENGTH_SHORT).show();

    }

    //앨범에서 이미지 가져오기 함수
    public void PhotoAlbumAction() {

        // /앨범호출
        Intent intent = new Intent();

        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "이미지를 선택하세요."), 0);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //request코드가 0이고 OK를 선택했고 data에 뭔가가 들어 있다면
        if(requestCode == 0 && resultCode == RESULT_OK){
            filePath = data.getData();
            Log.d(TAG,"uri:" + String.valueOf(filePath));
            try {
                //Uri 파일을 Bitmap으로 만들어서 ImageView에 집어 넣는다.
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imgUpimg.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //upload the file
    private void uploadFile() {
        //업로드할 파일이 있으면 수행
        if (filePath != null) {
            //업로드 진행 Dialog 보이기
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("업로드중...");
            progressDialog.show();

            //storage
            FirebaseStorage storage = FirebaseStorage.getInstance();

            //Unique한 파일명을 만들자.
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMHH_mmss");
            Date now = new Date();
            String filename = formatter.format(now) + ".png";
            //storage 주소와 폴더 파일명을 지정해 준다.
            StorageReference storageRef = storage.getReferenceFromUrl("gs://hwagae4.appspot.com").child("image/" + filename);
            //올라가거라...
            storageRef.putFile(filePath)
                    //성공시
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss(); //업로드 진행 Dialog 상자 닫기
                            Toast.makeText(getApplicationContext(), "업로드 완료!", Toast.LENGTH_SHORT).show();
                        }
                    })
                    //실패시
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "업로드 실패!", Toast.LENGTH_SHORT).show();
                        }
                    })
                    //진행중
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            @SuppressWarnings("VisibleForTests") //이걸 넣어 줘야 아랫줄에 에러가 사라진다. 넌 누구냐?
                                    double progress = (100 * taskSnapshot.getBytesTransferred()) /  taskSnapshot.getTotalByteCount();
                            //dialog에 진행률을 퍼센트로 출력해 준다
                            progressDialog.setMessage("Uploaded " + ((int) progress) + "% ...");
                        }
                    });
        } else {
            Toast.makeText(getApplicationContext(), "파일을 먼저 선택하세요.", Toast.LENGTH_SHORT).show();
        }
    }



    //startActivityForResult에 대한 처리
    //   @Override

   /* protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);


        if(resultCode != RESULT_OK){

            return;

        }

        switch (requestCode){

            case PICK_FROM_ALBUM : {

                //앨범에서 가져오기

                if(data.getData()!=null){

                    try{

                        File albumFile = null;

                        albumFile = createImageFile();


                        photoURI = data.getData();

                        albumURI = Uri.fromFile(albumFile);


                        galleryAddPic();

                        //이미지뷰에 이미지 셋팅
                        imgUpimg.setImageURI(photoURI);

                        //cropImage();

                    }catch (Exception e){

                        e.printStackTrace();

                        Log.v("알림","앨범에서 가져오기 에러");

                    }

                }

                break;

            }

            case PICK_FROM_CAMERA : {

                //카메라 촬영

                try{

                    Log.v("알림", "FROM_CAMERA 처리");

                    galleryAddPic();

                    imgUpimg.setImageURI(imgUri);

                }catch (Exception e){

                    e.printStackTrace();

                }

                break;

            }

        }

    }

*/


}





