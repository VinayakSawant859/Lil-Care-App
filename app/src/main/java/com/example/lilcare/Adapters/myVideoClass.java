package com.example.lilcare.Adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lilcare.Models.fileModel;
import com.example.lilcare.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.ui.StyledPlayerView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;

import java.util.Objects;


public class myVideoClass extends FirebaseRecyclerAdapter<fileModel,myVideoClass.myViewHolder> {


    public myVideoClass(@NonNull FirebaseRecyclerOptions<fileModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") final int position, @NonNull fileModel model) {
        holder.vidName.setText(model.getTitle());
        holder.Date.setText(model.getDate());

        //Preparing Player on Bind
        ExoPlayer player = new ExoPlayer.Builder(holder.playerView.getContext()).build();
        holder.playerView.setPlayer(player);
        MediaItem mediaItem = MediaItem.fromUri(model.getvUrl());
        player.setMediaItem(mediaItem);
        player.prepare();
        player.setPlayWhenReady(false);

        holder.deleteVID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.playerView.getContext());
                builder.setTitle("Delete Video!");
                builder.setMessage("Are you Sure you want to delete!")
                        .setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                FirebaseDatabase.getInstance().getReference().child("myVideos")
                                        .child(Objects.requireNonNull(getRef(position).getKey())).removeValue()
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.playerView.getContext(), "Video Deleted Successfully", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.playerView.getContext(), "Unable to Delete Video!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.show();
            }
        });


        holder.downloadVID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 String videoURL = model.getvUrl();

                 StorageReference storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(videoURL);
                 storageReference.getMetadata()
                         .addOnSuccessListener(new OnSuccessListener<StorageMetadata>() {
                             @Override
                             public void onSuccess(StorageMetadata storageMetadata) {
                                 String fileName = storageMetadata.getName();
                                 String fileType = storageMetadata.getContentType();
                                 String fileDirectory = Environment.DIRECTORY_DOWNLOADS;

                                 DownloadManager downloadManager = (DownloadManager) holder.playerView.getContext().getSystemService(Context.DOWNLOAD_SERVICE);

                                 Uri uri = Uri.parse(videoURL);

                                 DownloadManager.Request request = new DownloadManager.Request(uri);
                                 request.setDestinationInExternalPublicDir(""+fileDirectory, ""+fileName+".mp4");
                                 request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

                                 downloadManager.enqueue(request);
                             }
                         }).addOnFailureListener(new OnFailureListener() {
                     @Override
                     public void onFailure(@NonNull Exception e) {
                         Toast.makeText(holder.playerView.getContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                     }
                 });
            }
        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.videorow,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        StyledPlayerView playerView;
        TextView vidName, Date;
        ImageView downloadVID,deleteVID;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            playerView = itemView.findViewById(R.id.player);
            vidName = itemView.findViewById(R.id.vidName);
            Date = itemView.findViewById(R.id.vidDate);
            downloadVID = itemView.findViewById(R.id.downloadVID);
            deleteVID = itemView.findViewById(R.id.deleteVID);
        }
    }
}