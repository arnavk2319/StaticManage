package com.contentful.hello.android;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class SignInFragment extends Fragment {

    TextView pageTitle, contentTitle, contentDetails, contentURL, contentNumber;

    private SwipeRefreshLayout swipeRefresh;

    FirebaseFirestore db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.sign_in_fragment, container, false);

        pageTitle = view.findViewById(R.id.txtview_pageTitle);
        contentTitle = view.findViewById(R.id.txtview_title);
        contentDetails = view.findViewById(R.id.txtview_details);
        contentURL = view.findViewById(R.id.txtview_url);
        contentNumber = view.findViewById(R.id.txtview_phoneNumber);

        swipeRefresh = view.findViewById(R.id.pullToRefresh);

        db = FirebaseFirestore.getInstance();


        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                DocumentReference reference = db.collection("users").document("static_page");
                reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        if (task.isSuccessful()) {
                            DocumentSnapshot snapshot = task.getResult();
                            pageTitle.setText(snapshot.get("page_title").toString());
                            contentTitle.setText(snapshot.get("content_title").toString());
                            contentDetails.setText(snapshot.get("content_details").toString());
                            contentURL.setText(snapshot.get("content_url").toString());
                            contentNumber.setText(snapshot.get("content_number").toString());

                            Toast.makeText(getContext(), "success : FETCHING", Toast.LENGTH_LONG).show();
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "error : FETCHING", Toast.LENGTH_LONG).show();
                    }
                });

                swipeRefresh.setRefreshing(false);
            }
        });



        return view;


    }


    @Override
    public void onStart() {
        super.onStart();

        /*Map<String, Object> user = new HashMap<>();
        user.put("page_title", pageTitle.getText().toString());
        user.put("content_title", contentTitle.getText().toString());
        user.put("content_details", contentDetails.getText().toString());
        user.put("content_url", contentURL.getText().toString());
        user.put("content_number", contentNumber.getText().toString());

// Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getContext(), documentReference.getId(), Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Error: "+e, Toast.LENGTH_SHORT).show();

                    }
                });
*/

        final DocumentReference docRef = db.collection("users").document("static_page");
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w("Listen failed.", e);
                    return;
                }

                if (snapshot != null && snapshot.exists()) {

                    pageTitle.setText(snapshot.get("page_title").toString());
                    contentTitle.setText(snapshot.get("content_title").toString());
                    contentDetails.setText(snapshot.get("content_details").toString());
                    contentURL.setText(snapshot.get("content_url").toString());
                    contentNumber.setText(snapshot.get("content_number").toString());
                    Toast.makeText(getContext(), "Current data: " + snapshot.getData(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Current data: null", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }






}
