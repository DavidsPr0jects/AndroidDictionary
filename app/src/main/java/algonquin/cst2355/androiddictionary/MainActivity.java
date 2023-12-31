import android.app.ProgressDialog;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import algonquin.cst2355.androiddictionary.Adapters.MeaningAdapter;
import algonquin.cst2355.androiddictionary.Adapters.PhoneticAdapter;
import algonquin.cst2355.androiddictionary.Models.APIResponse;
import algonquin.cst2355.androiddictionary.OnFetchDataListener;
import algonquin.cst2355.androiddictionary.R;
import algonquin.cst2355.androiddictionary.RequestManager;

public class MainActivity extends AppCompatActivity {
    SearchView search_view;
    TextView textView_word;
    RecyclerView recycler_phonetics, recycler_meanings;
    ProgressDialog dialog;
    PhoneticAdapter phoneticAdapter;
    MeaningAdapter meaningAdapter;
    Toolbar toolbar;
    private PackageInfo mPackageInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search_view = findViewById(R.id.search_view);
        textView_word = findViewById(R.id.textView_word);
        recycler_phonetics = findViewById(R.id.recycler_phonetics);
        recycler_meanings = findViewById(R.id.recycler_meanings);
        toolbar = findViewById(R.id.toolbar);
        dialog = new ProgressDialog(this);

        try {
            mPackageInfo = this.getPackageManager().getPackageInfo(getPackageName(), 0);
            setupVersionInfo();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        dialog.setTitle("Loading...");
        dialog.show();
        RequestManager manager = new RequestManager(MainActivity.this);
        manager.getWordMeaning(listener, "hello");

        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dialog.setTitle("Fetching response for " + query);
                dialog.show();
                RequestManager manager = new RequestManager(MainActivity.this);
                manager.getWordMeaning(listener, query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private final OnFetchDataListener listener = new OnFetchDataListener() {
        @Override
        public void onFetchData(APIResponse apiResponse, String message) {
            dialog.dismiss();
            if (apiResponse==null){
                Toast.makeText(MainActivity.this, "No Data Found!", Toast.LENGTH_SHORT).show();
                return;
            }
            showResult(apiResponse);

        }

        @Override
        public void onError(String message) {
            dialog.dismiss();
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    private void showResult(APIResponse response){
        textView_word.setText("Word: " + response.getWord());

        recycler_phonetics.setHasFixedSize(true);
        recycler_phonetics.setLayoutManager(new GridLayoutManager(this, 1));
        phoneticAdapter = new PhoneticAdapter(this, response.getPhonetics());
        recycler_phonetics.setAdapter(phoneticAdapter);

        recycler_meanings.setHasFixedSize(true);
        recycler_meanings.setLayoutManager(new GridLayoutManager(this, 1));
        meaningAdapter = new MeaningAdapter(this, response.getMeanings());
        recycler_meanings.setAdapter(meaningAdapter);
    }

    private void setupVersionInfo() {

        if (mPackageInfo != null) {
            String vinfo = String.format("V: %s", mPackageInfo.versionName);
            toolbar.setSubtitle(vinfo);
        }

    }
}