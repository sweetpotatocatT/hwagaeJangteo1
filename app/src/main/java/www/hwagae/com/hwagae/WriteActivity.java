package www.hwagae.com.hwagae;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class WriteActivity extends AppCompatActivity {
    private static final int PICK_FROM_CAMERA = 0;
    private static final int PICK_FROM_ALBUM = 1;
    private static final int CROP_FROM_IMAGE=2; //아직구현x

    private Uri ImageCaptureUri;
    private String absoultePath;
    private int id_view;
    private EditText etItemname, etPrice, etContents;
    private TextView tvItemname, tvPrice;
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








        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 입력 없을 시 처리 추가

                WriteData write = new WriteData( etItemname.getText().toString(),
                        etContents.getText().toString(),
                        etPrice.getText().toString());
                databaseReference.child("board").push().setValue(write);

                Intent intent = new Intent(WriteActivity.this,ClickitemActivity.class);
                intent.putExtra("Contents",etContents.getText().toString());
                intent.putExtra("Itemname",etItemname.getText().toString());
                intent.putExtra("Price",etPrice.getText().toString());
                startActivity(intent);
                finish();

                // 입력 후 입력칸 초기화 추가
            }
        });


    }

    public void ImgUp(View v) { // btnUpimg 에있는 온클릭
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

    //Bitmap 저장

    private void storeCropImage(Bitmap bitmap,String filePath) {
        String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/SmartWheel";
        File directory_SmartWheel = new File(dirPath);

        if (!directory_SmartWheel.exists())
            directory_SmartWheel.mkdir();

        File copyFile = new File(filePath);
        BufferedOutputStream out = null;

        try{
            copyFile.createNewFile();
            out=new BufferedOutputStream(new FileOutputStream(copyFile));
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,out);

            //sendBroadcast를 통해 Crop된 사진을 앨범에 보이도록 갱신

            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,Uri.fromFile(copyFile)));

            out.flush();
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }



    }


    //카메라에서 사진촬영 함수
    public void PhotoAction() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //임시로 사용할 파일의 경로생성
        String url = "tmp_" + String.valueOf(System.currentTimeMillis()) + ".jpg";
        ImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), url));

        intent.putExtra(MediaStore.EXTRA_OUTPUT, ImageCaptureUri);
        startActivityForResult(intent, PICK_FROM_CAMERA);

    }

    //앨범에서 이미지 가져오기 함수
    public void PhotoAlbumAction() {

        // /앨범호출
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_ALBUM);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK)
            return;
        switch (requestCode) {
            case PICK_FROM_ALBUM:
                ImageCaptureUri = data.getData();
                Log.d("SmartWheel", ImageCaptureUri.getPath().toString());

            case PICK_FROM_CAMERA: {

                //이미지를 가져온 이후의 리사이즈할 이미지 크기 결정
                Intent intent = new Intent("come.android.camera.action.CROP");
                intent.setDataAndType(ImageCaptureUri,"image/*");

                intent.putExtra("ouputX",200); //CROP한 이미지 x축
                intent.putExtra("outputY",200);//y축
                intent.putExtra("aspectX",1);//CROP박스의X축비율
                intent.putExtra("aspectY",1);//y비율
                intent.putExtra("scale",true);
                intent.putExtra("return-data",true);
                //CROP_FROM_CAMERA 케이스문으로 이동
                startActivityForResult(intent,CROP_FROM_IMAGE);
                break;

            }

            case CROP_FROM_IMAGE:{
                //크롭이 된 이후의 이미지를 넘겨받는다.
                if (resultCode!=RESULT_OK){
                    return;
                }
                final Bundle extras = data.getExtras();

                //CROP된 이미지를 저장하기 위한 file 경로
                String filePath = Environment.getExternalStorageDirectory().getAbsolutePath()
                        + "/SmartWheel/"+System.currentTimeMillis()+".jpg";

                if(extras!=null){
                    Bitmap photo = extras.getParcelable("data"); //CROP된 BITMAP
                    imgUpimg.setImageBitmap(photo); //★레이아웃 이미지칸에 보여줌

                    storeCropImage(photo,filePath);
                    absoultePath =filePath;

                    break;



                }
                //임시파일삭제
                File f = new File(ImageCaptureUri.getPath());
                if(f.exists()){
                    f.delete();
                }
            }
        }



    }
}






