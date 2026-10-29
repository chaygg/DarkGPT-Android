
package com.darkgpt.chat;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity {

    private LinearLayout messages;
    private ScrollView scroll;
    private EditText input;

    private final int background = Color.rgb(10, 10, 12);
    private final int panel = Color.rgb(25, 25, 29);
    private final int red = Color.rgb(230, 35, 55);
    private final int white = Color.WHITE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(16, 16, 16, 12);
        root.setBackgroundColor(background);

        TextView title = new TextView(this);
        title.setText("DARKGPT");
        title.setTextColor(red);
        title.setTextSize(26);
        title.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        title.setGravity(Gravity.CENTER);
        title.setPadding(8, 12, 8, 16);
        root.addView(title);

        TextView subtitle = new TextView(this);
        subtitle.setText("مساعدك الذكي");
        subtitle.setTextColor(Color.LTGRAY);
        subtitle.setTextSize(14);
        subtitle.setGravity(Gravity.CENTER);
        subtitle.setPadding(0, 0, 0, 12);
        root.addView(subtitle);

        scroll = new ScrollView(this);
        messages = new LinearLayout(this);
        messages.setOrientation(LinearLayout.VERTICAL);
        scroll.addView(messages);

        root.addView(scroll, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 0, 1));

        addMessage("DarkGPT", "مرحبًا! كيف يمكنني مساعدتك؟", false);

        LinearLayout bottom = new LinearLayout(this);
        bottom.setGravity(Gravity.CENTER_VERTICAL);
        bottom.setPadding(0, 12, 0, 0);

        input = new EditText(this);
        input.setSingleLine(false);
        input.setTextColor(white);
        input.setHintTextColor(Color.GRAY);
        input.setHint("اكتب رسالتك...");
        input.setTextSize(16);
        input.setPadding(14, 10, 14, 10);
        input.setBackground(makeBackground(panel, 18));

        bottom.addView(input, new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));

        Button send = new Button(this);
        send.setText("إرسال");
        send.setTextColor(white);
        send.setBackground(makeBackground(red, 16));

        LinearLayout.LayoutParams buttonParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
        buttonParams.setMargins(8, 0, 0, 0);
        bottom.addView(send, buttonParams);

        root.addView(bottom);
        setContentView(root);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = input.getText().toString().trim();

                if (text.isEmpty()) return;

                addMessage("أنت", text, true);
                input.setText("");

                InputMethodManager imm = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(input.getWindowToken(), 0);
                }

                addMessage("DarkGPT",
                        "وصلت رسالتك! هذه نسخة تجريبية، ولم يتم ربطها بخادم ذكاء اصطناعي بعد.",
                        false);
            }
        });
    }

    private void addMessage(String sender, String text, boolean user) {
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(14, 10, 14, 10);
        card.setBackground(makeBackground(user
                ? Color.rgb(65, 22, 30) : panel, 14));

        TextView name = new TextView(this);
        name.setText(sender);
        name.setTextColor(user ? red : Color.rgb(255, 100, 115));
        name.setTextSize(13);
        name.setTypeface(Typeface.DEFAULT, Typeface.BOLD);

        TextView body = new TextView(this);
        body.setText(text);
        body.setTextColor(white);
        body.setTextSize(16);
        body.setPadding(0, 5, 0, 0);

        card.addView(name);
        card.addView(body);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 6, 0, 6);

        messages.addView(card, params);
        scroll.post(new Runnable() {
            @Override
            public void run() {
                scroll.fullScroll(View.FOCUS_DOWN);
            }
        });
    }

    private GradientDrawable makeBackground(int color, int radius) {
        GradientDrawable shape = new GradientDrawable();
        shape.setColor(color);
        shape.setCornerRadius(radius);
        return shape;
    }
                                             }
                                
