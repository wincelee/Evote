package manu.apps.evote.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import java.util.List;

import manu.apps.evote.R;
import manu.apps.evote.classes.ElectoralPosition;

public class PositionsAdapter extends RecyclerView.Adapter<PositionsAdapter.MyViewHolder>{

    private Context context;
    private List<ElectoralPosition> electoralPositionList;
    private RequestOptions requestOptions;
    OnClick onClick;


    public interface OnClick {
        void onEvent(ElectoralPosition electoralPosition, int pos);
    }


    public PositionsAdapter(Context context, List<ElectoralPosition> electoralPositionList,OnClick onClick) {
        this.context = context;
        this.electoralPositionList = electoralPositionList;

        // On Click Listener interface
        this.onClick = onClick;

        requestOptions = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.ic_refresh)
                .error(R.drawable.ic_refresh);

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.layout_electoral_position,parent,false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int position) {

        final ElectoralPosition electoralPosition = electoralPositionList.get(position);

        myViewHolder.tvPositionName.setText(electoralPosition.getPositionName());


        Glide.with(context)
                .load(electoralPosition.getPositionImage())
                .apply(requestOptions)
                .into(myViewHolder.imvPosition);

        myViewHolder.cvPosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onEvent(electoralPosition, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return electoralPositionList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        // If Image Button use image button, crash

        TextView tvPositionName;
        ImageView imvPosition;
        CardView cvPosition;

        public MyViewHolder(View itemView){
            super(itemView);

            tvPositionName = itemView.findViewById(R.id.tv_position_name);
            imvPosition = itemView.findViewById(R.id.imv_position);
            cvPosition = itemView.findViewById(R.id.cv_position);

        }
    }
}
