package net.internalerror.mytasks;

import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.internalerror.mytasks.data.Task;
import net.internalerror.mytasks.databinding.FragmentTaskBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MyTaskRecyclerViewAdapter extends RecyclerView.Adapter<MyTaskRecyclerViewAdapter.TaskFragmentViewHolder> {

    private final List<Task> mValues;

    public MyTaskRecyclerViewAdapter(List<Task> items) {
        mValues = items;
    }

    @NonNull
    @Override
    public TaskFragmentViewHolder onCreateViewHolder(ViewGroup pParent, int pViewType) {
        return new TaskFragmentViewHolder(FragmentTaskBinding.inflate(LayoutInflater.from(pParent.getContext()), pParent, false));
    }

    @Override
    public void onBindViewHolder(final TaskFragmentViewHolder pHolder, int pPosition) {
        pHolder.populate(mValues.get(pPosition));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class TaskFragmentViewHolder extends RecyclerView.ViewHolder {

        private final ImageButton imageButtonComplete;
        private final TextView textViewName;
        private final TextView textViewDeadLine;
        private final ImageView imageViewStatus;
        private final ImageButton imageButtonEdit;

        public Task task;

        public TaskFragmentViewHolder(FragmentTaskBinding pBinding) {
            super(pBinding.getRoot());
            imageButtonComplete = pBinding.imageButtonComplete;
            textViewName = pBinding.textViewName;
            textViewDeadLine = pBinding.textViewDeadLine;
            imageViewStatus = pBinding.imageViewStatus;
            imageButtonEdit = pBinding.imageButtonEdit;
        }

        @NonNull
        @Override
        public String toString() {
            return "TaskFragmentViewHolder{" + "ib_complete=" + imageButtonComplete +
                    ", tv_name=" + textViewName +
                    ", tv_deadline=" + textViewDeadLine +
                    ", iv_status=" + imageViewStatus +
                    ", ib_edit=" + imageButtonEdit +
                    ", task=" + task +
                    '}';
        }

        public void populate(Task pTask) {
            task = pTask;

            textViewName.setText(task.getName());
            DateFormat dateFormat = SimpleDateFormat.getDateInstance();
            textViewDeadLine.setText(dateFormat.format(task.getDeadline()));

            if (DateUtils.isToday(task.getDeadline().getTime())) {
                imageViewStatus.setImageResource(R.drawable.baseline_warning_amber_48);
            } else if (task.getDeadline().before(new Date())) {
                imageViewStatus.setImageResource(R.drawable.baseline_error_outline_48);
            } else {
                imageViewStatus.setVisibility(View.INVISIBLE);
            }

        }
    }
}