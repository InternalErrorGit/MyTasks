package net.internalerror.mytasks;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import net.internalerror.mytasks.data.Task;

import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton floatingActionButtonCreateTask;
    private RecyclerView recyclerViewTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButtonCreateTask = findViewById(R.id.floatingActionButtonCreateTask);
        recyclerViewTasks = findViewById(R.id.recyclerViewTasks);




        recyclerViewTasks.setAdapter(new MyTaskRecyclerViewAdapter(List.of(new Task("Test", new Date()))));
    }




}