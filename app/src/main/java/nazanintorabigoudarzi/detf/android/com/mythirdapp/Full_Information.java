package nazanintorabigoudarzi.detf.android.com.mythirdapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

//import static nazanintorabigoudarzi.detf.android.com.mythirdapp.R.id.circleView1;


public class Full_Information extends AppCompatActivity {

    public static int counter;
    public static CircleImageView imageView;
    public static Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full__information);

        imageView  = (CircleImageView) findViewById(R.id.circleView1);
        counter = getCounter();
      Picasso.with(getApplicationContext()).load(MainActivity.image[counter]).into(imageView);
        TextView textViewName = (TextView) findViewById(R.id.textViewName);
        textViewName.setText(MainActivity.name[counter]);
        TextView textViewTitle2 = (TextView) findViewById(R.id.textViewTitle2);
        textViewTitle2.setText(MainActivity.title[counter]);
        TextView textViewTags2 = (TextView) findViewById(R.id.textViewTags2);
        textViewTags2.setText(MainActivity.tag[counter]);
       /* TextView textViewScore = (TextView) findViewById(R.id.textViewScore);
        textViewScore.setText(MainActivity.score[counter]);
        TextView textViewVC = (TextView) findViewById(R.id.textViewVC);
        textViewVC.setText(MainActivity.VC[counter]);*/
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse(MainActivity.link[counter]));
                startActivity(browser);

            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Full_Information.this,MainActivity.class));
        finish();
    }
    public static void setCounter(int i) {
        counter = i;
    }
    public static int getCounter() {
        return counter;
    }
}
