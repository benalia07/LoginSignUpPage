package com.example.loginsignuppage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Services extends AppCompatActivity implements RecyclerViewInterface {
    RecyclerView rv;
    LinearLayoutManager linearLayoutManager;
    MyRvAdapter myRvAdapter;
    SearchView searchView;
    ArrayList<Service>  dataServicesSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        String category  = getIntent().getStringExtra("CATEGORY");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference serviceRef = database.getReference("Services");
        dataServicesSource = new ArrayList<>();


        serviceRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // dataServicesSource.clear(); // Clear the existing data

                // Iterate through the dataSnapshot to retrieve service data
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String serviceName = snapshot.child("Name").getValue(String.class);
                    String serviceDescription = snapshot.child("Description").getValue(String.class);
                    String serviceCategory = snapshot.child("Category").getValue(String.class);
                    String serviceImage = snapshot.child("MainImage").getValue(String.class);
                    // Retrieve the Integer values
                    Integer rating = snapshot.child("Rating").getValue(Integer.class);
                    Integer views = snapshot.child("NumOrders").getValue(Integer.class);
                    Integer prix = snapshot.child("Price").getValue(Integer.class);


                    Service service = new Service(serviceName, serviceDescription, serviceCategory,
                            serviceImage, (rating != null) ? rating.intValue() : 0,
                            (views != null) ? views.intValue() : 0,
                            (prix != null) ? prix.intValue() : 0);
                    if(category.equals(serviceCategory)) {
                        dataServicesSource.add(service);
                    }


                }
                // Notify the adapter that the data has changed
                myRvAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle database error
            }
        });
        rv = findViewById(R.id.servicesFromCatigory);
        searchView = findViewById(R.id.search_services);
        searchView.onActionViewExpanded();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });


        linearLayoutManager = new LinearLayoutManager(Services.this , LinearLayoutManager.VERTICAL,false);
        myRvAdapter = new MyRvAdapter( Services.this, dataServicesSource);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(myRvAdapter);
    }

    private void filterList(String text) {
        ArrayList<Service> filtredList = new ArrayList<>();
        for(Service s : dataServicesSource){
            if(s.getName().toString().toLowerCase().contains(text.toLowerCase()) || s.getDescription().toString().toLowerCase().contains(text.toLowerCase())){
                filtredList.add(s);
                myRvAdapter.setFilteredList(filtredList);
            }
        }
    }


    @Override
    public void onItemClick(int position) {

    }

    public void onItemClick2(int position) {
        Intent intent = new Intent(Services.this , OrderPage.class);
        intent.putExtra("TITLE", dataServicesSource.get(position).getName());
        intent.putExtra("DESCRIPTION",dataServicesSource.get(position).getDescription());
        intent.putExtra("IMAGE",dataServicesSource.get(position).getMainImage());
        intent.putExtra("IMAGES",dataServicesSource.get(position).getImages());
        startActivity(intent);
    }

    class MyRvAdapter extends RecyclerView.Adapter<MyRvAdapter.MyHolder>{
        private final RecyclerViewInterface recyclerViewInterface;

        ArrayList<Service> data;

        public MyRvAdapter(RecyclerViewInterface recyclerViewInterface, ArrayList<Service> data) {
            this.recyclerViewInterface = recyclerViewInterface;
            this.data = data;
        }
        public void  setFilteredList(ArrayList<Service> filteredList){
            this.data = filteredList;
            notifyDataSetChanged();
        }
        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(Services.this).inflate(R.layout.rv_item3,parent,false);
            return new MyHolder(view,recyclerViewInterface);

        }


        @Override
        public void onBindViewHolder(@NonNull MyHolder holder, int position) {
            holder.serviceName.setText(data.get(position).getName());
            String httpsUrl = data.get(position).getMainImage();
            Glide.with(Services.this).load(httpsUrl).into(holder.serviceImage);
            holder.numOrders.setText(String.valueOf(data.get(position).getNumOrders()));
            holder.servicePrice.setText(String.valueOf(data.get(position).getPrice()));
            holder.serviceRate.setText(String.valueOf(data.get(position).getRating()));
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class MyHolder extends RecyclerView.ViewHolder{
            TextView serviceName , servicePrice , serviceRate , numOrders;
            ImageView serviceImage;
            public MyHolder(@NonNull View itemView,RecyclerViewInterface recyclerViewInterface) {
                super(itemView);
                serviceName = itemView.findViewById(R.id.serviceName);
                serviceImage = itemView.findViewById(R.id.itemImage);
                servicePrice = itemView.findViewById(R.id.price);
                serviceRate = itemView.findViewById(R.id.rate);
                numOrders = itemView.findViewById(R.id.sells);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(recyclerViewInterface != null){
                            int pos = getAdapterPosition();

                            if(pos != RecyclerView.NO_POSITION){
                                recyclerViewInterface.onItemClick2(pos);
                            }
                        }
                    }
                });
            }
        }
    }
}