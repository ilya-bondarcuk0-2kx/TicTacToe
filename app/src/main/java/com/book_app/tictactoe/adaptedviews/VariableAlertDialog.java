package com.book_app.tictactoe.adaptedviews;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class VariableAlertDialog {



    static class VariableAlertException extends Exception{

        public VariableAlertException(String cause){
            super(cause);
        }

        public VariableAlertException(Exception e){
            super(e);
        }

    }


    public static AlertDialog  buildCustomDialog(Context context,
                                         int layoutId,
                                         String title,
                                         String msg,
                                         String positive,
                                         DialogInterface.OnClickListener positiveListener,
                                         String negative,
                                         DialogInterface.OnClickListener negativeListener){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        ConstraintLayout layout = (ConstraintLayout) View.inflate(context, layoutId, null);
        builder.setView(layout);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setPositiveButton(positive, positiveListener);
        builder.setNegativeButton(negative, negativeListener);

        return builder.create();

    }

    public static AlertDialog  buildCustomDialog(Context context,
                                                 int layoutId,
                                                 String title,
                                                 String msg,
                                                 String buttonText,
                                                 DialogInterface.OnClickListener listener,
                                                 AdapterView.OnItemSelectedListener itemClickListener,
                                                 boolean isPositive){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        ConstraintLayout layout = (ConstraintLayout) View.inflate(context, layoutId, null);
        builder.setView(layout);
        builder.setTitle(title);
        builder.setMessage(msg);

        if (isPositive) builder.setPositiveButton(buttonText, listener);
        else            builder.setNegativeButton(buttonText, listener);

        builder.setOnItemSelectedListener(itemClickListener);

        return builder.create();

    }

    public static AlertDialog  buildCustomDialog(Context context,
                                                 int layoutId,
                                                 String msg,
                                                 String positive,
                                                 DialogInterface.OnClickListener positiveListener,
                                                 String negative,
                                                 DialogInterface.OnClickListener negativeListener){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        ConstraintLayout layout = (ConstraintLayout) View.inflate(context, layoutId, null);
        builder.setView(layout);
        builder.setMessage(msg);

        builder.setPositiveButton(positive, positiveListener);
        builder.setNegativeButton(negative, negativeListener);


        return builder.create();

    }

    public static AlertDialog  buildCustomDialog(Context context,
                                                 String msg,
                                                 String positive,
                                                 DialogInterface.OnClickListener positiveListener,
                                                 String negative,
                                                 DialogInterface.OnClickListener negativeListener){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(msg);

        builder.setPositiveButton(positive, positiveListener);
        builder.setNegativeButton(negative, negativeListener);


        return builder.create();

    }

    public static AlertDialog  buildCustomDialog(Context context,
                                                 int layoutId,
                                                 String title,
                                                 String msg){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        ConstraintLayout layout = (ConstraintLayout) View.inflate(context, layoutId, null);
        builder.setView(layout);
        builder.setTitle(title);
        builder.setMessage(msg);


        return builder.create();

    }

    public static AlertDialog  buildCustomDialog(Context context,
                                                 String title,
                                                 String msg){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(msg);
        return builder.create();
    }

    public static AlertDialog  buildCustomDialog(Context context,
                                                 String text, int titleOrMessage) throws VariableAlertException {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        switch (titleOrMessage){
            case 1:
                builder.setTitle(text);
                break;
            case 2:
                builder.setMessage(text);
                break;
            default:
                throw new VariableAlertException("VariableAlertException: param 'titleOrMessage' can't be greater than 2 and less than 1");
        }

        return builder.create();
    }


    public static AlertDialog  buildCustomDialog(Context context,
                                                 int layoutId,
                                                 String title,
                                                 String [] items,
                                                 DialogInterface.OnClickListener itemListener,
                                                 String positive,
                                                 DialogInterface.OnClickListener positiveListener,
                                                 String negative,
                                                 DialogInterface.OnClickListener negativeListener){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        ConstraintLayout layout = (ConstraintLayout) View.inflate(context, layoutId, null);
        builder.setView(layout);
        builder.setTitle(title);
        builder.setItems(items, itemListener);
        builder.setPositiveButton(positive, positiveListener);
        builder.setNegativeButton(negative, negativeListener);

        return builder.create();

    }

    public static AlertDialog  buildCustomDialog(Context context,
                                                 String title,
                                                 String [] items,
                                                 DialogInterface.OnClickListener itemListener,
                                                 String positive,
                                                 DialogInterface.OnClickListener positiveListener,
                                                 String negative,
                                                 DialogInterface.OnClickListener negativeListener){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setItems(items, itemListener);
        builder.setPositiveButton(positive, positiveListener);
        builder.setNegativeButton(negative, negativeListener);

        return builder.create();

    }
    public static AlertDialog  buildCustomDialog(Context context,
                                                 String [] items,
                                                 DialogInterface.OnClickListener itemListener,
                                                 String positive,
                                                 DialogInterface.OnClickListener positiveListener,
                                                 String negative,
                                                 DialogInterface.OnClickListener negativeListener){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setItems(items, itemListener);
        builder.setPositiveButton(positive, positiveListener);
        builder.setNegativeButton(negative, negativeListener);

        return builder.create();

    }

    public static AlertDialog  buildCustomDialog(Context context,
                                                 String title,
                                                 String [] items,
                                                 DialogInterface.OnClickListener itemListener){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setItems(items, itemListener);

        return builder.create();

    }

    public static AlertDialog  buildCustomDialog(Context context,
                                                 String [] items,
                                                 DialogInterface.OnClickListener itemListener){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setItems(items, itemListener);

        return builder.create();

    }

    public static AlertDialog  buildCustomDialog(Context context,
                                                 String [] items,
                                                 DialogInterface.OnClickListener itemListener,
                                                 String buttonText,
                                                 DialogInterface.OnClickListener listener,
                                                 boolean isPositive){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setItems(items, itemListener);

        if (isPositive) builder.setPositiveButton(buttonText, listener);
        else            builder.setNegativeButton(buttonText, listener);

        return builder.create();

    }
    public static AlertDialog  buildCustomDialog(Context context,
                                                 String title,
                                                 String [] items,
                                                 DialogInterface.OnClickListener itemListener,
                                                 String buttonText,
                                                 DialogInterface.OnClickListener listener,
                                                 boolean isPositive){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setItems(items, itemListener);
        if (isPositive) builder.setPositiveButton(buttonText, listener);
        else            builder.setNegativeButton(buttonText, listener);

        return builder.create();

    }

    public static AlertDialog  buildCustomDialog(Context context,
                                                 int layoutId,
                                                 String title,
                                                 String [] items,
                                                 DialogInterface.OnClickListener itemListener,
                                                 String buttonText,
                                                 DialogInterface.OnClickListener listener,
                                                 boolean isPositive){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        ConstraintLayout layout = (ConstraintLayout) View.inflate(context, layoutId, null);
        builder.setView(layout);
        builder.setTitle(title);
        builder.setItems(items, itemListener);
        if (isPositive) builder.setPositiveButton(buttonText, listener);
        else            builder.setNegativeButton(buttonText, listener);

        return builder.create();

    }

}
