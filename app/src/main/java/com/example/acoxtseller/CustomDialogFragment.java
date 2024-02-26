package com.example.acoxtseller;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;



    public class CustomDialogFragment extends DialogFragment {

        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            // Create and configure your dialog here
            Dialog dialog = new Dialog(requireContext());
            dialog.setContentView(R.layout.dialog_location_select);

            // Configure dialog properties as needed

            return dialog;
        }
    }

