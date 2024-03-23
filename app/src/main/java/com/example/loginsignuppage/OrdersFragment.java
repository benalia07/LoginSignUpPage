package com.example.loginsignuppage;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OrdersFragment extends Fragment implements RecyclerViewInterface {
    String Email;
    Button ajouter;
    RecyclerView rv1, rv2;
    ArrayList<Category> dataCategorySource;
    ArrayList<Order> dataServicesSource;
    LinearLayoutManager linearLayoutManager1, linearLayoutManager2;
    MyRvAdapter myRvAdapter1;
    MyRvAdapter3 myRvAdapter2;
    DatabaseReference databaseReference,db2;
    String client ;
    Integer prix ;
    String service;
    boolean b=true;
    int i;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_orders, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv2 = view.findViewById(R.id.popular);
        ajouter=view.findViewById(R.id.ajouter);
        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Serviceajouter.class);
                startActivity(intent);
            }
        });

        dataServicesSource = new ArrayList<Order>();


        for( i=0;i<10;i++) {
            // Référence à la base de données Firebase
            databaseReference = FirebaseDatabase.getInstance().getReference().child("Notification").child(""+i);
            databaseReference.addValueEventListener(new ValueEventListener() {
                @SuppressLint("SuspiciousIndentation")
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    // Effacer les données existantes

                    // Vérifier si l'e-mail de l'utilisateur correspond à l'e-mail stocké dans Firebase
                    boolean isEmailMatched = false;
                    for (DataSnapshot notSnapshot : dataSnapshot.getChildren()) {
                        String notificationName = notSnapshot.getKey();
                        if (notificationName.equals("Email")) {
                            String email = notSnapshot.getValue(String.class);
                            Email = getActivity().getIntent().getStringExtra("email");

                            if (email != null && email.equals(Email)) {
                                isEmailMatched = true;
                                i=10;
                                break;
                            }
                        }
                    }

                    // Afficher les notifications si l'e-mail correspond
                    if (isEmailMatched) {
                        for (DataSnapshot notSnapshot : dataSnapshot.getChildren()) {
                            String notificationName = notSnapshot.getKey();
                            if (!notificationName.equals("Email")) {
                                client = notSnapshot.child("Client").getValue(String.class);
                                prix = notSnapshot.child("Prix").getValue(Integer.class);
                                service = notSnapshot.child("Service").getValue(String.class);
                                // Ajouter les nouvelles données à la liste
                                dataServicesSource.add(new Order(service, "the description", client,
                                        R.drawable.baseline_person_24, 4, 582, prix != null ? prix : 0));
                            }
                        }
                    }

                    // Mettez à jour l'adaptateur avec les nouvelles données
                    myRvAdapter2.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Gestion des erreurs lors de la récupération des données
                    Log.d("MainActivity", "Erreur lors de la récupération des notifications depuis Firebase: " + databaseError.getMessage());
                }
            });
        }
        linearLayoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        myRvAdapter2 = new MyRvAdapter3(dataServicesSource);
        rv2.setLayoutManager(linearLayoutManager2);
        rv2.setAdapter(myRvAdapter2);

    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getContext(), Orders.class);
        intent.putExtra("CATEGORY", dataCategorySource.get(position).getName());
        startActivity(intent);
    }

    @Override
    public void onItemClick2(int position) {

    }

    @NonNull
    @Override
    public CreationExtras getDefaultViewModelCreationExtras() {
        return super.getDefaultViewModelCreationExtras();
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
            View view = LayoutInflater.from(getContext()).inflate(R.layout.rv_item3,parent,false);
            return new MyHolder(view , recyclerViewInterface);

        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder holder, int position) {
            holder.textView.setText(dataCategory.get(position).name);
            //holder.imageView.setImageResource(dataCategory.get(position).getImage());
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

    class MyRv2Adapter2 extends RecyclerView.Adapter<MyRv2Adapter2.MyHolder>{
        ArrayList<Order> data;

        public MyRv2Adapter2( ArrayList<Order> data) {
            this.data = data;
        }
        public void  setFilteredList(ArrayList<Order> filteredList){
            this.data = filteredList;
            notifyDataSetChanged();
        }
        @NonNull
        @Override
        public OrdersFragment.MyRv2Adapter2.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.rv_item3,parent,false);
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
    class MyRvAdapter3 extends RecyclerView.Adapter<OrdersFragment.MyRvAdapter3.MyHolder> implements com.example.loginsignuppage.MyRvAdapter3 {

        ArrayList<Order> data;

        public MyRvAdapter3( ArrayList<Order> data) {
            this.data = dataServicesSource;
        }
        public void  setFilteredList(ArrayList<Order> filteredList){
            this.data = filteredList;
            notifyDataSetChanged();
        }
        @NonNull
        @Override
        public MyRvAdapter3.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.rv_item3,parent,false);
            return new MyRvAdapter3.MyHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder holder, @SuppressLint("RecyclerView") int position) {
            holder.serviceName.setText(data.get(position).getName());
            holder.serviceImage.setImageResource(data.get(position).getMainImage());
            holder.numOrders.setText(String.valueOf(data.get(position).getNumOrders()));
            holder.servicePrice.setText(String.valueOf(data.get(position).getPrice()));
            holder.serviceRate.setText(String.valueOf(data.get(position).getRating()));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Obtenir la position de l'élément cliqué
                    int clickedPosition = holder.getAdapterPosition();
                    if (clickedPosition != RecyclerView.NO_POSITION) {
                        // Gérer l'action de clic ici
                        // Par exemple, ouvrir une nouvelle activité avec les détails de l'élément
                        Intent intent = new Intent(v.getContext(), notification.class);
                        Order clickedOrder = data.get(clickedPosition);
                        intent.putExtra("CLIENT", clickedOrder.getClient());
                        intent.putExtra("SERVICE", clickedOrder.getName());
                        intent.putExtra("PRICE", clickedOrder.getPrice());
                        v.getContext().startActivity(intent);
                    }
                }
            });
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
    public String getclient() {
        return client;
    }
    public String getservice() {
        return service;
    }
    public int getprix() {
        return prix;
    }
    public void setEmail(String Email) {
        this.Email=Email;
    }
    public String getEmail()
    {
        return Email;
    }
}