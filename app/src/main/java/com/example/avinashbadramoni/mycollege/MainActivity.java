package com.example.avinashbadramoni.mycollege;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.marketing.internal.ButtonIndexingLogger;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {



    private FirebaseAuth mAuth;
    private RecyclerView mbloglist;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Global");
        mDatabase.keepSynced(true);

        mbloglist=(RecyclerView)findViewById(R.id.recycleview);
        mbloglist.setHasFixedSize(true);
        mbloglist.setLayoutManager(new LinearLayoutManager(this));






    }

    @Override
    public void onStart() {
        super.onStart();



        FirebaseRecyclerAdapter<blog,BlogViewHolder>firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<blog, BlogViewHolder>
                (blog.class,R.layout.blog_row,BlogViewHolder.class,mDatabase) {
            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, blog model, int position) {

                viewHolder.setTitle(model.getTitle());
                viewHolder.setDesc(model.getDesc());
                viewHolder.setImage(getApplicationContext(),model.getImage());

            }
        };
        mbloglist.setAdapter(firebaseRecyclerAdapter);

    }

    public static class BlogViewHolder extends RecyclerView.ViewHolder
    {
       View mView;
       public BlogViewHolder(View itemView){

           super(itemView);
           mView=itemView;
       }

       public void setTitle(String title)
       {
           TextView  post_title = (TextView)mView.findViewById(R.id.title);
           post_title.setText(title);

       }

        public void setDesc(String dese)
        {
            TextView  post_title = (TextView)mView.findViewById(R.id.dese);
            post_title.setText(dese);

        }

        public void setImage(Context ctx,String image)
        {
            ImageView  post_img = (ImageView)mView.findViewById(R.id.post_img);
            Picasso.get().load(image).into(post_img);


        }



    }


}
