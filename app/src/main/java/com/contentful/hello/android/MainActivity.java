package com.contentful.hello.android;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.contentful.java.cda.CDAArray;
import com.contentful.java.cda.CDAClient;
import com.contentful.java.cda.CDAContentType;
import com.contentful.java.cda.CDAEntry;
import com.contentful.java.cda.CDAResource;
import com.contentful.java.cda.CDASpace;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;


public class MainActivity extends AppCompatActivity {


  private TextView messageView,vehicleName,priceValue,priceTypeName,legendTextView,priceRetail,priceInvoice,costBillingTextView,
          cashIncentivesTextView;
  private Button addOptionsButton;
    private LinearLayout linearLayout, dropDownLayout;
    private ScrollView scrollView;
  private SwipeRefreshLayout swipeRefreshLayout;

  private FirebaseFirestore mFirestore;
  private Query mQuery;

  private boolean isFragmentDisplayed = false;

//  private Handler mHandler;
//  private Runnable refresh;

  /*
   * This private variable will be used for formatting the output. It will be set to
   * an empty string to annotate, that it should not have a topmost border. As soon
   * as something gets outputted, this limiter will be set to a border.
   */
  private String limiter = "";

  /**
   * Creates this activity.
   * <p>
   * Since the activity is not shown yet, we ignore this method.
   *
   * @param savedInstanceState arguments saved from the last time this activity opened, not used here.
   */
  @SuppressLint("NewApi")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    messageView = findViewById(R.id.pageHeading);
    vehicleName = findViewById(R.id.vehicleName);
    priceValue = findViewById(R.id.priceValue);
    priceTypeName = findViewById(R.id.priceTypeName);
    legendTextView = findViewById(R.id.legendTextView);
    priceRetail = findViewById(R.id.priceRetail);
    priceInvoice = findViewById(R.id.priceInvoice);
    costBillingTextView = findViewById(R.id.costBillingTextView);

    addOptionsButton = findViewById(R.id.add_options_button);
//    addOptionsTest = findViewById(R.id.add_options_test);

    scrollView = findViewById(R.id.reportScrollView);
    linearLayout = findViewById(R.id.activity_layout);
    dropDownLayout=findViewById(R.id.drop_down_layout);
    swipeRefreshLayout = findViewById(R.id.pullToRefresh);

    costBillingTextView.setElevation((float)15.0);
    addOptionsButton.setElevation((float) 100.0);

//    this.mHandler = new Handler();
//
//    this.mHandler.postDelayed(m_Runnable,5000);
    //    cashIncentivesTextView = findViewById(R.id.cashIncentivesTextView);
//    cashIncentivesTextView.setElevation((float)15.0);

      final String page_heading = "page_heading";
      final String option_button = "options_button";
      final String legend_heading = "legend_heading";
      final String retail_price_info = "retail_price_info";
      final String invoice_price_info = "invoice_price_info";
      final String cost_info_title = "cost_info_subtitle";
      final String base_info_title1 = "Base_info";
      final String freight_info_title1 = "freight_info";
      final String options_info_title = "options_info";
      final String base_info_title2 = "base_info";
      final String freight_info_title2 = "freight_info";
      final String retail_price_heading = "retail_price";
      final String invoice_price_heading = "invoice_price";
      final String total_string = "Total";

      final Gson gson = new GsonBuilder().setPrettyPrinting().create();

      FirebaseFirestore.setLoggingEnabled(true);

      mFirestore = FirebaseFirestore.getInstance();

      mQuery = mFirestore.collection("users");

    costBillingTextView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

//            FragmentManager fm = getSupportFragmentManager();
//            FragmentTransaction ft = fm.beginTransaction();
//            DetailsFragment detailsFragment = (DetailsFragment) fm.findFragmentByTag("tag");
//
//            if(detailsFragment == null) {  // not added
//                detailsFragment = DetailsFragment.newInstance();
//                ft.add(R.id.dropDownFragment, detailsFragment, "tag");
//                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//
//            } else {  // already added
//
//                ft.remove(detailsFragment);
//                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
//            }
//
//            ft.commit();
//            if(!isFragmentDisplayed)
//            {
//                displayFragment();
//            }
//            else
//            {
//                removeFragment();
//            }

            View view = getLayoutInflater().inflate(R.layout.drop_down_details,null);
            costBillingTextView.setVisibility(View.GONE);
            linearLayout.addView(view);
//            displayFragment();

        }
    });

//    addOptionsTest.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            costBillingTextView.setVisibility(View.VISIBLE);
////            dropDownLayout.setVisibility(View.GONE);
//            linearLayout.removeView(dropDownLayout);
////            removeFragment();
//        }
//    });


