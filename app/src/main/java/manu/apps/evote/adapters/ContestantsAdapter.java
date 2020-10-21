package manu.apps.evote.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

import manu.apps.evote.R;
import manu.apps.evote.classes.Config;
import manu.apps.evote.classes.Contestant;

public class ContestantsAdapter extends RecyclerView.Adapter<ContestantsAdapter.MyViewHolder>{

    private Context context;
    private List<Contestant> contestantList;
    private RequestOptions requestOptions;

    private Contestant contestant = new Contestant();

    public ContestantsAdapter(Context context, List<Contestant> contestantList) {
        this.context = context;
        this.contestantList = contestantList;

        requestOptions = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.ic_refresh)
                .error(R.drawable.ic_refresh);

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.layout_contestants,parent,false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int position) {

        final Contestant contestantPosition = contestantList.get(position);

        myViewHolder.tvContestantName.setText(contestantPosition.getContestantName());
        myViewHolder.tvPartyName.setText(contestantPosition.getPartyName());
        myViewHolder.tvContestantId.setText(String.valueOf(contestantPosition.getContestantId()));


        Glide.with(context)
                .load(contestantPosition.getContestantPhoto())
                .apply(requestOptions)
                .into(myViewHolder.imvContestant);

        Glide.with(context)
                .load(contestantPosition.getPartyPhoto())
                .apply(requestOptions)
                .into(myViewHolder.imvParty);

        myViewHolder.btnVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myViewHolder.btnVote.setText("Voted");

                contestant.setContestantName(contestantPosition.getContestantName());
                contestant.setVoteCount(1);
                contestant.setContestantPhoto(contestantPosition.getContestantPhoto());

                Config.contestantList.add(contestant);

                Config.showSnackBar(context, "Voted Successfully");

            }
        });

    }

    @Override
    public int getItemCount() {
        return contestantList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        // If Image Button use image button, crash

        TextView tvContestantName, tvPartyName, tvContestantId;
        ImageView imvContestant, imvParty;
        MaterialButton btnVote;

        public MyViewHolder(View itemView){
            super(itemView);

            tvContestantName = itemView.findViewById(R.id.tv_contestant_name);
            tvPartyName = itemView.findViewById(R.id.tv_party_name);
            tvContestantId = itemView.findViewById(R.id.tv_contestant_id);

            imvContestant = itemView.findViewById(R.id.imv_contestant);
            imvParty = itemView.findViewById(R.id.imv_party);

            btnVote = itemView.findViewById(R.id.btn_vote);

        }
    }
}
