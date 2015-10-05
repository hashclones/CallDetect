package com.hashclones.calldetect;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Dyso on 10/4/2015.
 */

public class IncomingCall extends Service {

    BroadcastReceiver callReceiver;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        // get an instance of the receiver in your service
        IntentFilter filter = new IntentFilter();
        filter.addAction("action");
        filter.addAction("anotherAction");
        callReceiver = new PhoneCallBroadcast();
        registerReceiver(callReceiver, filter);
    }

    public class PhoneCallBroadcast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                String state = extras.getString(TelephonyManager.EXTRA_STATE);
                Log.w("MY_DEBUG_TAG", state);
                if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                    String phoneNumber = extras
                            .getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
                    Log.w("MY_DEBUG_TAG", phoneNumber);

                    String text = "Phone Call Details: " + phoneNumber + state;
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        }
        public PhoneCallBroadcast(){

        }
    }

}

