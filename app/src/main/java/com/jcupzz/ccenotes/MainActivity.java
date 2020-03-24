package com.jcupzz.ccenotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.model.Document;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FirebaseFirestore db;
    RecyclerView mRecyclerView;
    ArrayList<DownModel> downModelArrayList = new ArrayList<>();
    MyAdapter myAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        /*
        if(STwoSubjects.s2_btn_physics.isPressed())
        {
            var = "Physics";
        }
        if(STwoSubjects.s2_btn_chemistry.isPressed()) {
            var = "Chemistry";
        }
        if(STwoSubjects.s2_btn_graphics.isPressed()){
            var="Graphics";
        }
        if(STwoSubjects.s2_btn_basics_of_mechanics.isPressed()){
            var="Mechanics";
        }
        if(STwoSubjects.s2_btn_basics_of_mechanical.isPressed()){
            var="Mechanical";
        }
        if(STwoSubjects.s2_btn_basics_of_electrical.isPressed()){
            var="Electrical";
        }
        if(STwoSubjects.s2_btn_basics_of_electronics.isPressed()){
            var="Electronics";
        }
        if(STwoSubjects.s2_btn_mathematics.isPressed()){
            var="Mathematics";
        }
        if(STwoSubjects.s2_btn_cs.isPressed()){
            var="Cs";
        }
        if(STwoSubjects.s2_btn_basics_of_civil.isPressed()){
            var="Civil";
        }
*/

        setUpFB();
        setUpRV();
        dataFromFirebase();


    }

    private void dataFromFirebase() {
        if(downModelArrayList.size()>0)
            downModelArrayList.clear();

        //db=FirebaseFirestore.getInstance();


db.collection(STwoSubjects.var)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for(DocumentSnapshot documentSnapshot: task.getResult()) {

                            DownModel downModel= new DownModel(documentSnapshot.getString("name"),
                                    documentSnapshot.getString("link"));
                            downModelArrayList.add(downModel);

                        }

                        myAdapter= new MyAdapter(MainActivity.this,downModelArrayList);
                        mRecyclerView.setAdapter(myAdapter);
                    }
                })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Error ;-.-;", Toast.LENGTH_SHORT).show();
                    }
                })
        ;


    }

    private void setUpFB(){
        db=FirebaseFirestore.getInstance();
    }

    private void setUpRV(){
        mRecyclerView= findViewById(R.id.recycle);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
