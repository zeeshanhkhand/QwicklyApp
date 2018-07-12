package com.example.zeeshanhussain.qwicklyapppart1.Functionality.barcodescan;

import android.content.Context;
import android.support.annotation.UiThread;

import com.example.zeeshanhussain.qwicklyapppart1.Functionality.ui.camera.GraphicOverlay;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.barcode.Barcode;



public class BarcodeGraphicTracker extends Tracker<Barcode>{

    private GraphicOverlay<BarcodeGraphic> mOverlay;
    private BarcodeGraphic graphic;

    private  BarcodeUpdateListener mBarcodeUpdateListener;


    BarcodeGraphicTracker(GraphicOverlay<BarcodeGraphic>mOverlay,BarcodeGraphic graphic, Context context){

        this.mOverlay = mOverlay;
        this.graphic=graphic;

        if(context instanceof BarcodeUpdateListener ) {
            this.mBarcodeUpdateListener =(BarcodeUpdateListener) context;

        }
        else {
            throw new RuntimeException("Hosting activity must implement BarcodeUpdateListener");

        }
    }


    @Override
    public void onNewItem(int id, Barcode item) {
        graphic.setId(id);
        mBarcodeUpdateListener.onBarcodeDetected(item);


    }


    @Override
    public void onUpdate(Detector.Detections<Barcode> detectionResults, Barcode item) {
    mOverlay.add(graphic);
    graphic.updateItem(item);
    }


    @Override
    public void onMissing(Detector.Detections<Barcode> detectionResults) {
        mOverlay.remove(graphic);
    }

    @Override
    public void onDone() {
        mOverlay.remove(graphic);
    }


}
