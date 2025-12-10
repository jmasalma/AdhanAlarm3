package islam.adhanalarm;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CrashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crash);

        TextView crashTextView = findViewById(R.id.crash_text);
        String stackTrace = getIntent().getStringExtra("stacktrace");
        crashTextView.setText(stackTrace);
    }
}
