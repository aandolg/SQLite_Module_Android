package in.good_work.sqlite_module;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import in.good_work.sqlite_module.Adapter.PersonAdapter;
import in.good_work.sqlite_module.Model.Person;
import in.good_work.sqlite_module.Model.PersonContract;
import java.util.ArrayList;

import in.good_work.sqlite_module.common.ItemClickListener;
import in.good_work.sqlite_module.db.DBContentProvider;

public class MainActivity extends AppCompatActivity implements ItemClickListener<Person>{
    private RecyclerView rvListPerson;
    private PersonAdapter personAdapter;
    private RecyclerView.LayoutManager personLayoutManager;
    private Button btnAddPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvListPerson = (RecyclerView) findViewById(R.id.rv_main_listperson);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rvListPerson.setLayoutManager(llm);


        Cursor cursor = getContentResolver().query(DBContentProvider.PERSONS_CONTENT_URI, null, null, null, null);
        ArrayList<Person> personList = new ArrayList<Person>();

        if (cursor.moveToFirst()) {

            while (cursor.moveToNext()) {
                Person person = new Person();
                person.setId(getStringValueCursor(cursor, PersonContract._ID));
                person.setName(getStringValueCursor(cursor, PersonContract.KEY_NAME));
                person.setSurname(getStringValueCursor(cursor, PersonContract.KEY_SURNAME));
                person.setEmail(getStringValueCursor(cursor, PersonContract.KEY_MAIL));
                person.setPhone(getStringValueCursor(cursor, PersonContract.KEY_PHONE));
                person.setSkype(getStringValueCursor(cursor, PersonContract.KEY_SKYPE));
                personList.add(person);
            }
        }


        personAdapter = new PersonAdapter();
        personAdapter.addAll(personList);
        rvListPerson.setAdapter(personAdapter);
        personAdapter.setInterface(this);

        btnAddPerson = (Button) findViewById(R.id.btn_main_add_person);
        btnAddPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_main_add_person:
                        Intent intent = new Intent(MainActivity.this, AddpersonActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        showText("Action unknown");
                        break;
                }
            }
        });
    }

    public String getStringValueCursor(Cursor cursor, String nameColumn) {
        return cursor.getString(cursor.getColumnIndex(nameColumn));
    }

    private void showText(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Intent intent = item.getIntent();

        switch (item.getItemId()) {
            case R.id.menu_view:
                break;
            case R.id.menu_delete:
                if (intent != null) {
                    String personId = intent.getStringExtra("personId");
                    Uri delete = Uri.parse(DBContentProvider.PERSONS_CONTENT_URI + "/" + personId);
                    getContentResolver().delete(delete, personId, null);
                    Toast.makeText(this, "DELETE " + personId, Toast.LENGTH_LONG).show();
                }
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onItemClicked(Person item, boolean b) {
        Toast.makeText(getBaseContext(),item.toString(), Toast.LENGTH_LONG).show();
    }
}
