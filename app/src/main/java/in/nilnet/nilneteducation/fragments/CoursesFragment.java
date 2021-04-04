package in.nilnet.nilneteducation.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import in.nilnet.nilneteducation.R;
import in.nilnet.nilneteducation.adapters.RandomNumListAdapter;

public class CoursesFragment extends Fragment {

    private RecyclerView recyclerView;
    private RandomNumListAdapter adapter = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.courses_fragment, container, false);
        /*recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new RandomNumListAdapter(1234);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);*/
        return view;
    }
}
