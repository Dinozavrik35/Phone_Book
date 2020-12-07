package com.example.phonebook;

import androidx.appcompat.app.AppCompatActivity;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    EditText name;
    EditText surname;
    EditText name3;
    EditText number;
    EditText mail;
    String Name;
    String Surname;
    String Name3;
    String Number;
    String Mail;
    TextView text1;
    EditText num1;
    String Num1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        something();
    }

    public void something() {

        setContentView(R.layout.activity_main);
        text1 = findViewById(R.id.text);

        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("phone_book.db", MODE_PRIVATE, null);

        db.execSQL("CREATE TABLE IF NOT EXISTS ppl(im TEXT, fam TEXT, ot TEXT, num TEXT, ml TEXT)");

        Cursor query = db.rawQuery("SELECT * FROM ppl;", null);

        if (query.moveToFirst()) {
            do {
                String name1 = query.getString(0);
                String surname1 = query.getString(1);
                String name31 = query.getString(2);
                String number1 = query.getString(3);
                String mail1 = query.getString(4);
                text1.append("\n" + name1 + "\n" + surname1 + "\n" + name31 + "\n" + number1 + "\n" + mail1 + "\n");
                text1.append("------------------------");
            }
            while (query.moveToNext());
        }

        query.close();
        db.close();

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.second_screen);

            }
        });

    }

    public void back(View view) {
        something();

    }
    public void drop_table(View view) {
        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("phone_book.db", MODE_PRIVATE, null);
        db.execSQL("DROP TABLE ppl");
        db.execSQL("CREATE TABLE IF NOT EXISTS ppl(im TEXT, fam TEXT, ot TEXT, num TEXT, ml TEXT)");
        db.execSQL("INSERT INTO ppl VALUES ('Имя', 'Фамилия', 'Отчество', 'Номер телефона', 'Электронная почта');");
        db.close();
        something();
    }
    public void delete1 (View view) {
        setContentView(R.layout.third_screen);
    }
    public void delete(View view) {
        num1 = findViewById(R.id.num1);
        Num1 = num1.getText().toString();
        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("phone_book.db", MODE_PRIVATE, null);
        db.execSQL("DELETE FROM ppl WHERE num = '" + Num1 + "';");
        db.close();
        something();
    }

    public void save(View view) {

            SQLiteDatabase db = getBaseContext().openOrCreateDatabase("phone_book.db", MODE_PRIVATE, null);

            name = findViewById(R.id.name);
            surname = findViewById(R.id.surname);
            name3 = findViewById(R.id.name3);
            number = findViewById(R.id.number);
            mail = findViewById(R.id.mail);

            Name = name.getText().toString();
            Surname = surname.getText().toString();
            Name3 = name3.getText().toString();
            Number = number.getText().toString();
            Mail = mail.getText().toString();

            db.execSQL("INSERT INTO ppl VALUES ('" + Name + "', '" + Surname + "', '" + Name3 + "', '" + Number + "', '" + Mail + "');");
            db.close();

            something();


        }
    }

