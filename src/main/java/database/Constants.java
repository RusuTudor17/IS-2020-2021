package database;

import java.util.*;

import static database.Constants.Rights.*;

import static database.Constants.Roles.*;


public class Constants {

    public static class Tables {

        public static final String USER = "user";
        public static final String ROLE = "role";
        public static final String RIGHT = "right";
        public static final String ROLE_RIGHT = "role_right";
        public static final String USER_ROLE = "user_role";
        public static final String CLIENT = "client";
        public static final String CONT = "cont";
        public static final String REPORT = "report";

        public static final String[] ORDERED_TABLES_FOR_CREATION = new String[]{USER, ROLE, RIGHT, ROLE_RIGHT, USER_ROLE, CLIENT,CONT,REPORT};
    }




    public static class Database{

        public static final String TESTARE = "test_bank_db";
        public static final String PRODUCTIE = "bank_db";

        public static final String[] SCHEMAS = new String[]{TESTARE, PRODUCTIE};


    }

    public static class Roles {
        public static final String ADMINISTRATOR = "administrator";
        public static final String REGULAR_USER = "regular_user";


        public static final String[] ROLES = new String[]{ADMINISTRATOR, REGULAR_USER};
    }

    public static class Rights {
        public static final String CREATE_CLIENT = "create_client";
        public static final String DELETE_CLIENT = "delete_client";
        public static final String UPDATE_CLIENT = "update_client";
        public static final String VIEW_CLIENT = "view_client";

        public static final String CREATE_CONT = "create_cont";
        public static final String DELETE_CONT = "delete_cont";
        public static final String UPDATE_CONT = "update_cont";
        public static final String VIEW_CONT = "view_cont";

        public static final String TRANSFER = "transfer";

        public static final String PROCESS_BILLS = "process_bills";

        //------------------------------------------------------------------------------\\

        public static final String CREATE_USER = "create_user";
        public static final String DELETE_USER = "delete_user";
        public static final String UPDATE_USER = "update_user";
        public static final String VIEW_USER = "view_user";


        public static final String GENERATE_REPORTS = "generate_reports";

        public static final String[] RIGHTS = new String[]{CREATE_CLIENT,DELETE_CLIENT,UPDATE_CLIENT,VIEW_CLIENT,CREATE_CONT,DELETE_CONT,
                UPDATE_CONT,VIEW_CONT,TRANSFER,PROCESS_BILLS,CREATE_USER,DELETE_USER,UPDATE_USER,VIEW_USER,GENERATE_REPORTS};


    }

    public static Map<String, List<String>> getRolesRights() {
        Map<String, List<String>> ROLES_RIGHTS = new HashMap<>();
        for (String role : ROLES) {
            ROLES_RIGHTS.put(role, new ArrayList<>());
        }
        ROLES_RIGHTS.get(ADMINISTRATOR).addAll(Arrays.asList(CREATE_USER,DELETE_USER,UPDATE_USER,VIEW_USER,GENERATE_REPORTS));

        ROLES_RIGHTS.get(REGULAR_USER).addAll(Arrays.asList(CREATE_CLIENT,DELETE_CLIENT,UPDATE_CLIENT,VIEW_CLIENT,CREATE_CONT,DELETE_CONT,UPDATE_CONT,VIEW_CONT,TRANSFER,PROCESS_BILLS));


        return ROLES_RIGHTS;
    }
}
