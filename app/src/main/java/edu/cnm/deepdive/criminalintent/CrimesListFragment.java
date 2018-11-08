package edu.cnm.deepdive.criminalintent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;

public class CrimesListFragment extends Fragment {

  private RecyclerView mCrimeRecyclerView;
  private CrimeAdapter mAdapter;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View theView = inflater.inflate(R.layout.fragment_crime_list, container, false);
    mCrimeRecyclerView = theView.findViewById(R.id.crime_recycler_view);
    mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    return theView;
  }

  private void updateUI(){
    CrimeLab crimeLab = CrimeLab.get(getActivity());
    List<Crime> crimes = crimeLab.getMCrimes();
    mAdapter = new CrimeAdapter(crimes);
    mCrimeRecyclerView.setAdapter(mAdapter);
  }

  private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private TextView mTitleTV;
    private TextView mDateTV;
    private Crime mCrime;

    public CrimeHolder(LayoutInflater inflater, ViewGroup parent) {
      super(inflater.inflate(R.layout.list_item_crime, parent, false));
      mTitleTV = itemView.findViewById(R.id.crime_title);
      mDateTV = itemView.findViewById(R.id.crime_date);
    }

    public void bind(Crime crime){
      mCrime = crime;
      mTitleTV.setText(mCrime.getmTitle());
      mDateTV.setText(mCrime.getmDate().toString());
    }

    @Override
    public void onClick(View v) {
      Toast.makeText(getActivity(), mCrime.getmTitle() + " clicked!", Toast.LENGTH_SHORT).show();
    }
  }

  private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder>{

    private List<Crime> mCrimes;

    public CrimeAdapter(List<Crime> crimes){
      mCrimes = crimes;
    }

    @NonNull
    @Override
    public CrimeHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
      LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
      return new CrimeHolder(layoutInflater, viewGroup);
    }

    @Override
    public void onBindViewHolder(@NonNull CrimeHolder crimeHolder, int i) {
      Crime crime = mCrimes.get(i);
      crimeHolder.bind(crime);
    }

    @Override
    public int getItemCount() {
      return mCrimes.size();
    }
  }
}
