package algonquin.cst2355.androiddictionary.Adapters;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.List;

import algonquin.cst2355.androiddictionary.Models.Phonetics;
import algonquin.cst2355.androiddictionary.R;
import algonquin.cst2355.androiddictionary.ViewHolders.PhoneticsViewHolder;

public class PhoneticAdapter extends RecyclerView.Adapter<PhoneticsViewHolder> {
    private Context context;
    private List<Phonetics> phoneticsList;

    public PhoneticAdapter(Context context, List<Phonetics> phoneticsList) {
        this.context = context;
        this.phoneticsList = phoneticsList;
    }

    @NonNull
    @Override
    public PhoneticsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PhoneticsViewHolder(LayoutInflater.from(context).inflate(R.layout.phonetic_list_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneticsViewHolder holder, int position) {
        holder.textView_phonetic.setText(phoneticsList.get(position).getText());
        holder.imageButton_audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer player = new MediaPlayer();
                try {
                    player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    player.setDataSource("https:" + phoneticsList.get(position).getAudio());
                    player.prepare();
                    player.start();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(context, "Couldn't play audio!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return phoneticsList.size();
    }
}
