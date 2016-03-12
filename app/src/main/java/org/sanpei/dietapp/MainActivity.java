package org.sanpei.dietapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    double mWeight = 62;
    String mPassword;
    String mUsername;
    TextView weightText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        weightText = (TextView)findViewById(R.id.Weight);
        weightText.setText(String.valueOf(mWeight));
        Setting setting = new Setting();
        mPassword = setting.loadPassword(this);
        mUsername = setting.loadUsername(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            // カスタムビューを設定
            LayoutInflater inflater = (LayoutInflater) this.getSystemService(
                    LAYOUT_INFLATER_SERVICE);
            final View layout = inflater.inflate(R.layout.setting_dialog,
                    (ViewGroup) findViewById(R.id.setting_dialog));

            // 省略
            // 実際にはここで現在の設定を読み込んで反映したりします。

            // アラートダイアログ を生成
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Settings");
            TextView usernameText;
            usernameText = (TextView)findViewById(R.id.username);
            usernameText.setText(mUsername);
            builder.setView(layout);

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // OK ボタンクリック処理
                    // 省略
                    // 実際は入力された値を保存する処理とメイン画面に反映する処理を記述しています
                    Setting setting = new Setting();
                    setting.savePassword(MainActivity.this, mPassword);
                    setting.saveUsername(MainActivity.this, mUsername);
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // Cancel ボタンクリック処理
                    // 処理はありません
                }
            });

            // 表示
            builder.create().show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void clickButtonDecrease05(View view) {
        mWeight = mWeight - 0.5;
        weightText.setText(String.valueOf(mWeight));
    }
    public void clickButtonIncrease05(View view) {
        mWeight = mWeight + 0.5;
        weightText.setText(String.valueOf(mWeight));
    }
    public void clickButtonDecrease01(View view) {
        mWeight = mWeight - 0.1;
        weightText.setText(String.valueOf(mWeight));
    }
    public void clickButtonIncrease01(View view) {
        mWeight = mWeight + 0.1;
        weightText.setText(String.valueOf(mWeight));
    }
    public class Setting {
        private static final String KEY_PASSWORD = "key_account_password";
        private static final String KEY_USERNAME = "key_account_name";

        public String loadPassword(Context context) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            return preferences.getString(KEY_PASSWORD, null);
        }

        public void savePassword(Context context, String password) {
            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
            editor.putString(KEY_PASSWORD, password);
            editor.commit();
        }
        public String loadUsername(Context context) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            return preferences.getString(KEY_USERNAME, null);
        }

        public void saveUsername(Context context, String password) {
            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
            editor.putString(KEY_USERNAME, password);
            editor.commit();
        }

    }
}
