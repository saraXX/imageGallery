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

public class GridAdapter extends BaseAdapter {
    Context context;
    List<Uri> uris;
    LayoutInflater inflter;
    String TAG;
    List<ImagesGallery> imagesGalleryList;
    FragmentManager fragmentManager;

    public GridAdapter(Context context, List<ImagesGallery> imagesGalleryList) {
        TAG = "GridAdapter";
        this.context = context;
        this.imagesGalleryList = imagesGalleryList;
        uris = getThumpsUris(imagesGalleryList);
        this.inflter = (LayoutInflater.from(context));
        fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
    }


    @Override
    public int getCount() {
        int count=uris.size(); //counts the total number of elements from the arrayList
        return count;//returns the total count to adapter
    }

    @Override
    public Object getItem(int i) {
        return imagesGalleryList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.item_layout, null);//set layout for displaying items
        ImageView icon = (ImageView) view.findViewById(R.id.thumpImgView);//get id for image view
        icon.setImageURI(uris.get(i));//set image of the item’s



        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                fragmentManager.beginTransaction()
//                        .replace(R.id.fragment_container_view, DisplayFragment.class, sendUri(uris.get(i)))
//                        .setReorderingAllowed(true)
//                        .addToBackStack("name") // name can be null
//                        .commit();

                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_view, DisplayFragment.class, sendItem(imagesGalleryList.get(i)))
                        .setReorderingAllowed(true)
                        .addToBackStack("name") // name can be null
                        .commit();
            }
        });
        return view;
    }


    public List<Uri> getThumpsUris(List<ImagesGallery> imagesGalleries){
        List<Uri> uris = new ArrayList<>();
        ImagesGallery list;
        for(int i=0; i<imagesGalleries.size();i++){
            Log.d(TAG, "getThumps:imagesGalleries.get(i) "+imagesGalleries.get(i).getName());
            list=imagesGalleries.get(i);
            uris.add(list.getUri());
        }
        return uris;
    }

    public Bundle sendUri(Uri uri){
        Bundle bundle = new Bundle();
        String s = uri.toString();
        bundle.putString("image",s);
        return bundle;

    }

    public Bundle sendItem(ImagesGallery imagesGallery){
        Bundle bundle = new Bundle();
        String name = imagesGallery.getName();
        String date = imagesGallery.getDate();
        String type = imagesGallery.getType();
        String uri = imagesGallery.getUri().toString();
        String high = String.valueOf(imagesGallery.getHigh());
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
