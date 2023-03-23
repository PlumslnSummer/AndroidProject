package com.example.compete6;


import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import com.example.compete6.bean.MeMoryBitmap;
import com.example.compete6.bean.RegistSucBean;
import com.example.compete6.bean.RegisterBean;
import com.example.compete6.util.Contants;
import com.example.compete6.util.HttpbinServices;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static retrofit2.Response.success;

public class RegisterActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitleTop;
    private ImageView ivAvatar;
    private ImageView ivTuku;
    private EditText etName;
    private EditText etNickname;
    private EditText etPwdRe;
    private EditText etPhone;
    private RadioButton raReM;
    private RadioButton raReW;
    private EditText etEmail;
    private EditText etIdcard;
    private Button btnRe;
    private Retrofit retrofit;
    private String sex;
    private RegisterBean registerBean = null;
    private HttpbinServices httpbinServices;
    public static final int PHOTO_REQUEST_CAREMA = 1;//拍照
    public static final int CROP_PHOTO = 2;//裁剪
    public static final int PHOTO_ALBUM = 3;//相册
    private Uri image_uri;
    private Uri album_uri;
    private ImageView ivPhotoshow;
    private MeMoryBitmap meMoryBitmap;
    private Intent intentPhoto;
    private static File tempFile,album_tempFile;
    private Context context;
    private static final String TAG="RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        retrofit = new Retrofit.Builder().baseUrl(Contants.WEB_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        context=RegisterActivity.this;
        meMoryBitmap=new MeMoryBitmap();
        registerBean = new RegisterBean();
        httpbinServices = retrofit.create(HttpbinServices.class);
        sex="0";
        initView();
        initData();
        initBack();
    }

    private void initBack() {
        tvTitleTop.setText("注册页面");
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        ivAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                meMoryBitmap.recycleImageView(ivPhotoshow);
                openCamera();
            }

        });
        ivTuku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                meMoryBitmap.recycleImageView(ivPhotoshow);
                openAlbum();
            }
        });
        raReM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sex = "0";
            }
        });
        raReW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sex = "1";
            }
        });
        btnRe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etName.getText().toString();
                String nickname = etNickname.getText().toString();
                String pwd = etPwdRe.getText().toString();
                String phonenumner = etPhone.getText().toString();
                String email = etEmail.getText().toString();
                String idcard = etIdcard.getText().toString();

                if(ivPhotoshow.getDrawable()==null){
                    Toast.makeText(RegisterActivity.this,"请打开相机拍照或者相册上传图片",Toast.LENGTH_SHORT).show();
                }
                if(username.isEmpty()){
                    Toast.makeText(RegisterActivity.this,"请输入用户名",Toast.LENGTH_SHORT).show();
                }else if(nickname.isEmpty()){
                    Toast.makeText(RegisterActivity.this,"昵称",Toast.LENGTH_SHORT).show();
                }else if(pwd.isEmpty()){
                    Toast.makeText(RegisterActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
                }else if(phonenumner.isEmpty()){
                    Toast.makeText(RegisterActivity.this,"请输入电话号码",Toast.LENGTH_SHORT).show();
                }else if(email.isEmpty()){
                    Toast.makeText(RegisterActivity.this,"请输入邮箱",Toast.LENGTH_SHORT).show();
                }else if(idcard.isEmpty()){
                    Toast.makeText(RegisterActivity.this,"请输入身份证号码",Toast.LENGTH_SHORT).show();
                }

                registerBean.setUserName(username);
                registerBean.setNickName(nickname);
                registerBean.setPassword(pwd);
                registerBean.setPhonenumber(phonenumner);
                registerBean.setEmail(email);
                registerBean.setSex(sex);
                registerBean.setIdCard(idcard);
                Call<RegistSucBean> call = httpbinServices.PostRegister(registerBean);
                call.enqueue(new Callback<RegistSucBean>() {
                    @Override
                    public void onResponse(Call<RegistSucBean> call, Response<RegistSucBean> response) {
                        Toast.makeText(RegisterActivity.this,response.body().getMsg()+response.body().getCode(),Toast.LENGTH_SHORT).show();
                        if(response.body().getCode()==200){
                            Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                            startActivity(intent);

                        }
                    }

                    @Override
                    public void onFailure(Call<RegistSucBean> call, Throwable throwable) {

                    }
                });
            }
        });
    }
    //图片上传，调用接口
    private void uploadOk(File file) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();
        //设置上传文件以及文件对应的MediaType类型
        RequestBody requestBody = MultipartBody.create(MediaType.parse("image/jpeg"), file);
        //MultipartBody文件上传
        /**区别：
         * addFormDataPart:   上传key:value形式
         * addPart:  只包含value数据
         */
        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)//设置文件上传类型
                .addFormDataPart("key", "img")//文件在服务器中保存的文件夹路径
                .addFormDataPart("file", file.getName(), requestBody)//包含文件名字和内容
                .build();
