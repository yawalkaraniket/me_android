package com.avenjr.me.me.ui.fragments;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.avenjr.me.me.R;
import com.avenjr.me.me.receivers.WifiDirectBroadcastReceiver;
import com.avenjr.me.me.ui.views.CustomTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.avenjr.me.me.ui.Utils.UiUtil.getScreenHeightInDp;
import static com.avenjr.me.me.ui.Utils.UiUtil.getScreenWidthInPixel;

public class ProfileFragment extends Fragment {

    @BindView(R.id.share_button)
    CustomTextView shareButton;

    @BindView(R.id.profile_background)
    ImageView profileBackground;

    @BindView(R.id.profile_image)
    CircleImageView profileImage;

    @BindView(R.id.profile_image_layout)
    RelativeLayout profileImageParentLayout;

    private WifiManager wifiManager;
    private WifiP2pManager wifiP2pManager;
    private WifiP2pManager.Channel channel;
    private BroadcastReceiver broadcastReceiver;
    private IntentFilter intentFilter;
    List<WifiP2pDevice> wifiP2pDevices = new ArrayList<WifiP2pDevice>();
    String[] deviceNameArray;
    WifiP2pDevice device;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);

        initializeWifiReceiver();

        setProfileImageLayout();

        return view;
    }

    public WifiP2pManager.PeerListListener peerListListener = new WifiP2pManager.PeerListListener() {
        @Override
        public void onPeersAvailable(WifiP2pDeviceList wifiP2pDeviceList) {
            Log.e("**Available devices", wifiP2pDeviceList.getDeviceList().toString());
//            if (!wifiP2pDeviceList.getDeviceList().equals(wifiP2pDevices)) {
//                wifiP2pDevices.clear();
//                wifiP2pDevices.addAll(wifiP2pDeviceList.getDeviceList());
//            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        this.getActivity().registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    public void onPause() {
        super.onPause();
        this.getActivity().unregisterReceiver(broadcastReceiver);
    }

    private void initializeWifiReceiver() {
        this.wifiManager = (WifiManager) this.getActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        this.wifiP2pManager = (WifiP2pManager) getActivity().getSystemService(Context.WIFI_P2P_SERVICE);
        this.channel = wifiP2pManager.initialize(this.getContext(), Looper.getMainLooper(), null);
        this.broadcastReceiver = new WifiDirectBroadcastReceiver(wifiP2pManager, channel, ProfileFragment.this);
        this.intentFilter = new IntentFilter();

        intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);
    }

    @OnClick(R.id.share_button)
    public void share() {
        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }
        searchDevices();
    }

    private void searchDevices() {
        wifiP2pManager.discoverPeers(channel, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(getActivity(), "Discovery Started", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int i) {
                Toast.makeText(getActivity(), "Discovery Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setProfileImageLayout() {
        profileImageParentLayout.setLayoutParams(getImageLayoutParams());
    }

    private RelativeLayout.LayoutParams getImageLayoutParams() {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        int width = getScreenWidthInPixel(this.getContext()) / 3;
        int height = (int) (getScreenHeightInDp(this.getContext()) / 1.5);
        params.height = width;
        params.width = width;
        params.setMargins(0, height, 0, 0);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        return params;
    }

}
