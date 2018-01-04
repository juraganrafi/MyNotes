package com.belajar.rafi.pajaku;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.belajar.rafi.pajaku.Notif.NotifPreference;
import com.belajar.rafi.pajaku.Notif.NotifReceiver;
import com.belajar.rafi.pajaku.db.NoteHelper;
import com.belajar.rafi.pajaku.entity.Note;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FormAddUpdateActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvReminderDate, tvReminderTime;
    private EditText edtReminderMessage, edtReminderTime;
    private Button btnReminderDate, btnReminderTime;

    private Calendar calDate, calTime;

    private NotifReceiver notifReceiver;
    private NotifPreference notifPreference;

    EditText edtVehicle, edtBbnkb, edtPkb, edtSwdkllaj, edtAdmSTNK, edtAdmTNKB, edtJumlah;
    Button btnSubmit;

    public static String EXTRA_NOTE = "extra_note";
    public static String EXTRA_POSITION = "extra_position";

    private boolean isEdit = false;
    public static int REQUEST_ADD       = 100;
    public static int RESULT_ADD        = 101;
    public static int REQUEST_UPDATE    = 200;
    public static int RESULT_UPDATE     = 201;
    public static int RESULT_DELETE     = 301;

    private Note note;
    private int position;
    private NoteHelper noteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_add_update);

        edtVehicle  = (EditText)findViewById(R.id.edt_vehicle);
        edtBbnkb    = (EditText)findViewById(R.id.edt_bbnkb);
        edtPkb      = (EditText)findViewById(R.id.edt_pkb);
        edtSwdkllaj = (EditText)findViewById(R.id.edt_swdkllaj);
        edtAdmSTNK  = (EditText)findViewById(R.id.edt_admSTNK);
        edtAdmTNKB  = (EditText)findViewById(R.id.edt_admTNKB);
        edtJumlah   = (EditText)findViewById(R.id.edt_jumlah);
        btnSubmit   = (Button)findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(this);

        tvReminderDate = (TextView)findViewById(R.id.tv_note_date);
        tvReminderTime = (TextView)findViewById(R.id.tv_note_time);
        edtReminderMessage  = (EditText)findViewById(R.id.edt_msg);
        btnReminderDate = (Button)findViewById(R.id.click_date);
        btnReminderDate.setOnClickListener(this);
        btnReminderTime = (Button)findViewById(R.id.click_hour);
        btnReminderTime.setOnClickListener(this);

        calDate = Calendar.getInstance();
        calTime = Calendar.getInstance();

        notifPreference = new NotifPreference(this);
        notifReceiver = new NotifReceiver();

        if (!TextUtils.isEmpty(notifPreference.getKeyDate())){
            setNotifText();
        }


        noteHelper = new NoteHelper(this);
        noteHelper.open();

        note = getIntent().getParcelableExtra(EXTRA_NOTE);

        if (note != null){
            position = getIntent().getIntExtra(EXTRA_POSITION, 0);
            isEdit = true;
        }

        String actionBarTitle = null;
        String btnTitle = null;

        if (isEdit){
            actionBarTitle = "Ubah";
            btnTitle = "Update";
            edtVehicle.setText(note.getVehicle());
            edtBbnkb.setText(note.getBbnkb());
            edtPkb.setText(note.getPkb());
            edtSwdkllaj.setText(note.getSwdkllaj());
            edtAdmSTNK.setText(note.getAdmSTNK());
            edtAdmTNKB.setText(note.getAdmTNKB());
            edtJumlah.setText(note.getJumlah());
        } else {
            actionBarTitle = "Tambah";
            btnTitle = "Simpan";
        }

        getSupportActionBar().setTitle(actionBarTitle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnSubmit.setText(btnTitle);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (noteHelper != null){
            noteHelper.close();
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_submit){
            String vehicle  = edtVehicle.getText().toString().trim();
            String bbnkb    = edtBbnkb.getText().toString().trim();
            String pkb      = edtPkb.getText().toString().trim();
            String swdkllaj = edtSwdkllaj.getText().toString().trim();
            String admSTNK  = edtAdmSTNK.getText().toString().trim();
            String admTNKB  = edtAdmTNKB.getText().toString().trim();
            String jumlah   = edtJumlah.getText().toString().trim();

            boolean isEmpty = false;

            if (TextUtils.isEmpty(vehicle)){
                isEmpty = true;
                edtVehicle.setError("Tidak boleh kosong");
            }

            if (!isEmpty){
                Note newNote = new Note();
                newNote.setVehicle(vehicle);
                newNote.setBbnkb(bbnkb);
                newNote.setPkb(pkb);
                newNote.setSwdkllaj(swdkllaj);
                newNote.setAdmSTNK(admSTNK);
                newNote.setAdmTNKB(admTNKB);
                newNote.setJumlah(jumlah);

                Intent intent = new Intent();

                if (isEdit){
                    newNote.setDate(note.getDate());
                    newNote.setId(note.getId());
                    noteHelper.update(newNote);

                    intent.putExtra(EXTRA_POSITION, position);
                    setResult(RESULT_UPDATE, intent);
                    finish();
                } else {
                    newNote.setDate(getCurrentDate());
                    noteHelper.insert(newNote);

                    setResult(RESULT_ADD);
                    finish();
                }
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String keyDate = dateFormat.format(calDate.getTime());

            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            String keyTime =timeFormat.format(calTime.getTime());
            String keyMessage = edtReminderMessage.getText().toString();
        }

        if (view.getId() == R.id.click_date){
            final Calendar currentDate = Calendar.getInstance();
            new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                    calDate.set(year, monthOfYear, dayOfMonth);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    tvReminderDate.setText(dateFormat.format(calDate.getTime()));
                }
            }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();
        } else if (view.getId() == R.id.click_hour){
            final Calendar currentDate = Calendar.getInstance();
            new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                    calTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    calTime.set(Calendar.MINUTE, minute);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
                    tvReminderTime.setText(dateFormat.format(calTime.getTime()));
                }
            }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), true).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (isEdit){
            getMenuInflater().inflate(R.menu.menu_form, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_delete:
                showAlertDialog(ALERT_DIALOG_DELETE);
                break;
            case android.R.id.home:
                showAlertDialog(ALERT_DIALOG_CLOSE);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        showAlertDialog(ALERT_DIALOG_CLOSE);
    }

    final int ALERT_DIALOG_CLOSE    = 10;
    final int ALERT_DIALOG_DELETE   = 20;

    private void showAlertDialog(int type){
        final boolean isDialogClose = type == ALERT_DIALOG_CLOSE;
        String dialogTitle = null, dialogMessage = null;

        if (isDialogClose){
            dialogTitle     = "Batal";
            dialogMessage   = "Apakah anda yakin ingin membatalkan perubahan?";
        } else {
            dialogTitle     = "Hapus Note";
            dialogMessage   = "Apakah anda yakin ingin menghapus note ini?";
        }

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(dialogTitle);
        alertDialogBuilder
                .setMessage(dialogMessage)
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        if (isDialogClose){
                            finish();
                        } else {
                            noteHelper.delete(note.getId());
                            Intent intent = new Intent();
                            intent.putExtra(EXTRA_POSITION, position);
                            setResult(RESULT_DELETE, intent);
                            finish();
                        }
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private String getCurrentDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        return dateFormat.format(date);
    }

    private void setNotifText() {
        tvReminderTime.setText(notifPreference.getKeyTime());
        tvReminderDate.setText(notifPreference.getKeyDate());
        edtReminderMessage.setText(notifPreference.getKeyMessage());
    }
}
