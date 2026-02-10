package com.example.sharedpreferences;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView tv_title, tv_question, tv_question_text;
    EditText et_answer;
    ImageView iv_setting;
    Button btn_check_answer;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toast.makeText(this, "MainActivity opened", Toast.LENGTH_SHORT).show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_title = findViewById(R.id.tv_title);
        tv_question = findViewById(R.id.tv_question);
        tv_question_text = findViewById(R.id.tv_question_text);
        et_answer = findViewById(R.id.et_answer);
        iv_setting = findViewById(R.id.iv_setting);
        btn_check_answer = findViewById(R.id.btn_check_answer);

        iv_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i_setting = new Intent(MainActivity.this , SettingsActivity.class);
                startActivity(i_setting);

            }
        });
        btn_check_answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_Answer = et_answer.getText().toString();
                if (user_Answer.isEmpty()){
                    Toast.makeText(MainActivity.this , "Please enter your answer" , Toast.LENGTH_LONG).show();
                    return;
                }
                sharedPreferences = getSharedPreferences("MySharedPreferences", MODE_PRIVATE);

                String language = sharedPreferences.getString("language", "English");

                if (language.equals("Arabic")) {

                    if (user_Answer.equalsIgnoreCase("sharedpreferences")) {
                        Toast.makeText(MainActivity.this, "إجابة صحيحة ,ممتاز ", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this,"إجابة خاطئة! ", Toast.LENGTH_LONG).show();
                    }

                } else {

                    if (user_Answer.equalsIgnoreCase("sharedpreferences")) {
                        Toast.makeText(MainActivity.this,"Correct, Excellent", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this,"Wrong answer!", Toast.LENGTH_LONG).show();
                    }
                }


            }
        });
        sharedPreferences = getSharedPreferences("MySharedPreferences", MODE_PRIVATE);
        if (!sharedPreferences.contains("language")) {
            Intent i_language = new Intent(MainActivity.this , SettingsActivity.class);
            startActivity(i_language);
            finish();
            return;
        }
        String language = sharedPreferences.getString("language", "English");
        String textSize = sharedPreferences.getString("textSize", "Medium");

        float size;
        if (textSize.equals("Small")) {
            size = 15f;
        }
        else if (textSize.equals("Medium")) {
            size = 20f;
        }
        else {
            size = 25f;
        }
        tv_question_text.setTextSize(size);
        et_answer.setTextSize(size);

        if (language.equals("Arabic")) {
            tv_title.setText("تطبيقي");
            tv_question.setText("السؤال :-");
            tv_question_text.setText("ما الذي يخزن بيانات المفتاح والقيمة الصغيرة؟");
            et_answer.setHint("اكتب إجابتك هنا...");
        }
        else {
            tv_title.setText("My App");
            tv_question.setText("Question :-");
            tv_question_text.setText("What stores small key-value data?");
            et_answer.setHint("Type your answer here.....");
        }
    }

}