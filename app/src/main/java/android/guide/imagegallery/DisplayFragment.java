package android.guide.imagegallery;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
// TODO 5 : CREATE THE SIBLING FRAGMENTS {
//  5.2 DisplayFragment
public class DisplayFragment extends Fragment {
    ImageView imageView;
    Button buttonView;
    Bundle bundle;
    String imagePath;
    Uri myUri;
    public DisplayFragment(){
        super(R.layout.fragment_display);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
//       todo 5.2.1 make sure to receive the bundle from previous fragment
//        which is GridFragment
//        the bundle contain several Strings specify the image attributes
        bundle = getArguments();
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display, container, false);
        imageView = (ImageView) view.findViewById(R.id.imgHolderView);
        buttonView = (Button) view.findViewById(R.id.desBtn);

//       todo 5.2.2 retrieve bundle to a String
        imagePath = bundle.getString("image uri");
//       todo 5.2.3 convert a String to an URI
        myUri = Uri.parse(imagePath);
//       todo 5.2.4 set fill image view with an image using the uri
        imageView.setImageURI(myUri);

// clicking on the button will show the DetailsFragment
        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = ((AppCompatActivity)getContext()).getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_view, DetailsFragment.class, bundle)
                        .setReorderingAllowed(true)
                        .addToBackStack("name") // name can be null
                        .commit();
            }
        });

        return view;
    }
}