//        Request request = new Request.Builder()
//                .url(HttpUtils.BASEURL+HttpUtils.EQUIP_UPLOADIMG)
//                .header("Token", UserManager.getToken())
//                .post(body)
//                .build();
        //  Call<SuccessBean> call=httpbinServices.PostRegister(registerBean);

//        okHttpClient.newCall(request).enqueue(new okhttp3.Callback() {
//            @Override
//            public void onFailure(okhttp3.Call call, IOException e) {
//                Log.d("e","e"+e.getMessage());
//            }
//            @Override
//            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
//                String str = response.body().string();
//                Gson gson = new Gson();
//                final UpLoadBean upLoadBean = gson.fromJson(str, UpLoadBean.class);
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (upLoadBean != null && upLoadBean.getCode() == 200) {
//                            Log.d(TAG, "uploadBean"+upLoadBean.getData().getUrl());
//                            success(upLoadBean);
//                        }
//                    }
//                });
//            }
//        });
    }

    //拍照
    private void openCamera() {
        Bitmap bitmap=null;
        ivPhotoshow.setImageBitmap(bitmap);
        //调用方法，解决内存泄露
        meMoryBitmap.recycleImageView(ivPhotoshow);
        //获取操作系统的版本号
        int currentapiVersion= Build.VERSION.SDK_INT;
        //MediaStore.ACTION_IMAGE_CAPTURE调用系统相机拍照
        intentPhoto=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(hasSdcard()){
            //获取时间戳
            SimpleDateFormat timeStampFormat=new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
            String filename=timeStampFormat.format(new Date());
            //获取外部存储根目录Environment.getExternalStorageDirectory()方法已经过时，改用context.getExternalFilesDir(null).getAbsolutePath()
            tempFile=new File(Environment.getExternalStorageDirectory(),
                    filename+".jpg");
            if(currentapiVersion<24){
                image_uri=Uri.fromFile(tempFile);
                //MediaStore.EXTRA_OUTPUT规避Intent携带信息的不靠谱
                intentPhoto.putExtra(MediaStore.EXTRA_OUTPUT,image_uri);
            }else{
                //兼容android7.0 使用共享文件的形式.ContentValues 和HashTable类似都是一种存储的机制 但是两者最大的区别就在于，
                // contenvalues只能存储基本类型的数据，像string，int之类的，不能存储对象这种东西，而HashTable却可以存储对象。
                ContentValues contentValues=new ContentValues(1);
                //获取文件
                contentValues.put(MediaStore.Images.Media.DATA,tempFile.getAbsolutePath());
                //判断应用是否授予权限
                if(ContextCompat.checkSelfPermission(RegisterActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED){
                    String[] PERMISSIONS={"android.permission.CAMERA","android.permission.WRITE_EXTERNAL_STORAGE"};
                    //权限申请结果
                    ActivityCompat.requestPermissions(RegisterActivity.this,PERMISSIONS,1);
                    return;
                }
                //getContext().getContentResolver().insert(...);
                /**
                 * getContext()是获得一个上下文对象（Context），一般在四大组件中都会获取上下文对象
                 * 在Activity和Service中，就没必要获取Context了，因为他本身就是，所以可以直接调用getContentResolver()。
                 * getContext().getContentResolver()返回的是ContentResolver 对象，ContentResolver负责获取ContentProvider提供的数据。
                 * 将图片保存至相册
                 */
                image_uri=getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,contentValues);
                intentPhoto.putExtra(MediaStore.EXTRA_OUTPUT,image_uri);
            }
        }
        /**
         * 在Activity中得到新打开Activity 关闭后返回的数据，
         * 需要使用系统提供的startActivityForResult(Intent intent, int requestCode)方法打开新的Activity，
         * 新的Activity 关闭后会向前面的Activity传回数据，为了得到传回的数据，
         * 必须在前面的Activity中重写onActivityResult(int requestCode, int resultCode, Intent data)方法
         */

        startActivityForResult(intentPhoto,PHOTO_REQUEST_CAREMA,null);
    }
    //调用图库
    private void openAlbum() {
        Bitmap bitmap=null;
        ivPhotoshow.setImageBitmap(bitmap);
        //获取时间戳
        SimpleDateFormat timeStampFormat=new SimpleDateFormat(
                "yyyy_MM_dd_HH_mm_ss"
        );
        String filename=timeStampFormat.format(new Date());
        //图片命名
        album_tempFile=new File(Environment.getExternalStorageDirectory(),filename+".jpg");
        if(album_tempFile.exists()){
            album_tempFile.delete();
        }else {
            /**
             * 创建目录的方式大致有这两种情况，这两种情况的区别是
             * 1. mkdir()：根据相对路径创建目录，只会在原有的目录里面创建，如果上面一级的目录xxdir不存在，则这次创建便会失败， 报xxxdir这个目录找不到的异常
             * 2. mkdirs()：根据绝对路径新建目录，如果上一级目录不存在，则会将上一级目录创建完后，再创建后面一级的目录
             */
            album_tempFile.mkdirs();
        }
        try {
            album_tempFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Uri.parse("file://" + new File(path).toString())==Uri.fromFile(new File(path))
        album_uri=Uri.fromFile(album_tempFile);
        Intent intent_album=null;
        //判断操作系统版本
        if(Build.VERSION.SDK_INT<19){
            //ACTION_GET_CONTENT:允许用户选择特殊种类的数据，并返回（特殊种类的数据：照一张相片或录一段音）调用图库，获取所有本地图片
            intent_album=new Intent(Intent.ACTION_GET_CONTENT);
            /**
             * 正常的访问系统自带的文件管理器。但是setType只支持单个setType一般是以下这种 intent_album.setType("image/*");
             * 我要限制只查看ppt ,doc,docx,pptx,pdf等文件
             * public static final String DOC = "application/msword";
             *     public static final String DOCX = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
             *     public static final String XLS = "application/vnd.ms-excel application/x-excel";
             *     public static final String XLSX = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
             *     public static final String PPT = "application/vnd.ms-powerpoint";
             *     public static final String PPTX = "application/vnd.openxmlformats-officedocument.presentationml.presentation";
             *     public static final String PDF = "application/pdf";
             *
             * try {
             *                     Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
             *                     intent.addCategory(Intent.CATEGORY_OPENABLE);
             *                     //设置doc,docx,ppt,pptx,pdf 5种类型
             *                     intent.setType("application/msword|application/vnd.openxmlformats-officedocument.wordprocessingml.document" +
             *                             "|application/vnd.ms-powerpoint|application/vnd.openxmlformats-officedocument.presentationml.presentation|application/pdf");
             *                     //在API>=19之后设置多个类型采用以下方式，setType不再支持多个类型
             *                     if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
             *                         intent.putExtra(Intent.EXTRA_MIME_TYPES,
             *                                 new String[]{DOC,DOCX, PPT, PPTX,PDF});
             *                     }
             *                     startActivityForResult(intent, 1001);
             *                 } catch (ActivityNotFoundException e) {
             *                 }
             */
            intent_album.setType("image/*");
        }else{
            /**
             * 一、调用图库，获取所有本地图片：
             * Intent imageIntent = new Intent(Intent.ACTION_GET_CONTENT);
             *  imageIntent.setType("image/*");
             *  startActivityForResult(imageIntent, PICK_CODE); //PICK_CODE是常量
             *  二、调用本地联系人：
             *  Intent intent = new Intent(Intent.ACTION_PICK);
             *  intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
             *  startActivityForResult(intent, PICK_CONTACT);
             *  三、调用音乐，获取所有本地音乐文件：
             * Intent audioIntent = new Intent(Intent.ACTION_GET_CONTENT);
             * audioIntent.setType("audio/*");
             * startActivityForResult(audioIntent, PICK_AUDIO);
             * 四、调用视频，获取所有本地视频文件：
             * Intent videoIntent = new Intent(Intent.ACTION_GET_CONTENT);
             * videoIntent.setType("video/*");
             * startActivityForResult(videoIntent, PICK_VIDEO);
             */
            intent_album=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        }
        //允许裁剪
        intent_album.putExtra("crop",true);
        //允许缩放
        intent_album.putExtra("scale",true);
        //图片的输出位置
        intent_album.putExtra(MediaStore.EXTRA_OUTPUT,album_uri);

        startActivityForResult(intent_album,PHOTO_ALBUM);
    }
    //判断sdcard是否被挂载,判断SD卡状态
    public static boolean hasSdcard(){
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case PHOTO_REQUEST_CAREMA:
                if(resultCode==RESULT_OK){
                    /**
                     * 调用安卓系统进行图片裁剪问题
                     */
                    Intent intent=new Intent("com.android.camera.action.CROP");
                    //设置intent的data和Type属性。
                    intent.setDataAndType(image_uri,"image/*");
                    intent.putExtra("scale",true);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,image_uri);
                    setPicture(ivPhotoshow,image_uri);
                }
                break;
            case PHOTO_ALBUM:
                if(resultCode==RESULT_OK){
                    album_uri=data.getData();
                    Intent intent=new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(album_uri,"image/*");
                    intent.putExtra("scale",true);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,album_uri);
                    setPicture(ivPhotoshow,album_uri);
                    // 启动裁剪
                    //  startActivityForResult(intent, SHOW_PHOTO_ALBUM);
                }
                break;
            //图片裁剪
            case CROP_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(this.getContentResolver()
                                .openInputStream(image_uri));
                        ivPhotoshow.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    private void setPicture(ImageView imageView,Uri uri) {
        Bitmap bitmap1=null;
        ivPhotoshow.setImageBitmap(bitmap1);
        meMoryBitmap.recycleImageView(ivPhotoshow);
        if(context!=null){
            //获取图片大小
            int targetW=imageView.getWidth();
            int targetH=imageView.getHeight();
            //Options，此类用于解码Bitmap时的各种参数控制，
            BitmapFactory.Options bmpOptions=new BitmapFactory.Options();
            //如果将inJustDecodeBounds这个值置为true，那么在解码的时候将不会返回bitmap，只会返回这个bitmap的尺寸。
            // 这个属性的目的是，如果你只想知道一个bitmap的尺寸，但又不想将其加载到内存时。这是一个非常有用的属性。
            //充分利用它，来避免大图片的溢出问题。
            bmpOptions.inJustDecodeBounds=true;
            try {
                //BitmapFactory.decodeStream解决OOM内存用完了的情况
                BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri));
                int photoW=bmpOptions.outWidth;
                int photoH=bmpOptions.outHeight;
                int scaleFactor=Math.min(photoW/targetW,photoH/targetH);
                bmpOptions.inJustDecodeBounds=false;
                //Options中有个属性inSampleSize。我们可以充分利用它，实现缩放
                bmpOptions.inSampleSize=scaleFactor;
                int sizeFactor=calculateInSampleSize(bmpOptions,targetW,targetH);
                Log.d(TAG,"sizeFactor"+sizeFactor+",scaleFactor"+scaleFactor);
                /*节约内存 下面两个字段需要组合使用 */
                bmpOptions.inPurgeable=true;
                bmpOptions.inInputShareable = true;
                //再次decode获取bitmap
                Bitmap bitmap=BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri),null,bmpOptions);
                imageView.setImageBitmap(bitmap);
                registerBean.setAvatar(String.valueOf(uri));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private int calculateInSampleSize(BitmapFactory.Options bmpOptions, int reqWidth, int reqHeight) {
        final int height=bmpOptions.outHeight;
        final  int width=bmpOptions.outWidth;
        int inSampleSize=1;
        if(height>reqHeight||width>reqWidth){
            final  int halfheight=height/2;
            final  int halWidth=width/2;
            while((halfheight/inSampleSize)>reqHeight&&(halWidth/inSampleSize)>reqWidth){
                inSampleSize*=2;
            }
        }
        return inSampleSize;
    }

    private void initView() {
        ivBack = findViewById(R.id.iv_back);
        tvTitleTop = findViewById(R.id.tv_title);
        ivAvatar = findViewById(R.id.iv_avatar);
        ivTuku = findViewById(R.id.iv_tuku);
        etName = findViewById(R.id.et_name);
        etNickname = findViewById(R.id.et_nickname);
        etPwdRe = findViewById(R.id.et_pwd_re);
        etPhone = findViewById(R.id.et_phone);
        raReM = findViewById(R.id.ra_re_m);
        raReW = findViewById(R.id.ra_re_w);
        etEmail = findViewById(R.id.et_email);
        etIdcard = findViewById(R.id.et_idcard);
        btnRe = findViewById(R.id.btn_re);
        ivPhotoshow = findViewById(R.id.iv_photoshow);
    }
}