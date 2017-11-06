package in.good_work.sqlite_module;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import in.good_work.sqlite_module.Model.Person;
import in.good_work.sqlite_module.Model.PersonContract;
import in.good_work.sqlite_module.db.DBContentProvider;

public class AddpersonActivity extends AppCompatActivity {
    EditText edName;
    EditText edSurname;
    EditText edPhone;
    EditText edEmail;
    EditText edSkype;
    TextView tvResult;

    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addperson);

        this.edName = (EditText) findViewById(R.id.et_addperson_name);
        this.edSurname = (EditText) findViewById(R.id.et_addperson_surname);
        this.edPhone = (EditText) findViewById(R.id.et_addperson_phone);
        this.edEmail = (EditText) findViewById(R.id.et_addperson_email);
        this.edSkype = (EditText) findViewById(R.id.et_addperson_skype);
        this.tvResult = (TextView) findViewById(R.id.tv_addperson_result);

        this.btnSave = (Button) findViewById(R.id.btn_addperson_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv = new ContentValues();
                cv.put(PersonContract.KEY_NAME, edName.getText().toString());
                cv.put(PersonContract.KEY_SURNAME, edSurname.getText().toString());
                cv.put(PersonContract.KEY_MAIL, edEmail.getText().toString());
                cv.put(PersonContract.KEY_PHONE, edPhone.getText().toString());
                cv.put(PersonContract.KEY_SKYPE, edSkype.getText().toString());
                getContentResolver().insert(DBContentProvider.PERSONS_CONTENT_URI, cv);

                Intent intent = new Intent(AddpersonActivity.this, MainActivity.class);
                startActivity(intent);
                /*Cursor cursor = getContentResolver().query(DBContentProvider.PERSONS_CONTENT_URI, null, null, null, null);
                if (cursor.moveToFirst()) {
                    Person person = new Person();
                    while (cursor.moveToNext()) {
                        person.setName(getStringValueCursor(cursor, PersonContract.KEY_NAME));
                        person.setSurname(getStringValueCursor(cursor, PersonContract.KEY_SURNAME));
                        person.setEmail(getStringValueCursor(cursor, PersonContract.KEY_MAIL));
                        person.setPhone(getStringValueCursor(cursor, PersonContract.KEY_PHONE));
                        person.setSkype(getStringValueCursor(cursor, PersonContract.KEY_SKYPE));
                        tvResult.setText(tvResult.getText() + person.toString());
                    }
                }*/
            }
        });

    }
}
