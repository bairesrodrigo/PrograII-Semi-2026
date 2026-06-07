package com.example.miprimeraapp;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

public class chats extends Activity {

    ImageView img;
    TextView tempVal, txtMsg;
    ImageButton btn;
    String to="", from="", user="", msg="", urlFoto="", urlCompletaFotoFirestore="";
    DatabaseReference databaseReference;
    private chatsArrayAdapter chatArrayAdapter;
    ListView ltsChats;

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter(MyFirebaseMessagingService.DISPLAY_MESSAGE_ACTION);
        //LocalBroadcastManager.getInstance(this).registerReceiver(notificacionPush, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //LocalBroadcastManager.getInstance(this).unregisterReceiver(notificacionPush);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);

        try {
            img = findViewById(R.id.imgAtras);
            img.setOnClickListener(view -> {
                abrirVentana();
            });
            txtMsg = findViewById(R.id.txtMsgChats);
            txtMsg.setOnKeyListener((v, keyCode, event)->{
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    try {
                        guardarMsgFirebase(txtMsg.getText().toString());
                        sendChatMessage(false, txtMsg.getText().toString());
                    }catch (Exception e){
                        mostrarMsg(e.getMessage());
                    }
                }
                return false;
            });
            tempVal = findViewById(R.id.lblToChats);
            Bundle parametros = getIntent().getExtras();
            if (parametros != null && parametros.getString("to") != null && parametros.getString("to") != "") {
                to = parametros.getString("to");
                from = parametros.getString("from");
                user = parametros.getString("nombre");
                urlFoto = parametros.getString("urlFoto");
                urlCompletaFotoFirestore = parametros.getString("urlCompletaFotoFirestore");
                tempVal.setText(user);
            }
            mostrarFoto();
            enviarMsg();
            ltsChats = findViewById(R.id.ltsChats);

            chatArrayAdapter = new chatsArrayAdapter(getApplicationContext(), R.layout.msgizquierda);
            ltsChats.setAdapter(chatArrayAdapter);
            historialMsg();
        }catch (Exception e){
            mostrarMsg("Error al cargar la ventana de chats: "+ e.getMessage());
        }
    }
}
