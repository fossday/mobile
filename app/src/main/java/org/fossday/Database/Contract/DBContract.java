package org.fossday.Database.Contract;

import android.provider.BaseColumns;

public class DBContract {

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private DBContract() {}

    /* Inner class that defines the table contents */
    public static class Categories implements BaseColumns {
        public static final String TABLE_NAME = "Categories";
        public static final String COLUMN_EID = "eid"; // External ID from WordPress
        public static final String COLUMN_NAME = "name";
    }

    /* Inner class that defines the table contents */
    public static class Speakers implements BaseColumns {
        public static final String TABLE_NAME = "Speakers";
        public static final String COLUMN_EID = "eid"; // External ID from WordPress
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_POSITION = "position";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_FACEBOOK = "facebook";
        public static final String COLUMN_INSTAGRAM = "instagram";
        public static final String COLUMN_TWITTER = "twitter";
        public static final String COLUMN_LINKEDIN = "linkedin";
        public static final String COLUMN_YOUTUBE = "youtube";
        public static final String COLUMN_GITHUB = "github";
    }

    /* Inner class that defines the table contents */
    public static class Sponsors implements BaseColumns {
        public static final String TABLE_NAME = "Sponsors";
        public static final String COLUMN_EID = "eid"; // External ID from WordPress
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_WEBSITE = "website";
        public static final String COLUMN_TYPE = "type";
    }

    /* Inner class that defines the table contents */
    public static class Lectures implements BaseColumns {
        public static final String TABLE_NAME = "Lectures";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_PERIOD = "period";
        public static final String COLUMN_TIME = "time";
        public static final String COLUMN_ROOM = "room";
        public static final String COLUMN_SPEAKER_ID = "speakerId";
    }
}

