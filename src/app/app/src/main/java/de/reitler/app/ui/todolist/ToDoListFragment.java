package de.reitler.app.ui.todolist;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import de.reitler.app.R;
import de.reitler.app.ui.todolist.daily.DailyToDoListFragment;
import de.reitler.app.ui.todolist.weekly.WeeklyToDoListFragment;

public class ToDoListFragment extends Fragment {

    private ToDoListViewModel toDoListViewModel;
    private View view;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toDoListViewModel =
                new ViewModelProvider(this).get(ToDoListViewModel.class);
        view = inflater.inflate(R.layout.fragment_todolist, container, false);
        toDoListViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DemoCollectionPagerAdapter demoCollectionPagerAdapter = new DemoCollectionPagerAdapter(getChildFragmentManager(), getContext());
        ViewPager viewPager = view.findViewById(R.id.pager);
        viewPager.setAdapter(demoCollectionPagerAdapter);
        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }
}
class DemoCollectionPagerAdapter extends FragmentStatePagerAdapter {

    private Context context;

    public DemoCollectionPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case 0: return context.getString(R.string.tab_daily_todolist);
            case 1: return context.getString(R.string.tab_weekly_todolist);
            default: return "OBJECT " + (position + 1);
        }
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0: return new DailyToDoListFragment();
            case 1: return new WeeklyToDoListFragment();
            default: return null;
        }
    }

}