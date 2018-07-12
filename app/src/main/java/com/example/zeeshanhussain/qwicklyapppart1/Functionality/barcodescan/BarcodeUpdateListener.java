package com.example.zeeshanhussain.qwicklyapppart1.Functionality.barcodescan;

import android.support.annotation.UiThread;

import com.google.android.gms.vision.barcode.Barcode;

interface BarcodeUpdateListener {
@UiThread
    void onBarcodeDetected(Barcode barcode);
}
