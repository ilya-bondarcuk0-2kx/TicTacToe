package com.book_app.tictactoe.client.connection.backgroundservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.book_app.tictactoe.client.connection.ConnectionHandler;

import java.util.Timer;
import java.util.TimerTask;




public class ServerService extends Service {

    private static final String TAG = "ServerService";
    private Timer timer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        ConnectionHandler.ServerConnection.connectToServer("192.168.1.177", 3000);

        timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                if(!ConnectionHandler.isConnected()){
                    /*TODO: Диалоговое окно для пользователя для переподключения к серверу*/
                }

            }
        }, 10000);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ConnectionHandler.ServerConnection.disconnectFromServer();
    }


}
