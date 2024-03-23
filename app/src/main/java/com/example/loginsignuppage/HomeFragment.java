 package com.example.loginsignuppage;
import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import android.content.Intent;
 import android.os.Bundle;
  import androidx.annotation.NonNull;
  import androidx.annotation.Nullable;
  import androidx.fragment.app.Fragment;
  import androidx.recyclerview.widget.LinearLayoutManager;
  import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
  import android.view.View;
  import android.view.ViewGroup;
  import android.widget.ImageView;
  import android.widget.TextView;
  import java.util.ArrayList;


        public class HomeFragment extends Fragment implements RecyclerViewInterface {
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
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference categoryRef = database.getReference("Categories");
                DatabaseReference serviceRef = database.getReference("Services");

                dataCategorySource = new ArrayList<>();

                categoryRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Category category = new Category(snapshot.child("Name").getValue(String.class),snapshot.child("Image").getValue(String.class));
                            dataCategorySource.add(category);
                        }

                        myRvAdapter1.notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Handle database error
                    }
                });




                dataServicesSource = new ArrayList<>();


                serviceRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        // dataServicesSource.clear(); // Clear the existing data

                        // Iterate through the dataSnapshot to retrieve service data
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            String serviceName = snapshot.child("Name").getValue(String.class);
                            String serviceDescription = snapshot.child("Description").getValue(String.class);
                            String serviceCategory = snapshot.child("category").getValue(String.class);
                            String serviceImage = snapshot.child("MainImage").getValue(String.class);
                            // Retrieve the Integer values
                            Long ratingLong = snapshot.child("Rating").getValue(Long.class);
                            Long viewsLong = snapshot.child("NumOrders").getValue(Long.class);
                            Long prixLong = snapshot.child("Price").getValue(Long.class);

// Create a new Service object and add it to the list
                            Service service = new Service(serviceName, serviceDescription, serviceCategory,
                                    serviceImage, (ratingLong != null) ? ratingLong.intValue() : 0,
                                    (viewsLong != null) ? viewsLong.intValue() : 0,
                                    (prixLong != null) ? prixLong.intValue() : 0);
                            dataServicesSource.add(service);

                        }
                        // Notify the adapter that the data has changed
                        myRvAdapter2.notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Handle database error
                    }
                });


                linearLayoutManager1 = new LinearLayoutManager(getContext() , LinearLayoutManager.HORIZONTAL,false);
                myRvAdapter1 = new MyRvAdapter(dataCategorySource,this);

                rv1.setLayoutManager(linearLayoutManager1);
                rv1.setAdapter(myRvAdapter1);
                linearLayoutManager2 = new LinearLayoutManager(getContext() , LinearLayoutManager.HORIZONTAL,false);
                myRvAdapter2 = new MyRv2Adapter(dataServicesSource, this);
                rv2.setLayoutManager(linearLayoutManager2);
                rv2.setAdapter(myRvAdapter2);

                searchBar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(getContext() , Services.class ));
                    }
                });
                // Inside onViewCreated() method

// Listen for category data changes




            }
            public void adapternotify(ArrayList<Category> dataCategorySource  )
            {

                myRvAdapter1 = new MyRvAdapter(dataCategorySource,this);

            }

            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext() , Services.class);
                intent.putExtra("CATEGORY",dataCategorySource.get(position).getName());
                startActivity(intent);
            }

            @Override
            public void onItemClick2(int position) {
                Intent intent = new Intent(getContext() , OrderPage.class);
                intent.putExtra("TITLE", dataServicesSource.get(position).getName());
                intent.putExtra("DESCRIPTION",dataServicesSource.get(position).getDescription());
                intent.putExtra("IMAGE",dataServicesSource.get(position).getMainImage());
                intent.putExtra("IMAGES",dataServicesSource.get(position).getImages());
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
                    String httpsUrl = dataCategory.get(position).imageUrl;
                    Glide.with(requireContext()).load(httpsUrl).into(holder.imageView);
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
                private final RecyclerViewInterface recyclerViewInterface;
                public MyRv2Adapter(ArrayList<Service> dataServices, RecyclerViewInterface recyclerViewInterface) {
                    this.dataServices = dataServices;
                    this.recyclerViewInterface = recyclerViewInterface;
                }


                @NonNull
                @Override
                public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                    View view = LayoutInflater.from(getContext()).inflate(R.layout.rv_item2,parent,false);
                    return new MyHolder(view,recyclerViewInterface);
                }

                @Override
                public void onBindViewHolder(@NonNull MyHolder holder, int position) {
                    holder.serviceName.setText(dataServices.get(position).getDescription());
                    holder.userName.setText(dataServices.get(position).getName());
                    String httpsUrl = dataServices.get(position).getMainImage();
                    Glide.with(requireContext()).load(httpsUrl).into(holder.serviceImage);
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
                    public MyHolder(@NonNull View itemView,RecyclerViewInterface recyclerViewInterface) {
                        super(itemView);
                        rating = itemView.findViewById(R.id.rate);
                        numOrders = itemView.findViewById(R.id.sells);
                        serviceName = itemView.findViewById(R.id.serviceName);
                        profileImage = itemView.findViewById(R.id.profileImage);
                        userName = itemView.findViewById(R.id.sellerName);
                        serviceImage = itemView.findViewById(R.id.itemImage);
                        price = itemView.findViewById(R.id.price);

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