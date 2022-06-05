package android.guide.imagegallery;

// TODO 0 READ STEPS :
//  1 ADD FRAGMENT DEPENDENCIES
//  2 ADD PERMISSIONS {READ EXTERNAL STORAGE}
//  3 CREATE ImagesGallery CLASS
//      the class attr : uri, name, size ..etc.
//  4 SETUP LAYOUTS
//  5 CREATE A SIBLING LAYOUTS
//          A - GridFragment.
//          B - DisplayFragment.
//          C - DetailsFragment.
//  6 CREATE A CUSTOM ADAPTER FOR GRID VIEW




import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int CODE;
    String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//      any text for log tab
        TAG = "msg";
//      could be any number.
        CODE =1;

//      request permission
        getReadPermission();
//      start showing the GridFragment by starting the MainActivity()
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, GridFragment.class, null)
                .setReorderingAllowed(true)
                .addToBackStack("name") // name can be null
                .commit();
    }

//    TODO 2.2 ACCESS EXTERNAL MEMORY BY OVERRIDE onRequestPermissionsResult()
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    Log.d(TAG, "onCreate: " + "permission  accpeted");
                }
                break;
            default:
                break;
        }
    }

    //    TODO 2.3 CHECK THE PERMISSION
    public void getReadPermission(){
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, CODE);
        } else {
            Log.d(TAG, "onCreate: "+"permission  accpeted");
        }
    }
}