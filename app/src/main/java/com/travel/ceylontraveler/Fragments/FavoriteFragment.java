package com.travel.ceylontraveler.Fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.travel.ceylontraveler.Database.DatabaseHelper;
import com.travel.ceylontraveler.R;

import java.util.ArrayList;

public class FavoriteFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        // Accessing the database and retrieving favorite pages
        DatabaseHelper dbHelper = new DatabaseHelper(getActivity());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {DatabaseHelper.COLUMN_PAGE_NAME};
        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME2, projection, null, null, null, null, null);
        ArrayList<String> favoritePagesList = new ArrayList<>();
        while (cursor.moveToNext()) {
            String pageName = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PAGE_NAME));
            favoritePagesList.add(pageName);
        }
        cursor.close();
        db.close();

        /// Creating an adapter for listView///
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireActivity(), android.R.layout.simple_list_item_1, favoritePagesList);
        ListView listView = view.findViewById(R.id.favorites_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedPageName = (String) parent.getItemAtPosition(position);
                openSelectedPage(selectedPageName);
            }
        });return view;
    }


    // open the selected screen in FavoriteFragment
    private void openSelectedPage(String pageName) {
        Fragment fragment;
        switch (pageName) {
            case "Jaffna":
                fragment = new JaffnaFragment();
                break;
            case "Anuradapuraya":
                fragment = new AnuradapurayaFragment();
                break;
            case "Sri Daladamaligawa":
                fragment = new DaladamaligawaFragment();
                break;
            case "Gal Viharaya":
                fragment = new GalviharayaFragment();
                break;
            case "Hambantota":
                fragment = new HambantotaFragment();
                break;
            case "Isurumuniya":
                fragment = new IsurumuniyaFragment();
                break;
            case "JaffnaFort":
                fragment = new JaffnaFortFragment();
                break;
            case "Kandy":
                fragment = new KandyFragment();
                break;
            case "KandyViewPoint":
                fragment = new KandyViewpointFragment();
                break;
            case "Katharagama":
                fragment = new KatharagamaFragment();
                break;
            case "Kirinda":
                fragment = new KirindaFragment();
                break;
            case "King Parakramabahu Statue":
                fragment = new KingParakramabahuFragment();
                break;
            case "Naagadeepaya":
                fragment= new NagadeepayaFragment();
                break;
            case "Nallur":
                fragment = new NallurFragment();
                break;
            case "Polonnaruwa":
                fragment = new PolonnaruwaFragment();
                break;
            case "Royal Botanical Garden Peradeniya":
                fragment = new RoyalBotanicalFragment();
                break;
            case "Ruwanwali Mahasaya":
                fragment = new RuwanwalimahasayaFragment();
                break;
            case "Sigiriya":
                fragment = new SigiriyaFragment();
                break;
            case "Srimahabodiya":
                fragment = new SrimahabodiyaFragment();
                break;
            case "Yala":
                fragment = new YalaFragment();
                break;
            default:

                return;
        }
        //Replace the current fragment  with the new fragment
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .addToBackStack(null)
                .commit();
    }


}
