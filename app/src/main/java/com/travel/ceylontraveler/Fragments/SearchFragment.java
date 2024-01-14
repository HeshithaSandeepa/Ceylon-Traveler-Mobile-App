package com.travel.ceylontraveler.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.travel.ceylontraveler.R;

import java.util.ArrayList;

public class SearchFragment extends Fragment {
    // List View object
    ListView listView;

    // Define array adapter for ListView
    ArrayAdapter<String> adapter;

    // Define array List for List View data
    ArrayList<String> mylist;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);



        // Initialise ListView with id
        listView = view.findViewById(R.id.listView1);

        // Add items to Array List
        mylist = new ArrayList<>();
        mylist.add("Anuradapuraya");
        mylist.add("polonnaruwa");
        mylist.add("Jaffna");
        mylist.add("Hambantota");
        mylist.add("kandy");
        mylist.add("King Parakramabahu Palace");
        mylist.add("Temple of Tooth Relic(Daladamaligawa)");
        mylist.add("Galviharaya");
        mylist.add("Isurumuniya");
        mylist.add("Jaffna Fort");
        mylist.add("Kandy View Point");
        mylist.add("Katharagama Temple");
        mylist.add("Kirinda Temple Temple");
        mylist.add("Nagadeepaya Temple");
        mylist.add("kandy");
        mylist.add("Royal Botanical Garden Peradeniya");
        mylist.add("Ruwanwali Mahasaya");
        mylist.add("SriMahabodiya");
        mylist.add("Sigiriya");
        mylist.add("Yala National Park");
        mylist.add("kandy");
        mylist.add("Nallur");


        // Set adapter to ListView
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, mylist);
        listView.setAdapter(adapter);

        // Set item click listener to ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item from the ListView
                String selectedItem = (String) parent.getItemAtPosition(position);

                // Create the fragment object for the selected item
                Fragment fragment;
                if (selectedItem.equals("Anuradapuraya")) {
                    fragment = new AnuradapurayaFragment();
                } else if (selectedItem.equals("polonnaruwa")) {
                    fragment = new PolonnaruwaFragment();
                } else if (selectedItem.equals("Jaffna")) {
                    fragment = new JaffnaFragment();
                } else if (selectedItem.equals("Hambantota")) {
                    fragment = new HambantotaFragment();
                } else if (selectedItem.equals("kandy")) {
                    fragment = new KandyFragment();
                } else if (selectedItem.equals("King Parakramabahu Palace")) {
                    fragment = new AncientpalaceFragment();
                } else if (selectedItem.equals("Temple of Tooth Relic(Daladamaligawa)")) {
                    fragment = new DaladamaligawaFragment();
                } else if (selectedItem.equals("Galviharaya")) {
                    fragment = new GalviharayaFragment();
                } else if (selectedItem.equals("Isurumuniya")) {
                    fragment = new IsurumuniyaFragment();
                } else if (selectedItem.equals("Jaffna Fort")) {
                    fragment = new JaffnaFortFragment();
                } else if (selectedItem.equals("Kandy View Point")) {
                    fragment = new KandyViewpointFragment();
                } else if (selectedItem.equals("Katharagama Temple")) {
                    fragment = new KatharagamaFragment();
                } else if (selectedItem.equals("Kirinda Temple")) {
                    fragment = new KirindaFragment();
                } else if (selectedItem.equals("Nagadeepaya Temple")) {
                    fragment = new NagadeepayaFragment();
                } else if (selectedItem.equals("Royal Botanical Garden Peradeniya")) {
                    fragment = new RoyalBotanicalFragment();
                } else if (selectedItem.equals("Ruwanwali Mahasaya")) {
                    fragment = new RuwanwalimahasayaFragment();
                } else if (selectedItem.equals("Sigiriya")) {
                    fragment = new SigiriyaFragment();
                } else if (selectedItem.equals("SriMahabodiya")) {
                    fragment = new SrimahabodiyaFragment();
                } else if (selectedItem.equals("Nallur")) {
                    fragment = new NallurFragment();
                } else if (selectedItem.equals("Yala National Park")) {
                    fragment = new YalaFragment();
                } else {
                    fragment = null;
                }
                // Navigate to the selected fragment screen using the FragmentManager
                if (fragment != null) {
                    FragmentManager fragmentManager = getParentFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frame_layout, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            }
        });
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu, menu);

        // Remove the search menu item for other fragments
        menu.findItem(R.id.search_bar).setVisible(true);

        // Initialise menu item search bar
        // with id and take its object
        MenuItem searchViewItem = menu.findItem(R.id.search_bar);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);

        // attach setOnQueryTextListener
        // to search view defined above
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // Override onQueryTextSubmit method which is call when submit query is searched
            @Override
            public boolean onQueryTextSubmit(String query) {
                // If the list contains the search query than filter the adapter
                // using the filter method with the query as its argument
                if (mylist.contains(query)) {
                    adapter.getFilter().filter(query);
                } else {
                    // Search query not found in List View
                    Toast.makeText(getContext(), "Not found", Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
}
