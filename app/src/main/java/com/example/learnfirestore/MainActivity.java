package com.example.learnfirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity
{

    ArrayList<Object> test = new ArrayList<Object>();
    ArrayList<Object> test1 = new ArrayList<Object>();
    ArrayList<Names> test2 = new ArrayList<Names>();

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        // Create a new user with a first and last name
//        Map<String, Object> user = new HashMap<>();
//        user.put("first", "Alan");
//        user.put("middle", "Mathison");
//        user.put("last", "Turing");
//        user.put("born", 1912);
//
//        // Add a new document with a generated ID
//        db.collection("users")
//                .add(user)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w(TAG, "Error adding document", e);
//                    }
//                });

        

        db.collection("users")
            .get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Log.d(TAG, document.getId() + " => " + document.getData());
                            //List<String> group = (List<String>) document.get()
                            //Log.d(TAG, "onComplete: TEST " + document.get("first"));
                            test.add(document.get("first"));
                            //https://stackoverflow.com/questions/50233281/how-to-get-an-array-from-firestore
                            test1.add(document.getData());

//                            String firstName = String.valueOf(document.get("first"));
//                            String lastName = String.valueOf(document.get("last"));
//                            int birthYear = (int) document.get("born");

                            //Log.d(TAG, "onComplete: firstName = " + firstName + ", lastName = " + lastName + ", birthYear = " + birthYear);


                            Names name = document.toObject(Names.class);

                            name.setId(document.getId());

                            test2.add(name);

                            logArrayContants();
                        }
                    } else {
                        Log.w(TAG, "Error getting documents.", task.getException());
                    }
                }
            });




    }
    
    public void logArrayContants()
    {
        Log.d(TAG, "logArrayContants: Called");
        for (int i = 0; i < test.size(); i++)
        {

            Log.d(TAG, "onCreate: Array list in " + i + " = " + test.get(i) );
            Log.d(TAG, "onCreate: Array list in test1 " + i + " = " + test1.get(i) );
            //Log.d(TAG, "onCreate: Array list in test1 " + i + " = " + test1.get(i.get("first") );

            HashMap<String, Object> tmpData = (HashMap<String, Object>) test1.get(i);
            Log.d(TAG, "logArrayContants: first in " + i + " = " + tmpData.get("first"));
            Log.d(TAG, "logArrayContants: last in " + i + " = " + tmpData.get("last"));
            Log.d(TAG, "logArrayContants: born in " + i + " = " + tmpData.get("born"));

            Log.d(TAG, "logArrayContants: testing testArrayList2");
            Log.d(TAG, "logArrayContants: !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            Log.d(TAG, "logArrayContants: first is " + test2.get(i).getFirst() + ", last is " + test2.get(i).getLast() + ", born is " + test2.get(i).getBorn() + ", ID is " + test2.get(i).getId());
            Log.d(TAG, "logArrayContants: called here");
        }
    }

}
