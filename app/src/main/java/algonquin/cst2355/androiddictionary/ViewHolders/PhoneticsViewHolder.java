package algonquin.cst2355.androiddictionary.ViewHolders;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import algonquin.cst2355.androiddictionary.R;

public class PhoneticsViewHolder extends RecyclerView.ViewHolder {
    public TextView textView_phonetic;
    public ImageButton imageButton_audio;
    public PhoneticsViewHolder(@NonNull View itemView) {
        super(itemView);
        textView_phonetic = itemView.findViewById(R.id.textView_phonetic);
        imageButton_audio = itemView.findViewById(R.id.imageButton_audio);
    }

}
