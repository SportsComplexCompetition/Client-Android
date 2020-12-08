package com.eduvation.pecontest.Activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eduvation.pecontest.Adapter.Cert_Adapter;
import com.eduvation.pecontest.Class.Cert;
import com.eduvation.pecontest.Class.EachCert;
import com.eduvation.pecontest.R;
import com.eduvation.pecontest.Singleton.ManageCert;
import com.eduvation.pecontest.Singleton.ManageUser;

import java.util.ArrayList;
import java.util.Comparator;

public class MatchCertificateActivity extends AppCompatActivity {
    int id;
    private final int SELECT=1;
    int touch=0;
    Intent certintent;
    ImageView cert_img;
    TextView alarm_img, alarm_video;
    RecyclerView cert_recyclerview;
    ArrayList<Cert> certs=null;
    Cert_Adapter certAdapter=null;
    Context certcontext;

    ImageView no_mymatch_img;
    EditText cert_count;
    Button cert_btn;
    Uri uri=null;
    int count=-1;
    int upload=-1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_certificate);

        setting_view();
        setting_recyclerview();

    }

    public void setting_view(){
        id=getIntent().getIntExtra("id", 0);

        cert_img=findViewById(R.id.cert_img);
        alarm_img=findViewById(R.id.alarm_img);
        alarm_video=findViewById(R.id.alarm_video);
        cert_recyclerview=findViewById(R.id.cert_recyclerview);
        no_mymatch_img=findViewById(R.id.no_match_img);
        cert_count=findViewById(R.id.cert_count);
        cert_btn=findViewById(R.id.cert_btn);
        certcontext=this;



        cert_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getting_image_video();
            }
        });
        alarm_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getting_image_video();
            }
        });
        alarm_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getting_image_video();
            }
        });

        cert_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                certification();
            }
        });
    }
    public void setting_recyclerview(){
        certs=new ArrayList<>();
        ArrayList<EachCert> temp=new ArrayList<>();
        temp=ManageCert.getInstance().getCert_total();
        for(int i=0; i<temp.size(); i++){
            if(id==temp.get(i).getPk()){
                certs.add(new Cert(temp.get(i).getName(), temp.get(i).getCount(), temp.get(i).getData(), temp.get(i).getType()));
            }
        }
        cert_recyclerview.setLayoutManager(new LinearLayoutManager(certcontext));
        certAdapter=new Cert_Adapter(certs, certcontext);
        cert_recyclerview.setAdapter(certAdapter);
        certAdapter.sort();
    }
    public void getting_image_video(){
        certintent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        certintent.setType("image/* video/*");
        startActivityForResult(certintent, SELECT);
    }

    public void certification(){
        if(TextUtils.isEmpty(cert_count.getText().toString().trim())){
            Toast.makeText(certcontext, "개수를 적어주세요", Toast.LENGTH_SHORT).show();
            return;
        }
        if(upload==-1){
            Toast.makeText(certcontext, "인증 사진/영상을 올려주세요", Toast.LENGTH_SHORT).show();
            return;
        }
        count=Integer.parseInt(cert_count.getText().toString().trim());
        Cert newcert=new Cert(ManageUser.getInstance().getMe().getNickname(), count, uri, upload);
        certAdapter.addnewItem(newcert);
        EachCert eachCert=new EachCert(id, ManageUser.getInstance().getMe().getNickname(), count, uri, upload);
        ManageCert.getInstance().addnewItem(eachCert);
        upload=-1;
        count=-1;
        cert_count.setText("");
        cert_img.setVisibility(View.VISIBLE);
        alarm_img.setVisibility(View.GONE);
        alarm_video.setVisibility(View.GONE);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            uri=data.getData();
            if(resultCode==RESULT_OK){
                if(uri.toString().contains("image")){
                    cert_img.setVisibility(View.GONE);
                    alarm_img.setVisibility(View.VISIBLE);
                    alarm_video.setVisibility(View.GONE);
                    upload=0;
                    System.out.println("image image =============================");
                }
                else if(uri.toString().contains("video")){
                    cert_img.setVisibility(View.GONE);
                    alarm_img.setVisibility(View.GONE);
                    alarm_video.setVisibility(View.VISIBLE);
                    upload=1;
                    System.out.println("video video =============================");
                }
            }
        }

    }

}
