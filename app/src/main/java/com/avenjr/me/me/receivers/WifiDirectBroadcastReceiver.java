package com.avenjr.me.me.receivers;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.widget.Toast;

import com.avenjr.me.me.ui.activity.NavigationActivity;
import com.avenjr.me.me.ui.fragments.ProfileFragment;

public class WifiDirectBroadcastReceiver extends BroadcastReceiver {

    private WifiP2pManager wifiP2pManager;
    private WifiP2pManager.Channel channel;
    private ProfileFragment fragment;

    public WifiDirectBroadcastReceiver(WifiP2pManager wifiP2pManager, WifiP2pManager.Channel channel, ProfileFragment activity) {
        this.wifiP2pManager = wifiP2pManager;
        this.channel = channel;
        this.fragment= activity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {
            int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);

            // Returns the state of the Wifi connection.
            if (state==WifiP2pManager.WIFI_P2P_STATE_ENABLED) {
                Toast.makeText(context, "ON", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "OFF", Toast.LENGTH_SHORT).show();
            }

        } else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {

            if (wifiP2pManager!=null) {
                wifiP2pManager.requestPeers(channel, fragment.peerListListener);
            }

        } else if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {

        } else if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action)) {

        }
    }
}
