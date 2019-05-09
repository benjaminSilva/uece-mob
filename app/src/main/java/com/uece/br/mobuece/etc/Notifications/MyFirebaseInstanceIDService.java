package com.uece.br.mobuece.etc.Notifications;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by jeancarlos on 11/29/16.
 * Classe responsável por manipular os tokens e ids dos devices para o FCM
 */


public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "FIREBASE_TOKEN";


    @Override
    public void onCreate() {
        super.onCreate();
        onTokenRefresh();
    }

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        Log.d(TAG, "Refreshed token: " + refreshedToken);
        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
//        sendRegistrationToServer(refreshedToken);
    }

    /*private void sendRegistrationToServer(String refreshedToken) {
        String userToken = (String) CacheUtil.retrieveObject(getApplicationContext(), User.TOKEN_CACHE_TAG);
        new NotificationPresenter().registerDevice(userToken, createJson(refreshedToken));
    }*/

    /*public JsonObject createJson(String token) {
        final JsonObject deviceData = new JsonObject();
        final JsonObject data = new JsonObject();

        deviceData.addProperty("uuid", Settings.Secure.getString(getApplication().getContentResolver(), Settings.Secure.ANDROID_ID));
        deviceData.addProperty("name", android.os.Build.MODEL);
        deviceData.addProperty("device_type", "2");
        deviceData.addProperty("device_token", token);

        data.add("deviceData", deviceData);
        return data;
    }*/
}
