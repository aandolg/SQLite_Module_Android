package in.good_work.sqlite_module;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import in.good_work.sqlite_module.Model.Person;
import in.good_work.sqlite_module.Model.PersonContract;
import in.good_work.sqlite_module.db.DBContentProvider;

public class DetailsPersonActivity extends AppCompatActivity{
    EditText edName;
    EditText edSurname;
    EditText edPhone;
    EditText edEmail;
    EditText edSkype;

    ToggleButton tbtnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_person);
        Intent intent = getIntent();
        String itemId = String.valueOf(intent.getLongExtra("itemId",0));
        Cursor cursor = getContentResolver().query(DBContentProvider.PERSONS_CONTENT_URI, null, PersonContract._ID + " = " + itemId,null, null);
        final Person person = new Person();
        if (cursor.moveToFirst()) {
            person.setId(Long.parseLong(MainActivity.getStringValueCursor(cursor, PersonContract._ID)));
            person.setName(MainActivity.getStringValueCursor(cursor, PersonContract.KEY_NAME));
            person.setSurname(MainActivity.getStringValueCursor(cursor, PersonContract.KEY_SURNAME));
            person.setEmail(MainActivity.getStringValueCursor(cursor, PersonContract.KEY_MAIL));
            person.setPhone(MainActivity.getStringValueCursor(cursor, PersonContract.KEY_PHONE));
            person.setSkype(MainActivity.getStringValueCursor(cursor, PersonContract.KEY_SKYPE));
        }

        this.edName = (EditText) findViewById(R.id.et_detailsperson_name);
        this.edSurname = (EditText) findViewById(R.id.et_detailsperson_surname);
        this.edPhone = (EditText) findViewById(R.id.et_detailsperson_phone);
        this.edEmail = (EditText) findViewById(R.id.et_detailsperson_email);
        this.edSkype = (EditText) findViewById(R.id.et_detailsperson_skype);

        this.tbtnSave = (ToggleButton) findViewById(R.id.tbtn_detailsperson_save);

        this.edName.setText(person.getName());
        edName.setEnabled(false);
        this.edSurname.setText(person.getSurname());
        edSurname.setEnabled(false);
        this.edPhone.setText(person.getPhone());
        edPhone.setEnabled(false);
        this.edEmail.setText(person.getEmail());
        edEmail.setEnabled(false);
        this.edSkype.setText(person.getSkype());
        edSkype.setEnabled(false);

        tbtnSave.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    edName.setEnabled(true);
                    edSurname.setEnabled(true);
                    edPhone.setEnabled(true);
                    edEmail.setEnabled(true);
                    edSkype.setEnabled(true);
                } else {
                    ContentValues cv = new ContentValues();
                    cv.put(PersonContract.KEY_NAME, edName.getText().toString());
                    cv.put(PersonContract.KEY_SURNAME, edSurname.getText().toString());
                    cv.put(PersonContract.KEY_MAIL, edEmail.getText().toString());
                    cv.put(PersonContract.KEY_PHONE, edPhone.getText().toString());
                    cv.put(PersonContract.KEY_SKYPE, edSkype.getText().toString());
                    getContentResolver().update(DBContentProvider.PERSONS_CONTENT_URI, cv, PersonContract._ID + " = " + String.valueOf(person.getId()), null);
                    Intent intent = new Intent(DetailsPersonActivity.this, MainActivity.class);
                    startActivity(intent);
                }

            }
        });
    }


}
