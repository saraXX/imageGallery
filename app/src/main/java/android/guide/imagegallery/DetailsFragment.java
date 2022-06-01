package android.guide.imagegallery;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class DetailsFragment extends Fragment {
    Bundle bundle;
    String imageName;
    TextView textView;

    public DetailsFragment() {
        super(R.layout.fragment_details);
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        bundle = getArguments();
        super.onCreate(savedInstanceState);

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        textView = (TextView) view.findViewById(R.id.desTextView);


        imageName = bundle.getString("image name");
        Log.d("TAG", "onCreateView: "+imageName);
        textView.setText(imageName);


        return view;
    }


}
