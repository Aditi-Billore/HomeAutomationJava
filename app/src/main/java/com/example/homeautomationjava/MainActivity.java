package com.example.homeautomationjava;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;


public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_ENABLE_BT = 1;
    private final String UUID_STRING_WELL_KNOWN_SPP =
            "00001101-0000-1000-8000-00805F9B34FB";
    BluetoothDevices BluetoothDevice;
    BluetoothAdapter bluetoothAdapter;
    ArrayList<BluetoothDevices> pairedDevicesNamesSpinner;
    //TextView textInfo;
    TextView textStatus;
    Spinner spinnerPairedDevice;
    LinearLayout inputPane;
    Switch switchBtn1,switchBtn2,switchBtn3,switchBtn4,switchBtnAllOff;
    ImageView imageAppliance1,imageAppliance2,imageAppliance3,imageAppliance4,imageAllOff;
    Button btnConnect,btnDisconnect;
    ArrayAdapter<BluetoothDevices> pairedDeviceAdapterSpinner;
    ThreadConnectBTdevice myThreadConnectBTdevice;
    ThreadConnected myThreadConnected;
    BluetoothSocket connectedBluetoothSocket;
    InputStream connectedInputStream;
    OutputStream connectedOutputStream;

    private UUID myUUID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // textInfo = (TextView)findViewById(R.id.info);
        textStatus = (TextView) findViewById(R.id.status);
        spinnerPairedDevice = (Spinner) findViewById(R.id.pairedlistSpinner);
        inputPane = (LinearLayout) findViewById(R.id.inputpane);

        btnConnect = (Button) findViewById(R.id.connect);
        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myThreadConnectBTdevice = new ThreadConnectBTdevice(BluetoothDevice.getDevice());
                myThreadConnectBTdevice.start();
            }
        });

        btnDisconnect = (Button) findViewById(R.id.disconnect);
        btnDisconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Disconnect Bluetooth","Disconnecting Bluetooth");
                if (myThreadConnectBTdevice != null) {
                    myThreadConnectBTdevice.cancel();
                }
            }
        });

        imageAppliance1 = (ImageView) findViewById(R.id.imageAppliance1);
        switchBtn1 = (Switch) findViewById(R.id.switchBtn1);
        switchBtn1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked)
                {
                    if (myThreadConnected != null) {
                        int data = 101;
                        myThreadConnected.write(data);
                        imageAppliance1.setImageResource(R.drawable.ic_blur_on_black_24dp);
                    }else{
                        Toast.makeText(MainActivity.this,
                                "Please connect a bluetooth device.",
                                Toast.LENGTH_LONG).show();
                        switchBtn1.setChecked(false);
                    }
                }
                else
                {
                    if (myThreadConnected != null) {
                        int data = 102;
                        myThreadConnected.write(data);
                        imageAppliance1.setImageResource(R.drawable.ic_blur_off_black_24dp);
                    }else{
                        Toast.makeText(MainActivity.this,
                                "Please connect a bluetooth device.",
                                Toast.LENGTH_LONG).show();
                        switchBtn1.setChecked(true);
                    }
                }
            }
        });

        imageAppliance2 = (ImageView) findViewById(R.id.imageAppliance2);
        switchBtn2 = (Switch) findViewById(R.id.switchBtn2);
        switchBtn2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked)
                {
                    if (myThreadConnected != null) {
                        int data = 103;
                        myThreadConnected.write(data);
                        imageAppliance2.setImageResource(R.drawable.ic_blur_on_black_24dp);
                    }else{
                        Toast.makeText(MainActivity.this,
                                "Please connect a bluetooth device.",
                                Toast.LENGTH_LONG).show();
                        switchBtn2.setChecked(false);
                    }
                }
                else
                {
                    if (myThreadConnected != null) {
                        int data = 104;
                        myThreadConnected.write(data);
                        imageAppliance2.setImageResource(R.drawable.ic_blur_off_black_24dp);
                    }else{
                        Toast.makeText(MainActivity.this,
                                "Please connect a bluetooth device.",
                                Toast.LENGTH_LONG).show();
                        switchBtn2.setChecked(true);
                    }
                }
            }
        });

        imageAppliance3 = (ImageView) findViewById(R.id.imageAppliance3);
        switchBtn3 = (Switch) findViewById(R.id.switchBtn3);
        switchBtn3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked)
                {
                    if (myThreadConnected != null) {
                        int data = 105;
                        myThreadConnected.write(data);
                        imageAppliance3.setImageResource(R.drawable.ic_blur_on_black_24dp);
                    }else{
                        Toast.makeText(MainActivity.this,
                                "Please connect a bluetooth device.",
                                Toast.LENGTH_LONG).show();
                        switchBtn3.setChecked(false);
                    }
                }
                else
                {
                    if (myThreadConnected != null) {
                        int data = 106;
                        myThreadConnected.write(data);
                        imageAppliance3.setImageResource(R.drawable.ic_blur_off_black_24dp);
                    }else{
                        Toast.makeText(MainActivity.this,
                                "Please connect a bluetooth device.",
                                Toast.LENGTH_LONG).show();
                        switchBtn3.setChecked(true);
                    }
                }
            }
        });

        imageAppliance4 = (ImageView) findViewById(R.id.imageAppliance4);
        switchBtn4 = (Switch) findViewById(R.id.switchBtn4);
        switchBtn4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked)
                {
                    if (myThreadConnected != null) {
                        int data = 107;
                        myThreadConnected.write(data);
                        imageAppliance4.setImageResource(R.drawable.ic_blur_on_black_24dp);
                    }else{
                        Toast.makeText(MainActivity.this,
                                "Please connect a bluetooth device.",
                                Toast.LENGTH_LONG).show();
                        switchBtn4.setChecked(false);
                    }
                }
                else
                {
                    if (myThreadConnected != null) {
                        int data = 108;
                        myThreadConnected.write(data);
                        imageAppliance4.setImageResource(R.drawable.ic_blur_off_black_24dp);
                    }else{
                        Toast.makeText(MainActivity.this,
                                "Please connect a bluetooth device.",
                                Toast.LENGTH_LONG).show();
                        switchBtn4.setChecked(true);
                    }
                }
            }
        });

        imageAllOff = (ImageView) findViewById(R.id.imageAllOff);
        switchBtnAllOff = (Switch) findViewById(R.id.switchBtnAllOff);
        switchBtnAllOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked)
                {
                    if (myThreadConnected != null) {
                        int[] data = new int[]{102,104,106,108};
                        for(int i =0;i<4;i++) {
                            myThreadConnected.write(data[i]);

                        }
                        imageAppliance1.setImageResource(R.drawable.ic_blur_off_black_24dp);
                        imageAppliance2.setImageResource(R.drawable.ic_blur_off_black_24dp);
                        imageAppliance3.setImageResource(R.drawable.ic_blur_off_black_24dp);
                        imageAppliance4.setImageResource(R.drawable.ic_blur_off_black_24dp);
                        switchBtn1.setChecked(false);
                        switchBtn2.setChecked(false);
                        switchBtn3.setChecked(false);
                        switchBtn4.setChecked(false);

                    }
                    else{
                        Toast.makeText(MainActivity.this,
                                "Please connect a bluetooth device.",
                                Toast.LENGTH_LONG).show();
                        switchBtnAllOff.setChecked(false);
                    }
                }
            }
        });



        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH)) {
            Toast.makeText(this,
                    "FEATURE_BLUETOOTH NOT support",
                    Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        //using the well-known SPP UUID
        myUUID = UUID.fromString(UUID_STRING_WELL_KNOWN_SPP);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            Toast.makeText(this,
                    "Bluetooth is not supported on this hardware platform",
                    Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        String stInfo = bluetoothAdapter.getName() + "\n" +
                bluetoothAdapter.getAddress();
        //* textInfo.setText(stInfo);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Turn ON BlueTooth if it is OFF
        if (!bluetoothAdapter.isEnabled()) {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
        }

        setup();

    }

    private void setup() {
        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
        if (pairedDevices.size() > 0) {
            pairedDevicesNamesSpinner = new ArrayList<BluetoothDevices>();
            try {
                for (BluetoothDevice device : pairedDevices) {
                    String deviceName = device.getName();
                    pairedDevicesNamesSpinner.add(new BluetoothDevices(device, deviceName));
                    Log.d("Device Creation","Adding New Device! : "+deviceName);
                }
            }catch (NullPointerException e){
                e.printStackTrace();
            }

            pairedDeviceAdapterSpinner = new ArrayAdapter<BluetoothDevices>(this, android.R.layout.simple_spinner_dropdown_item, pairedDevicesNamesSpinner);
            spinnerPairedDevice.setAdapter(pairedDeviceAdapterSpinner);
            spinnerPairedDevice.setPrompt("Select a Bluetooth device");
            spinnerPairedDevice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    BluetoothDevice = (BluetoothDevices) parent.getSelectedItem();

//                    Toast.makeText(MainActivity.this,
//                            "Name: " + BluetoothDevice.getDevice().getName() + "\n"
//                                    + "Address: " + BluetoothDevice.getDevice().getAddress() + "\n"
//                                    + "BondState: " + BluetoothDevice.getDevice().getBondState() + "\n"
//                                    + "BluetoothClass: " + BluetoothDevice.getDevice().getBluetoothClass() + "\n"
//                                    + "Class: " + BluetoothDevice.getDevice().getClass(),
//                            Toast.LENGTH_LONG).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d("Destroying App","Destroy algo running");
        if (myThreadConnectBTdevice != null) {
            myThreadConnectBTdevice.cancel();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == Activity.RESULT_OK) {
                setup();
            } else {
                Toast.makeText(this,
                        "BlueTooth NOT enabled",
                        Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    //Called in ThreadConnectBTdevice once connect successed
    //to start ThreadConnected
    private void startThreadConnected(BluetoothSocket socket) {

        myThreadConnected = new ThreadConnected(socket);
        myThreadConnected.start();
    }

    /*
    ThreadConnectBTdevice:
    Background Thread to handle BlueTooth connecting
    */
    public class ThreadConnectBTdevice extends Thread {

        private final BluetoothDevice bluetoothDevice;
        private BluetoothSocket bluetoothSocket = null;


        private ThreadConnectBTdevice(BluetoothDevice device) {
            bluetoothDevice = device;

            try {
                bluetoothSocket = device.createRfcommSocketToServiceRecord(myUUID);
                // textStatus.setText("bluetoothSocket: \n" + bluetoothSocket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            boolean success = false;
            try {
                bluetoothSocket.connect();
                success = true;
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        textStatus.setText("Connected!");
                        Toast.makeText(MainActivity.this,
                                "Name: " + BluetoothDevice.getDevice().getName() + "\n"
                                        + "Address: " + BluetoothDevice.getDevice().getAddress(),
                                Toast.LENGTH_LONG).show();

                    }
                });
            } catch (IOException e) {
                e.printStackTrace();

                final String eMessage = e.getMessage();
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        textStatus.setText("Problem occurred with bluetoothSocket.connect(): \n" + eMessage);
                    }
                });

                try {
                    bluetoothSocket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            if (success) {
                //connect successful
                /*final String msgconnected = "connect successful:\n"
                        + "BluetoothSocket: " + bluetoothSocket + "\n"
                        + "BluetoothDevice: " + bluetoothDevice;*/

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        textStatus.setText("Connected!");

                        inputPane.setVisibility(View.VISIBLE);
                    }
                });

                startThreadConnected(bluetoothSocket);
            } else {
                //fail
            }
        }

        public void cancel() {
            if (myThreadConnected != null) {
                int[] data = new int[]{102,104,106,108};
                for(int i =0;i<4;i++) {
                    myThreadConnected.write(data[i]);
                }
                imageAppliance1.setImageResource(R.drawable.ic_blur_off_black_24dp);
                imageAppliance2.setImageResource(R.drawable.ic_blur_off_black_24dp);
                imageAppliance3.setImageResource(R.drawable.ic_blur_off_black_24dp);
                imageAppliance4.setImageResource(R.drawable.ic_blur_off_black_24dp);
                switchBtn1.setChecked(false);
                switchBtn2.setChecked(false);
                switchBtn3.setChecked(false);
                switchBtn4.setChecked(false);
                switchBtnAllOff.setChecked(false);
            }
            Toast.makeText(getApplicationContext(),
                    "close bluetoothSocket",
                    Toast.LENGTH_LONG).show();

            try {
                    connectedInputStream.close();
                    connectedOutputStream.close();
                    myThreadConnected = null;

                bluetoothSocket.close();
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        textStatus.setText("Disconnected!");
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    /*
    ThreadConnected:
    Background Thread to handle Bluetooth data communication
    after connected
     */
    private class ThreadConnected extends Thread {

        public ThreadConnected(BluetoothSocket socket) {
            connectedBluetoothSocket = socket;
            InputStream in = null;
            OutputStream out = null;

            try {
                in = socket.getInputStream();
                out = socket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }

            connectedInputStream = in;
            connectedOutputStream = out;
        }

        @Override
        public void run() {
            byte[] buffer = new byte[1024];
            int bytes;

            while (true) {
                try {

                    bytes = connectedInputStream.read(buffer);
                    String strReceived = new String(buffer, 0, bytes);
                    /*final String msgReceived = String.valueOf(bytes) +
                            " bytes received:\n"
                            + strReceived;
*/
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            // textStatus.setText(msgReceived);
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();

                    final String msgConnectionLost = "Connection lost:\n"
                            + e.getMessage();
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
//                            textStatus.setText(msgConnectionLost);
                        }
                    });
                }
            }
        }

        public void write(int buffer) {
            try {
                connectedOutputStream.write(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        public void cancel() {
//            try {
//                connectedBluetoothSocket.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }

}