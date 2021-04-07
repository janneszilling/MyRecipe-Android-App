package com.example.myrecipe;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class MyCursorAdapter extends CursorAdapter {
    public MyCursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.row_layout, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvpindex = view.findViewById(R.id.tvpindex);
        TextView tvname = view.findViewById(R.id.tvname);
        TextView tvworktime = view.findViewById(R.id.tvworktime);
        TextView tvcookingtime = view.findViewById(R.id.tvcookingtime);
        TextView tvdifficulty = view.findViewById(R.id.tvdifficulty);

        // Inhalt des Cursors holen, also die einzelnen Zellen PRO Zeilen
        int id = cursor.getInt(cursor.getColumnIndexOrThrow(DBSQueries.ID));
        String name = cursor.getString(cursor.getColumnIndexOrThrow(DBSQueries.NAME));
        String worktime = cursor.getString(cursor.getColumnIndexOrThrow(DBSQueries.WORKTIME));
        String cookingtime = cursor.getString(cursor.getColumnIndexOrThrow(DBSQueries.COOKINGTIME));
        String difficulty = cursor.getString(cursor.getColumnIndexOrThrow(DBSQueries.DIFFICULTY));

        tvpindex.setText(Integer.toString(id));
        tvname.setText((name));
        tvworktime.setText((worktime));
        tvcookingtime.setText((cookingtime));
        tvdifficulty.setText((difficulty));
    }
}

