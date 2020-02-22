package com.example.onboarding.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onboarding.R;

import static com.example.onboarding.R.id.tvcname;

public class ContactsAdapter extends CursorAdapter {

    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private SearchView searchView;


    public ContactsAdapter(Context context, Cursor c, SearchView sv) {
        super(context, c, false);
        mContext = context;
        searchView = sv;
        mLayoutInflater = LayoutInflater.from(context);

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        View v = mLayoutInflater.inflate(R.layout.contectslist,parent,false);
        return v;
    }

    @Override
    public void bindView(final View view, final Context context, Cursor cursor) {

        String name = cursor.getString(cursor.getColumnIndex(
                ContactsContract.Contacts.DISPLAY_NAME));
        String phone = cursor.getString(cursor.getColumnIndex(
                ContactsContract.Contacts.HAS_PHONE_NUMBER));


        TextView nametv = view.findViewById(tvcname);
        nametv.setText(name);
        TextView phonetv = view.findViewById(R.id.tvcphone);
        phonetv.setText(phone);


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView pName = (TextView) view.findViewById(tvcname);
                searchView.setIconified(true);
                //get contact details and display
                Toast.makeText(context, "Selected Contact "+pName.getText(),
                        Toast.LENGTH_LONG).show();
            }
        });

    }
}
