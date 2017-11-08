package com.perry.test.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.perry.http.parser.ConfigXmlParser;
import com.perry.http.parser.HttpUrlEntry;
import com.perry.test.R;

/**
 * Created by lipengjun on 2016/11/30.
 */

public class RequestAdapter extends RecyclerView.Adapter<RequestViewHolder> {

    private static final String TAG = "RequestAdapter";

    @Override
    public RequestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e(TAG, "onCreateViewHolder, i: " + viewType);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_request, null);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new RequestViewHolder(view, onRecyclerViewListener);
    }

    @Override
    public void onBindViewHolder(RequestViewHolder holder, int position) {
        Log.e(TAG, "onBindViewHolder, position: " + position + ", holder: " + holder);
//        RequestViewHolder holder = (RequestViewHolder) holder;
        holder.position = position;
        HttpUrlEntry httpUrlEntry = ConfigXmlParser.getPluginEntries().get(position);
        holder.setEntry(httpUrlEntry);
        holder.nameTextView.setText(httpUrlEntry.urlTitle);
//        holder.valueTextView.setText(httpUrlEntry.urlName);
        holder.valueTextView.setText(httpUrlEntry.urlAddress);
    }

    @Override
    public int getItemCount() {
        return ConfigXmlParser.getPluginEntries().size();
    }

    public void setOnRecyclerViewListener(OnRecyclerViewListener onRecyclerViewListener) {
        this.onRecyclerViewListener = onRecyclerViewListener;
    }

    private OnRecyclerViewListener onRecyclerViewListener;

    public interface OnRecyclerViewListener {
        void onItemClick(int position);

        void onItemClick(int position,HttpUrlEntry httpUrlEntry);

        boolean onItemLongClick(int position);
    }
}

class RequestViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    private static final String TAG = "RequestViewHolder";
    public View rootView;
    public TextView nameTextView;
    public TextView valueTextView;
    public int position;
    HttpUrlEntry entry;
    RequestAdapter.OnRecyclerViewListener onRecyclerViewListener;

    public RequestViewHolder(View itemView, RequestAdapter.OnRecyclerViewListener onRecyclerViewListener) {
        super(itemView);
        nameTextView = (TextView) itemView.findViewById(R.id.item_tv_request_name);
        valueTextView = (TextView) itemView.findViewById(R.id.item_tv_request_value);
        rootView = itemView.findViewById(R.id.item_request_linear);
        this.onRecyclerViewListener = onRecyclerViewListener;
        rootView.setOnClickListener(this);
        rootView.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Log.e(TAG, "position:" + position);
        if (null != onRecyclerViewListener) {
            onRecyclerViewListener.onItemClick(position);
            onRecyclerViewListener.onItemClick(position,entry);
        }

    }

    @Override
    public boolean onLongClick(View view) {
        if (null != onRecyclerViewListener) {
            onRecyclerViewListener.onItemLongClick(position);
        }
        return false;
    }

    public void setEntry(HttpUrlEntry entry) {
        this.entry = entry;
    }
}
