package manu.apps.evote.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.snackbar.Snackbar;
import java.util.List;

import manu.apps.evote.R;
import manu.apps.evote.classes.Config;
import manu.apps.evote.classes.Contestant;

public class VotesAdapter extends RecyclerView.Adapter<VotesAdapter.ContestantViewHolder> {


    private Context context;
    private List<Contestant> contestantList;
    private RequestOptions requestOptions;


    public VotesAdapter(Context context) {
        this.context = context;
        this.contestantList = Config.contestantList;

        requestOptions = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.ic_refresh)
                .error(R.drawable.ic_refresh);

    }

    @NonNull
    @Override
    public ContestantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_votes, parent, false);
        return new ContestantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ContestantViewHolder viewHolder, final int position) {

        final Contestant contestantPosition = contestantList.get(position);

        viewHolder.tvContestantName.setText(contestantPosition.getContestantName());
        viewHolder.tvVoteCount.setText(String.valueOf(contestantPosition.getVoteCount()));


        Glide.with(context)
                .load(contestantPosition.getContestantPhoto())
                .apply(requestOptions)
                .into(viewHolder.imvContestant);

    }

    @Override
    public int getItemCount() {
        return contestantList.size();
    }


    public static class ContestantViewHolder extends RecyclerView.ViewHolder {


        TextView tvContestantName, tvVoteCount;
        ImageView imvContestant;

        public ContestantViewHolder(View itemView) {
            super(itemView);

            tvContestantName = itemView.findViewById(R.id.tv_contestant_name);
            tvVoteCount = itemView.findViewById(R.id.tv_vote_count);
            imvContestant = itemView.findViewById(R.id.imv_contestant);

        }
    }

}
