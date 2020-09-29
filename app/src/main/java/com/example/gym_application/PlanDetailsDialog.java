package com.example.gym_application;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import static com.example.gym_application.TrainingActivity.TRAINING_KEY;

public class PlanDetailsDialog extends DialogFragment
{

    public interface PassPlanInterface
    {
        void getPlan(Plan plan);
    }

    private PassPlanInterface passPlanInterface;

    private Spinner spinner;
    private EditText editText;
    private Button dismiss, add;
    private TextView name;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view=getActivity().getLayoutInflater().inflate(R.layout.dialog_plan_details, null);
        initViews(view);

        AlertDialog.Builder builder=  new AlertDialog.Builder(getActivity()).setView(view);
        Bundle bundle= getArguments();
        if(bundle!=null)
        {
            final Training training= bundle.getParcelable(TRAINING_KEY);
            if(training!= null)
            {
                name.setText(training.getName());

                dismiss.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();
                    }
                });

                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String day= spinner.getSelectedItem().toString();
                        Integer min= Integer.valueOf(editText.getText().toString());
                        Plan plan= new Plan(training, min, day, false);

                        try{
                            passPlanInterface = (PassPlanInterface) getActivity();
                            passPlanInterface.getPlan(plan);
                            dismiss();
                        }
                        catch (ClassCastException e)
                        {
                            e.printStackTrace();
                            dismiss();
                        }
                    }
                });
            }
        }

        return builder.create();
    }

    public void initViews(View view)
    {
        name= view.findViewById(R.id.trainingname);
        editText= view.findViewById(R.id.editText);
        dismiss= view.findViewById(R.id.dismissBtn);
        add= view.findViewById(R.id.addBtn);
        spinner= view.findViewById(R.id.spinner);
    }
}
