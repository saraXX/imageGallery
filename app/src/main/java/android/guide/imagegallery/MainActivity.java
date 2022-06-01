package android.guide.imagegallery;
// TODO 1 ADD DEPENDENCIES {FRAGMENTS, ANDROID X} done
// TODO 2 ADD PERMISSIONS {READ EXTERNAL STORAGE} done
// TODO 3 CREATE IMAGE CLASS done
// TODO 4 CREATE A CUSTOM ADAPTER FOR GRID VIEW
// TODO 5 SETUP LAYOUT {LIST VIEW AND FRAGMENT LAYOUT}
// TODO 6 ACCESS EXTERNAL MEMORY
// TODO 7 LINK EVERYTHING IN THE MainActivity



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
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TAG = "msg";
        CODE =1;

        getReadPermission();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, GridFragment.class, null)
                .setReorderingAllowed(true)
                .addToBackStack("name") // name can be null
                .commit();
    }

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

    public void getReadPermission(){
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, CODE);
        } else {
            Log.d(TAG, "onCreate: "+"permission  accpeted");
        }
    }
}