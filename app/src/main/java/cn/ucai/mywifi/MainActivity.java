package cn.ucai.mywifi;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button startButton;
    Button shutupButton;
    Button checkoutButton;

    String src=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        startButton.setOnClickListener(new StartWifiListener());
        shutupButton.setOnClickListener(new shutdownWifiListener());
        checkoutButton.setOnClickListener(new checkoutWifiListener());
    }

    private void initView() {
        startButton= (Button) findViewById(R.id.StartWifiButton);
        shutupButton = (Button) findViewById(R.id.ShutdownWifiButton);
        checkoutButton = (Button) findViewById(R.id.CheckoutButton);
    }

    private class StartWifiListener implements View.OnClickListener {
         WifiManager  wifiManager = (WifiManager) MainActivity.this.getSystemService(Context.WIFI_SERVICE);

        @Override
        public void onClick(View v) {
            if (wifiManager.setWifiEnabled(true)) {
                src = "startwifi";
        } else {
            src = "startwififailed";
        }
                    Toast.makeText(MainActivity.this, src, Toast.LENGTH_LONG).show();
        }
    }

    private class shutdownWifiListener implements View.OnClickListener {
          WifiManager  wifiManager = (WifiManager) MainActivity.this.getSystemService(Context.WIFI_SERVICE);

        @Override
        public void onClick(View v) {
            if (wifiManager.setWifiEnabled(false)) {
                src = "关闭wifi服务成功";
            } else {
                src = "关闭wifi服务失败";
            }
            Toast.makeText(MainActivity.this,src,Toast.LENGTH_LONG).show();
            }

        }


     private class checkoutWifiListener implements View.OnClickListener {
         WifiManager  wifiManager = (WifiManager) MainActivity.this.getSystemService(Context.WIFI_SERVICE);

         @Override
        public void onClick(View v) {
            switch (wifiManager.getWifiState()) {
                case WifiManager.WIFI_STATE_DISABLED:
                    src = "wifi close";
                    break;
                case WifiManager.WIFI_STATE_DISABLING:
                    src = "wifi is close";
                    break;
                case WifiManager.WIFI_STATE_ENABLED:
                    src = "wifi is using";
                    break;
                case WifiManager.WIFI_STATE_ENABLING:
                    src = "wifi is starting ";
                    break;
                case WifiManager.WIFI_STATE_UNKNOWN:
                    src = "未知wifi 状态";
                    break;

            }
            Toast.makeText(MainActivity.this,src,Toast.LENGTH_LONG).show();

        }
    }

}
