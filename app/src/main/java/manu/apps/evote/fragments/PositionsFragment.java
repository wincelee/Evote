package manu.apps.evote.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import manu.apps.evote.R;
import manu.apps.evote.adapters.PositionsAdapter;
import manu.apps.evote.classes.ElectoralPosition;

public class PositionsFragment extends Fragment {

    RecyclerView rvElectoralPosition;
    List<ElectoralPosition> electoralPositionList;

    NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.positions_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        electoralPositionList = new ArrayList<>();

        rvElectoralPosition = view.findViewById(R.id.rv_electoral_position);

        setUpElectoralPositionRecyclerView(electoralPositionList);

    }

    private void loadElectoralPositions(){

        electoralPositionList.add(new ElectoralPosition("President", "president",R.drawable.ic_president));
        electoralPositionList.add(new ElectoralPosition("MP", "mp", R.drawable.ic_mparliament));
        electoralPositionList.add(new ElectoralPosition("Women Rep", "womenrep",R.drawable.ic_woman_rep));
        electoralPositionList.add(new ElectoralPosition("Senator", "senator", R.drawable.ic_senator));
        electoralPositionList.add(new ElectoralPosition("Governor", "governor", R.drawable.ic_governer));
        electoralPositionList.add(new ElectoralPosition("Ward Rep", "wardrep",R.drawable.ic_ward_rep));
    }

    private void setUpElectoralPositionRecyclerView(final List<ElectoralPosition> electoralPositionList){

        loadElectoralPositions();

        PositionsAdapter positionsAdapter = new PositionsAdapter(getActivity(), electoralPositionList, new PositionsAdapter.OnClick() {
            @Override
            public void onEvent(ElectoralPosition electoralPosition, int pos) {

                Bundle bundle = new Bundle();
                bundle.putString("bundleName", electoralPosition.getBundleName());
                bundle.putString("contestantType", electoralPosition.getPositionName());
                navController.navigate(R.id.action_position_to_contestants_fragment, bundle);

            }
        });

        rvElectoralPosition.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rvElectoralPosition.setAdapter(positionsAdapter);

    }
}