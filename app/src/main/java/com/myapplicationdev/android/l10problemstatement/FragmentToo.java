package com.myapplicationdev.android.l10problemstatement;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentToo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentToo extends Fragment {

    ImageView iv;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentToo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentToo.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentToo newInstance(String param1, String param2) {
        FragmentToo fragment = new FragmentToo();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_too, container, false);
        iv = root.findViewById(R.id.myZoomageView);
        String imageUrl = "https://www.wtffunfact.com/wp-content/uploads/2020/07/WTF-Fun-Fact-Weird-Al-Beer-Money.png";
        Picasso.with(this.getContext()).load(imageUrl).into(iv);
        return root;
    }
}