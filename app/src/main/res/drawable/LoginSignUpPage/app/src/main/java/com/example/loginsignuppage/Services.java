package com.example.loginsignuppage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Services extends AppCompatActivity {
    RecyclerView rv;
    ArrayList<Service> dataSource;
    LinearLayoutManager linearLayoutManager;
    MyRvAdapter myRvAdapter;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        String category  = getIntent().getStringExtra("CATEGORY");
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



        dataSource = new ArrayList<>();
        dataSource.add(new Service("Design logo for your business with unlimited revisions","the description","Graphics & Design",R.drawable.baseline_person_24,4,582,140));
        dataSource.add(new Service("Design logo","the description","Programming & Tech",R.drawable.baseline_person_24,4,582,140));
        dataSource.add(new Service("Design logo for your business","the description","Graphics & Design",R.drawable.baseline_person_24,4,582,140));
        dataSource.add(new Service("Design logo for your business with unlimited revisions","the description","Graphics & Design",R.drawable.baseline_person_24,4,582,140));
        dataSource.add(new Service("Design logo","the description","Programming & Tech",R.drawable.baseline_person_24,4,582,140));
        dataSource.add(new Service("Design logo for your business with unlimited revisions","the description","Graphics & Design",R.drawable.baseline_person_24,4,582,140));
        dataSource.add(new Service("Design logo for your business with","the description","Digital Marketing",R.drawable.baseline_person_24,4,582,140));
        linearLayoutManager = new LinearLayoutManager(Services.this , LinearLayoutManager.VERTICAL,false);
        myRvAdapter = new Services.MyRvAdapter(dataSource);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(myRvAdapter);
    }

    private void filterList(String text) {
        ArrayList<Service> filtredList = new ArrayList<>();
        for(Service s : dataSource){
            if(s.getName().toString().toLowerCase().contains(text.toLowerCase()) || s.getDescription().toString().toLowerCase().contains(text.toLowerCase())){
                filtredList.add(s);
                myRvAdapter.setFilteredList(filtredList);
            }
        }
    }

    class MyRvAdapter extends RecyclerView.Adapter<MyRvAdapter.MyHolder>{

        ArrayList<Service> data;

        public MyRvAdapter( ArrayList<Service> data) {
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
                return new MyHolder(view);

        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder holder, int position) {
            holder.serviceName.setText(data.get(position).getName());
            holder.serviceImage.setImageResource(data.get(position).getMainImage());
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
            public MyHolder(@NonNull View itemView) {
                super(itemView);
                serviceName = itemView.findViewById(R.id.serviceName);
                serviceImage = itemView.findViewById(R.id.itemImage);
                servicePrice = itemView.findViewById(R.id.price);
                serviceRate = itemView.findViewById(R.id.rate);
                numOrders = itemView.findViewById(R.id.sells);
            }
        }
    }
}