//      .collection("users2").document("ONLaNaRvu1o6F4XbGTFu")

      addOptionsButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              final Map<String, Object> map = new HashMap<>();
              map.put("Top_Page_Heading",page_heading);
              map.put("Option Button",option_button);
              map.put("Legend Heading",legend_heading);
              map.put("Retail Price Info",retail_price_info);
              map.put("Invoice Price Info",invoice_price_info);
              map.put("Cost Info Title",cost_info_title);
              map.put("Base Info Title1",base_info_title1);
              map.put("Freight Info Title1",freight_info_title1);
              map.put("Options Info Title",options_info_title);
              map.put("Base Info Title2",base_info_title2);
              map.put("Freight Info Title2",freight_info_title2);
              map.put("Retail Price Heading",retail_price_heading);
              map.put("Invoice Price Heading",invoice_price_heading);
              map.put("Total",total_string);

              mFirestore.collection("users").document("jvcq2ed63kyrg9270yg").set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                  @Override
                  public void onSuccess(Void aVoid) {
                      Toast.makeText(getApplicationContext(),"Success : POSTING",Toast.LENGTH_LONG).show();

                  }

              }).addOnFailureListener(new OnFailureListener() {
                  @Override
                  public void onFailure(@NonNull Exception e) {
                      Toast.makeText(getApplicationContext(),"Error : POSTING", Toast.LENGTH_LONG).show();
                  }
              });
          }
      });

      swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
          @Override
          public void onRefresh() {

              DocumentReference reference = mFirestore.collection("users").document("jvcq2ed63kyrg9270yg");
//                      .collection("users2").document("ONLaNaRvu1o6F4XbGTFu");
              reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                  @Override
                  public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                      if(task.isSuccessful())
                      {
                          DocumentSnapshot snapshot = task.getResult();
//                          String resultString = snapshot.get("Name").toString();
                          messageView.setText(snapshot.get("Top_Page_Heading").toString());
                          addOptionsButton.setText(snapshot.get("Option Button").toString());
                          legendTextView.setText(snapshot.get("Legend Heading").toString());
                          priceRetail.setText(snapshot.get("Retail Price Info").toString());
                          priceInvoice.setText(snapshot.get("Invoice Price Info").toString());
                          costBillingTextView.setText(snapshot.get("Cost Info Title").toString());

                          String retailPrice1Text = snapshot.get("Retail Price Heading").toString();
                          String invoicePrice1Text = snapshot.get("Invoice Price Heading").toString();
                          String baseInfoText1 = snapshot.get("Base Info Title1").toString();
                          String freightInfo1 = snapshot.get("Freight Info Title1").toString();
                          String optionsInfoTextView = snapshot.get("Options Info Title").toString();
                          String retailPrice2Text = snapshot.get("Retail Price Heading").toString();
                          String invoicePrice2Text = snapshot.get("Invoice Price Heading").toString();
                          String baseInfoText2 = snapshot.get("Base Info Title2").toString();
                          String freightInfoText2 = snapshot.get("Freight Info Title2").toString();
                          String totalInfoTextView = snapshot.get("Total").toString();

                          Bundle bundle = new Bundle();
                          bundle.putString("sample",retailPrice1Text);
                          DetailsFragment detailsFragment = DetailsFragment.newInstance();
                          detailsFragment.setArguments(bundle);

                          Toast.makeText(getApplicationContext(),"success : FETCHING",Toast.LENGTH_LONG).show();
                      }

                  }
              }).addOnFailureListener(new OnFailureListener() {
                  @Override
                  public void onFailure(@NonNull Exception e) {
                      Toast.makeText(getApplicationContext(),"error : FETCHING",Toast.LENGTH_LONG).show();
                  }
              });

              swipeRefreshLayout.setRefreshing(false);
          }
      });

//      addOptionsTest.setOnClickListener(new View.OnClickListener() {
//          @Override
//          public void onClick(View v) {
//
//              DocumentReference reference = mFirestore.collection("users").document("jvcq2ed63kyrg9270yg")
//                      .collection("users2").document("ONLaNaRvu1o6F4XbGTFu");
//              reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                  @Override
//                  public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//
//                      if(task.isSuccessful())
//                      {
//                          DocumentSnapshot snapshot = task.getResult();
////                          String resultString = snapshot.get("Name").toString();
//                          messageView.setText(snapshot.get("Name").toString());
//                          Toast.makeText(getApplicationContext(),"success : FETCHING",Toast.LENGTH_LONG).show();
//                      }
//
//                  }
//              }).addOnFailureListener(new OnFailureListener() {
//                  @Override
//                  public void onFailure(@NonNull Exception e) {
//                      Toast.makeText(getApplicationContext(),"error : FETCHING",Toast.LENGTH_LONG).show();
//                  }
//              });
//          }
//      });



  }

//    public void initFirestore()
//    {
//
//    }


//    public void displayFragment()
//    {
//        DetailsFragment detailsFragment =DetailsFragment.newInstance();
//
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//        fragmentTransaction.add(R.id.dropDownFragment,detailsFragment).addToBackStack(null).commit();
//
//        isFragmentDisplayed = true;
//    }

//    public void removeFragment()
//    {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//
//        DetailsFragment detailsFragment =(DetailsFragment)fragmentManager.findFragmentById(R.id.dropDownFragment);
//
//        if(detailsFragment != null) {
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.remove(detailsFragment).commit();
//        }
//        else
//        {
//            Toast.makeText(this,"fragment null",Toast.LENGTH_LONG).show();
//        }
//
//        isFragmentDisplayed = false;
//    }

//    @Override
//    public void onFragmentInteraction(Uri uri) {
//        removeFragment();
//        costBillingTextView.setVisibility(View.VISIBLE);
//    }

//    private final Runnable m_Runnable = new Runnable()
//    {
//        public void run()
//
//        {
//            Toast.makeText(this,"in runnable",Toast.LENGTH_SHORT).show();
//
//            refresh.this.mHandler.postDelayed(m_Runnable, 5000);
//        }
//
//    };
}
