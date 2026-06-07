package com.example.miprimeraapp;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import java.util.concurrent.atomic.AtomicReference;

public class MyfirebaseInstanceIDService extends FirebaseMessagingService {

    public String obtenerToken() {
        // Correcto: Se inicializa el contenedor, no se deja en null
        AtomicReference<String> token = new AtomicReference<>("");

        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult() != null) {
                token.set(task.getResult());
            }
        });

        return token.get();
    }
}
