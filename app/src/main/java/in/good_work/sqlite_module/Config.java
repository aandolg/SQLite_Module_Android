package in.good_work.sqlite_module;

import in.good_work.sqlite_module.Model.PersonContract;

/**
 * Created by Alex on 30.10.2017.
 */

public class Config {
    public static String DB_NAME = "test_sqllite";
    public static Integer DB_VERSION = 1;
    public static String TABLE_PERSON = "persons";

    public static final String COMMAND_CREATE = "create table IF NOT EXISTS "
            + TABLE_PERSON + " ("
            + PersonContract._ID + " INTEGER PRIMARY KEY, "
            + PersonContract.KEY_NAME + " TEXT, "
            + PersonContract.KEY_SURNAME + " TEXT, "
            + PersonContract.KEY_PHONE + " TEXT, "
            + PersonContract.KEY_MAIL + " TEXT, "
            + PersonContract.KEY_SKYPE + " TEXT "
            + ");";

    public static final String COMMAND_DELETE = "DROP TABLE IF EXISTS " + TABLE_PERSON;
}
