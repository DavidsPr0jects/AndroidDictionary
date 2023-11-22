package algonquin.cst2355.androiddictionary.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import algonquin.cst2355.androiddictionary.R;

public class DefinitionViewHolder extends RecyclerView.ViewHolder {
    public TextView textView_definition, textView_example, textView_synonyms, textView_antonyms;
    public DefinitionViewHolder(@NonNull View itemView) {
        super(itemView);
        textView_definition = itemView.findViewById(R.id.textView_definitions);
        textView_example = itemView.findViewById(R.id.textView_example);
        textView_synonyms = itemView.findViewById(R.id.textView_synonyms);
        textView_antonyms = itemView.findViewById(R.id.textView_antonyms);
    }

}