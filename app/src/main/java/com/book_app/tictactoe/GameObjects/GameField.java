package com.book_app.tictactoe.GameObjects;

import android.app.Activity;
import android.net.Network;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.book_app.tictactoe.activities.GameActivity;
import com.book_app.tictactoe.activities.MultiPlayerGameActivity;
import com.book_app.tictactoe.R;
import com.book_app.tictactoe.client.Client;
import com.book_app.tictactoe.client.Packet;
import java.util.concurrent.atomic.AtomicBoolean;


public class GameField {

    private final Cell[] cells;

    private Side side;

    private final TextView state;

    private int turn;

    private final AtomicBoolean isGameOver;

    public GameField(GameActivity activity) {

        turn = 1;
        isGameOver = new AtomicBoolean(false);
        cells = new Cell[9];
        side = new Cross();
        state = activity.findViewById(R.id.state);

        cells[0] = new Cell(activity.findViewById(R.id.img1), new Intermediate());
        cells[1] = new Cell(activity.findViewById(R.id.img2), new Intermediate());
        cells[2] = new Cell(activity.findViewById(R.id.img3), new Intermediate());
        cells[3] = new Cell(activity.findViewById(R.id.img4), new Intermediate());
        cells[4] = new Cell(activity.findViewById(R.id.img5), new Intermediate());
        cells[5] = new Cell(activity.findViewById(R.id.img6), new Intermediate());
        cells[6] = new Cell(activity.findViewById(R.id.img7), new Intermediate());
        cells[7] = new Cell(activity.findViewById(R.id.img8), new Intermediate());
        cells[8] = new Cell(activity.findViewById(R.id.img9), new Intermediate());

        for (int i = 0; i < 9; ++i) {


            Cell cell = cells[i];

            cell.getImageView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (cell.getSide().getClass() != Intermediate.class || isGameOver.get())
                        return;


                    cell.setSide(side);
                    cell.setImage(side.getImage());


                    ++turn;
                    switch (checkField()) {

                        case CROSS_WIN:
                            state.setText(R.string.cross_win);
                            return;

                        case ZERO_WIN:
                            state.setText(R.string.zero_win);
                            return;
                        case DRAW:
                            state.setText(R.string.draw);
                            return;
                    }
                    changeSide();


                }
            });

        }
    }

    public GameField(MultiPlayerGameActivity activity, Side side, Client client){


        isGameOver = new AtomicBoolean(false);
        cells = new Cell[9];
        this.side = side;
        state = activity.findViewById(R.id.state);


        new Thread(new Runnable() {
            @Override
            public void run() {



                /*"192.168.1.177"*/
                /*192.168.1.188*/
                if(!client.connectToServer("192.168.1.177", 3000)) {

                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            isGameOver.set(true);
                            Toast.makeText(activity,"Сервер оффлайн", Toast.LENGTH_LONG).show();
                            activity.finish();
                        }
                    });
                    return;
                }

                if (client.getMsg().equals("OK")) {
                    serverResponseThread(activity, client);
                } else {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            isGameOver.set(true);
                            Toast.makeText(activity,"Противник уже выбрал эту сторону", Toast.LENGTH_LONG).show();
                            activity.finish();
                        }
                    });
                }
            }
        }).start();




        cells[0] = new Cell(activity.findViewById(R.id.img_m_1), new Intermediate());
        cells[1] = new Cell(activity.findViewById(R.id.img_m2), new Intermediate());
        cells[2] = new Cell(activity.findViewById(R.id.img_m3), new Intermediate());
        cells[3] = new Cell(activity.findViewById(R.id.img_m4), new Intermediate());
        cells[4] = new Cell(activity.findViewById(R.id.img_m5), new Intermediate());
        cells[5] = new Cell(activity.findViewById(R.id.img_m6), new Intermediate());
        cells[6] = new Cell(activity.findViewById(R.id.img_m7), new Intermediate());
        cells[7] = new Cell(activity.findViewById(R.id.img_m8), new Intermediate());
        cells[8] = new Cell(activity.findViewById(R.id.img_m9), new Intermediate());

        for (int i = 0; i < 9; ++i) {

            Cell cell = cells[i];

            int index = i;

            cell.getImageView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (cell.getSide().getClass() != Intermediate.class || isGameOver.get())
                        return;


                    Thread th = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            client.sendPacket(new Packet(side.toString(), index, turn));
                        }
                    });
                    th.start();



                }

            });
        }
    }


    public void stopGame(){
        isGameOver.set(false);
    }

    private void serverResponseThread(Activity activity, Client client){

        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {

                    if(isGameOver.get())
                        return;


                    switch (client.receivePacket()) {

                        case EXCEPTION:
                        case SOCKET_ERROR:
                            return;
                        case PACKET_NULL:
                            continue;
                        case WAIT:
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(activity, "Ожидайте противника", Toast.LENGTH_LONG).show();
                                }
                            });
                            continue;
                        case OPPONENT_CONNECTED:
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(activity, "Противник присоеденился к комнате", Toast.LENGTH_LONG).show();
                                }
                            });
                            break;
                        case OPPONENT_DISCONNECT:
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(activity, "Противник отключился", Toast.LENGTH_LONG).show();
                                }
                            });
                            return;

                    }

                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (client.getPacket() == null) {

//                        Toast.makeText(activity.getBaseContext(), "Opponent choose", Toast.LENGTH_LONG).show();
                                return;
                            }
                            if (client.getPacket().getSide().equals("Cross")) {

                                cells[client.getPacket().getIndex()].setSide(new Cross());
                                cells[client.getPacket().getIndex()].setImage(new Cross().getImage());
                                state.setText(R.string.zero_step);
                            } else {
                                cells[client.getPacket().getIndex()].setSide(new Zero());
                                cells[client.getPacket().getIndex()].setImage(new Zero().getImage());
                                state.setText(R.string.cross_step);
                            }

                            turn = client.getPacket().getTurn();

                            switch (checkField(client)) {

                                case CROSS_WIN:
                                    state.setText(R.string.cross_win);
                                    return;
                                case ZERO_WIN:
                                    state.setText(R.string.zero_win);
                                    return;
                                case DRAW:
                                    state.setText(R.string.draw);
                                    return;
                            }
                        }
                    });
                }
            }
        });
        th.start();

    }


    private CheckResult checkField(){

        for (int i = 0; i < 8; ++i) {
            int count = 0;
            for (int j = 0; j < 3; ++j) {
                if(cells[Constants.wins[i][j]].getSide().getClass() == side.getClass())
                    ++count;
            }

            if(count == 3){
                isGameOver.set(true);
                if(side.getClass() == Cross.class)
                    return CheckResult.CROSS_WIN;
                else
                    return CheckResult.ZERO_WIN;
            }
        }

        if(turn == 10){
            isGameOver.set(true);
            return CheckResult.DRAW;
        }

        return CheckResult.GAME_CONTINUE;
    }


    private CheckResult checkField(Client client){
        for (int i = 0; i < 8; ++i) {
            int count = 0;
            for (int j = 0; j < 3; ++j) {
                if(cells[Constants.wins[i][j]].getSide().toString().equals(client.getPacket().getSide()))
                    ++count;
            }

            if(count == 3){
                isGameOver.set(true);
                if(client.getPacket().getSide().equals("Cross"))
                    return CheckResult.CROSS_WIN;
                else
                    return CheckResult.ZERO_WIN;
            }
        }

        if(turn == 10){

            isGameOver.set(true);
            return CheckResult.DRAW;
        }

        return CheckResult.GAME_CONTINUE;
    }


    private void changeSide(){

        if(side.getClass() == Cross.class)
        {
            side = new Zero();
            state.setText(R.string.zero_step);
        }
        else {
            side = new Cross();
            state.setText(R.string.cross_step);
        }

    }



}