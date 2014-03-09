package nu.mackli.sitc.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;

import nu.mackli.sitc.R;
import nu.mackli.sitc.fragments.base.ContractDialogFragment;

/**
 * Created by macklinu on 3/9/14.
 */
@EFragment
public class RoleAssignDialog extends ContractDialogFragment<RoleAssignDialog.RoleAssignListener> {
    public static final String FRAGMENT_TAG = "roleAssignDialog";

    public interface RoleAssignListener {
        public void onRoleAccessPositiveClick(RoleAssignDialog dialog);

        public void onRoleAccessNegativeClick(RoleAssignDialog dialog);
    }

    @FragmentArg String email;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_role_assign, null);
        ((TextView) view.findViewById(R.id.email)).setText(email);
        builder.setView(view);
        builder.setTitle("Access denied");
        builder.setPositiveButton("Notify SITC", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getContract().onRoleAccessPositiveClick(RoleAssignDialog.this);
            }
        });
        builder.setNegativeButton("Continue as volunteer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getContract().onRoleAccessNegativeClick(RoleAssignDialog.this);
            }
        });
        return builder.create();
    }
}
