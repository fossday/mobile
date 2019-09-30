package org.fossday.Helper;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class StorageHelper {

    private static final String TAG = "StorageHelper";

    private Context context;
    private boolean externalStorage = false;
    private String fileName = "image.png";
    private String directoryName = "images";

    public StorageHelper(Context context) {
        this.context = context;
    }

    public StorageHelper setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public StorageHelper setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
        return this;
    }

    public StorageHelper setExternal(boolean external) {
        this.externalStorage = external;
        return this;
    }

    public void saveImage(Bitmap finalBitmap) {

        File file = new File(createDir(), fileName);
        if (file.exists()) file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.PNG, 60, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveFile(ResponseBody body) {
        try {
            // todo change the file location/name according to your needs
            // context.getFilesDir() = Internal Storage
//            File file = new File(context.getFilesDir(), filename);

            //External Storage
//            File file = new File(
//                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
//                    filename
//            );

            File file = new File(createDir(), fileName);
            if (file.exists()) file.delete();

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(file);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                    Log.d(TAG , fileSizeDownloaded + " of " + fileSize);
                }

                outputStream.flush();

//                return true;
            } catch (IOException e) {
                Log.d(TAG, "saveFileToDisk: " + e);
//                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            Log.d(TAG, "saveFileToDisk: " + e);
//            return false;
        }
    }

    private File createDir() {
        ContextWrapper cw = new ContextWrapper(context);

        File file = new File(cw.getFilesDir(), directoryName);

        if(!file.exists()){
            Log.d(TAG, "createDir: Dir not Exists");
            file.mkdir();
        } else {
            Log.d(TAG, "createDir: Dir Exists");
        }

        if (!file.mkdirs()) {
            Log.e(TAG, "Directory not created");
        }

        // path to /data/data/yourapp/app_data/imageDir
        return cw.getDir(directoryName, Context.MODE_PRIVATE);
    }

    public File getDir() {
        ContextWrapper cw = new ContextWrapper(context);

        File file = new File(cw.getFilesDir(), directoryName);

        return cw.getDir(directoryName, Context.MODE_PRIVATE);
    }

    public Bitmap load() {
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(createDir());
            return BitmapFactory.decodeStream(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private File getImageInternalStorageDirectory(String directoryName) {
//        File file = new File(context.getDir(directoryName, Context.MODE_PRIVATE));

        File file = new File(context.getFilesDir(), directoryName);
        if(!file.exists()){
            file.mkdir();
        }
        if (!file.mkdirs()) {
            Log.e(TAG, "Directory not created");
        }
        return file;
    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    /* Create picture private directory (Only app have access) */
    private File getImageExternalStorageDirectory(String albumName) {
        File file = new File(context.getExternalFilesDir(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
            Log.e(TAG, "Directory not created");
        }
        return file;
    }
}
