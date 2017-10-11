package com.example.karim.videotestonmap;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import jp.shts.android.storiesprogressview.StoriesProgressView;

public class MainActivity extends AppCompatActivity {

    Button Btn_Toast;
    StoriesProgressView storiesProgressView;

    ImageView imageView;
    int counter=0;
    int []resurese=new int[]{
      R.drawable.img,
            R.drawable.got1,
            R.drawable.got2
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Btn_Toast=(Button)findViewById(R.id.btn_Toast);

        Btn_Toast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent I=new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(I);
              /*  AlertDialog.Builder mBuilder=new AlertDialog.Builder(MainActivity.this);
                View mView=getLayoutInflater().inflate(R.layout.dialog,null);
                storiesProgressView=(StoriesProgressView) mView.findViewById(R.id.Stories2);
                storiesProgressView.setStoriesCount(3);
                storiesProgressView.setStoryDuration(1500L);
                storiesProgressView.setStoriesListener(new StoriesProgressView.StoriesListener() {
                    @Override
                    public void onNext() {
                        imageView.setImageResource(resurese[++counter]);
                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(MainActivity.this, "Compelete", Toast.LENGTH_SHORT).show();
                    }
                });
                storiesProgressView.startStories();
                imageView=(ImageView)mView.findViewById(R.id.image2);

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        storiesProgressView.skip();
                    }
                });
                imageView.setImageResource(resurese[counter]);
                mBuilder.setView(mView);
                AlertDialog alertDialog=mBuilder.create();
                alertDialog.show();*/

            }
        });
    }

}
