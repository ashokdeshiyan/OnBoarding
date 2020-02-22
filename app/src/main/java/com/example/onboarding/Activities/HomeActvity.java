package com.example.onboarding.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.SearchManager;
import android.app.TimePickerDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.onboarding.Adapters.ContactsAdapter;
import com.example.onboarding.R;
import com.example.onboarding.databinding.ActivityHomeActvityBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.regex.Pattern;

public class HomeActvity extends AppCompatActivity {


    TextWatcher textWatcher;
    ActivityHomeActvityBinding binding;


    private static final int REQUEST_PERMISSION = 1;
    private ListView contactslist;
    private BottomSheetDialog mBottomSheetDialog;

    TextInputLayout email,pass;
    private SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_home_actvity);
            binding = DataBindingUtil.setContentView(this,R.layout.activity_home_actvity);

            contactslist = findViewById(R.id.contactlist);

        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                       REQUEST_PERMISSION);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }


//        if (ActivityCompat.checkSelfPermission(this,
//                Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this,
//                    new String[]{Manifest.permission.READ_CONTACTS,
//                            Manifest.permission.WRITE_CONTACTS}, REQUEST_PERMISSION);
//
//        }

        final View bottomSheetLayout = getLayoutInflater().inflate(R.layout.bottomsheet_dialog, null);
        (bottomSheetLayout.findViewById(R.id.button_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBottomSheetDialog.dismiss();
            }
        });
        (bottomSheetLayout.findViewById(R.id.button_ok)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Ok button clicked", Toast.LENGTH_SHORT).show();
            }
        });

        mBottomSheetDialog = new BottomSheetDialog(this);
        mBottomSheetDialog.setContentView(bottomSheetLayout);
        mBottomSheetDialog.show();


        binding.msgdialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageDialog();
            }
        });

        binding.timepiker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker = new TimePickerDialog(HomeActvity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        binding.timepiker.setText(hourOfDay + ":" + minute);
                    }
                }, hour, minute, false);
                mTimePicker.setTitle("Set Time");
                mTimePicker.show();
            }
        });



        binding.listitems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollallert();
            }
        });

        binding.forms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formDialog();
            }
        });






    }



    private void formDialog() {




        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActvity.this);


        LayoutInflater inflater = HomeActvity.this.getLayoutInflater();

        View login = inflater.inflate(R.layout.logindialog,null);

        builder.setView(login)
                .setPositiveButton("Login", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {


                        dialog.dismiss();

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                       dialog.dismiss();
                    }
                });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

//        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TextInputLayout email = findViewById(R.id.username);
//                TextInputLayout pass = findViewById(R.id.passward);
//                Boolean isValidMail = false;
//                Boolean isValidPass = false;
//                String emailval = email.getEditText().getText().toString().trim();
//                String passval = pass.getEditText().getText().toString().trim();
//
//                if (emailval.length()==0){
//                    email.setError("*Emaill can't be empty.");
//                }else if (!Patterns.EMAIL_ADDRESS.matcher(emailval).matches()){
//                    email.setError("*Invalid eamil.");
//                }
//                else {
//                    email.setError(null);
//                    isValidMail =true;
//                }
//
//
//                if (passval.length()==0){
//                    pass.setError("*Password is required.");
//                }else if (passval.length()<8){
//                    pass.setError("*Atleast 8 character required.");
//                }else{
//                    pass.setError(null);
//                    isValidPass=true;
//                }
//
//                if (!isValidMail && !isValidPass){
//
//
//                }
//            }
//        });


//                .show();




    }

    private void scrollallert() {

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View myScroll = inflater.inflate(R.layout.scroll,null,false);

        TextView tv = myScroll.findViewById(R.id.tvScroll);
        tv.setText("Hello");
        for (int x = 1; x < 15; x++) {
            tv.append("Testing !\n");
            tv.append("Here also testing.\n");
            tv.append("definately testing.\n\n");
        }

        new AlertDialog.Builder(HomeActvity.this).setView(myScroll)
                .setTitle("Scroll View")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }

                }).show();
    }



    public void messageDialog(){
        new AlertDialog.Builder(HomeActvity.this)
                .setTitle("Message Dialog")
                .setMessage("This is message body")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(HomeActvity.this,"Thanks",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }).show();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu,menu);

        MenuItem searchItem = menu.findItem(R.id.searchbar);
        searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint(getResources().getString(R.string.search_hint));
        searchView.setOnQueryTextListener(onQueryTextListener);


        return super.onCreateOptionsMenu(menu);
    }

    private SearchView.OnQueryTextListener onQueryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            Cursor contacts = getListOfContactNames(query);
            String[] cursorColumns = {
                    ContactsContract.Contacts.DISPLAY_NAME_PRIMARY };
            int[] viewIds = {R.id.tvcname};

            SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(HomeActvity.this,
                    R.layout.contectslist,
                    contacts,
                    cursorColumns,
                    viewIds,
                    0
                    );

            contactslist.setAdapter(simpleCursorAdapter);
            contactslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TextView textViewname = view.findViewById(R.id.tvcname);
                    Toast.makeText(HomeActvity.this,"Selected Contect"+textViewname.getText(),
                            Toast.LENGTH_SHORT).show();
                }
            });

            return true;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            Cursor cursor =getListOfContactNames(newText);
            ContactsAdapter contactsAdapter = new ContactsAdapter(HomeActvity.this, cursor,searchView);
            searchView.setSuggestionsAdapter(contactsAdapter);

            return true;
        }
    };

    private Cursor getListOfContactNames(String query) {
        Cursor cur = null;
        ContentResolver cr = getContentResolver();

        String[] mProjection = {ContactsContract.Contacts._ID,
                ContactsContract.Contacts.LOOKUP_KEY,
                ContactsContract.Contacts.HAS_PHONE_NUMBER,
                ContactsContract.Contacts.DISPLAY_NAME_PRIMARY};

        Uri uri = ContactsContract.Contacts.CONTENT_URI;

        String selection = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + " LIKE ?";
        String[] selectionArgs = new String[]{"%"+query+"%"};

                                                                                                                                                                                                                                                                cur = cr.query(uri, mProjection, selection, selectionArgs, null);

        return cur;
    }
}
