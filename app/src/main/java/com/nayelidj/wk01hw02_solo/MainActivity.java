package com.nayelidj.wk01hw02_solo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private LibDB ldb;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ldb = LibDB.getInstance(this);
        ldb.seed();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.text_view_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }
                List<Post> posts = response.body();
                for (Post post : posts) {
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "User ID: " + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Text: " + post.getText() + "\n\n";
                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
    public void login(View view){
        Intent intent = new Intent(this, Landing.class);
        EditText username = findViewById(R.id.username);
        EditText pass = findViewById(R.id.password);
        String user = String.valueOf(username.getText());
        String password = String.valueOf(pass.getText());
        int duration = Toast.LENGTH_LONG;
        String emptyField = "Username is required!";

        List<User> userE = ldb.Lib().getUser(user);

        if (userE.isEmpty()){
            Toast.makeText(getApplicationContext(), emptyField, duration).show();
        }
        else if(!userE.isEmpty()){
            Toast.makeText(this, "Welcome " + userE, Toast.LENGTH_LONG).show();
            startActivity(intent);
        }
    }
}