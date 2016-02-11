package com.example.louisccc.tripal.model;

import android.content.ContentValues;

/**
 * Created by louisccc on 2/11/16.
 */
public class TriParticipation {

    public static final String DATABASE_TABLE_NAME = "participations";

    public static final String KEY_LOCALID = "part_local_id";
    public static final String KEY_CLOUDID = "part_cloud_id";
    public static final String KEY_TRIPID  = "trip_id";
    public static final String KEY_USERID  = "usr_id";
    public static final String KEY_TIMESTAMP = "last_modified_timestamp";
    public static final String KEY_NEEDSYNC = "need_sync";


    public static final String TRIPS_PARTICIPATION_CREATE =
            "create table " + DATABASE_TABLE_NAME
                    + " ("
                    + KEY_LOCALID       + " integer not null primary key autoincrement, "
                    + KEY_CLOUDID       + " integer default 0, "
                    + KEY_TRIPID        + " integer, "
                    + KEY_USERID        + " integer, "
                    + KEY_TIMESTAMP     + " timestamp default current_timestamp, "
                    + KEY_NEEDSYNC      + " integer default 1 "
                    + ");";

    /* members */
    private int mLocal_id;
    private int mCloud_id;
    private int mTrip_id;
    private int mUser_id;
    private double mDefault_rate; // TODO
    private Long mTimestamp;
    private boolean mNeedSync;

    public TriParticipation() { /* clean constructor */
        mLocal_id = 0;
        mCloud_id = 0;
        mTrip_id = 0;
        mUser_id = 0;
        mTimestamp = System.currentTimeMillis();
        mNeedSync = true;
    }

    public TriParticipation( TriTrip trip, TriFriend user ) {
        mLocal_id = 0;
        mCloud_id = 0;
        mTrip_id = trip.getLocalId(); mUser_id = user.getLocalId();
        mTimestamp = System.currentTimeMillis();
        mNeedSync = true;
    }

    public ContentValues getContentValues() {
        ContentValues contentValues = new ContentValues();
        // local id is assigned.
        contentValues.put( KEY_CLOUDID      , mCloud_id );
        contentValues.put( KEY_TRIPID       , mTrip_id );
        contentValues.put( KEY_USERID       , mUser_id );
        contentValues.put( KEY_TIMESTAMP    , mTimestamp );
        contentValues.put( KEY_NEEDSYNC     , mNeedSync );

        return contentValues;
    }

    public int getLocalId() {
        return mLocal_id;
    }

    public void setlocalId(long localId) {
        this.mLocal_id = (int)localId;
    }
}