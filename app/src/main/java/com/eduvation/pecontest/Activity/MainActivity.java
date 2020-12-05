package com.eduvation.pecontest.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.eduvation.pecontest.Class.Comment;
import com.eduvation.pecontest.Fragment.Fragment1;
import com.eduvation.pecontest.Fragment.Fragment2;
import com.eduvation.pecontest.Fragment.Fragment3;
import com.eduvation.pecontest.Fragment.Fragment4;
import com.eduvation.pecontest.Network.RetrofitAPI;
import com.eduvation.pecontest.Network.RetrofitClient;
import com.eduvation.pecontest.R;
import com.eduvation.pecontest.Singleton.ManageComment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    FrameLayout container;
    LinearLayout frag1, frag2, frag3, frag4;
    ImageView tab1, tab2, tab3, tab4;
    Fragment1 fragment1=null;
    Fragment2 fragment2=null;
    Fragment3 fragment3=null;
    Fragment4 fragment4=null;
    FragmentManager fragmentManager;
    public static Context main_context;

    RetrofitAPI myAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setting_view();
        setting_clicklistener();

        getting_comment();
    }

    public void setting_view(){
        container=findViewById(R.id.container);
        frag1=findViewById(R.id.frag1);
        frag2=findViewById(R.id.frag2);
        frag3=findViewById(R.id.frag3);
        frag4=findViewById(R.id.frag4);
        tab1=findViewById(R.id.tab_img1);
        tab2=findViewById(R.id.tab_img2);
        tab3=findViewById(R.id.tab_img3);
        tab4=findViewById(R.id.tab_img4);


        main_context=this;
    }

    public void setting_clicklistener(){
        frag1.setOnClickListener(setting_fragment);
        frag2.setOnClickListener(setting_fragment);
        frag3.setOnClickListener(setting_fragment);
        frag4.setOnClickListener(setting_fragment);

        fragment1=new Fragment1();
        fragment2=new Fragment2();

        fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.add(R.id.container, fragment1);
        transaction.add(R.id.container, fragment2);
        transaction.hide(fragment2);
        transaction.commit();

        Glide.with(main_context).load(R.drawable.home).into(tab1);
        Glide.with(main_context).load(R.drawable.commute_n).into(tab2);
        Glide.with(main_context).load(R.drawable.rank_n).into(tab3);
        Glide.with(main_context).load(R.drawable.my_n).into(tab4);
    }

    private View.OnClickListener setting_fragment=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id=v.getId();
            FragmentTransaction trans=fragmentManager.beginTransaction();
            switch(id){
                case R.id.frag1:
                    if(fragment1==null){
                        fragment1=new Fragment1();
                        trans.add(R.id.container, fragment1);
                    }
                    if(fragment1!=null){
                        trans.show(fragment1);
                    }
                    if(fragment2!=null){
                        trans.hide(fragment2);
                    }
                    if(fragment3!=null){
                        trans.hide(fragment3);
                    }
                    if(fragment4!=null){
                        trans.hide(fragment4);
                    }
                    trans.commit();
                    Glide.with(main_context).load(R.drawable.home).into(tab1);
                    Glide.with(main_context).load(R.drawable.commute_n).into(tab2);
                    Glide.with(main_context).load(R.drawable.rank_n).into(tab3);
                    Glide.with(main_context).load(R.drawable.my_n).into(tab4);
                    return;
                case R.id.frag2:
                    if(fragment2==null){
                        fragment2=new Fragment2();
                        trans.add(R.id.container, fragment2);
                    }
                    if(fragment2!=null){
                        trans.show(fragment2);
                    }
                    if(fragment1!=null){
                        trans.hide(fragment1);
                    }
                    if(fragment3!=null){
                        trans.hide(fragment3);
                    }
                    if(fragment4!=null){
                        trans.hide(fragment4);
                    }
                    trans.commit();
                    Glide.with(main_context).load(R.drawable.home_n).into(tab1);
                    Glide.with(main_context).load(R.drawable.commute).into(tab2);
                    Glide.with(main_context).load(R.drawable.rank_n).into(tab3);
                    Glide.with(main_context).load(R.drawable.my_n).into(tab4);
                    return;
                case R.id.frag3:
                    if(fragment3==null){
                        fragment3=new Fragment3();
                        trans.add(R.id.container, fragment3);
                    }
                    if(fragment3!=null){
                        trans.show(fragment3);
                    }
                    if(fragment1!=null){
                        trans.hide(fragment1);
                    }
                    if(fragment2!=null){
                        trans.hide(fragment2);
                    }
                    if(fragment4!=null){
                        trans.hide(fragment4);
                    }
                    trans.commit();
                    Glide.with(main_context).load(R.drawable.home_n).into(tab1);
                    Glide.with(main_context).load(R.drawable.commute_n).into(tab2);
                    Glide.with(main_context).load(R.drawable.rank).into(tab3);
                    Glide.with(main_context).load(R.drawable.my_n).into(tab4);
                    return;
                case R.id.frag4:
                    if(fragment4==null){
                        fragment4=new Fragment4();
                        trans.add(R.id.container, fragment4);
                    }
                    if(fragment4!=null){
                        trans.show(fragment4);
                    }
                    if(fragment1!=null){
                        trans.hide(fragment1);
                    }
                    if(fragment2!=null){
                        trans.hide(fragment2);
                    }
                    if(fragment3!=null){
                        trans.hide(fragment3);
                    }
                    trans.commit();
                    Glide.with(main_context).load(R.drawable.home_n).into(tab1);
                    Glide.with(main_context).load(R.drawable.commute_n).into(tab2);
                    Glide.with(main_context).load(R.drawable.rank_n).into(tab3);
                    Glide.with(main_context).load(R.drawable.my).into(tab4);
                    return;
                default:
                    return;
            }
        }
    };

    public void getting_comment(){
        myAPI= RetrofitClient.getApiService();
        ArrayList<Comment> comment_total=new ArrayList<>();
        Call<ArrayList<Comment>> getcomment=myAPI.get_comment();
        getcomment.enqueue(new Callback<ArrayList<Comment>>() {
            @Override
            public void onResponse(Call<ArrayList<Comment>> call, Response<ArrayList<Comment>> response) {
                if(response.isSuccessful()){
                    for(Comment item:response.body()){
                        comment_total.add(item);
                    }
                }
                ManageComment.getInstance().setComment_total(comment_total);
            }

            @Override
            public void onFailure(Call<ArrayList<Comment>> call, Throwable t) {

            }
        });


    }
}