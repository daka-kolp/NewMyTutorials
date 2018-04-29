package com.example.darya.bitsandpizzas;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PastaMaterialFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        RecyclerView pastaRecycle = (RecyclerView) inflater.inflate(
                R.layout.fragment_pasta_material, container, false);

        String[] pastaName = new String[Pasta.pastas.length];
        for(int i = 0; i < pastaName.length; i++) {
            pastaName[i] = Pasta.pastas[i].getName();
        }

        int[] pastaImage = new int[Pasta.pastas.length];
        for(int i = 0; i < pastaImage.length; i++) {
            pastaImage[i] = Pasta.pastas[i].getImageResourceId();
        }

        CaptionedImageAdapter adapter = new CaptionedImageAdapter(pastaName, pastaImage);
        pastaRecycle.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        pastaRecycle.setLayoutManager(layoutManager);
        return pastaRecycle;
    }
}
