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
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

import manu.apps.evote.R;
import manu.apps.evote.adapters.ContestantsAdapter;
import manu.apps.evote.classes.Contestant;

public class ContestantFragment extends Fragment {

    private List<Contestant> contestantList;
    RecyclerView rvContestants;
    TextView tvContestantType;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.contestant_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        firebaseAuth = FirebaseAuth.getInstance();

        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        contestantList = new ArrayList<>();

        tvContestantType = view.findViewById(R.id.tv_contestant_type);
        rvContestants = view.findViewById(R.id.rv_contestants);

        String contestantType = getArguments().getString("contestantType");
        String bundleName = getArguments().getString("bundleName");

        tvContestantType.setText(contestantType + " " + "Contestants");

        contestantList = new ArrayList<>();

        rvContestants = view.findViewById(R.id.rv_contestants);

        setUpContestantsRecyclerView(contestantList, contestantType, bundleName);

    }

    private void loadPresidentsList(String contestantType){

        contestantList.add(new Contestant(1123564464,"Victor Koinet ", contestantType,
                R.drawable.ic_presidents1, "Orange Democratic Movement",R.drawable.ic_odm));
        contestantList.add(new Contestant(564988552,"Mark Lemaya ", contestantType,
                R.drawable.ic_presidents2, "Peoples Party of Kenya",R.drawable.ic_ppk));
        contestantList.add(new Contestant(125656888,"James Voila ", contestantType,
                R.drawable.ic_presidents3, "Jubilee Party",R.drawable.ic_jubilee));

    }

    private void loadMpsList(String contestantType){

        contestantList.add(new Contestant(5571155,"James Voila ", contestantType,
                R.drawable.ic_mp1, "National Rainbow Coalition",R.drawable.ic_narc));
        contestantList.add(new Contestant(156655665,"Valor Kario ", contestantType,
                R.drawable.ic_mp2, "Orange Democratic Movement",R.drawable.ic_odm));
        contestantList.add(new Contestant(215454,"Karl locko ", contestantType,
                R.drawable.ic_mp3, "Peoples Party of Kenya",R.drawable.ic_ppk));
        contestantList.add(new Contestant(55885,"Justus Salta ", contestantType,
                R.drawable.ic_mp4, "Jubilee Party",R.drawable.ic_jubilee));

    }

    private void setUpContestantsRecyclerView(final List<Contestant> contestantList, String contestantType, String bundleName){


        if (bundleName.equalsIgnoreCase("president")){

            loadPresidentsList(contestantType);
        }
        if (bundleName.equalsIgnoreCase("mp")){

            loadMpsList(contestantType);
        }
        if (bundleName.equalsIgnoreCase("womenrep")){

            loadPresidentsList(contestantType);

        }
        if (bundleName.equalsIgnoreCase("senator")){

            loadPresidentsList(contestantType);

        }
        if (bundleName.equalsIgnoreCase("governor")){

            loadPresidentsList(contestantType);

        }
        if (bundleName.equalsIgnoreCase("wardrep")){

            loadPresidentsList(contestantType);

        }

        ContestantsAdapter contestantsAdapter = new ContestantsAdapter(getActivity(), contestantList);

        rvContestants.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvContestants.setAdapter(contestantsAdapter);

    }
}