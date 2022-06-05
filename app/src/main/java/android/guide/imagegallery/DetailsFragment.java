package android.guide.imagegallery;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
// TODO 5 : CREATE THE SIBLING FRAGMENTS {
//  5.3 DetailsFragment

public class DetailsFragment extends Fragment {
    Bundle bundle;
    String information;
    TextView textView;

    public DetailsFragment() {
        super(R.layout.fragment_details);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
//       todo 5.3.1 make sure to receive the bundle from previous fragment
//          which is DisplayFragment
//          the bundle contain several Strings specify the image attributes
        bundle = getArguments();
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        textView = (TextView) view.findViewById(R.id.desTextView);

//      todo 5.3.2 convert bundle to a String
        information = bundle.getString("image name")+"\n"
                +bundle.getString("image data")+"\n"
                +bundle.getString("image type")+"\n"
                +bundle.getString("image uri")+"\n"
                +bundle.getString("image dim")+"\n"
                +bundle.getString("image size");

        Log.d("TAG", "onCreateView: "+ information);
        textView.setText(information);

        return view;
    }


}
