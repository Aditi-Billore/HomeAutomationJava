package com.example.homeautomationjava;

import android.bluetooth.BluetoothDevice;

public class BluetoothDevices {
    private BluetoothDevice device;
    private String name;

    public BluetoothDevices(BluetoothDevice device, String name) {
        this.device = device;
        this.name = name;
    }


    public BluetoothDevice getDevice() {
        return device;
    }

    public void setDevice(BluetoothDevice device) {
        this.device = device;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    //to display object as a string in spinner
    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof BluetoothDevices){
            BluetoothDevices btd = (BluetoothDevices )obj;
            if(btd.getName().equals(name) && btd.getDevice()==device ) return true;
        }

        return false;
    }
}
