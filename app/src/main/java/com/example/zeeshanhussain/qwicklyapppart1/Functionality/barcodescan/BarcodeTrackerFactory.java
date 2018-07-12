package com.example.zeeshanhussain.qwicklyapppart1.Functionality.barcodescan;

import android.content.Context;

import com.example.zeeshanhussain.qwicklyapppart1.Functionality.ui.camera.GraphicOverlay;
import com.google.android.gms.vision.MultiProcessor;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.barcode.Barcode;


 class BarcodeTrackerFactory implements MultiProcessor.Factory<Barcode> {

     private GraphicOverlay<BarcodeGraphic> mGraphicOverlay;
     private Context context;

     public  BarcodeTrackerFactory(GraphicOverlay<BarcodeGraphic> mGraphicOverlay,Context context) {
         this.mGraphicOverlay= mGraphicOverlay;
         this.context=context;

     }
     @Override
     public Tracker<Barcode> create(Barcode barcode) {
      BarcodeGraphic graphic = new BarcodeGraphic(mGraphicOverlay);


         return new BarcodeGraphicTracker(mGraphicOverlay,graphic,context);
     }
 }
