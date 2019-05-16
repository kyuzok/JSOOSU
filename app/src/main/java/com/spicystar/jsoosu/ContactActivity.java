package com.spicystar.jsoosu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Bundle;

public class ContactActivity extends AppCompatActivity {

    private EditText message;
    private Button sendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        sendBtn = findViewById(R.id.btnSendEmail);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendEmail();
            }
        });
    }

    private void sendEmail() {
        message = findViewById(R.id.etEmailBody);
        String body = message.getText().toString().trim();

        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"tekitoh.otoko@gmail.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "Message From JSO App");
        i.putExtra(Intent.EXTRA_TEXT   , body);
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(ContactActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }

    }
}
