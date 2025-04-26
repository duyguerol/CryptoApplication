package com.duyer.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.duyer.Model.CryptoModeli;
import com.duyer.retrofitjava.databinding.CardTasarimBinding;

import java.util.ArrayList;

public class CryptoAdapter extends RecyclerView.Adapter<CryptoAdapter.CryptoHolder> {
    private ArrayList<CryptoModeli> cryptoList;
    private String[] colors = {"#ff0943", "#ff6500", "#4ef022", "#dea444", "#d87cd3", "#317180", "#abc123"};

    public CryptoAdapter(ArrayList<CryptoModeli> cryptoList) {
        this.cryptoList = cryptoList;
    }

    @NonNull
    @Override
    public CryptoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardTasarimBinding tasarim = CardTasarimBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);

        return new CryptoHolder(tasarim);
    }

    @Override
    public void onBindViewHolder(@NonNull CryptoHolder holder, int position) {
        holder.bind(cryptoList.get(position),colors,position);
    }

    @Override
    public int getItemCount() {
        return cryptoList.size();
    }

    public class CryptoHolder extends RecyclerView.ViewHolder {
        private CardTasarimBinding tasarim;
        public CryptoHolder(CardTasarimBinding tasarim) {
            super(tasarim.getRoot());
            this.tasarim = tasarim;
        }
        public void bind(CryptoModeli cryptoModeli,String[] colors, Integer position) {
            tasarim.card.setBackgroundColor(Color.parseColor(colors[position % 7]));
            tasarim.textViewName.setText(cryptoModeli.currency);
            tasarim.textViewPrice.setText(cryptoModeli.price);

        }



    }


}

