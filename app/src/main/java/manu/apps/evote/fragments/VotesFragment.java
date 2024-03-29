package manu.apps.evote.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import manu.apps.evote.R;
import manu.apps.evote.adapters.VotesAdapter;

public class VotesFragment extends Fragment {

    RecyclerView rvVotes;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.votes_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvVotes = view.findViewById(R.id.rv_votes);

        setUpVoteRecyclerView();
    }

    private void setUpVoteRecyclerView(){
        VotesAdapter votesAdapter = new VotesAdapter(getActivity());

        rvVotes.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvVotes.setAdapter(votesAdapter);
    }
}