package com.example.wetok.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wetok.R;
import com.example.wetok.bean.Post;
import com.example.wetok.bean.User;
import com.example.wetok.dao.CurrentUser;
import com.example.wetok.dao.PostDao;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * This is the SendPostActivity page
 * @author Zhaoting Jiang
 * @author Yuxin Hong
 * @author Xinyue Hu
 */
public class SendPostActivity extends AppCompatActivity {
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = SendPostActivity.this;
        User user = CurrentUser.current_user;
        setContentView(R.layout.activity_sendpost);

        setTitle("Send Post");
        Button btnSend = findViewById(R.id.btn_post_send);
        EditText content = findViewById(R.id.post_send_content);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // send post
                String postContent = content.getText().toString();

                //Check user's mode
                if(CurrentUser.current_user == null) {
                    Toast.makeText(context,"Please login first!",Toast.LENGTH_LONG).show();
                }else{
                    PostDao.addPost(postContent);
                }

                setResult(0, new Intent());
                finish();
            }
        });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
