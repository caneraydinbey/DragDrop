package com.example.caneraydin.myapplication;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import java.util.ArrayList;
import java.util.List;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;
import android.view.View.OnTouchListener;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends Activity implements View.OnDragListener, View.OnLongClickListener {


    private ImageView img1, img2, img3;
    String TAG = "Chic";
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "oncreate start");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_lands);

        img1 = (ImageView) findViewById(R.id.img1);
        //img1.setOnLongClickListener(this);
        img2 = (ImageView) findViewById(R.id.img2);
       // img2.setOnLongClickListener(this);
        img3 = (ImageView) findViewById(R.id.img3);
      //  img3.setOnLongClickListener(this);

        img1.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                    v.startDrag(data, shadowBuilder, v, 0);
                   v.setVisibility(View.INVISIBLE);
                    return true;
                } else {
                    return false;
                }
            }
        });

        img2.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                    v.startDrag(data, shadowBuilder, v, 0);
                   v.setVisibility(View.INVISIBLE);
                    return true;
                } else {
                    return false;
                }
            }
        });

        img3.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                    v.startDrag(data, shadowBuilder, v, 0);
                    v.setVisibility(View.INVISIBLE);
                    return true;
                } else {
                    return false;
                }
            }
        });


        btn = (Button) findViewById(R.id.btn);

        img1.setOnDragListener(this);
        img2.setOnDragListener(this);
        img3.setOnDragListener(this);

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                getJSON("http://oep.esy.es/android_get_all.php");

            }
        });

        Log.d(TAG, "oncreate end");
    }


    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
            view.startDrag(data, shadowBuilder, view, 0);
            view.setVisibility(View.INVISIBLE);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean onLongClick(View imageView) {
        Log.d(TAG, "onlongclick start");
        ImageView ddroppedImage = (ImageView)imageView;
        Log.d(TAG, "onlongclick ddroppedImage tag: " + ddroppedImage.getTag());
        Log.d(TAG, "onlongclick ddroppedImage tag: "+imageView.getTag());
//        the ball has been touched
        //            create clip data holding data of the type MIMETYPE_TEXT_PLAIN
        ClipData clipData = ClipData.newPlainText("", "");

        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(imageView);
        /*start the drag - contains the data to be dragged,
            metadata for this data and callback for drawing shadow*/
        imageView.startDrag(clipData, shadowBuilder, imageView, 0);
        //        we're dragging the shadow so make the view invisible
        imageView.setVisibility(View.INVISIBLE);

        Log.d(TAG, "onlongclick end");
        return true;
    }

    public enum ImgTags{
        IMG1,
        IMG2,
        IMG3
    }

    @Override
    public boolean onDrag(View receivingLayoutView, DragEvent dragEvent) {
       // Log.d(TAG, "ondrag start");
      //  View draggedImageView = (View) dragEvent.getLocalState();
      //  ImageView draggedImg = (ImageView) draggedImageView;

       // Log.d(TAG, "ondrag start receiving visibility: "+receivingLayoutView.getVisibility()+" draggged visib "+draggedImageView.getVisibility());
        // Handles each of the expected events
        switch (dragEvent.getAction()) {

            case DragEvent.ACTION_DRAG_STARTED:
              //  Log.d(TAG, "drag action started");

                // Determines if this View can accept the dragged data
                if (dragEvent.getClipDescription()
                        .hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                    Log.d(TAG, "Can accept this data");

                    // returns true to indicate that the View can accept the dragged data.
                    return true;

                } else {
                    Log.d(TAG, "Can not accept this data");

                }

                // Returns false. During the current drag and drop operation, this View will
                // not receive events again until ACTION_DRAG_ENDED is sent.
                return false;

            case DragEvent.ACTION_DRAG_ENTERED:
              //  Log.d(TAG, "drag action entered");
                //                the drag point has entered the bounding box
                return true;

            case DragEvent.ACTION_DRAG_LOCATION:
             //   Log.d(TAG, "drag action location");
            /*triggered after ACTION_DRAG_ENTERED
                stops after ACTION_DRAG_EXITED*/
                return true;

            case DragEvent.ACTION_DRAG_EXITED:
                Log.d(TAG, "drag action exited");
                //                the drag shadow has left the bounding box
                return true;

            case DragEvent.ACTION_DROP:  Log.d(TAG, "drag action droop");
            /* the listener receives this action type when
                  drag shadow released over the target view
            the action only sent here if ACTION_DRAG_STARTED returned true
            return true if successfully handled the drop else false*/
//               RelativeLayout bottomLinearLayout = (RelativeLayout) receivingLayoutView;
              //  ImageView droppedImage = (ImageView) receivingLayoutView;
              //  ImageView droppedTargetImage = (ImageView) draggedImageView;

             //   ViewGroup from = (ViewGroup) draggedImageView.getParent();

                /*Log.d(TAG, "ffrom " +from.toString());
                Log.d(TAG, "ffrom " +from);
               // from.removeAllViews();

                Log.d(TAG, "from " + from.toString());
                Log.d(TAG, "from " +from);

                Log.d(TAG, "ondrag receiving "+receivingLayoutView.getTag()+" - drag event "+((View) dragEvent.getLocalState()).getTag());

                Log.d(TAG, "ondrag draggedimageview tag: "+draggedImageView.getTag());
                ImageView droppedImage = (ImageView) receivingLayoutView;
                ImageView droppedTargetImage = (ImageView) draggedImageView;
                Log.d(TAG, "fdroppedtargetimagetag: "+droppedTargetImage.getTag());

                Log.d(TAG, "fdroppedtargetimagetagx: "+draggedImageView.getTag());

                Log.d(TAG, "fdroppedimagetag: " + droppedImage.getTag());
                Log.d(TAG, "fdroppedimagetagx: " + receivingLayoutView.getTag());
*/
                ImageView dropOntoImg = (ImageView) receivingLayoutView;
 View draggedImageView = (View) dragEvent.getLocalState();
                 // ImageView draggedImg = (ImageView) draggedImageView;

                Log.d(TAG,"droponto: "+receivingLayoutView.getTag()+" dropped: "+draggedImageView.getTag());

                Log.d(TAG,  "swtich önesi "+ dropOntoImg.getId());

                Log.d(TAG,  "swtich önesii "+dropOntoImg.getTag().toString());

               ImgTags imgtag =  ImgTags.valueOf(dropOntoImg.getTag().toString().toUpperCase(Locale.ENGLISH));
                Log.d(TAG,  "swtich ön esi "+imgtag.toString());


                switch (imgtag) {
                    case IMG1:

                       Log.d(TAG,"img1 dragged true "+ draggedImageView.getTag() + "");
                       // if(draggedImageView.getTag().equals(droppedImage.getTag()))
                       // {
                      //      ViewGroup draggedImageViewParentLayout
                          //          = (ViewGroup) draggedImageView.getParent();
                      //      draggedImageViewParentLayout.removeView(draggedImageView);

                        //    bottomLinearLayout.addView(draggedImageView);
                           // draggedImageView.setVisibility(View.VISIBLE);
                        //    imgAnswer.setVisibility(View.GONE);
                          //  nextQuestion();
                      //  }




                        return true;

                    case IMG2:
                     Log.d(TAG,"img2 tiklndi dragged false "+ draggedImageView.getTag() + "");

                      //  if(draggedImageView.getTag().equals(droppedImage.getTag()))
                      //  {
                          //  ViewGroup draggedImageViewParentLayout
                             //       = (ViewGroup) draggedImageView.getParent();
                          //  draggedImageViewParentLayout.removeView(draggedImageView);

                         //   bottomLinearLayout.addView(draggedImageView);
                          //  draggedImageView.setVisibility(View.VISIBLE);
                          //  imgAnswer.setVisibility(View.GONE);
                          //  nextQuestion();
                     //   }
                        return false;


                    default:

                        Log.d(TAG, "daggedimage in default false");
                        return false;
                }

            case DragEvent.ACTION_DRAG_ENDED:




                draggedImageView = (View) dragEvent.getLocalState();
                Log.d(TAG, "drag action ended");
                Log.d(TAG, "getResult: " + dragEvent.getResult());
               // receivingLayoutView.setVisibility(View.VISIBLE);

               // draggedImageView.setVisibility(View.VISIBLE);
                //                if the drop was not successful, set the ball to visible
                if (!dragEvent.getResult()) {
                    Log.d(TAG, "false, setting visible");

                    final View droppedView = (View) dragEvent.getLocalState();
                    droppedView.post(new Runnable() {
                        @Override
                        public void run() {
                            droppedView.setVisibility(View.VISIBLE);
                        }
                    });
                }

                return true;
            // An unknown action type was received.
            default:
                Log.d(TAG, "Unknown action type received by OnDragListener.");
                break;
        }
        return false;


    }







//does get json from url
    private void getJSON(String url) {
        class GetJSON extends AsyncTask<String, Void, String>{
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                Log.d(TAG, "onpre");
                loading = ProgressDialog.show(MainActivity.this, "Please Wait...", null, true, true);
            }

            @Override
            protected String doInBackground(String... params) {
                Log.d(TAG,"doin");
                String uri = params[0];

                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);  Log.d(TAG, "url "+url);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                  //  Log.d(TAG," bufferedReader "+ bufferedReader);
                    String json;
                    while((json = bufferedReader.readLine())!= null){
                        sb.append(json+"\n");//Log.d(TAG, "sb "+sb.toString());
                    }

                    return sb.toString().trim();

                }catch(Exception e){
                    e.printStackTrace();
                    Log.d(TAG, " error");
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
             Log.d(TAG,"json= "+s);
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute(url);
    }





    }

