package com.example.loginsignuppage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Orders extends AppCompatActivity {
    RecyclerView rv;
    ArrayList<Order> dataSource;
    LinearLayoutManager linearLayoutManager;
    MyRvAdapter3 myRvAdapter3;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        String category  = getIntent().getStringExtra("CATEGORY");




        dataSource = new ArrayList<Order>();
        dataSource.add(new Order("Notification1","the description","Graphics & Design",R.drawable.baseline_person_24,4,582,140));
        dataSource.add(new Order("Notification2","the description","Programming & Tech",R.drawable.baseline_person_24,4,582,140));
        dataSource.add(new Order("Notification3","the description","Graphics & Design",R.drawable.baseline_person_24,4,582,140));
        dataSource.add(new Order("Notification4","the description","Graphics & Design",R.drawable.baseline_person_24,4,582,140));
        dataSource.add(new Order("Notification5 ","the description","Programming & Tech",R.drawable.baseline_person_24,4,582,140));
        dataSource.add(new Order("Notification6","the description","Graphics & Design",R.drawable.baseline_person_24,4,582,140));
        dataSource.add(new Order("Notification7","the description","Digital Marketing",R.drawable.baseline_person_24,4,582,140));
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Notification").child("1");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot notSnapshot : dataSnapshot.getChildren()) {
                    String notificationName = notSnapshot.getKey();
                    String client = notSnapshot.child("Client").getValue(String.class);
                    Integer prix = notSnapshot.child("Prix").getValue(Integer.class);
                    String service = notSnapshot.child("Service").getValue(String.class);

                    dataSource.add(new Order(notificationName, "the description", service,
                            R.drawable.baseline_person_24, 4, 582, prix != null ? prix : 0));
                }
                // Mettez à jour l'adaptateur avec les nouvelles données
                myRvAdapter3.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Gérez l'erreur de la base de données
            }
        });


        linearLayoutManager = new LinearLayoutManager(Orders.this , LinearLayoutManager.VERTICAL,false);
        myRvAdapter3 = new Orders.MyRvAdapter3(dataSource);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(myRvAdapter3);
    }

    private void filterList(String text) {
        ArrayList<Order> filtredList = new ArrayList<>();
        for(Order s : dataSource){
            if(s.getName().toString().toLowerCase().contains(text.toLowerCase()) || s.getDescription().toString().toLowerCase().contains(text.toLowerCase())){
                filtredList.add(s);
                myRvAdapter3.setFilteredList(filtredList);
            }
        }
    }

    class MyRvAdapter3 extends RecyclerView.Adapter<MyRvAdapter3.MyHolder>{

        ArrayList<Order> data;

        public MyRvAdapter3( ArrayList<Order> data) {
            this.data = data;
        }
        public void  setFilteredList(ArrayList<Order> filteredList){
            this.data = filteredList;
            notifyDataSetChanged();
        }
        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(Orders.this).inflate(R.layout.rv_item3,parent,false);
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