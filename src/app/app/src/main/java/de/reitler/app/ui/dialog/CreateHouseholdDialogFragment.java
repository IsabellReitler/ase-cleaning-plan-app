package de.reitler.app.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import de.reitler.app.ui.add_household.AddHouseholdActivity;
import de.reitler.app.R;

public class CreateHouseholdDialogFragment extends DialogFragment implements TextView.OnEditorActionListener {

    public interface CreateHouseholdDialogListener {
        public void onDialogPositiveClick(String input);
    }

    CreateHouseholdDialogListener listener;
    EditText input;

    @Override

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        input.setOnEditorActionListener(this);

    }



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_create_household, null);
        input = view.findViewById(R.id.create_household_name);
        builder.setView(view)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        listener.onDialogPositiveClick(input.getText().toString());
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        return builder.create();
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (EditorInfo.IME_ACTION_DONE == actionId) {
            CreateHouseholdDialogListener listener = (CreateHouseholdDialogListener) getActivity();
            listener.onDialogPositiveClick(input.getText().toString());
            dismiss();
            return true;
        }
        return false;
    }

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (CreateHouseholdDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(AddHouseholdActivity.class.toString()
                    + " must implement NoticeDialogListener");
        }
    }

}
