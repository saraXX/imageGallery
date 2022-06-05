package android.guide.imagegallery;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;
//  TODO 6 CREATE A CUSTOM ADAPTER FOR THE GRID VIEW

public class GridAdapter extends BaseAdapter {
    Context context;
    String TAG;
    LayoutInflater inflater;
    List<Uri> imageUrisList;
    List<ImagesGallery> imagesGalleryList;
    FragmentManager fragmentManager;

// only get the context and the list from GridFragment
    public GridAdapter(Context context, List<ImagesGallery> imagesGalleryList) {
        TAG = "GridAdapter";
        this.context = context;
        this.imagesGalleryList = imagesGalleryList;
//        clone images uri in a separate list
        imageUrisList = getThumpsUris(imagesGalleryList);
        this.inflater = (LayoutInflater.from(context));
//        initializing a fragment manger is differ in ordinary class
        fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
    }

// not important
    @Override
    public int getCount() {
        int count=imageUrisList.size(); //counts the total number of elements from the arrayList
        return count;//returns the total count to adapter
    }
    // not important
    @Override
    public Object getItem(int i) {
        return imagesGalleryList.get(i);
    }
    // not important
    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.item_layout, null);//set layout for displaying a single item in the grid
        ImageView icon = (ImageView) view.findViewById(R.id.thumpImgView);//get id for the thump image view
        icon.setImageURI(imageUrisList.get(i));//set image thump of the item’s

//      ones item clicked it will trigger fragment manger to start another fragment
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction()
//                        todo 6.3 make sure to send a bundle too of a specific item (i)
                        .replace(R.id.fragment_container_view, DisplayFragment.class, sendItem(imagesGalleryList.get(i)))
                        .setReorderingAllowed(true)
                        .addToBackStack("name") // name can be null
                        .commit();
            }
        });
        return view;
    }

// todo 6.1 clone images uri in a separate list
//    args :  List<ImagesGallery> - received from GridFragment
//    return : List<Uri> -  to retrieve image easily in a grid view
    public List<Uri> getThumpsUris(List<ImagesGallery> ImagesGalleryList){
        List<Uri> urisList = new ArrayList<>();
        ImagesGallery img;
        for(int i=0; i<ImagesGalleryList.size();i++){
            Log.d(TAG, "getThumps:imagesGalleries.get(i) "+ImagesGalleryList.get(i).getName());
            img=ImagesGalleryList.get(i);
            urisList.add(img.getUri());
        }
        return urisList;
    }
// todo 6.2 convert imagesGallery object to a bundle to be shared between fragments
//    args :  ImagesGallery - by passing one object from imagesGalleryList.get(i)
//    return : Bundle -  contain many String displaying image information
    public Bundle sendItem(ImagesGallery imagesGallery){
        Bundle bundle = new Bundle();
        String name = imagesGallery.getName();
        String date = imagesGallery.getDate();
        String type = imagesGallery.getType();
        String uri = imagesGallery.getUri().toString();
// combine both height and width in the same line
        String dim = String.valueOf(imagesGallery.getWidth()+"X"+imagesGallery.getHigh());
        String size = String.valueOf(imagesGallery.getSize()+"KB");

        bundle.putString("image name",name);
        bundle.putString("image data", date);
        bundle.putString("image type", type);
        bundle.putString("image uri", uri);
        bundle.putString("image dim", dim);
        bundle.putString("image size", size);
        return bundle;

    }
}
