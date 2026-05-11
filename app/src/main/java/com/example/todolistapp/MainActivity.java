package com.example.todolistapp;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Calendar;



public class MainActivity extends AppCompatActivity {
    Button add;
    AlertDialog dialog;
    LinearLayout layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = findViewById(R.id.add);
        layout = findViewById(R.id.container);

        TextView greetingText = findViewById(R.id.greetingText);

        // Get the current hour
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        String userName = "John"; // Replace this with actual user's name

        // Determine greeting based on time
        String greeting;
        if (hour >= 5 && hour < 12) {
            greeting = "Good Morning, " + userName + "! ☀️";
        } else if (hour >= 12 && hour < 18) {
            greeting = "Good Afternoon, " + userName + "! 🌤️";
        } else {
            greeting = "Good Evening, " + userName + "! 🌙";
        }

        // Set the greeting text
        greetingText.setText(greeting);

        buildDialog();
        add.setOnClickListener(v -> dialog.show());
    }
    public void buildDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        View view=getLayoutInflater().inflate(R.layout.dialog,null);


        final EditText name =view.findViewById(R.id.nameedit);

        builder.setView(view);
        builder.setTitle("Enter Your Task")
                 .setPositiveButton("Save",new DialogInterface.OnClickListener(){
                     @Override
                     public void onClick ( DialogInterface dialog, int which ){
                              addCard(name.getText().toString());
            }
        })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        dialog=builder.create();
    }
    private void addCard(String name) {
        if (name.trim().isEmpty()) {
            return; // Avoid adding empty tasks
        }

        final View view = getLayoutInflater().inflate(R.layout.card, null);

        TextView nameView = view.findViewById(R.id.name);
        Button delete = view.findViewById(R.id.delete);

        nameView.setText(name);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.removeView(view);
            }
        });

                layout.addView(view);
                layout.requestLayout();
        System.out.println("Total tasks: " + layout.getChildCount());// Ensure UI updates correctly
            }


}
