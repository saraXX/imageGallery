package android.guide.imagegallery;

import android.Manifest;
import android.content.ContentUris;
import android.content.LocusId;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;
// TODO 5 : CREATE THE SIBLING FRAGMENTS {
//  5.1 GridFragment

public class GridFragment extends Fragment {
    GridView gridView;
    List<ImagesGallery> imageList;
    GridAdapter customAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//      todo 5.1.1 create an array from ImagesGallery class
        imageList= new ArrayList<ImagesGallery>();
//      todo 5.1.2 fill the list with an imagesGallery objects.
        imageList= myImagelist();
//      todo 5.1.3 call the adapter
        customAdapter = new GridAdapter(getContext(), imageList);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grid, container, false);
        gridView = (GridView) view.findViewById(R.id.gridView);
//        set the adapter
        gridView.setAdapter(customAdapter);
        return view;
    }
//    this methode show how to query images information that stored in the external memory storage
//    like the /DCMI /PICTURE /... directories.
//    return : List<ImagesGallery> object
//    details in : https://developer.android.com/training/data-storage/shared/media#query-collection
    public List<ImagesGallery> myImagelist(){
        List<ImagesGallery> imageList = new ArrayList<ImagesGallery>();
        Uri collection;
//        Is the current sdk is 29 or higher?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            collection = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL);
        } else {
            collection = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        }

        String[] projection = new String[]{
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.WIDTH,
                MediaStore.Images.Media.HEIGHT,
                MediaStore.Images.Media.DATE_ADDED,
                MediaStore.Images.Media.MIME_TYPE,
                MediaStore.Images.Media.SIZE
        };
//        A Selection statement to sort the results

//        String selection = MediaStore.Images.Media.MIME_TYPE +
//                " = ?";
//        String[] selectionArgs = new String[]{
//                "JPEG"
//        };
        String sortOrder = MediaStore.Images.Media.DISPLAY_NAME + " ASC";

        try (Cursor cursor = getContext().getContentResolver().query(
                collection,
                projection,
                null,
                null,
                sortOrder
        )) {
            // Cache column indices.
            int idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID);
            int nameColumn =
                    cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME);
            int widthColumn =
                    cursor.getColumnIndexOrThrow(MediaStore.Images.Media.WIDTH);
            int heightColumn =
                    cursor.getColumnIndexOrThrow(MediaStore.Images.Media.HEIGHT);
            int dateColumn =
                    cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_ADDED);
            int typeColumn =
                    cursor.getColumnIndexOrThrow(MediaStore.Images.Media.MIME_TYPE);
            int sizeColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE);

            while (cursor.moveToNext()) {
                // Get values of columns for a given video.
                long id = cursor.getLong(idColumn);
                String name = cursor.getString(nameColumn);
                int width = cursor.getInt(widthColumn);
                int height = cursor.getInt(heightColumn);
                String date = cursor.getString(dateColumn);
                String type = cursor.getString(typeColumn);
                int size = cursor.getInt(sizeColumn);

                Uri contentUri = ContentUris.withAppendedId(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id);

                imageList.add(new ImagesGallery(contentUri, name, width, height, date, type, size));
            }
        }
        return imageList;
    }
}
