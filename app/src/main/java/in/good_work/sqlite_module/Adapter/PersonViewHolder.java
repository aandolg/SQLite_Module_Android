package in.good_work.sqlite_module.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import in.good_work.sqlite_module.MainActivity;
import in.good_work.sqlite_module.Model.Person;
import in.good_work.sqlite_module.R;
import in.good_work.sqlite_module.common.ItemClickListener;
import in.good_work.sqlite_module.db.DBContentProvider;

/**
 * Created by Alex on 06.11.2017.
 */

public class PersonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener {
    private TextView tvPersonName;
    private TextView tvPersonSurname;
    private TextView tvPersonPhone;
    private TextView tvPersonSkype;
    private TextView tvPersonEmail;
    private ImageButton imbntCall;
    private Person person;
    Context context;
    private ItemClickListener<Person> listener;

    public PersonViewHolder(View itemView, Context context) {
        super(itemView);
        this.context = itemView.getContext();
        tvPersonName = (TextView) itemView.findViewById(R.id.person_name);
        tvPersonSurname = (TextView) itemView.findViewById(R.id.person_surname);
        tvPersonPhone = (TextView) itemView.findViewById(R.id.person_phone);
        tvPersonSkype = (TextView) itemView.findViewById(R.id.person_skype);
        tvPersonEmail = (TextView) itemView.findViewById(R.id.person_email);
        imbntCall = (ImageButton) itemView.findViewById(R.id.btn_person_list_call);
        imbntCall.setOnClickListener(this);
        itemView.setOnClickListener(this);

    }
    public void bind(Person person) {
        if (person != null) {
            this.person = person;
            tvPersonName.setText(person.getName());
            tvPersonSurname.setText(person.getSurname());
            tvPersonPhone.setText(person.getPhone());
            tvPersonEmail.setText(person.getEmail());

            tvPersonSkype.setText(person.getSkype());
            itemView.setOnCreateContextMenuListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(context, "Click icon", Toast.LENGTH_LONG).show();
        switch (v.getId()) {
            case R.id.btn_person_list_call:
                if (person != null) {
                    if (person.getPhone() != null) {
                        //Call with change phone
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", person.getPhone(), null));
                        //Call
                       /* Intent intent = new Intent(Intent.ACTION_CALL, Uri.fromParts("tel", user.getmPhone(), null));
                        if (ActivityCompat.checkSelfPermission(MainActivity.getAppContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }*/
                        v.getContext().startActivity(intent);
                    }
                }
                break;
            case R.id.btn_person_list_skype:
                break;
            case R.id.btn_person_list_delete:
                Long personId = person.getId();
                Uri delete = Uri.parse(DBContentProvider.PERSONS_CONTENT_URI + "/" + personId);
                context.getContentResolver().delete(delete, personId.toString(), null);
                Toast.makeText(context, "DELETE " + personId, Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_person_list_email:
                Toast.makeText(context, "Email send", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("message/rfc822");
                intent.putExtra(Intent.EXTRA_EMAIL  , new String[]{person.getEmail()});
                intent.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
                intent.putExtra(Intent.EXTRA_TEXT   , "body of email");
                try {
                    v.getContext().startActivity(Intent.createChooser(intent, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(context, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                Toast.makeText(context, v.toString(), Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = new MenuInflater(context);
        inflater.inflate(R.menu.person_list_context_menu, menu);

         MenuItem item = menu.getItem(0);
        /* Intent intentView = new Intent(context, ShowInfoUserActivity.class);
        intentView.putExtra("userId", this.user.getUserId());
        item.setIntent(intentView);*/
        item = menu.getItem(1);
        Intent intentDelete = new Intent(context, MainActivity.class);
        intentDelete.putExtra("personId", this.person.getId());
        item.setIntent(intentDelete);
    }

    public void setListener(ItemClickListener<Person> listener) {
        this.listener = listener;
    }
}
