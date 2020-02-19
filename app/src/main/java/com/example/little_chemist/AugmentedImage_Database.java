//package com.example.little_chemist;
//
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.util.Log;
//
//import com.google.ar.core.AugmentedImageDatabase;
//import com.google.ar.core.Config;
//import com.google.ar.core.Session;
//
//import java.io.IOException;
//import java.io.InputStream;
//
//import static androidx.constraintlayout.widget.Constraints.TAG;
//
//public class AugmentedImage_Database {
//
//
//    public boolean loadDataBase(Session session){
//        try{
//        InputStream inputStream = context.getAssets().open("example.imgdb");
//        AugmentedImageDatabase imageDatabase = AugmentedImageDatabase.deserialize(session,inputStream);}
//        catch(IOException e){
//
//            System.out.println(e);
//        }
//
//        return true;
//        return false;
//    }
//
//
////    public boolean setupAugmentedImagesDb(Config config, Session session) {
////        AugmentedImageDatabase augmentedImageDatabase;
////        Bitmap bitmap = loadAugmentedImage();
////        if (bitmap == null) {
////            return false;
////        }
////        augmentedImageDatabase = new AugmentedImageDatabase(session);
////        augmentedImageDatabase.addImage("tiger", bitmap);
////        config.setAugmentedImageDatabase(augmentedImageDatabase);
////        return true;
////    }
////    private Bitmap loadAugmentedImage() {
////        try (InputStream is = getAssets().open("blanket.jpeg")) {
////            return BitmapFactory.decodeStream(is);
////        } catch (IOException e) {
////            Log.e("ImageLoad", "IO Exception", e);
////        }
////        return null;
////    }
//
//}
