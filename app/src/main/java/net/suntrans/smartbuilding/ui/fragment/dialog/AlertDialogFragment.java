package net.suntrans.smartbuilding.ui.fragment.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.chad.library.adapter.base.BaseQuickAdapter;

import net.suntrans.smartbuilding.R;

/**
 * Created by Looney on 2017/8/25.
 */

public class AlertDialogFragment extends DialogFragment implements DialogInterface.OnClickListener{

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(getArguments().getString("msg"))
                .setPositiveButton(getString(R.string.ok),AlertDialogFragment.this)
                .setNegativeButton(getString(R.string.cancel),null);
        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (onOkClickListener!=null){
            onOkClickListener.onOkClick(dialog);
        }
    }

    private onOkClickListener onOkClickListener;

    public AlertDialogFragment.onOkClickListener getOnOkClickListener() {
        return onOkClickListener;
    }

    public void setOnOkClickListener(AlertDialogFragment.onOkClickListener onOkClickListener) {
        this.onOkClickListener = onOkClickListener;
    }

    public interface onOkClickListener{
        void onOkClick(DialogInterface dialog);
    }
}
