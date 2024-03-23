package com.example.loginsignuppage;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class HomeFragment extends Fragment implements RecyclerViewInterface{
    TextView searchBar ;
    RecyclerView rv1 , rv2;
    ArrayList<Category> dataCategorySource;
    ArrayList<Service>  dataServicesSource;
    LinearLayoutManager linearLayoutManager1 , linearLayoutManager2;
    MyRvAdapter myRvAdapter1 ;
    MyRv2Adapter myRvAdapter2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv1 = view.findViewById(R.id.category);
        searchBar = view.findViewById(R.id.search_edittext);
        rv2 = view.findViewById(R.id.popular);

        dataCategorySource = new ArrayList<>();


        dataServicesSource = new ArrayList<>();
        dataServicesSource.add(new Service("Design logo for your business with unlimited revisions","the description","Graphics & Design",R.drawable.baseline_person_24,4,582,140));
        dataServicesSource.add(new Service("Design logo for your business with unlimited revisions","the description","Graphics & Design",R.drawable.baseline_person_24,4,582,140));
        dataServicesSource.add(new Service("Design logo for your business with unlimited revisions","the description","Graphics & Design",R.drawable.baseline_person_24,4,582,140));
        dataServicesSource.add(new Service("Design logo for your business with unlimited revisions","the description","Graphics & Design",R.drawable.baseline_person_24,4,582,140));
        dataServicesSource.add(new Service("Design logo for your business with unlimited revisions","the description","Graphics & Design",R.drawable.baseline_person_24,4,582,140));
        dataServicesSource.add(new Service("Design logo for your business with unlimited revisions","the description","Graphics & Design",R.drawable.baseline_person_24,4,582,140));
        dataServicesSource.add(new Service("Design logo for your business with unlimited revisions","the description","Graphics & Design",R.drawable.baseline_person_24,4,582,140));


        linearLayoutManager1 = new LinearLayoutManager(getContext() , LinearLayoutManager.HORIZONTAL,false);
        myRvAdapter1 = new MyRvAdapter(dataCategorySource,this);
        rv1.setLayoutManager(linearLayoutManager1);
        rv1.setAdapter(myRvAdapter1);
        linearLayoutManager2 = new LinearLayoutManager(getContext() , LinearLayoutManager.HORIZONTAL,false);
        myRvAdapter2 = new MyRv2Adapter(dataServicesSource);
        rv2.setLayoutManager(linearLayoutManager2);
        rv2.setAdapter(myRvAdapter2);

        searchBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext() , Services.class ));
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getContext() , Services.class);
        intent.putExtra("CATEGORY",dataCategorySource.get(position).getName());
        startActivity(intent);
    }

    class MyRvAdapter extends RecyclerView.Adapter<MyRvAdapter.MyHolder>{
        private final RecyclerViewInterface recyclerViewInterface;
        ArrayList<Category> dataCategory;
        public MyRvAdapter( ArrayList<Category> dataCategory , RecyclerViewInterface recyclerViewInterface) {
            this.dataCategory = dataCategory;
            this.recyclerViewInterface = recyclerViewInterface;
        }


        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.rv_item,parent,false);
            return new MyHolder(view , recyclerViewInterface);

        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder holder, int position) {
            holder.textView.setText(dataCategory.get(position).name);
            holder.imageView.setImageResource(dataCategory.get(position).image);
        }

        @Override
        public int getItemCount() {
            return dataCategory.size();
        }

        class MyHolder extends RecyclerView.ViewHolder{
            TextView textView;
            ImageView imageView;
            public MyHolder(@NonNull View itemView , RecyclerViewInterface recyclerViewInterface) {
                super(itemView);

                textView = itemView.findViewById(R.id.itemText);
                imageView = itemView.findViewById(R.id.itemImage);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(recyclerViewInterface != null){
                            int pos = getAdapterPosition();

                            if(pos != RecyclerView.NO_POSITION){
                                recyclerViewInterface.onItemClick(pos);
                            }
                        }
                    }
                });

            }
        }
    }

    class MyRv2Adapter extends RecyclerView.Adapter<MyRv2Adapter.MyHolder>{
        ArrayList<Service> dataServices;
        public MyRv2Adapter( ArrayList<Service> dataServices ) {
            this.dataServices = dataServices;
        }


        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.rv_item2,parent,false);
            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder holder, int position) {
            holder.serviceName.setText(dataServices.get(position).name);
            holder.userName.setText("Mohamed Ghodbane");
            holder.serviceImage.setImageResource(dataServices.get(position).mainImage);
            holder.numOrders.setText("("+String.valueOf(dataServices.get(position).numOrders)+")");
            holder.price.setText("$"+String.valueOf(dataServices.get(position).price));
            holder.rating.setText(String.valueOf(dataServices.get(position).rating));
            holder.profileImage.setImageResource(R.drawable.baseline_person_24);
        }

        @Override
        public int getItemCount() {
            return dataServices.size();
        }

        class MyHolder extends RecyclerView.ViewHolder{
            TextView userName , serviceName , numOrders , rating , price;
            ImageView serviceImage , profileImage;
            public MyHolder(@NonNull View itemView) {
                super(itemView);
                rating = itemView.findViewById(R.id.rate);
                numOrders = itemView.findViewById(R.id.sells);
                serviceName = itemView.findViewById(R.id.serviceName);
                profileImage = itemView.findViewById(R.id.profileImage);
                userName = itemView.findViewById(R.id.sellerName);
                serviceImage = itemView.findViewById(R.id.itemImage);
                price = itemView.findViewById(R.id.price);
            }
        }
    }
}