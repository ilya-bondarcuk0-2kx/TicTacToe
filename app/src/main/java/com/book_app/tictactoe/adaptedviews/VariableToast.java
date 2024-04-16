package com.book_app.tictactoe.adaptedviews;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.book_app.tictactoe.R;

public class VariableToast  {

    /*Делает уже предопределенный дизайн*/
    public static Toast makeText(Context context,String text, int length){
        View layout = View.inflate(context,/*вот этот дизайн*/ R.layout.custom_toast, null);
        TextView toastText = layout.findViewById(R.id.toastText);
        Toast toast = new Toast(context);
        toastText.setText(text);
        toast.setDuration(length);
        toast.setView(layout);
        return toast;
    }

    /*Делает любой дизайн*/
    public static Toast makeText(Context context, int layoutId , String text, int length){
        View layout = View.inflate(context, layoutId, null);
        TextView toastText = layout.findViewById(R.id.toastText);
        Toast toast = new Toast(context);
        toastText.setText(text);
        toast.setDuration(length);
        toast.setView(layout);
        return toast;
    }
}
