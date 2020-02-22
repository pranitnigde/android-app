package com.example.lonavalacityguide.daily_planner;


import android.arch.persistence.room.Room;
import android.content.Context;

public class DatabaseClient {

    private Context context;
    private static DatabaseClient databaseClient;

    private AppDatabase appDatabase;

    private DatabaseClient(Context context){
        this.context=context;
        appDatabase= Room.databaseBuilder(context,AppDatabase.class,"MyTodos").build();
    }

    public static synchronized DatabaseClient getInstance(Context ctx){
        if(databaseClient==null){
            databaseClient=new DatabaseClient(ctx);
        }
        return databaseClient;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
